package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import graphics.DialogSizeInput;

public class GraphicsController implements ActionListener {

    MainController controller;
    ArrayList<JButton> listenedButtonsArray;
    int iterator;

    public GraphicsController(MainController controller) {
        this.controller = controller;

    }

    public void setListenedButtonsArray() {
        listenedButtonsArray = controller.getViewButtonPanelArray();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        iterator = 0;
        listenedButtonsArray.forEach((button) -> {
            if (e.getSource() == button && iterator == 0) {

            } else if (e.getSource() == button && iterator == 1) {

            } else if (e.getSource() == button && iterator == 2) {
                changeSizeField();
            } else if (e.getSource() == button && iterator == 3) {

            } else if (e.getSource() == button && iterator == 4) {

            }
            iterator += 1;
        });

    }

    private void changeSizeField() {
        DialogSizeInput dialog = new DialogSizeInput();
        dialog.init();
    }
}
