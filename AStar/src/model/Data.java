package model;

public class Data {
    private int sizeY, sizeX;
    private Cell[][] field;
    private Cell startCell;
    private Cell finishCell;

    public Data(int sizeX, int sizeY) {
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
