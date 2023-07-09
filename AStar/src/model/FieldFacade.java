package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;
import java.util.List;

public class FieldFacade {
    private Data fData;
    private AStar aStar;
    private AStarAlgorithm aStarTest;

    private static EnumMap<CellType, Integer> costTypeMap;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FieldFacade(int sizeY, int sizeX, CellType[][] mapType) {
        initMap(sizeY, sizeX);

        initEnumMap();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                CellType curCellType = mapType[y][x];
                changeVertex(x, y, curCellType);
                if (curCellType == CellType.SOURCE_TYPE)
                    setStart(x, y);
                else if (curCellType == CellType.STOCK_TYPE)
                    setFinish(x, y);
            }
        }
    }

    public FieldFacade(int sizeY, int sizeX) {

        costTypeMap = new EnumMap<>(CellType.class);

        initMap(sizeY, sizeX);
        initEnumMap();
    }

    private void initMap(int sizeY, int sizeX) {
        fData = new Data(sizeX, sizeY);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                fData.getField()[y][x] = new Cell(x, y);
            }
        }
    }

    public void setMap(int sizeY, int sizeX, CellType[][] mapType) {
        fData.setSizeY(sizeY);
        fData.setSizeX(sizeX);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                CellType curCellType = mapType[y][x];
                changeVertex(x, y, curCellType);
                if (curCellType == CellType.SOURCE_TYPE)
                    setStart(x, y);
                else if (curCellType == CellType.STOCK_TYPE)
                    setFinish(x, y);
            }
        }
        notify("Field", fData);
    }

    private void initEnumMap() {
        costTypeMap = new EnumMap<>(CellType.class);
        int cost = 1;
        for (CellType type : CellType.values())
            costTypeMap.put(type, cost++);

        costTypeMap.put(CellType.BLOCK_TYPE, Integer.MAX_VALUE);
        costTypeMap.put(CellType.SOURCE_TYPE, -1);
        costTypeMap.put(CellType.STOCK_TYPE, -2);
    }

    public void addPropertyChangeListener(PropertyChangeListener pListener) {
        support.addPropertyChangeListener(pListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener pListener) {
        support.removePropertyChangeListener(pListener);
    }

    public void notify(String message, Data data) {
        support.firePropertyChange(message, null, data);
    }

    public void resize(int sizeY, int sizeX) {
        initMap(sizeY, sizeX);
        notify("Size", fData);
    }

    public void fResetField() {
        fData.dResetField();
        notify("Size", fData);
    }

    public void changeVertex(int posX, int posY, CellType newType) {
        Cell cell = fData.getField()[posY][posX];
        cell.setSelfCost(costTypeMap.get(newType));
        cell.setType(newType);
    }

    public void setFinish(int posX, int posY) {
        if (fData.getFinishCell() != null)
            fData.getFinishCell().setSelfCost(1);

        if (fData.getField()[posY][posX].getType() == CellType.SOURCE_TYPE) // Проверка на вершину Старта
            fData.setStartCell(null);

        fData.getField()[posY][posX].setSelfCost(costTypeMap.get(CellType.STOCK_TYPE));
        fData.getField()[posY][posX].setType(CellType.STOCK_TYPE);
        fData.setFinishCell(fData.getField()[posY][posX]);
    }

    public void setStart(int posX, int posY) {
        if (fData.getStartCell() != null)
            fData.getStartCell().setSelfCost(1);

        if (fData.getField()[posY][posX].getType() == CellType.STOCK_TYPE) // Проверка на вершину Финиша
            fData.setFinishCell(null);

        fData.getField()[posY][posX].setSelfCost(costTypeMap.get(CellType.SOURCE_TYPE));
        fData.getField()[posY][posX].setType(CellType.SOURCE_TYPE);
        fData.setStartCell(fData.getField()[posY][posX]);
    }

    public void launchAlgorithm() {
        // aStarTest = new AStarAlgorithm(fData);
        // List<Cell> answer = aStarTest.findPath();
        // System.out.println(answer.get(answer.size() - 1).getPosY());
        // System.out.println(answer.get(answer.size() - 1).getPosX());

        aStar = new AStar();
        Cell answer = aStar.solve(fData);
        while(answer.getParentCell() != null){
            if(!answer.isFinish())
                fData.addToPath(answer);
            answer = answer.getParentCell();
        }

        notify("Path", fData);
        System.out.println(answer.getPosY());
        System.out.println(answer.getPosX());

    }

    public Data getfData() {
        return fData;
    }
}