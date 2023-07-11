package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;

public class FieldFacade {

    private Data backupData;
    private Data fData;
    private AStar aStar = null;

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
        initMap(sizeY, sizeX);
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
        backupData = null;
        initMap(fData.getSizeY(), fData.getSizeX());
        notify("Size", fData);
    }

    public void changeVertex(int posX, int posY, CellType newType) {
        Cell cell = fData.getField()[posY][posX];
        cell.setSelfCost(costTypeMap.get(newType));
        cell.setType(newType);
    }

    public void setFinish(int posX, int posY) {
        if (fData.getFinishCell() != null){
            fData.getFinishCell().setSelfCost(1);
            fData.getFinishCell().setFinish(false);
            fData.getFinishCell().setType(CellType.FIRST_TYPE);
        }
            

        if (fData.getField()[posY][posX].getType() == CellType.SOURCE_TYPE) {
            fData.getStartCell().setSelfCost(1);
            fData.setStartCell(null);
        }

        fData.getField()[posY][posX].setSelfCost(costTypeMap.get(CellType.STOCK_TYPE));
        fData.getField()[posY][posX].setType(CellType.STOCK_TYPE);
        fData.setFinishCell(fData.getField()[posY][posX]);
    }

    public void setStart(int posX, int posY) {
        if (fData.getStartCell() != null){
            fData.getStartCell().setSelfCost(1);
            fData.getStartCell().setStart(false);
            fData.getStartCell().setType(CellType.FIRST_TYPE);
        }
            

        if (fData.getField()[posY][posX].getType() == CellType.STOCK_TYPE) {
            fData.getFinishCell().setSelfCost(1);
            fData.setFinishCell(null);
        }

        fData.getField()[posY][posX].setSelfCost(costTypeMap.get(CellType.SOURCE_TYPE));
        fData.getField()[posY][posX].setType(CellType.SOURCE_TYPE);
        fData.setStartCell(fData.getField()[posY][posX]);
    }

    public void prepareAlgorithm(PropertyChangeListener viewListener) {
        if (CheckfDataCellStartFinish() == 0) {
            aStar = new AStar(fData, viewListener);
        }
    }

    public void launchFullAlgorithm() {
        Cell answer = aStar.solve(false);
        generateAnswer(answer);
        notify("FullAlgorithm", fData);
    }

    public void launchStepAlgorithm() {
        Cell tempAnswer = aStar.solve(true);

        if (aStar.isAnswered()) {
            generateAnswer(tempAnswer);
        }

    }

    public void generateAnswer(Cell answerCell) {
        while (answerCell != null && answerCell.getParentCell() != null) {
            if (!answerCell.isFinish())
                fData.addToPath(answerCell);
            answerCell = answerCell.getParentCell();
        }

        if (answerCell == null)
            notify("NoPath", fData);
        else
            notify("Path", fData);

    }

    public boolean isStartedAStar() {
        if (aStar == null)
            return false;
        return true;
    }

    public Data getfData() {
        return fData;
    }

    public int CheckfDataCellStartFinish() {
        if (fData.getStartCell() == null) {
            notify("NoStart", fData);
            return -1;
        } else if (fData.getFinishCell() == null) {
            notify("NoFinish", fData);
            return -2;
        }
        return 0;
    }

    public void saveBackup() {
        backupData = new Data(fData);
    }

    public void loadBackup() {
        aStar = null;
        fData = new Data(backupData);
        notify("Field", fData);
    }

}