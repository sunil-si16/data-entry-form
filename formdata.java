import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class formdata {
    private JFrame frame;
    private JTextField nameField, emailField, phoneField, ageField, addressField;
    private JButton submitButton;
    private JLabel messageLabel;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                formdata window = new formdata();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public formdata() {
        frame = new JFrame();
        frame.setTitle("Data Entry Form");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(7, 2));
        frame.getContentPane().add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.getContentPane().add(nameField);
        frame.getContentPane().add(new JLabel("Email:"));
        emailField = new JTextField();
        frame.getContentPane().add(emailField);
        frame.getContentPane().add(new JLabel("Phone:"));
        phoneField = new JTextField();
        frame.getContentPane().add(phoneField);
        frame.getContentPane().add(new JLabel("Age:"));
        ageField = new JTextField();
        frame.getContentPane().add(ageField);
        frame.getContentPane().add(new JLabel("Address:"));
        addressField = new JTextField();
        frame.getContentPane().add(addressField);
        submitButton = new JButton("Submit");
        frame.getContentPane().add(submitButton);
        messageLabel = new JLabel("");
        frame.getContentPane().add(messageLabel);
        submitButton.addActionListener(e -> validateAndSubmitForm());
    }
    private void validateAndSubmitForm() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String age = ageField.getText().trim();
        String address = addressField.getText().trim();
        if (name.isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            return;
        }
        if (!isValidEmail(email)) {
            messageLabel.setText("Invalid email format.");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            messageLabel.setText("Phone number must be 10 digits.");
            return;
        }
        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue <= 0) {
                messageLabel.setText("Age must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Age must be a valid number.");
            return;
        }
        if (address.isEmpty()) {
            messageLabel.setText("Address cannot be empty.");
            return;
        }
        messageLabel.setText("Form submitted successfully!");
    }
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
