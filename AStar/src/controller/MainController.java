package controller;

import javax.swing.*;
import java.util.*;
import java.io.IOException;
import javax.swing.SwingUtilities;
import graphics.View;
import model.FieldFacade;

public class MainController {
    private View view;
    private GraphicsController gController;
    private FieldFacade facade;
    private Reader reader;

    public MainController() {
        reader = new Reader();
        view = new View();
        gController = new GraphicsController(this);
        view.init(gController);
        gController.setListenedButtonsArray();
    }

    public void init() {
        try {
            reader.read();
            facade = new FieldFacade(reader.getSizeY(), reader.getSizeX(), reader.getField());
        } catch (IOException e) {
            System.out.println("Файл сохранения поврежден или не обнаружен.");
            facade = new FieldFacade(0, 0); // to do
        }
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });

    }

    public ArrayList<JButton> getViewButtonPanelArray() {
        return view.getButtonPanelArray();
    }
}
