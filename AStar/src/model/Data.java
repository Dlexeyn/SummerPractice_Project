package model;

import java.util.ArrayList;

public class Data {
    public Data(Data otherData) {
        sizeX = otherData.getSizeX();
        sizeY = otherData.getSizeY();

        field = new Cell[sizeY][sizeX];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                field[y][x] = new Cell(otherData.getField()[y][x]);
            }
        }
        startCell = new Cell(otherData.getStartCell());
        finishCell = new Cell(otherData.getFinishCell());
    }

    private int sizeY, sizeX;
    private Cell[][] field;
    private Cell startCell;
    private Cell finishCell;
    private Cell updatedCell;
    private Cell curCell;
    private ArrayList<Cell> path;
    private int pathCost;
    private ArrayList<Cell> openList;

    public void addToPath(Cell cell) {
        path.add(cell);
    }

    public ArrayList<Cell> getPath() {
        return path;
    }

    public Cell getUpdatedCell() {
        return updatedCell;
    }

    public void setUpdatedCell(int posX, int posY) {
        this.updatedCell = field[posY][posX];
    }

    public Data(int sizeX, int sizeY) {
        path = new ArrayList<>();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        startCell = null;
        finishCell = null;
        field = new Cell[sizeY][sizeX];
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public Cell[][] getField() {
        return field;
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }

    public void dResetField() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                field[y][x].reset();
            }
        }
    }

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getFinishCell() {
        return finishCell;
    }

    public void setFinishCell(Cell finishCell) {
        this.finishCell = finishCell;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public ArrayList<Cell> getOpenList() {
        return openList;
    }

    public void setOpenList(ArrayList<Cell> openList) {
        this.openList = openList;
    }

    public void setCurCell(Cell curCell) {
        this.curCell = curCell;
    }

    public Cell getCurCell() {
        return curCell;
    }

}
