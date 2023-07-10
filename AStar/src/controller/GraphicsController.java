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
    DialogSizeInput dialog;

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
                gResetField();
                setStateButtons(true);
            } else if (e.getSource() == button && iterator == 2) { // resize
                changeSizeField();
            } else if (e.getSource() == button && iterator == 3) { // launch normal
                controller.launchAStar();
            } else if (e.getSource() == button && iterator == 4) { // launch step
                setStateButtons(false);
                listenedButtonsArray.get(5).setVisible(true);
                listenedButtonsArray.get(5).setEnabled(true);
                controller.launchAStarStep();
            } else if (e.getSource() == button && iterator == 5) { // forward
                controller.launchAStarStep();
            }
        });
    }

    public void setStateButtons(boolean state) {
        listenedButtonsArray.forEach((button) -> {
            button.setEnabled(state);
        });
    }

    private void changeSizeField() {
        setStateButtons(false);
        dialog = new DialogSizeInput(controller, this);
        dialog.init();
    }

    private void gResetField() {
        controller.mResetField();
    }
}
