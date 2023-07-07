import graphics.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        SwingUtilities.invokeLater(() -> {
            view.init();
        });
    }
}