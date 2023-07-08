package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;


public class CellViewer extends JButton {

    private int row;
    private int column;
    static final Color DEFAULT_COLOR = Colors.WHITE_WITH_YELLOW.getColor();

    public CellViewer(int row, int column){
        this.row = row;
        this.column = column;
        this.setFont(new Font("Arial", Font.PLAIN, 8));
        this.setTypeColor(DEFAULT_COLOR);
    }

    public void setTypeColor(Color color){
        setBackground(color);
        this.updateUI();
    }

    public void addParams(int g, int h, int f)
    {
        this.setText("");
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}



