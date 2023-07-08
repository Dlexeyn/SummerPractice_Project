import controller.MainController;

public class Main {
    private static MainController controller;
    public static void main(String[] args) {

        System.out.println();
        controller = new MainController();
        controller.init();
        controller.start();
    }
}