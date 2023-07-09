package view;

import javax.swing.*;
import java.awt.*;

import controller.GraphicsController;
import controller.MainController;

public class DialogSizeInput {

    public JFrame frame;

    JPanel labelPanel;
    JPanel textFieldPanel;

    JTextField yTextField;
    JTextField xTextField;
    JLabel label;
    JButton buttonOk;


    public DialogSizeInput(MainController controller, GraphicsController gController) {

        frame = new JFrame("Ввод размеров графа");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        yTextField = new JTextField(2);
        xTextField = new JTextField(2);
        label = new JLabel("Введите размеры поля графа:");

        labelPanel = new JPanel(new BorderLayout());
        frame.add(labelPanel, BorderLayout.CENTER);
        labelPanel.add(label, BorderLayout.NORTH);

        textFieldPanel = new JPanel(new GridLayout(1, 4));
        textFieldPanel.add(new JLabel("Y: "));
        textFieldPanel.add(yTextField);
        textFieldPanel.add(new JLabel("X: "));
        textFieldPanel.add(xTextField);
        labelPanel.add(textFieldPanel, BorderLayout.CENTER);

        buttonOk = new JButton("OK");

        // Перенести слушателя в контроллеры
        buttonOk.addActionListener(e -> {
            if(controller.checkSizeinput(xTextField.getText(), yTextField.getText())){
                frame.dispose();
                gController.setStateButtons(true);
            }
        });

        labelPanel.add(buttonOk, BorderLayout.SOUTH);
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void init() {
        frame.pack();
        frame.setVisible(true);
    }
}
