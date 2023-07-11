package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import view.DialogSizeInput;

public class GraphicsController implements ActionListener {

    MainController controller;
    ArrayList<JButton> listenedButtonsArray;
    ArrayList<JButton> menuBarButtons;
    int iterator;
    DialogSizeInput dialog;

    public GraphicsController(MainController controller) {
        this.controller = controller;

    }

    public void setListenedButtonsArray() {
        listenedButtonsArray = controller.getViewButtonPanelArray();
        menuBarButtons = controller.getViewMenuBarArray();
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
                listenedButtonsArray.get(6).setVisible(false);
            } else if (e.getSource() == button && iterator == 2) { // resize
                changeSizeField();
            } else if (e.getSource() == button && iterator == 3
                    && controller.getFacade().CheckfDataCellStartFinish() == 0) { // launch normal
                setMenuButtonsState(false);
                controller.launchAStar();
            } else if (e.getSource() == button && iterator == 4
                    && controller.getFacade().CheckfDataCellStartFinish() == 0) { // launch step
                setStateButtons(false);
                listenedButtonsArray.get(5).setVisible(true);
                listenedButtonsArray.get(5).setEnabled(true);
                setMenuButtonsState(false);
                controller.launchAStarStep();
            } else if (e.getSource() == button && iterator == 5) { // forward
                controller.launchAStarStep();
            } else if (e.getSource() == button && iterator == 6) { // clear
                controller.backupAction(false);
                setStateButtons(true);
                listenedButtonsArray.get(5).setEnabled(false);
                listenedButtonsArray.get(5).setVisible(false);
                button.setVisible(false);
                setMenuButtonsState(true);
            }
        });
    }

    public void setStateButtons(boolean state) {
        listenedButtonsArray.forEach((button) -> {
            button.setEnabled(state);
        });
    }

    public void setMenuButtonsState(boolean state) {
        menuBarButtons.forEach((button) -> {
            button.setEnabled(state);
        });
    }

    private void changeSizeField() {
        setStateButtons(false);
        dialog = new DialogSizeInput(controller, this);
        dialog.init();
        setStateButtons(true);
    }

    private void gResetField() {
        controller.mResetField();
    }
}
