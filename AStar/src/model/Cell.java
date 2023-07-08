package model;


class Cell{

    Cell parentCell;

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

    public static int getHeuristicValue(int x, int y)
    {
        // to do
        return 0;
    }
}