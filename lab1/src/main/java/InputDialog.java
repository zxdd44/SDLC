import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {
    private final JTextField weightField;
    private final JComboBox<WeightModel.Unit> fromUnitBox;
    private final JComboBox<WeightModel.Unit> toUnitBox;

    public InputDialog(JFrame parent, WeightModel model, WeightController controller) {
        super(parent, "Ввод данных", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Вес:"));
        weightField = new JTextField(String.valueOf(model.getLastWeight()));
        add(weightField);

        add(new JLabel("Из:"));
        fromUnitBox = new JComboBox<>(WeightModel.Unit.values());
        fromUnitBox.setSelectedItem(model.getLastFromUnit());
        add(fromUnitBox);

        add(new JLabel("В:"));
        toUnitBox = new JComboBox<>(WeightModel.Unit.values());
        toUnitBox.setSelectedItem(model.getLastToUnit());
        add(toUnitBox);

        JButton submitButton = new JButton("Конвертировать");
        submitButton.addActionListener(e -> {
            try {
                controller.processInput(
                    weightField.getText(),
                    (WeightModel.Unit) fromUnitBox.getSelectedItem(),
                    (WeightModel.Unit) toUnitBox.getSelectedItem()
                );
                dispose(); // Закрываем окно при успехе
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Некорректные данные: введите положительное число.",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(new JLabel()); // Пустая ячейка для сетки
        add(submitButton);
    }
}