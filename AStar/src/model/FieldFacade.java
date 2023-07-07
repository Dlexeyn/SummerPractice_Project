package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;

public class FieldFacade{
    private int sizeY, sizeX;
    private Cell [] [] field;
    private Cell startCell;
    private Cell finishCell;

    private static EnumMap<CellType, Integer> costTypeMap;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FieldFacade(int sizeY, int sizeX, CellType [][] mapType){
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.field = new Cell[sizeY][sizeX];
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++)
            {
                CellType curCellType = mapType[y][x];
                changeVertex(x, y, curCellType);
                if(curCellType == CellType.SOURCE_TYPE)
                    setStart(x, y);
                else if(curCellType == CellType.STOCK_TYPE)
                    setFinish(x, y);
            }
        }

        // init EnumMap
        initEnumMap();
    }

    public FieldFacade(int sizeY, int sizeX){
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.field = new Cell[sizeY][sizeX];
        costTypeMap = new EnumMap<>(CellType.class);

        // init EnumMap
        initEnumMap();
    }

    private void initEnumMap()
    {
        int cost = 1;
        for(CellType type : CellType.values())
            costTypeMap.put(type, cost++);
        
        costTypeMap.put(CellType.BLOCK_TYPE, Integer.MAX_VALUE);
        costTypeMap.put(CellType.SOURCE_TYPE, -1);
        costTypeMap.put(CellType.STOCK_TYPE, -2);
    }

    public void addPropertyChangeListener(PropertyChangeListener pListener)
    {
        support.addPropertyChangeListener(pListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener pListener)
    {
        support.removePropertyChangeListener(pListener);
    }

    public Cell [] [] getField(){
        return field;
    }

    // Оповестить визуализацию
    public void resize(int sizeY, int sizeX)
    {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.field = new Cell[sizeY][sizeX];
    }

    // Оповестить визуализацию
    public void changeVertex(int posX, int posY, CellType newType)
    {
        field[posY][posX].setgCost(costTypeMap.get(newType));
    }
    // Оповестить визуализацию
    public void setFinish(int posX, int posY)
    {
        if(finishCell != null)
            finishCell.setgCost(1);

        field[posY][posX].setgCost(costTypeMap.get(CellType.STOCK_TYPE));
        finishCell = field[posY][posX];
    }

    // Оповестить визуализацию
    public void setStart(int posX, int posY)
    {
        if(startCell != null)
            startCell.setgCost(1);

        field[posY][posX].setgCost(costTypeMap.get(CellType.SOURCE_TYPE));
        startCell = field[posY][posX];
    }

    // Оповестить визуализацию
    public void startAlgorithm()
    {

    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }



    
}