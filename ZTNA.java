import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZTNA extends JFrame {
    private JTextField usernameField, otpField;
    private JPasswordField passwordField;
    private String newUsername, newPassword;
    private int otp;

    public ZTNA() {
        // Window Setup
        setTitle("Zero Trust Network Access Simulation");
        setSize(450, 350); // Adjusting the size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Step 1: Set Credentials Panel
        JPanel setPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 4 nunchi 3 rows ki
        setPanel.setBorder(BorderFactory.createTitledBorder("Set Credentials"));

        setPanel.add(new JLabel("Create Username: "));
        JTextField newUsernameField = new JTextField(15);
        setPanel.add(newUsernameField);

        setPanel.add(new JLabel("Create Password: "));
        JPasswordField newPasswordField = new JPasswordField(15);
        setPanel.add(newPasswordField);

        JButton setButton = new JButton("Set Credentials");
        setPanel.add(new JLabel("")); // Empty space
        setPanel.add(setButton);

        // Step 2: Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        loginPanel.add(new JLabel("Username: "));
        usernameField = new JTextField(15);
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField(15);
        loginPanel.add(passwordField);

        loginPanel.add(new JLabel("OTP: "));
        otpField = new JTextField(15);
        loginPanel.add(otpField);

        JButton loginButton = new JButton("Login");
        loginPanel.add(new JLabel("")); // Empty space
        loginPanel.add(loginButton);

        // Add panels to main panel
        mainPanel.add(setPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between panels
        mainPanel.add(loginPanel);

        add(mainPanel);

        // Random OTP
        Random random = new Random();
        otp = random.nextInt(9000) + 1000;

        // Actions
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newUsername = newUsernameField.getText();
                if (newUsername.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Username cannot be empty!");
                } else {
                    newPassword = new String(newPasswordField.getPassword());
                    JOptionPane.showMessageDialog(null, "Credentials set! Your OTP is: " + otp);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userOtp = otpField.getText();

                if (!username.equals(newUsername)) {
                    JOptionPane.showMessageDialog(null, "Access Denied: Wrong Username");
                } else if (!password.equals(newPassword)) {
                    JOptionPane.showMessageDialog(null, "Access Denied: Wrong Password");
                } else if (!userOtp.equals(String.valueOf(otp))) {
                    JOptionPane.showMessageDialog(null, "Access Denied: Wrong OTP");
                } else {
                    JOptionPane.showMessageDialog(null, "Access Granted: Welcome to the Network!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ZTNA();
    }
}