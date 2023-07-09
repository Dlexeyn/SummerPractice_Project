package controller;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import model.CellType;

import view.CellViewer;

public class ColorsController implements ActionListener{

    ArrayList<JToggleButton> listenedColorButtons;

    MainController controller;

    private CellType cellType;
    private VertexListener vListener;
    private ChoiceListener cListener;
    
    public ColorsController(MainController controller) {
        this.controller = controller;
        cListener = new ChoiceListener();
        vListener = new VertexListener(controller);
        cellType = CellType.FIRST_TYPE;
    }

    public void setListenedButtonsArray() {
        listenedColorButtons = controller.getViewColorButtonPanelArray();
    }

    public void setCellType(CellType cellType){
        this.cellType = cellType;
    }
    public CellType getCellType(){
        return cellType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listenedColorButtons.forEach((button) -> {
            if (button != e.getSource())
                button.setSelected(false);
        });
    }

    public VertexListener getvListener() {
        return vListener;
    }

    public ChoiceListener getcListener() {
        return cListener;
    }

    private class ChoiceListener implements ActionListener {

        int iterator;

        @Override
        public void actionPerformed(ActionEvent e) {
            iterator = 0;
            listenedColorButtons.forEach((button) -> {
                iterator += 1;
                if (e.getSource() == button && iterator == 1) { // 1
                    setCellType(CellType.FIRST_TYPE);
                } else if (e.getSource() == button && iterator == 2) { // 2
                    setCellType(CellType.SECOND_TYPE);
                } else if (e.getSource() == button && iterator == 3) { // 3
                    setCellType(CellType.THIRD_TYPE);
                } else if (e.getSource() == button && iterator == 4) { // 4
                    setCellType(CellType.FOURTH_TYPE);
                } else if (e.getSource() == button && iterator == 5) { // 5
                    setCellType(CellType.FIFTH_TYPE);
                } else if (e.getSource() == button && iterator == 6) { // STONE
                    setCellType(CellType.BLOCK_TYPE);
                } else if (e.getSource() == button && iterator == 7) { // START
                    setCellType(CellType.SOURCE_TYPE);
                } else if (e.getSource() == button && iterator == 8) { // FINISH
                    setCellType(CellType.STOCK_TYPE);
                }
            });
        }
    }

    private class VertexListener implements ActionListener {

        MainController controller;

        public VertexListener(MainController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CellViewer cellViewer = (CellViewer) e.getSource();
            controller.changeStateVertex(cellViewer.getColumn(),cellViewer.getRow(), getCellType());
        }

    }
}