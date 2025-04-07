
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Feedback extends JFrame {

    private JLabel feedbackLabel, instructionLabel;
    private JTextArea feedbackTextArea;
    private JButton submitFeedbackButton;
    private JLabel feedbackStatusLabel;

    public Feedback() {
        // Set window properties
        setTitle("Hotel Feedback System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Add background image (optional)
        ImageIcon i1 = new ImageIcon("picture/background2.jpg"); // Replace with your image path
        Image i2 = i1.getImage().getScaledInstance(1000, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1000, 900);
        add(img);

        // Feedback instruction label
        instructionLabel = new JLabel("Please provide your feedback about your stay:");
        instructionLabel.setBounds(50, 60, 500, 30);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        img.add(instructionLabel);

        // Text area for feedback input
        feedbackTextArea = new JTextArea();
        feedbackTextArea.setBounds(50, 100, 400, 100);
        feedbackTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setWrapStyleWord(true);
        feedbackTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        img.add(feedbackTextArea);

        // Button to submit feedback
        submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.setBounds(170, 230, 200, 30);
        img.add(submitFeedbackButton);

        // Feedback status label
        feedbackStatusLabel = new JLabel("");
        feedbackStatusLabel.setBounds(50, 320, 500, 30);
        feedbackStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        img.add(feedbackStatusLabel);

        // Action listener for submitting feedback
        submitFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });
        setVisible(false);
    }

    // Method to submit feedback and show confirmation
    private void submitFeedback() {
        String feedback = feedbackTextArea.getText().trim();

        if (feedback.isEmpty()) {
            feedbackStatusLabel.setText("Feedback cannot be empty.");
        } else {
            feedbackStatusLabel.setText("Thank you for your feedback! We value your opinion.");
            feedbackStatusLabel.setBounds(40,255,400,30);
            feedbackTextArea.setText(""); // Clear the feedback area after submission
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Feedback().setVisible(true);
            }
        });
    }
}

