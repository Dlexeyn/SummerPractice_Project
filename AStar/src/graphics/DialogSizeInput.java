package graphics;

import javax.swing.*;
import java.awt.*;

public class DialogSizeInput {

    JFrame frame;

    JPanel labelPanel;
    JPanel textFieldPanel;

    JTextField yTextField;
    JTextField xTextField;
    JLabel label;
    JButton buttonOk;

    public DialogSizeInput() {

        frame = new JFrame("Ввод размеров графа");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        yTextField = new JTextField(5);
        xTextField = new JTextField(5);
        label = new JLabel("Введите размеры поля графа:");

        labelPanel = new JPanel(new BorderLayout());
        frame.add(labelPanel, BorderLayout.CENTER);
        labelPanel.add(label, BorderLayout.NORTH);

        textFieldPanel = new JPanel(new GridLayout(2, 2));
        textFieldPanel.add(new JLabel("Height: "));
        textFieldPanel.add(yTextField);
        textFieldPanel.add(new JLabel("Width: "));
        textFieldPanel.add(xTextField);
        labelPanel.add(textFieldPanel, BorderLayout.CENTER);

        buttonOk = new JButton("OK");
        // buttonOk.addActionListener(e -> {
        //     int height = Integer.parseInt(yTextField.getText());
        //     int width = Integer.parseInt(xTextField.getText());
        //     frame.dispose(); // Close the dialog window
        // });
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
