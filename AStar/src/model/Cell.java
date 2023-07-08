package model;


public class Cell{

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

    public Cell(){
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

    public boolean isStart()
    {
        return start;
    }

    public boolean isFinish()
    {
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
        this.type = type;
    }

    public static int getHeuristicValue(int x, int y)
    {
        // to do
        return 0;
    }
}