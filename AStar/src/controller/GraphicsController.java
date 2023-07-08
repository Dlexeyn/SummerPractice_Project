package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import view.DialogSizeInput;

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
        iterator = -1;
        listenedButtonsArray.forEach((button) -> {
            iterator += 1;
            if (e.getSource() == button && iterator == 0) { // change color

            } else if (e.getSource() == button && iterator == 1) { // reset

            } else if (e.getSource() == button && iterator == 2) { // resize
                changeSizeField();
            } else if (e.getSource() == button && iterator == 3) { // launch

            } else if (e.getSource() == button && iterator == 4) { // forward

            }
        });

    }

    private void setStateButtons(boolean state) {
        listenedButtonsArray.forEach((button) -> {
            button.setEnabled(state);
        });
    }

    private void changeSizeField() {
        setStateButtons(false);
        DialogSizeInput dialog = new DialogSizeInput(controller);
        dialog.init();
        setStateButtons(true);
    }

    private void resetField() {
        
    }
}
