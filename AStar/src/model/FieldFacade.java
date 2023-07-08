package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;

public class FieldFacade {
    private Data fData;

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
                fData.getField()[y][x] = new Cell();
            }
        }
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

    // Оповестить визуализацию
    public void resize(int sizeY, int sizeX) {
        initMap(sizeY, sizeX);
        notify("Size", fData);
    }

    // Оповестить визуализацию
    public void changeVertex(int posX, int posY, CellType newType) {
        Cell cell = fData.getField()[posY][posX];
        cell.setgCost(costTypeMap.get(newType));
        cell.setType(newType);
    }

    // Оповестить визуализацию
    public void setFinish(int posX, int posY) {
        if (fData.getFinishCell() != null)
            fData.getFinishCell().setgCost(1);

        fData.getField()[posY][posX].setgCost(costTypeMap.get(CellType.STOCK_TYPE));
        fData.setFinishCell(fData.getField()[posY][posX]);
    }

    // Оповестить визуализацию
    public void setStart(int posX, int posY) {
        if (fData.getStartCell() != null)
            fData.getStartCell().setgCost(1);

        fData.getField()[posY][posX].setgCost(costTypeMap.get(CellType.SOURCE_TYPE));
        fData.setStartCell(fData.getField()[posY][posX]);
    }

    // Оповестить визуализацию
    public void startAlgorithm() {

    }

    public Data getfData() {
        return fData;
    }
}