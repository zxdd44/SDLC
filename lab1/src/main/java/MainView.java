import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JFrame implements PropertyChangeListener {
    private final WeightModel model;
    private final WeightController controller;
    private JLabel inputInfoLabel; // Метка для вывода изначальных данных
    private JLabel resultLabel;

    public MainView(WeightModel model, WeightController controller) {
        this.model = model;
        this.controller = controller;
        this.model.addPropertyChangeListener(this); // Подписка на активную модель
        initUI();
    }

    private void initUI() {
        setTitle("Конвертер веса");
        setSize(420, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Верхняя панель для отображения исходных данных (выравнивание по левому краю)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        inputInfoLabel = new JLabel(" ");
        inputInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(inputInfoLabel);
        add(topPanel, BorderLayout.NORTH);

        // Метка результата в центре
        resultLabel = new JLabel("Ожидание ввода данных...", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel, BorderLayout.CENTER);

        // Кнопка ввода данных снизу
        JButton inputButton = new JButton("Ввести данные");
        inputButton.setFocusPainted(false);
        inputButton.addActionListener(e -> openInputWindow());
        add(inputButton, BorderLayout.SOUTH);
    }

    private void openInputWindow() {
        InputDialog dialog = new InputDialog(this, model, controller);
        dialog.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("conversionResult".equals(evt.getPropertyName())) {
            // Вывод исходных данных сверху слева
            String inputInfo = String.format("Исходные данные: %s %s → %s",
                formatWeight(model.getLastWeight()),
                model.getLastFromUnit().toString(),
                model.getLastToUnit().toString());
            inputInfoLabel.setText(inputInfo);

            // Вывод итогового результата по центру
            String resultText = String.format("Результат: %.4f %s",
                model.getConversionResult(), model.getLastToUnit().toString());
            resultLabel.setText(resultText);
        }
    }

    private String formatWeight(double weight) {
        if (weight == (long) weight) {
            return String.format("%d", (long) weight);
        } else {
            return String.format("%s", weight);
        }
    }
}