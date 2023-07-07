package graphics;

import javax.swing.*;

public class DialogSizeInput extends JFrame{

    JTextField xField;
    JTextField yField;
    JLabel label;
    GroupLayout setSizeLayout;

    public DialogSizeInput(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        xField = new JTextField(5);
        yField = new JTextField(5);
        label = new JLabel("Введите размеры поля графа:");

        

    }


}
