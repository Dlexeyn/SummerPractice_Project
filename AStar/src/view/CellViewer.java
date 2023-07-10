package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


public class CellViewer extends JButton {

    private int row;
    private int column;
    static final Color DEFAULT_COLOR = Colors.WHITE_WITH_YELLOW.getColor();

    public CellViewer(int row, int column, int sizeFont){
        this.row = row;
        this.column = column;
        this.setFont(this.getFont().deriveFont(Font.BOLD, 25)); // TEST!!!
        this.setTypeColor(DEFAULT_COLOR);
    }


    public void setTypeColor(Color color){
        setBackground(color);
        this.updateUI();
    }

    public void addParams(int g, int h, int f)
    {
        StringBuilder sBuilder = new StringBuilder("(" + g + ", "+ h + ", " + f + ")");
        this.setText(sBuilder.toString());
        this.setForeground(Color.black);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}



