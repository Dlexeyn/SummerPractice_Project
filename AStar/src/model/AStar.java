package model;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private int posFinishX;
    private int posFinishY;

    public Cell solve(Data field) {
        PriorityQueue<Cell> closedList = new PriorityQueue<>();
        PriorityQueue<Cell> openList = new PriorityQueue<>();
        posFinishX = field.getFinishCell().getPosX();
        posFinishY = field.getFinishCell().getPosY();
        field.getStartCell().setHCost(Integer.MAX_VALUE);
        openList.add(field.getStartCell());

        while (!openList.isEmpty()) {
            Cell cell = openList.peek();
            if (cell == field.getFinishCell()) {
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
        }
        closedList.clear();
        openList.clear();
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
        return 1;
    }

    public int calculateHeuristic(int posX, int posY) {
        return (Math.abs(posX - posFinishX )-1 + Math.abs(posY - posFinishY)-1);
    }

    public ArrayList<Cell> getNeighbors(int posX, int posY, Data field) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (posX != 0) {
            if (field.getField()[posY][posX - 1].getType() != CellType.BLOCK_TYPE) {
                neighbors.add(field.getField()[posY][posX - 1]);
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
        }
        return neighbors;
    }

    public void printWay(Cell finish) {
        while (finish.getParentCell() != null) {
            // finish.setType(CellType.)
            finish = finish.getParentCell();
        }
    }
}
