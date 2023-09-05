import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

public class COCOMOCalculator extends JFrame {
    private final JTextField linesOfCodeField;
    private final JComboBox<String> projectTypeComboBox;

    public COCOMOCalculator() {
        setTitle("COCOMO Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel linesOfCodeLabel = new JLabel("Lines of Code:");
        linesOfCodeField = new JTextField();

        JLabel projectTypeLabel = new JLabel("Project Type:");
        String[] projectTypes = {"Organic", "Semi-Detached", "Embedded"};
        projectTypeComboBox = new JComboBox<>(projectTypes);

        JButton calculateButton = new JButton("Calculate");

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCOCOMO();
            }
        });

        panel.add(linesOfCodeLabel);
        panel.add(linesOfCodeField);
        panel.add(projectTypeLabel);
        panel.add(projectTypeComboBox);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(calculateButton);

        add(panel, BorderLayout.CENTER);
    }

    private void calculateCOCOMO() {
        try {
            double linesOfCode = Double.parseDouble(linesOfCodeField.getText());

            // Отримати вибраний тип проекту
            String selectedProjectType = (String) projectTypeComboBox.getSelectedItem();
            double a, b;

            // Встановити значення коефіцієнтів a і b відповідно до вибраного типу проекту
            switch (Objects.requireNonNull(selectedProjectType)) {
                case "Organic" -> {
                    a = 2.4;
                    b = 1.05;
                }
                case "Semi-Detached" -> {
                    a = 3.0;
                    b = 1.12;
                }
                case "Embedded" -> {
                    a = 3.6;
                    b = 1.20;
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Невідомий тип проекту.");
                    return;
                }
            }

            // Розрахунок трудоємності та виведення результату
            double effort = a * Math.pow(linesOfCode, b);
            double duration = 2.5 * Math.pow(effort, 0.38);

            JOptionPane.showMessageDialog(this, "Трудоємність: " + effort + " PM\nТривалість: " + duration + " місяці(ів)");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Будь ласка, введіть правильні дані.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                COCOMOCalculator calculator = new COCOMOCalculator();
                calculator.setVisible(true);
            }
        });
    }
}

