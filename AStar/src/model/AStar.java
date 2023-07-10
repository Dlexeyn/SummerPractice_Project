package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private final PropertyChangeSupport support;
    private int posFinishX;
    private int posFinishY;
    private PriorityQueue<Cell> closedList;
    private PriorityQueue<Cell> openList;
    private Data field;
    private boolean isAnswer = false;
    private ArrayList<Cell> seenCell;

    public AStar(Data fData, PropertyChangeListener viewListener){
        field = fData;
        posFinishX = fData.getFinishCell().getPosX();
        posFinishY = fData.getFinishCell().getPosY();
        closedList = new PriorityQueue<>();
        openList = new PriorityQueue<>();
        field.getStartCell().setHCost(Integer.MAX_VALUE);
        openList.add(field.getStartCell());
        seenCell = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(viewListener);
    }

    public Cell solve(boolean isStepMode) {

        while (!openList.isEmpty()) {
            Cell cell = openList.peek();
            if (cell == field.getFinishCell()) {
                field.setPathCost(cell.getGCost());
                isAnswer = true;
                return cell;
            }
            ArrayList<Cell> neighbors = getNeighbors(cell.getPosX(), cell.getPosY(), field);
            for (Cell curCell : neighbors) {
                int totalWight = cell.getGCost() + getWeight(curCell.getType());
                if (!openList.contains(curCell) && !closedList.contains(curCell)) {
                    curCell.setParentCell(cell);
                    curCell.setGCost(getWeight(curCell.getType()) + cell.getGCost());
                    curCell.setHCost(calculateHeuristic(curCell.getPosX(), curCell.getPosY()));
                    curCell.setFCost(curCell.getGCost() + curCell.getHCost());
                    openList.add(curCell);
                } else {
                    if (totalWight < curCell.getGCost()) {
                        curCell.setParentCell(cell);
                        curCell.setGCost(totalWight);
                        curCell.setHCost(calculateHeuristic(curCell.getPosX(), curCell.getPosY()));
                        curCell.setFCost(curCell.getGCost() + curCell.getHCost());
                        if (closedList.contains(curCell)) {
                            closedList.remove(curCell);
                            openList.add(curCell);
                        }
                    }
                }
            }

            openList.remove(cell);
            closedList.add(cell);
            openList.forEach((c) -> {
                if(!seenCell.contains(c)){
                    seenCell.add(c);
                }
            });
            field.setCurCell(openList.peek());
            // Остановился
            // Сигнал View, 
            // 
            if(isStepMode){
                field.setOpenList(seenCell);
                support.firePropertyChange("Step", null, field);
                return null;
            }
 
        }
        closedList.clear();
        openList.clear();
        isAnswer = true;
        return null;
    }

    public int getWeight(CellType cellType) {
        switch (cellType) {
            case FIRST_TYPE:
                return 1;
            case SECOND_TYPE:
                return 2;
            case THIRD_TYPE:
                return 3;
            case FOURTH_TYPE:
                return 4;
            case FIFTH_TYPE:
                return 5;
        }
        return 0;
    }

    public int calculateHeuristic(int posX, int posY) {
        return (Math.max(Math.abs(posX - posFinishX )-1 , Math.abs(posY - posFinishY)-1));
    }

    public ArrayList<Cell> getNeighbors(int posX, int posY, Data field) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (posX != 0) {
            if (field.getField()[posY][posX - 1].getType() != CellType.BLOCK_TYPE) {
                neighbors.add(field.getField()[posY][posX - 1]);
            }
            if(posY!=0){
                if (field.getField()[posY-1][posX - 1].getType() != CellType.BLOCK_TYPE) {
                    neighbors.add(field.getField()[posY-1][posX - 1]);
                }
            }
            if(posY != field.getSizeY() - 1){
                if (field.getField()[posY + 1][posX - 1].getType() != CellType.BLOCK_TYPE) {
                    neighbors.add(field.getField()[posY + 1][posX - 1]);
                }
            }
        }
        if (posY != 0) {
            if (field.getField()[posY - 1][posX].getType() != CellType.BLOCK_TYPE) {
                neighbors.add(field.getField()[posY - 1][posX]);
            }
        }
        if (posY != field.getSizeY() - 1) {
            if (field.getField()[posY + 1][posX].getType() != CellType.BLOCK_TYPE) {
                neighbors.add(field.getField()[posY + 1][posX]);
            }
        }
        if (posX != field.getSizeX() - 1) {
            if (field.getField()[posY][posX + 1].getType() != CellType.BLOCK_TYPE) {
                neighbors.add(field.getField()[posY][posX + 1]);
            }
            if( posY!= 0){
                if (field.getField()[posY-1][posX + 1].getType() != CellType.BLOCK_TYPE) {
                    neighbors.add(field.getField()[posY-1][posX + 1]);
                }
            }
            if(posY != field.getSizeY() - 1){
                if (field.getField()[posY + 1][posX + 1].getType() != CellType.BLOCK_TYPE) {
                    neighbors.add(field.getField()[posY + 1][posX + 1]);
                }
            }
        }
        return neighbors;
    }

    public void printWay(Cell finish) {
        while (finish.getParentCell() != null) {
            // finish.setType(CellType.)
            finish = finish.getParentCell();
        }
    }

    public PriorityQueue<Cell> getOpenList() {
        return openList;
    }
    public boolean isAnswered() {
        return isAnswer;
    }
}
