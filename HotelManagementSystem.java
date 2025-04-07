import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HotelManagementSystem extends JFrame {

    private JTextField numberOfRoomsField;
    private JButton showRoomsButton,confirmBookingButton;
    private JLabel roomStatusLabel, roomSelectionLabel, instructionLabel;
    private JCheckBox[] roomCheckBoxes;
    private String[] roomTypes = {"Single", "Double"};
    private boolean[] rooms = new boolean[5]; // Track booked rooms (false = available, true = booked)
    private int roomsToBook = 0; // Number of rooms the customer wants to book
    private int roomsSelected = 0; // Number of rooms the customer has selected
    private Image backgroundImage;

    public HotelManagementSystem() {
        // Set window properties
        setTitle("Hotel Management System");
        setSize(800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Load background image (Ensure the path is correct)
        ImageIcon i1 = new ImageIcon("picture/background2.jpg"); // Replace with your image path
        Image i2 = i1.getImage().getScaledInstance(1000, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1000, 900);
        add(img);

        // Instruction label
        instructionLabel = new JLabel("Our hotel has 5 rooms. How many rooms would you like to book?");
        instructionLabel.setBounds(50, 60, 600, 30);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        img.add(instructionLabel);

        // Input field for number of rooms
        numberOfRoomsField = new JTextField();
        numberOfRoomsField.setBounds(300, 100, 100, 30);
        numberOfRoomsField.setFont(new Font("Arial", Font.PLAIN, 14));
        img.add(numberOfRoomsField);

        // Button to show room selection
        showRoomsButton = new JButton("Show Room Selection");
        showRoomsButton.setBounds(250, 140, 200, 30);
        img.add(showRoomsButton);

        // Room selection label
        roomSelectionLabel = new JLabel("Select Room Types:");
        roomSelectionLabel.setBounds(270, 200, 200, 30);
        roomSelectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roomSelectionLabel.setVisible(false); // Initially hidden
        img.add(roomSelectionLabel);

        // Room status label
        roomStatusLabel = new JLabel("");
        roomStatusLabel.setBounds(250, 500, 300, 30); // Adjusted to fit the window
        roomStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        img.add(roomStatusLabel);
        // Button to confirm booking
        confirmBookingButton = new JButton("Confirm Booking");
        confirmBookingButton.setBounds(250, 540, 200, 30);
        //confirmBookingButton.setVisible(false); // Initially hidden
        img.add(confirmBookingButton);

        // Action listener for showing room selection options
        showRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get number of rooms the customer wants to book
                    roomsToBook = Integer.parseInt(numberOfRoomsField.getText());

                    // Validate the number of rooms entered
                    if (roomsToBook <= 0 || roomsToBook > 5) {
                        roomStatusLabel.setText("Invalid number of rooms. Enter a number between 1 and 5.");
                        roomStatusLabel.setBounds(200, 500, 450, 30);;
                        return;
                    }

                    // Display room selection UI
                    roomSelectionLabel.setVisible(true);
                    showRoomOptions(img);

                    roomStatusLabel.setText(roomsToBook + " rooms selected.");
                } catch (NumberFormatException ex) {
                    roomStatusLabel.setText("Please enter a valid number.");
                }
            }
        });
        setVisible(true);
     // Action listener for confirming the booking
        confirmBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Book the rooms and show confirmation message
                bookRooms();
                roomStatusLabel.setText("Booking confirmed! Congratulations to our Hotel.");
                roomStatusLabel.setBounds(250,570,350,30);
                confirmBookingButton.setEnabled(false); // Disable the button after booking
            }
        });
    }
    

    // Method to show available room selection options (room types)
    private void showRoomOptions(JLabel img) {
        roomCheckBoxes = new JCheckBox[5];
        int yPosition = 280; // Starting Y position for checkboxes
        for (int i = 0; i < 5; i++) {
            // Create checkboxes for each room
            roomCheckBoxes[i] = new JCheckBox("Room " + (i + 1) + " (" + roomTypes[i % 2] + ")");
            roomCheckBoxes[i].setBounds(250, yPosition, 200, 30);
            roomCheckBoxes[i].setFont(new Font("Arial", Font.BOLD, 14));
            roomCheckBoxes[i].setEnabled(!rooms[i]); // Disable room if already booked
            roomCheckBoxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    // Update the selected rooms count as checkboxes are toggled
                    updateSelectedRoomCount();
                }
            });
            img.add(roomCheckBoxes[i]);
            yPosition += 40; // Space between checkboxes
        }
    }

    // Method to update the count of selected rooms
    private void updateSelectedRoomCount() {
        roomsSelected = 0;
        int roomsAvailable = 5;
        for (JCheckBox checkBox : roomCheckBoxes) {
            if (checkBox.isSelected()) {
                roomsSelected++;
                roomsAvailable =5- roomsSelected;
            }
        }

        // Display the count of selected rooms
//        roomStatusLabel.setText(roomsSelected + "/" + roomsToBook + " rooms selected.");
//
//        // Update message: how many rooms booked and how many are still available
//        int roomsBooked = 0;
//        for (int i = 0; i < 5; i++) {
//            if (rooms[i]) {
//                roomsBooked++;
//                roomsAvailable -= roomsBooked;
//            }
//        }

       // int roomsAvailable = 5- roomsBooked;
        
       
        roomStatusLabel.setText(roomsSelected+   " rooms booked, " + roomsAvailable + " rooms available.");
        
        // If the number of rooms selected matches the number of rooms to book, book them
        if (roomsSelected == roomsAvailable) {
            bookRooms();
        }
    }
//
  // Method to handle the booking process when rooms are selected
        private void bookRooms() {
        int roomsBooked = 0;
    
        for (int i = 0; i < 5; i++) {
           if (roomCheckBoxes[i].isSelected() && !rooms[i]) {
            rooms[i] = true; // Mark room as booked
               roomsBooked++;
        }
        }
        // Show booking result
        if (roomsBooked == roomsToBook) {
           roomStatusLabel.setText(roomsBooked + " rooms booked successfully!");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Feedback().setVisible(true);
                }
            });
            
       
        
        }else {
            roomStatusLabel.setText("Please select the correct number of rooms.");
        }

    }
       
        
        
        
        
        public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelManagementSystem().setVisible(true);
            }
            
        });
    }
}

