import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField displayField;
    private double firstOperand;
    private String operation;

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        displayField = new JTextField();
        displayField.setEditable(false);
        panel.add(displayField);

        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "0", ".", "=", "+"};

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ButtonClickListener());
            panel.add(btn);
        }

        add(panel);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.matches("[0-9.]")) {
                displayField.setText(displayField.getText() + command);
            } else if ("+-*/".contains(command)) {
                firstOperand = Double.parseDouble(displayField.getText());
                operation = command;
                displayField.setText("");
            } else if ("=".equals(command)) {
                double secondOperand = Double.parseDouble(displayField.getText());
                double result = calculate(firstOperand, secondOperand, operation);
                displayField.setText(String.valueOf(result));
            }
        }
    }

    private double calculate(double firstOperand, double secondOperand, String operation) {
        switch (operation) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                if (secondOperand == 0) {
                    return Double.NaN; // Indicate division by zero
                }
                return firstOperand / secondOperand;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
