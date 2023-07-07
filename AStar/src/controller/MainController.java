package controller;

import java.io.IOException;
import javax.swing.SwingUtilities;
import graphics.View;
import model.FieldFacade;

public class MainController {
    private View view;
    private GraphicsController gController;
    private FieldFacade facade;
    private Reader reader;

    public MainController()
    {
        reader = new Reader();
        gController = new GraphicsController(this);
        view = new View(gController);
    }

    public void init()
    {
        try{
            reader.read();
            facade = new FieldFacade(reader.getSizeY(), reader.getSizeX(), reader.getField());
        } 
        catch(IOException e)
        {
            System.out.println("Файл сохранения поврежден или не обнаружен.");
            facade = new FieldFacade(0, 0);
        } 
    }

    public void start()
    {
        SwingUtilities.invokeLater(() -> {
            view.init();
        });
    }
}
