package controller;

import javax.swing.*;
import java.util.*;
import java.io.IOException;

import model.CellType;
import view.View;
import model.FieldFacade;

public class MainController {
    private View view;
    private GraphicsController gController;
    private ColorsController colorsController;
    private FieldFacade facade;
    private Reader reader;

    public MainController() {
        reader = new Reader();
        view = new View();
        gController = new GraphicsController(this);
        colorsController = new ColorsController(this);
        view.init(gController, colorsController);
        gController.setListenedButtonsArray();
        colorsController.setListenedButtonsArray();
    }

    public void init() {
        try {
            reader.read();
            facade = new FieldFacade(reader.getSizeY(), reader.getSizeX(), reader.getField());
        } catch (IOException e) {
            System.out.println("Файл сохранения поврежден или не обнаружен.");
            facade = new FieldFacade(20, 20); // to do
        } finally {
            facade.addPropertyChangeListener(view);
            facade.addPropertyChangeListener(view.getGraphPanel());
            facade.notify("Field", facade.getfData());
        }
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });
    }

    public void changeStateVertex(int posX, int posY, CellType newType){
        facade.changeVertex(posX, posY, newType);
        facade.getfData().setUpdatedCell(posX, posY);
        facade.notify("Vertex", facade.getfData());
    }
    public FieldFacade getFacade() {
        return facade;
    }

    public ArrayList<JButton> getViewButtonPanelArray() {
        return view.getButtonPanelArray();
    }

    public ArrayList<JToggleButton> getViewColorButtonPanelArray() {
        return view.getColorButtonPanelArray();
    }

    
}
