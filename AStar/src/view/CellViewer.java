package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;


public class CellViewer extends JButton {

    private int row;
    private int column;
    static final Color DEFAULT_COLOR = Colors.WHITE_WITH_YELLOW.getColor();

    public CellViewer(int row, int column, int sizeFont){
        this.row = row;
        this.column = column;
        this.setFont(new Font("Dialog", Font.PLAIN, sizeFont));
        this.setTypeColor(DEFAULT_COLOR);
    }

    public void setTypeColor(Color color){
        setBackground(color);
        this.updateUI();
    }

    public void addParams(int g, int h, int f)
    {
        StringBuilder sBuilder = new StringBuilder("(");
        sBuilder.append(g + ", ");
        sBuilder.append(h + ", ");
        sBuilder.append(f + ")");
        this.setText(sBuilder.toString());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}



