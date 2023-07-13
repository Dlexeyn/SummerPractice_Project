package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import controller.GraphicsController;
import controller.MainController;

public class DialogSizeInput {

    JFrame frame;

    JPanel labelPanel;
    JPanel textFieldPanel;

    JTextField yTextField;
    JTextField xTextField;
    JLabel label;
    JButton buttonOk;


    public DialogSizeInput(MainController controller, GraphicsController gController) {

        //frame.setMaximumSize(new Dimension (350, 200));
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

        buttonOk.addActionListener(e -> {
            if(controller.checkSizeinput(xTextField.getText(), yTextField.getText())){
                frame.dispose();
                gController.setStateButtons(true);
            }
        });

        labelPanel.add(buttonOk, BorderLayout.SOUTH);
        frame.setSize(300, 150);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();
                int newWidth = width;
                int newHeight = height;
                if (width > 400) {
                    newWidth = 400;
                }
                if (width < 300) {
                    newWidth = 300;
                }
                if (height > 250) {
                    newHeight = 250;
                }
                if (height < 150) {
                    newHeight = 150                                                                                                             ;
                }
                if (newHeight != height || newWidth != width) {
                    frame.setSize(newWidth, newHeight);
                }
            }
        });
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void init() {
        frame.pack();
        frame.setVisible(true);
    }
}
