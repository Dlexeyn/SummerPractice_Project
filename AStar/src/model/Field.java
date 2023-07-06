package model;

 public class Field{
    private int sizeY, sizeX;
    private Cell [] [] field;

    public Field(int sizeY, int sizeX){
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.field = new Cell[sizeY][sizeX];
    }

    public Cell [] [] getField(){
        return field;
    }

    
}