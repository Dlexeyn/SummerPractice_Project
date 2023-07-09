package model;

import java.util.ArrayList;

public class Data {
    private int sizeY, sizeX;
    private Cell[][] field;
    private Cell startCell;
    private Cell finishCell;
    private Cell updatedCell;
    ArrayList<Cell> path;

    public void addToPath(Cell cell){
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

}
