import controller.MainController;

public class Main {
    private static MainController controller;
    public static void main(String[] args) {

        controller = new MainController();
        controller.start();
    }
}