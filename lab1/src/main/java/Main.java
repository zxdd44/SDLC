import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeightModel model = new WeightModel();
            WeightController controller = new WeightController(model);
            MainView mainView = new MainView(model, controller);
            mainView.setVisible(true);
        });
    }
}