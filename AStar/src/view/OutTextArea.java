package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextArea;

public class OutTextArea extends JTextArea implements PropertyChangeListener{

    public OutTextArea(int row, int columns){
        super(row, columns);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals(new String("ErrorRead"))){
            setText(e.getPropertyName() + ": " + (String) e.getNewValue());
        }
    }
}
