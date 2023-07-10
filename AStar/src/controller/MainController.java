package controller;

import javax.swing.*;
import java.util.*;
import java.io.IOException;

import model.CellType;
import view.View;
import model.FieldFacade;
import controller.IOput.*;

public class MainController {

    private static final String NAME_FILE = System.getProperty("user.dir") + "/save.txt";
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE_Y = 20;
    private static final int MAX_SIZE_X = 30;

    private View view;
    private GraphicsController gController;
    private ColorsController cController;
    private SaveLoadController slController;
    private FieldFacade facade;
    private Reader reader;
    private Writer writer;

    public MainController() {
        reader = new Reader();
        writer = new Writer();
        view = new View();
        gController = new GraphicsController(this);
        cController = new ColorsController(this);
        slController = new SaveLoadController(this, reader, writer);
        view.init(gController, cController, slController);
        gController.setListenedButtonsArray();
        cController.setListenedButtonsArray();
        slController.setListenedButtonsArray();
    }

    public void init() {
        try {
            reader.read(NAME_FILE);
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

    public void changeStateVertex(int posX, int posY, CellType newType) {
        if (newType != CellType.SOURCE_TYPE && newType != CellType.STOCK_TYPE) {
            facade.changeVertex(posX, posY, newType);
            facade.getfData().setUpdatedCell(posX, posY);
            facade.notify("Vertex", facade.getfData());
        } else if (newType == CellType.SOURCE_TYPE) {
            facade.setStart(posX, posY);
            facade.notify("Start", facade.getfData());
        } else {
            facade.setFinish(posX, posY);
            facade.notify("Finish", facade.getfData());
        }
    }

    public boolean checkSizeinput(String xTextField, String yTextField) {
        int sizeX, sizeY;
        try {
            sizeY = Integer.parseInt(yTextField);
            sizeX = Integer.parseInt(xTextField);
        } catch (NumberFormatException e) {
            return false;
        }
        if (sizeX >= MIN_SIZE && sizeX <= MAX_SIZE_X && sizeY >= MIN_SIZE && sizeY <= MAX_SIZE_Y) {
            facade.resize(sizeY, sizeX);
            return true;
        }
        return false;
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

    public ArrayList<JButton> getViewMenuBarArray() {
        return view.getMenuBarArray();
    }

    public void mResetField() {
        facade.fResetField();
    }

    public void setFieldFromFile(int sizeY, int sizeX, CellType[][] field) {
        facade.setMap(sizeY, sizeX, field);
    }

    public void launchAStar() {
        backupAction(true);
        facade.prepareAlgorithm(view);
        facade.launchFullAlgorithm();
    }

    public void launchAStarStep() {
        backupAction(true);
        if (!facade.isStartedAStar())
            facade.prepareAlgorithm(view.getGraphPanel());

        facade.launchStepAlgorithm();
    }

    public void backupAction(boolean flag) { // true - save, false - load
        if (flag == true) {
            facade.saveBackup();
        } else {
            facade.loadBackup();
        }
    }
}
