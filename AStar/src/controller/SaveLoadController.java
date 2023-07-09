package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import controller.IOput.*;

public class SaveLoadController implements ActionListener {

    MainController controller;
    Writer writer;
    Reader reader;
    ArrayList<JButton> listenedButtonsArray;
    int iterator;
    String fileName;

    JFileChooser fileChooser;

    public SaveLoadController(MainController ctrl, Reader rdr, Writer wrtr) {
        controller = ctrl;
        reader = rdr;
        writer = wrtr;
    }

    public void setListenedButtonsArray() {
        listenedButtonsArray = controller.getViewMenuBarArray();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        iterator = -1;
        fileChooser = new JFileChooser();

        listenedButtonsArray.forEach((button) -> {
            iterator += 1;
            if (e.getSource() == button && iterator == 0) { // save

                writer.fieldData = controller.getFacade().getfData();

                try {
                    writer.write();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else if (e.getSource() == button && iterator == 1) { // load
                int status = fileChooser.showDialog(null, "Открыть файл");
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    fileName = fileChooser.getSelectedFile().getAbsolutePath();
                }

                try {
                    reader.read(fileName);
                    controller.setFieldFromFile(reader.getSizeY(), reader.getSizeX(), reader.getField());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}