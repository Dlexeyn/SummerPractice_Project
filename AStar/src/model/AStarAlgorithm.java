package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarAlgorithm {
    private Data data;
    private PriorityQueue<Cell> openSet;
    private Set<Cell> closedSet;

    public AStarAlgorithm(Data data) {
        this.data = data;
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
    }

    public List<Cell> findPath() {
        Cell startCell = data.getStartCell();
        Cell finishCell = data.getFinishCell();

        startCell.setGCost(0);
        startCell.setFCost(heuristicCost(startCell, finishCell));
        openSet.add(startCell);

        while (!openSet.isEmpty()) {
            Cell currentCell = openSet.poll();

            if (currentCell == finishCell) {
                return reconstructPath(currentCell);
            }

            closedSet.add(currentCell);

            for (Cell neighbor : getNeighbors(currentCell)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = currentCell.getGCost() + neighbor.getSelfCost();
                if (tentativeGScore < neighbor.getGCost()) {
                    neighbor.setParentCell(currentCell);
                    //neighbor.setCameFrom(currentCell);
                    neighbor.setGCost(tentativeGScore);
                    neighbor.setFCost(tentativeGScore + heuristicCost(neighbor, finishCell));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getPosX();
        int y = cell.getPosY();

        if (isValidPosition(x, y - 1)) {
            neighbors.add(data.getField()[y - 1][x]);
        }
        if (isValidPosition(x, y + 1)) {
            neighbors.add(data.getField()[y + 1][x]);
        }
        if (isValidPosition(x - 1, y)) {
            neighbors.add(data.getField()[y][x - 1]);
        }
        if (isValidPosition(x + 1, y)) {
            neighbors.add(data.getField()[y][x + 1]);
        }

        return neighbors;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < data.getSizeX() && y >= 0 && y < data.getSizeY();
    }

    private int heuristicCost(Cell fromCell, Cell toCell) {
        int dx = Math.abs(fromCell.getPosX() - toCell.getPosX());
        int dy = Math.abs(fromCell.getPosY() - toCell.getPosY());
        return dx + dy;
    }

    private List<Cell> reconstructPath(Cell currentCell) {
        List<Cell> path = new ArrayList<>();
        while (currentCell != null) {
            path.add(currentCell);
            currentCell = currentCell.getParentCell();
        }
        Collections.reverse(path);
        return path;
    }
}
