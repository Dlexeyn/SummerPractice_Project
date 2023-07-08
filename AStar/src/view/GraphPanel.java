package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.CellType;
import model.Data;

public class GraphPanel extends JPanel implements PropertyChangeListener  {

    static final int MAX_ROW = 15;
    static final int MAX_COLUMN = 25;
    private static EnumMap<CellType, Colors> colorTypeMap;
    CellViewer start = null;
    CellViewer finish = null;
    CellViewer[][] cellsViewers;

    public GraphPanel() {
        colorTypeMap = new EnumMap<>(CellType.class);
    }

    public void init(int sizeX, int sizeY) {
        ArrayList<CellType> types = new ArrayList<CellType>(EnumSet.allOf(CellType.class));
        removeAll();
        cellsViewers = new CellViewer[sizeY][sizeX];
        int index = 0;
        for (Colors color : Colors.values())
            colorTypeMap.put(types.get(index++), color);

        setLayout(new GridLayout(sizeY, sizeX));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, false));

        for (int row = 0; row < sizeY; row++) {
            for (int col = 0; col < sizeX; col++) {
                cellsViewers[row][col] = new CellViewer(row, col);
                add(cellsViewers[row][col]);
            }
        }
        updateUI();
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Data newData = (Data) e.getNewValue();
        if (e.getPropertyName().equals(new String("Start"))) {
            if (start != null)
                start.setTypeColor(colorTypeMap.get(CellType.FIRST_TYPE).getColor());

            start = cellsViewers[newData.getStartCell().getPosY()][newData.getStartCell().getPosX()];
        } else if (e.getPropertyName().equals(new String("Finish"))) {
            if (finish != null)
                finish.setTypeColor(colorTypeMap.get(CellType.FIRST_TYPE).getColor());

            finish = cellsViewers[newData.getFinishCell().getPosY()][newData.getFinishCell().getPosX()];
        } //else if (e.getPropertyName() == colorsPanel)

    }

}
