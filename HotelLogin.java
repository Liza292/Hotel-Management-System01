import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField passField;
    private JLabel messageLabel;

    // Default Email & Password
    private final String validEmail = "admin@hotel.com";
    private final String validPassword = "admin123";

    public HotelLogin() {
        // Set window properties
        setTitle("Hotel Management - Login");
        setSize(400, 350);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use null layout for custom positioning with setBounds()
        
        // Set background color for the window
        getContentPane().setBackground(new Color(173, 216, 230));  // Light blue background


        // Create and position components
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 30); // Position: x=50, y=50, width=100, height=30
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30); 
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30); 
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(150, 100, 200, 30); 
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 150, 100, 30);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get email and password from fields
                String email = emailField.getText();
                String password = new String(passField.getPassword());

                // Check credentials
                if (email.equals(validEmail) && password.equals(validPassword)) {
                    messageLabel.setText("Login successful! Redirecting...");
                    messageLabel.setForeground(Color.blue);


                    // Transition to the hotel management window safely
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new HotelManagementSystem().setVisible(true); // Open the Hotel Management System page
                        }
                        
                    });
                    //new HotelLogin().setVisible(true);
                    setVisible(false); 
                 
                } else {
                    messageLabel.setText("Its invallid. Please try again.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
        add(loginBtn);

        // Message label to show feedback (login status)
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 200, 300, 30); // Position: x=50, y=200, width=300, height=30
        add(messageLabel);

        setVisible(true);  // Make the login window visible
    }

    public static void main(String[] args) {
        // Ensure the login window is created in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelLogin(); // Start the login page
            }
        });
    }
}
