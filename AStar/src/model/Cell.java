package model;

import java.util.PriorityQueue;

public class Cell implements Comparable {

    Cell parentCell;
    private CellType type;
    private int posX;
    private int posY;
    private int selfCost;
    private int gCost;
    private int hCost;
    private int fCost;
    private boolean start;
    private boolean finish;

    public Cell(int x, int y) {
        posX = x;
        posY = y;
        parentCell = null;
        start = false;
        finish = false;
        gCost = 1;
        type = CellType.FIRST_TYPE;
    }

    public Cell getParentCell() {
        return parentCell;
    }

    public void setParentCell(Cell parentCell) {
        this.parentCell = parentCell;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isFinish() {
        return finish;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        if (type == CellType.SOURCE_TYPE) {
            start = true;
            selfCost = 1;
        } else if (type == CellType.STOCK_TYPE) {
            finish = true;
            selfCost = 1;
        }
        this.type = type;
    }

    public void reset() {
        selfCost = 1;
        start = false;
        finish = false;
        type = CellType.FIRST_TYPE;
    }

    public void setGCost(int g) {
        gCost = g;
    }

    public int getGCost() {
        return gCost;
    }

    public void setFCost(int f) {
        fCost = f;
    }

    public int getFCost() {
        return fCost;
    }

    public void setHCost(int h) {
        hCost = h;
    }

    public int getHCost() {
        return hCost;
    }

    public void setSelfCost(int sc) {
        selfCost = sc;
    }

    public int getSelfCost() {
        return selfCost;
    }

    @Override
    public int compareTo(Object o) {
        Cell cell = (Cell) o;
        return Integer.compare(this.fCost, cell.getFCost());
    }
}