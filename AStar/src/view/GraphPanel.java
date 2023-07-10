package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Cell;
import model.CellType;
import model.Data;

public class GraphPanel extends JPanel implements PropertyChangeListener {

    static final int MAX_ROW = 15;
    static final int MAX_COLUMN = 25;
    private static EnumMap<CellType, Colors> colorTypeMap;
    ActionListener vListener;
    int rows, colomns;
    CellViewer start = null;
    CellViewer finish = null;
    CellViewer targetCellViewer = null;
    CellViewer[][] cellsViewers;

    public GraphPanel() {
        colorTypeMap = new EnumMap<>(CellType.class);
        ArrayList<CellType> types = new ArrayList<CellType>(EnumSet.allOf(CellType.class));
        int index = 0;
        for (Colors color : Colors.values())
            colorTypeMap.put(types.get(index++), color);
    }

    public void defaultSetup(int sizeX, int sizeY) {
        removeAll();
        rows = sizeY;
        colomns = sizeX;
        cellsViewers = new CellViewer[sizeY][sizeX];
        for (int row = 0; row < sizeY; row++) {
            for (int col = 0; col < sizeX; col++) {
                cellsViewers[row][col] = new CellViewer(row, col, 10);
                add(cellsViewers[row][col]);
            }
        }
        updateListener();
        setLayout(new GridLayout(sizeY, sizeX));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));
        updateUI();

    }

    public void setupFromField(int sizeX, int sizeY, Cell[][] newfield) {
        removeAll();
        rows = sizeY;
        colomns = sizeX;
        cellsViewers = new CellViewer[sizeY][sizeX];
        for (int row = 0; row < sizeY; row++) {
            for (int col = 0; col < sizeX; col++) {
                CellViewer cellViewer = new CellViewer(row, col, 10);
                if (newfield[row][col].getType() == CellType.SOURCE_TYPE)
                    start = cellViewer;
                else if (newfield[row][col].getType() == CellType.STOCK_TYPE)
                    finish = cellViewer;
                setParamsToCellView(cellViewer, newfield[row][col]);
                cellsViewers[row][col] = cellViewer;
                add(cellsViewers[row][col]);
            }
        }
        updateListener();
        setLayout(new GridLayout(sizeY, sizeX));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));

        updateUI();
    }

    private void setParamsToCellView(CellViewer cViewer, Cell cell) {
        cViewer.setTypeColor(colorTypeMap.get(cell.getType()).getColor());
        cViewer.addParams(cell.getSelfCost(), cell.getHCost(), cell.getFCost());
    }

    public void setListener(ActionListener listener) {
        vListener = listener;
    }

    public void updateListener() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < colomns; col++) {
                cellsViewers[row][col].addActionListener(vListener);
            }
        }
    }

    public void setEnabledbuttons(boolean value) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < colomns; col++) {
                cellsViewers[row][col].setEnabled(value);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Data newData = (Data) e.getNewValue();
        if (e.getPropertyName().equals(new String("Start"))) {
            int x = newData.getStartCell().getPosX();
            int y = newData.getStartCell().getPosY();

            if (start != null) {
                start.setTypeColor(colorTypeMap.get(CellType.FIRST_TYPE).getColor());
                start.addParams(1, 0, 0);
            }

            if (cellsViewers[y][x] == finish)
                finish = null;

            start = cellsViewers[y][x];
            start.setTypeColor(colorTypeMap.get(CellType.SOURCE_TYPE).getColor());
            start.setText("");
        } else if (e.getPropertyName().equals("Finish")) {
            int x = newData.getFinishCell().getPosX();
            int y = newData.getFinishCell().getPosY();

            if (finish != null) {
                finish.setTypeColor(colorTypeMap.get(CellType.FIRST_TYPE).getColor());
                finish.addParams(1, 0, 0);
            }

            if (cellsViewers[y][x] == start)
                start = null;

            finish = cellsViewers[y][x];
            finish.setTypeColor(colorTypeMap.get(CellType.STOCK_TYPE).getColor());
            finish.setText("");
        } else if (e.getPropertyName().equals("Vertex")) {
            Cell updatedCell = newData.getUpdatedCell();
            setParamsToCellView(cellsViewers[updatedCell.getPosY()][updatedCell.getPosX()], updatedCell);
        } else if (e.getPropertyName().equals("Path")) {
            for (Cell cell : newData.getPath()) {
                cellsViewers[cell.getPosY()][cell.getPosX()].setBackground(Color.CYAN);
            }

        } else if (e.getPropertyName().equals("Step")) {
            for (Cell cell : newData.getOpenList()) {
                cellsViewers[cell.getPosY()][cell.getPosX()].addParams(cell.getSelfCost(), cell.getHCost(),
                        cell.getFCost());
                cellsViewers[cell.getPosY()][cell.getPosX()]
                        .setBorder(BorderFactory.createLineBorder(Color.GREEN, 6, false));
            }
            if (targetCellViewer != null) {
                targetCellViewer.setBorder(BorderFactory.createLineBorder(Color.GREEN, 6, false));
            }
            if (newData.getCurCell() != null) {
                targetCellViewer = cellsViewers[newData.getCurCell().getPosY()][newData.getCurCell().getPosX()];
                // targetCellViewer.setBorderPainted(true);
                targetCellViewer.setBorder(BorderFactory.createLineBorder(Color.BLUE, 6, false));
            }

        } else if (e.getPropertyName().equals("FullAlgorithm")) {
            for (Cell[] cellRow : newData.getField()) {
                for (Cell cell : cellRow) {
                    cellsViewers[cell.getPosY()][cell.getPosX()].addParams(cell.getSelfCost(), cell.getHCost(),
                            cell.getFCost());
                }
            }
        }

    }

}