package AeroTripGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.Objects;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;

public class OnlineAirportGUI {
    JFrame frame = new JFrame();

    String dirname = System.getProperty("user.dir");
    final String db = dirname + "\\Databases\\LoginSignUpDatabase.txt";

    String username, password;

    JLabel msg; // Used to print corresponding messages

    final Font timesNewRoman = new Font("Times New Roman", Font.BOLD, 15); // font equipped by every component
//    final Font forgotPasswordFont = new Font("Times New Roman", Font.BOLD, 10); // font equipped by forgot password label

    JLabel optionPaneLabel = new JLabel(); // label added into JOptionPanes to show corresponding messages
//    int optionPaneResult; // used for storing option pane results

    Image icon = Toolkit.getDefaultToolkit().getImage(dirname + "airplane.png"); // Title Bar Icon

    public static void main(String[] args) {
        new OnlineAirportGUI().homepage();
    }

    void homepage() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setSize(350, 300);
        frame.setTitle("Homepage");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(icon);
//        frame.setLocationRelativeTo(false);

        optionPaneLabel.setFont(timesNewRoman);

        JButton homepageLoginButton = new JButton("Login");
        JButton homepageSignUpButton = new JButton("SignUp");

        frame.add(homepageLoginButton);
        frame.add(homepageSignUpButton);

        homepageLoginButton.setBounds(50, 95, 100, 30);
        homepageLoginButton.setForeground(Color.BLACK);
        homepageLoginButton.setBackground(Color.ORANGE);
        homepageLoginButton.setFont(timesNewRoman);
        homepageLoginButton.addActionListener(new LoginUI());

        homepageSignUpButton.setBounds(180, 95, 100, 30);
        homepageSignUpButton.setForeground(Color.BLACK);
        homepageSignUpButton.setBackground(Color.ORANGE);
        homepageSignUpButton.setFont(timesNewRoman);
        homepageSignUpButton.addActionListener(e -> new SignUpUI().signUpUI("homepage"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method shows an option pane for confirmation from the user about a
     * particular action
     */
    int areYouSureJOP(JFrame jFrame) {
        optionPaneLabel.setText("Are You Sure?");
        return JOptionPane.showConfirmDialog(jFrame, optionPaneLabel, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    class Back implements ActionListener {
        int num;

        Back(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (num == 1 || num == 2) {
                if (areYouSureJOP(frame) == JOptionPane.YES_OPTION) {
                    if (num == 1) homepage();
                    else if (num == 2) displayMenu();
                }
            } else if (num == 3) {
                displayMenu();
            }
        }
    }

    void addBackButton(int num) {
        JButton backButton = new JButton("Back");
        frame.add(backButton);

        backButton.setBounds(0, 0, 80, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(timesNewRoman);
        backButton.addActionListener(new Back(num));
    }

    long encryptPassword(String password) {
        long encryptedPassword = 0, a = 1;
        short i, len = (short) password.length();
        for (i = 0; i < len; i++) {
            encryptedPassword += ((int) password.charAt(i)) * a;
            a *= 100;
        }
        return encryptedPassword;
    }

    static class ShowPasswordsCheckBox implements ActionListener {
        JPasswordField pf;

        ShowPasswordsCheckBox(JPasswordField pf) {
            this.pf = pf;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox c = (JCheckBox) e.getSource();
            pf.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
        }
    }

    /**
     * This Method is used to show a message dialog that contains a JLabel.
     * The messages are kept in JLabel so that they can be equipped with
     * "timesNewRoman" font.
     * optionPaneLabel has been set to have font "timesNewRoman" in Homepage method
     */
    void showMessageDialogJOP(JFrame jFrame, String msg, String title, int magicConstant) {
        optionPaneLabel.setText(msg);
        JOptionPane.showMessageDialog(jFrame, optionPaneLabel, title, magicConstant);
    }

    // static components used in login & signup
    JLabel userLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel rePasswordLabel = new JLabel("Re-Type Password:");

    class LoginUI implements ActionListener {
        JTextField userField;
        JPasswordField passwordField;
        JCheckBox showPasswordCB;
        JButton loginButton = new JButton("Login");

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setTitle("Login");
            frame.setLocationRelativeTo(null);

            addBackButton(1);
            userField = new JTextField();
            passwordField = new JPasswordField();
            showPasswordCB = new JCheckBox("Show Password");
            msg = new JLabel();

            frame.add(userLabel);
            frame.add(passwordLabel);
            frame.add(userField);
            frame.add(passwordField);
            frame.add(showPasswordCB);
            frame.add(loginButton);
            frame.add(msg);

            userField.setText("");
            passwordField.setText("");
            msg.setText("");

            userLabel.setBounds(50, 50, 80, 25);
            userLabel.setFont(timesNewRoman);

            userField.setBounds(130, 50, 120, 25);
            userField.setFont(timesNewRoman);

            passwordLabel.setBounds(50, 80, 80, 25);
            passwordLabel.setFont(timesNewRoman);

            passwordField.setBounds(130, 80, 120, 25);
            passwordField.setFont(timesNewRoman);

            showPasswordCB.setBounds(90, 110, 150, 25);
            showPasswordCB.setFont(timesNewRoman);
            showPasswordCB.addActionListener(new ShowPasswordsCheckBox(passwordField));

            loginButton.setBounds(120, 140, 80, 25);
            loginButton.setBackground(Color.DARK_GRAY);
            loginButton.setForeground(Color.WHITE);
            loginButton.setFont(timesNewRoman);
            loginButton.addActionListener(new LoginMainCode());

            msg.setBounds(0, 190, 350, 25);
            msg.setFont(timesNewRoman);
            msg.setHorizontalAlignment(0);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        class LoginMainCode implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = userField.getText().trim();
                password = String.valueOf(passwordField.getPassword()).trim();

                msg.setText("");

                if (username.equals("") || password.equals("")) {
                    msg.setText("Please Fill all the fields");
                } else {
                    String str;
                    String[] credentials;
                    boolean usernameFound = false, passwordFound = false;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(db));
                        while ((str = reader.readLine()) != null) {
                            credentials = str.split(",");
                            if (username.equals(credentials[0])) {
                                usernameFound = true;
                                if (String.valueOf(encryptPassword(password)).equals(credentials[1])) {
                                    passwordFound = true;
                                    break;
                                }
                                break;
                            }
                        }
                        reader.close();
                    } catch (Exception ex) {
                        msg.setText("Error in reading file");
                    }

                    if (!usernameFound) {
                        optionPaneLabel.setText("<html>No User With Given Credentials\nDo You Want to SignUp?</html>".replaceAll("\n", "<br>"));
                        if (JOptionPane.showConfirmDialog(frame, optionPaneLabel,
                                "Account Not Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            SignUpUI signUpObj = new SignUpUI();
                            signUpObj.userField = new JTextField(username);
                            signUpObj.passwordField = new JPasswordField(password);
                            signUpObj.signUpUI("login");
                        }
                    } else if (!passwordFound) {
                        msg.setForeground(Color.RED);
                        msg.setText("Password Incorrect");
                    } else {
                        displayMenu();
                    }
                }
            }
        }
    }

    boolean isPasswordAccepted(String password, JFrame frame1) {
        boolean inRange = false, hasWhiteSpace = false, hasLowerCaseLetter = false, hasUpperCaseLetter = false, hasDigit = false,
                hasSpecialCharacter = false;
        byte i, len = (byte) password.length();
        if (len >= 8 && len <= 16)
            inRange = true;

        msg.setText("");

        if (!inRange) {
            showMessageDialogJOP(frame1, "<html>Password isn't in the Range of 8-16<br>Please try with another Password</html>",
                    "Password Not Accepted", JOptionPane.ERROR_MESSAGE);
        } else {
            for (i = 0; i < len; i++) {
                if (Character.isAlphabetic(password.charAt(i))) {
                    if (Character.isLowerCase(password.charAt(i))) hasLowerCaseLetter = true;
                    else hasUpperCaseLetter = true;
                } else if (Character.isDigit(password.charAt(i))) {
                    hasDigit = true;
                } else if (Character.isWhitespace(password.charAt(i))) {
                    hasWhiteSpace = true;
                } else hasSpecialCharacter = true;
            }

            if (!(hasLowerCaseLetter && hasUpperCaseLetter && hasDigit && !hasWhiteSpace && hasSpecialCharacter)) {
                StringBuilder message = new StringBuilder();
                if (!hasLowerCaseLetter) message.append("Password Must Contain at least one Lowercase letter").append("\n");
                if (!hasUpperCaseLetter) message.append("Password Must Contain at least one Uppercase letter").append("\n");
                if (!hasDigit) message.append("Password Must Contain at least one Digit").append("\n");
                if (!hasSpecialCharacter) message.append("Password Must Contain at least one Special Character").append("\n");
                if (hasWhiteSpace) message.append("Password Shouldn't Contain a White space Character");

                JOptionPane.showMessageDialog(frame1, message, "Password Not Accepted", JOptionPane.ERROR_MESSAGE);
            }
        }

        return inRange && !hasWhiteSpace && hasLowerCaseLetter && hasUpperCaseLetter && hasDigit && hasSpecialCharacter;
    }

    class SignUpUI {
        JTextField userField;
        JPasswordField passwordField, rePasswordField;
        JButton signUpButton = new JButton("SignUp");
        JCheckBox showPasswordCB1, showPasswordCB2;

        void signUpUI(String calledBy) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setTitle("SignUp");
            frame.setLocationRelativeTo(null);

            JButton backButton = new JButton("Back");
            showPasswordCB1 = new JCheckBox("Show Password");
            rePasswordField = new JPasswordField();
            showPasswordCB2 = new JCheckBox("Show Password");
            msg = new JLabel();

            if (calledBy.equals("homepage")) {
                userField = new JTextField();
                passwordField = new JPasswordField();
            }

            frame.add(userLabel);
            frame.add(passwordLabel);
            frame.add(rePasswordLabel);
            frame.add(userField);
            frame.add(passwordField);
            frame.add(showPasswordCB1);
            frame.add(rePasswordField);
            frame.add(showPasswordCB2);
            frame.add(signUpButton);
            frame.add(msg);
            frame.add(backButton);

            addBackButton(1);

            userLabel.setBounds(50, 50, 80, 25);
            userLabel.setFont(timesNewRoman);

            userField.setBounds(130, 50, 120, 25);
            userField.setFont(timesNewRoman);

            passwordLabel.setBounds(50, 80, 80, 25);
            passwordLabel.setFont(timesNewRoman);

            passwordField.setBounds(130, 80, 120, 25);
            passwordField.setFont(timesNewRoman);

            showPasswordCB1.setBounds(90, 110, 150, 25);
            showPasswordCB1.setFont(timesNewRoman);
            showPasswordCB1.addActionListener(new ShowPasswordsCheckBox(passwordField));

            rePasswordLabel.setBounds(0, 140, 130, 25);
            rePasswordLabel.setFont(timesNewRoman);

            rePasswordField.setBounds(130, 140, 120, 25);
            rePasswordField.setFont(timesNewRoman);

            showPasswordCB2.setBounds(90, 170, 150, 25);
            showPasswordCB2.setFont(timesNewRoman);
            showPasswordCB2.addActionListener(new ShowPasswordsCheckBox(rePasswordField));

            signUpButton.setBounds(125, 200, 100, 25);
            signUpButton.setBackground(Color.DARK_GRAY);
            signUpButton.setForeground(Color.WHITE);
            signUpButton.setFont(timesNewRoman);
            signUpButton.addActionListener(new SignUpMainCode());

            msg.setBounds(0, 230, 350, 25);
            msg.setFont(timesNewRoman);
            msg.setHorizontalAlignment(0);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        class SignUpMainCode implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = userField.getText().trim();
                password = String.valueOf(passwordField.getPassword());
                String rePassword = String.valueOf(rePasswordField.getPassword());

                if (username.equals("") || password.equals("") || String.valueOf(rePasswordField.getPassword()).equals("")) {
                    msg.setText("Please Fill all the fields");
                } else if (!password.equals(rePassword)) {
                    msg.setText("Passwords doesn't match");
                } else {
                    String str;
                    boolean found = false;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(db));
                        while ((str = reader.readLine()) != null) {
                            if (username.equals(str.split(",")[0])) {
                                msg.setText("Username Already Taken");
                                found = true;
                                break;
                            }
                        }

                        if (!found && isPasswordAccepted(password, frame) &&
                                new File(dirname + "\\Databases\\TransactionHistories\\" + username + ".txt").createNewFile() &&
                                new File(dirname + "\\Databases\\Enquiries\\" + username + ".txt").createNewFile()) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(db, true));
                            writer.write(username + "," + encryptPassword(password) + "\n");
                            writer.flush();
                            writer.close();
                            showMessageDialogJOP(frame, "Account Created Successfully", "SignUp Successful", JOptionPane.INFORMATION_MESSAGE);
                            displayMenu();
                        }
                    } catch (Exception ex) {
                        showMessageDialogJOP(frame, "Due to some Error we couldn't create your account", "Account Not Created", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    final JLabel airplaneImage = new JLabel(new ImageIcon(dirname + "\\airplane.png"));
    void displayMenu() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setTitle("Online Airliner");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        JButton bookTicketButton = new JButton("Book for a Journey");
        JButton enquiryButton = new JButton("Enquire");
        JButton cancelTicketButton = new JButton("Cancel the Ticket");
        JButton printTicketButton = new JButton("Print a Ticket");
        JButton logoutButton = new JButton("Logout");

        frame.add(bookTicketButton);
        frame.add(enquiryButton);
        frame.add(cancelTicketButton);
        frame.add(printTicketButton);
        frame.add(logoutButton);
        frame.add(airplaneImage);

        airplaneImage.setBounds(210, 25, 150, 100); // Change Width

        bookTicketButton.setBounds(200, 150, 200, 30);
        bookTicketButton.setBackground(Color.DARK_GRAY);
        bookTicketButton.setForeground(Color.WHITE);
        bookTicketButton.setFont(timesNewRoman);
        bookTicketButton.addActionListener(new BookTicket());

        enquiryButton.setBounds(200, 210, 200, 30);
        enquiryButton.setBackground(Color.DARK_GRAY);
        enquiryButton.setForeground(Color.WHITE);
        enquiryButton.setFont(timesNewRoman);
        enquiryButton.addActionListener(new Enquiry());

        cancelTicketButton.setBounds(200, 270, 200, 30);
        cancelTicketButton.setBackground(Color.DARK_GRAY);
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.setFont(timesNewRoman);
        cancelTicketButton.addActionListener(new CancelTicket());

        printTicketButton.setBounds(200, 330, 200, 30);
        printTicketButton.setBackground(Color.DARK_GRAY);
        printTicketButton.setForeground(Color.WHITE);
        printTicketButton.setFont(timesNewRoman);
        printTicketButton.addActionListener(new PrintTicket());

        logoutButton.setBounds(250, 390, 100, 30);
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(timesNewRoman);
        logoutButton.addActionListener(new Back(1));
    }

    final String phoneRegex = "^[6-9]\\d{9}";
    JLabel nameLabel, phoneLabel, adultLabel, childrenLabel, infantLabel, fromLabel, toLabel, refIDLabel, textMessage, phoneMessage;
    JTextField nameText, phoneText, refIDText;
    JComboBox<String> flightFromLocations, flightToLocations, adultField, childrenField, infantField;
    int refID;
    String[] flightLocations = {"--Select--", "Hyderabad", "Chennai", "Delhi", "Bombay", "Bengaluru", "Dubai", "SanFransisco", "Istanbul", "London", "KualaLumpur"};
    String[] numberArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] adultArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    Date date = new Date();
    byte i = 0;

    class BookTicket implements ActionListener {
        JButton backButton;

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setLocationRelativeTo(null);

            JButton bookingButton = new JButton("Book A Ticket");

            nameLabel = new JLabel("Name:");
            phoneLabel = new JLabel("Phone:");
            adultLabel = new JLabel("Adults:");
            childrenLabel = new JLabel("Children:");
            infantLabel = new JLabel("Infants:");
            fromLabel = new JLabel("From:");
            toLabel = new JLabel("To:");
            nameText = new JTextField();
            phoneText = new JTextField();
            backButton = new JButton("Back");
            flightFromLocations = new JComboBox<>(flightLocations);
            flightToLocations = new JComboBox<>(flightLocations);
            adultField = new JComboBox<>(adultArray);
            childrenField = new JComboBox<>(numberArray);
            infantField = new JComboBox<>(numberArray);
            textMessage = new JLabel();
            phoneMessage = new JLabel();

            frame.setTitle("Booking");
            frame.add(nameLabel);
            frame.add(phoneLabel);
            frame.add(adultLabel);
            frame.add(childrenLabel);
            frame.add(infantLabel);
            frame.add(fromLabel);
            frame.add(toLabel);
            frame.add(nameText);
            frame.add(phoneText);
            frame.add(adultField);
            frame.add(childrenField);
            frame.add(infantField);
            frame.add(flightFromLocations);
            frame.add(flightToLocations);
            frame.add(bookingButton);
            frame.add(backButton);
            frame.add(textMessage);
            frame.add(phoneMessage);

            nameLabel.setBounds(180, 50, 100, 25);
            phoneLabel.setBounds(180, 100, 100, 25);
            adultLabel.setBounds(180, 150, 100, 25);
            childrenLabel.setBounds(180, 200, 100, 25);
            infantLabel.setBounds(180, 250, 100, 25);
            fromLabel.setBounds(180, 300, 100, 25);
            toLabel.setBounds(180, 350, 100, 25);

            nameText.setBounds(280, 50, 100, 25);
            phoneText.setBounds(280, 100, 100, 25);
            adultField.setBounds(280, 150, 100, 25);
            childrenField.setBounds(280, 200, 100, 25);
            infantField.setBounds(280, 250, 100, 25);
            flightFromLocations.setBounds(280, 300, 100, 25);
            flightToLocations.setBounds(280, 350, 100, 25);

            textMessage.setBounds(0, 450, 600, 50);
            textMessage.setFont(new Font("Courier", Font.PLAIN, 13));
            textMessage.setHorizontalAlignment(0);

            phoneMessage.setBounds(400, 100, 200, 25);
            phoneMessage.setForeground(Color.RED);
            phoneMessage.setHorizontalAlignment(0);

            backButton.setBounds(0, 0, 80, 30);
            backButton.setBackground(Color.BLACK);
            backButton.setForeground(Color.WHITE);
            backButton.addActionListener(new Back(2));

            bookingButton.setBounds(210, 400, 150, 25);
            bookingButton.setBackground(Color.GREEN);
            bookingButton.setForeground(Color.WHITE);
            bookingButton.addActionListener(new BookingCode());
        }

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();

        class BookingCode implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                textMessage.setText("");
                phoneMessage.setText("");
                if (nameText.getText().trim().equals("") || phoneText.getText().trim().equals("")
                        || Objects.equals(flightFromLocations.getSelectedItem(), "--Select--") ||
                        Objects.equals(flightToLocations.getSelectedItem(), "--Select--")) {
                    textMessage.setText("Please Fill all the fields");
                } else if (String.valueOf(flightFromLocations.getSelectedItem()).equals(String.valueOf(flightToLocations.getSelectedItem()))) {
                    textMessage.setText("From and To Addresses cannot be same");
                } else if (!phoneText.getText().matches(phoneRegex)) {
                    phoneMessage.setText("Enter a Valid Phone Number");
                } else {
                    try {
                        i = 0;
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.setLocationRelativeTo(null);

                        frame.add(button1);
                        frame.add(button2);
                        frame.add(button3);
                        frame.add(backButton);
                        frame.add(textMessage);

                        backButton.setBounds(0, 0, 80, 30);
                        backButton.setBackground(Color.BLACK);
                        backButton.setForeground(Color.WHITE);
                        backButton.addActionListener(new Back(2));

                        textMessage.setBounds(0, 200, 600, 25);
                        textMessage.setHorizontalAlignment(0);
                        textMessage.setText("Select A Flight of your Choice:");

                        FileInputStream fin = new FileInputStream("P:\\JavaProject\\FlightNames.txt");
                        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
                        boolean found = false;
                        String[] flightInfo;
                        String str;
                        while ((str = buffin.readLine()) != null) {
                            flightInfo = str.split(",");
                            if (String.valueOf(flightFromLocations.getSelectedItem()).equals(flightInfo[0]) && String.valueOf(flightToLocations.getSelectedItem()).equals(flightInfo[1])) {
                                found = true;
                                if (i == 1) {
                                    button1.setBounds(50, 235, 500, 25);
                                    button1.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + ",Cost: " + flightInfo[2] + ",Duration: " + flightInfo[3]);
                                } else if (i == 2) {
                                    button2.setBounds(50, 275, 500, 25);
                                    button2.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + ",Cost: " + flightInfo[2] + ",Duration: " + flightInfo[3]);
                                } else {
                                    button3.setBounds(50, 315, 500, 25);
                                    button3.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + ",Cost: " + flightInfo[2] + ",Duration: " + flightInfo[3]);
                                }
                                i++;
                            }
                        }
                        if (!found) {
                            textMessage.setFont(new Font("Courier", Font.PLAIN, 14));
                            textMessage.setText("Sorry! No Flights Available");
                        } else {
                            button1.addActionListener(new AfterSelection(1));
                            button2.addActionListener(new AfterSelection(2));
                            button3.addActionListener(new AfterSelection(3));
                        }
                    } catch (IOException exception) {
                        textMessage.setText("Sorry! Some Error occurred");
                    }
                }
            }
        }

        float finalCost, duration;

        class AfterSelection implements ActionListener {
            int num;
            String[] splittedText;

            AfterSelection(int num) {
                this.num = num;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (num == 1) {
                        splittedText = button1.getText().split(",");
                    } else if (num == 2) {
                        splittedText = button2.getText().split(",");
                    } else {
                        splittedText = button3.getText().split(",");
                    }

                    int adults = Integer.parseInt(String.valueOf(adultField.getSelectedItem()));
                    int children = Integer.parseInt(String.valueOf(childrenField.getSelectedItem()));
                    int infants = Integer.parseInt(String.valueOf(infantField.getSelectedItem()));
                    float cost = Float.parseFloat(splittedText[2].replace("Cost: ", ""));
                    duration = Float.parseFloat((splittedText[3].replace("Duration: ", "")).replace("hrs", ""));
                    finalCost = (float) (((cost * adults) + (0.2 * cost * children) + (0.9 * cost * infants)));
                    String[] fromTo = splittedText[1].split("->");

                    // Calculating and storing reference ID
                    FileInputStream refIDin = new FileInputStream(dirname + "\\Databases\\ReferenceID.txt");
                    BufferedReader refIDbr = new BufferedReader(new InputStreamReader(refIDin));
                    refID = Integer.parseInt(refIDbr.readLine());
                    refIDbr.close();
                    FileOutputStream refIDout = new FileOutputStream(dirname + "\\Databases\\ReferenceID.txt");
                    BufferedWriter refIDbw = new BufferedWriter(new OutputStreamWriter(refIDout));
                    refIDbw.write(String.valueOf(refID + 1));
                    refIDbw.flush();
                    refIDbw.close();

                    // Storing the Data in File in csv format
                    File f = new File(dirname + "\\CustomersDataBase.txt");
                    BufferedWriter bufOut = new BufferedWriter(new FileWriter(f, true));
                    bufOut.write(nameText.getText().trim() + "," + phoneText.getText().trim() + "," + adultField.getSelectedItem() + "," +
                            childrenField.getSelectedItem() + "," + infantField.getSelectedItem() + "," + flightFromLocations.getSelectedItem() + "," +
                            flightToLocations.getSelectedItem() + "," + date + "," + refID + "," + finalCost + "," + duration + "," + splittedText[0] + "\n");
                    bufOut.flush();
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.setLocationRelativeTo(null);

                    frame.add(textMessage);
                    frame.add(backButton);

                    backButton.setBounds(0, 0, 80, 30);
                    backButton.setBackground(Color.BLACK);
                    backButton.setForeground(Color.WHITE);
                    backButton.addActionListener(new Back(2));

                    textMessage.setBounds(0, 100, 600, 200);
                    textMessage.setHorizontalAlignment(0);
                    textMessage.setVerticalAlignment(0);
                    textMessage.setForeground(Color.GREEN);
                    textMessage.setFont(new Font("Courier", Font.BOLD, 30));
                    textMessage.setText("Booked a Ticket Successfully");

                    JButton printButton = new JButton("Print Ticket");
                    frame.add(printButton);
                    printButton.setBounds(250, 500, 100, 25);
                    printButton.setBackground(Color.DARK_GRAY);
                    printButton.setForeground(Color.WHITE);
                    printButton.addActionListener(new PrintTicketInBookingClass(splittedText[0], fromTo[0], fromTo[1], nameText.getText().trim(),
                            phoneText.getText().trim(), adults, children, infants, finalCost, duration));
                } catch (IOException exception) {
                    textMessage.setText("Sorry, Some Error Occurred, Couldn't Book ticket. Sorry for the inconvenience");
                }
            }
        }

        class PrintTicketInBookingClass implements ActionListener {
            String flightName, name, phn, from, to;
            int adults, children, infants;
            float cost, duration;

            PrintTicketInBookingClass(String flightName, String from, String to, String name, String phn, int adults, int children, int infants, float cost, float duration) {
                this.flightName = flightName;
                this.from = from;
                this.to = to;
                this.name = name;
                this.phn = phn;
                this.adults = adults;
                this.children = children;
                this.infants = infants;
                this.cost = cost;
                this.duration = duration;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
//                createPDF(flightName, from, to, name, phn, adults, children, infants, cost, duration);
            }
        }
    }

    JLabel label1, label2, label3;

    class Enquiry implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setLocationRelativeTo(null);

            JButton enquireButton = new JButton("Enquire");
            JButton bookButton = new JButton("Book Ticket");
            fromLabel = new JLabel("From:");
            toLabel = new JLabel("To:");
            msg = new JLabel();
            JButton backButton = new JButton("Back");
            flightFromLocations = new JComboBox<>(flightLocations);
            flightToLocations = new JComboBox<>(flightLocations);
            frame.setTitle("Enquiry");
            label1 = new JLabel();
            label2 = new JLabel();
            label3 = new JLabel();

            frame.add(fromLabel);
            frame.add(toLabel);
            frame.add(flightFromLocations);
            frame.add(flightToLocations);
            frame.add(enquireButton);
            frame.add(backButton);
            frame.add(msg);
            frame.add(label1);
            frame.add(label2);
            frame.add(label3);

            fromLabel.setBounds(200, 50, 100, 25);
            flightFromLocations.setBounds(300, 50, 100, 25);

            toLabel.setBounds(200, 100, 100, 25);
            flightToLocations.setBounds(300, 100, 100, 25);

            backButton.setBounds(0, 0, 80, 30);
            backButton.setBackground(Color.BLACK);
            backButton.setForeground(Color.WHITE);
            backButton.addActionListener(new Back(2));

            label1.setBounds(0, 250, 600, 100);
            label1.setHorizontalAlignment(0);
            label1.setFont(new Font("Courier", Font.PLAIN, 15));
            label2.setBounds(0, 270, 600, 100);
            label2.setHorizontalAlignment(0);
            label2.setFont(new Font("Courier", Font.PLAIN, 15));
            label3.setBounds(0, 290, 600, 100);
            label3.setHorizontalAlignment(0);
            label3.setFont(new Font("Courier", Font.PLAIN, 15));

            bookButton.setBounds(150, 320, 400, 100);
            bookButton.addActionListener(new BookTicket());

            msg.setBounds(0, 0, 600, 600);
            msg.setHorizontalAlignment(0);
            msg.setVerticalAlignment(0);

            enquireButton.setBounds(250, 150, 100, 25);
            enquireButton.setBackground(Color.DARK_GRAY);
            enquireButton.setForeground(Color.WHITE);
            enquireButton.addActionListener(new EnquiryCode());
        }

        class EnquiryCode implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                i = 0;
                label1.setText("");
                label2.setText("");
                label3.setText("");
                msg.setText("");
                if (Objects.equals(flightFromLocations.getSelectedItem(), "--Select--") || Objects.equals(flightToLocations.getSelectedItem(), "--Select--")) {
                    msg.setText("Please Complete All the fields");
                } else {
                    try {
                        FileInputStream fin = new FileInputStream(dirname + "\\FlightNames.txt");
                        BufferedReader buffin = new BufferedReader(new InputStreamReader(fin));
                        String str;
                        boolean found = false;
                        String[] flightInfo;
                        while ((str = buffin.readLine()) != null) {
                            flightInfo = str.split(",");
                            if (Objects.equals(flightFromLocations.getSelectedItem(), flightInfo[0]) && Objects.equals(flightToLocations.getSelectedItem(), flightInfo[1])) {
                                found = true;
                                if (i == 1) {
                                    label1.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + "   Cost: " + flightInfo[2] + "   Duration: " + flightInfo[3]);
                                } else if (i == 2) {
                                    label2.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + "   Cost: " + flightInfo[2] + "   Duration: " + flightInfo[3]);
                                } else {
                                    label3.setText(flightInfo[4] + "," + flightInfo[0] + "->" + flightInfo[1] + "   Cost: " + flightInfo[2] + "   Duration: " + flightInfo[3]);
                                }
                                i++;
                            }
                        }
                        if (!found) {
                            msg.setText("Sorry! Flights from " + flightFromLocations.getSelectedItem() + " to " +
                                    flightToLocations.getSelectedItem() + " not Available");
                        }
                    } catch (Exception exception) {
                        msg.setText("Error in reading a file");
                    }
                }
            }
        }
    }

    class CancelTicket implements ActionListener {
        JLabel msg = new JLabel();
        JButton cancelButton = new JButton("Cancel the Ticket");

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setLocationRelativeTo(null);

            phoneLabel = new JLabel("Phone:");
            phoneText = new JTextField();
            refIDLabel = new JLabel("Reference ID:");
            refIDText = new JTextField();
            JButton backButton = new JButton("Back");
            phoneMessage = new JLabel();

            frame.add(phoneLabel);
            frame.add(phoneText);
            frame.add(refIDLabel);
            frame.add(refIDText);
            frame.add(msg);
            frame.add(cancelButton);
            frame.add(backButton);
            frame.add(phoneMessage);

            phoneLabel.setBounds(200, 50, 100, 25);
            phoneText.setBounds(300, 50, 100, 25);
            phoneMessage.setBounds(400, 50, 200, 25);
            phoneMessage.setForeground(Color.RED);
            phoneMessage.setHorizontalAlignment(0);

            refIDLabel.setBounds(200, 100, 100, 25);
            refIDText.setBounds(300, 100, 100, 25);

            msg.setBounds(0, 250, 600, 50);
            msg.setHorizontalAlignment(0);

            backButton.setBounds(0, 0, 80, 30);
            backButton.setBackground(Color.BLACK);
            backButton.setForeground(Color.WHITE);
            backButton.addActionListener(new Back(2));

            cancelButton.setBounds(230, 150, 150, 25);
            cancelButton.setBackground(Color.RED);
            cancelButton.setForeground(Color.WHITE);
            cancelButton.addActionListener(new CancelCode());
        }

        class CancelCode implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                msg.setText("");
                phoneMessage.setText("");
                if (phoneText.getText().equals("") || refIDText.getText().equals("")) {
                    msg.setText("Please fill all the fields");
                } else if (!(phoneText.getText().matches(phoneRegex))) {
                    phoneMessage.setText("Enter a Valid Phone Number");
                } else {
                    try {
                        phoneMessage.setText("");
                        File inputFile = new File(dirname + "\\CustomersDataBase.txt");
                        FileInputStream fin = new FileInputStream(inputFile);
                        File tempFile = new File(dirname + "\\temporary.tmp");
                        FileOutputStream fout = new FileOutputStream(tempFile);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
                        String str;
                        String[] info;
                        while ((str = br.readLine()) != null) {
                            info = str.split(",");
                            if (!phoneText.getText().trim().equals(info[1]) && !refIDText.getText().trim().equals(info[8])) {
                                bw.write(str);
                                bw.newLine();
                            } else {
                                found = true;
                            }
                        }
                        bw.flush();
                        bw.close();
                        br.close();

                        inputFile.delete();
                        tempFile.renameTo(inputFile);

                        if (found) {
                            msg.setText("Cancelled Your Ticket Successfully");
                        } else {
                            msg.setText("No Passenger Available with given Credentials. Please check the details and enter again");
                        }
                    } catch (Exception ex) {
                        msg.setText("Sorry Some Error Occurred");
                    }
                }
            }
        }
    }

//    void createPDF(String flightName, String from, String to, String name, String phn, int adults, int children, int infants, float cost, float duration) {
//        try {
//            Document document = new Document();
//            document.setPageSize(new com.itextpdf.text.Rectangle(1000, 300));
//            PdfWriter.getInstance(document, new FileOutputStream(dirname + "\\Tickets\\" + refID + "_Ticket.pdf"));
//
//            //open the document
//            document.open();
//
//            //creating table
//            float[] columnWidths = {5f, 5f, 5f, 7f, 6f, 5f, 5f, 5f, 5f, 5f};
//            PdfPTable table = new PdfPTable(columnWidths);
//            table.addCell(new PdfPCell(new Paragraph("Flight Name")));
//            table.addCell(new PdfPCell(new Paragraph("From")));
//            table.addCell(new PdfPCell(new Paragraph("To")));
//            table.addCell(new PdfPCell(new Paragraph("Passenger Name")));
//            table.addCell(new PdfPCell(new Paragraph("Phone No.")));
//            table.addCell(new PdfPCell(new Paragraph("Adults")));
//            table.addCell(new PdfPCell(new Paragraph("Children")));
//            table.addCell(new PdfPCell(new Paragraph("Infants")));
//            table.addCell(new PdfPCell(new Paragraph("Cost")));
//            table.addCell(new PdfPCell(new Paragraph("Duration")));
//
//            table.addCell(new PdfPCell(new Paragraph(flightName)));
//            table.addCell(new PdfPCell(new Paragraph(from)));
//            table.addCell(new PdfPCell(new Paragraph(to)));
//            table.addCell(new PdfPCell(new Paragraph(name)));
//            table.addCell(new PdfPCell(new Paragraph(phn)));
//            table.addCell(new PdfPCell(new Paragraph(String.valueOf(adults))));
//            table.addCell(new PdfPCell(new Paragraph(String.valueOf(children))));
//            table.addCell(new PdfPCell(new Paragraph(String.valueOf(infants))));
//            table.addCell(new PdfPCell(new Paragraph(String.valueOf(cost))));
//            table.addCell(new PdfPCell(new Paragraph(String.valueOf(duration))));
//
//            document.add(table);
//            document.close();
//
//            Desktop.getDesktop().open(new File(dirname + "\\Tickets\\" + refID + "_Ticket.pdf"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    class PrintTicket implements ActionListener {
        JButton printingButton = new JButton("Print Ticket");

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setLocationRelativeTo(null);

            phoneLabel = new JLabel("Phone:");
            phoneText = new JTextField();
            refIDLabel = new JLabel("Reference ID:");
            refIDText = new JTextField();
            JButton backButton = new JButton("Back");
            phoneMessage = new JLabel();

            frame.add(phoneLabel);
            frame.add(phoneText);
            frame.add(refIDLabel);
            frame.add(refIDText);
            frame.add(msg);
            frame.add(printingButton);
            frame.add(backButton);
            frame.add(phoneMessage);

            phoneLabel.setBounds(200, 50, 100, 25);
            phoneText.setBounds(300, 50, 100, 25);
            phoneMessage.setBounds(400, 50, 200, 25);
            phoneMessage.setForeground(Color.RED);
            phoneMessage.setHorizontalAlignment(0);

            refIDLabel.setBounds(200, 100, 100, 25);
            refIDText.setBounds(300, 100, 100, 25);

            msg.setBounds(0, 350, 600, 25);
            msg.setHorizontalAlignment(0);
            msg.setVerticalAlignment(0);

            backButton.setBounds(0, 0, 80, 30);
            backButton.setBackground(Color.BLACK);
            backButton.setForeground(Color.WHITE);
            backButton.addActionListener(new Back(2));

            printingButton.setBounds(250, 150, 100, 25);
            printingButton.setBackground(Color.DARK_GRAY);
            printingButton.setForeground(Color.WHITE);
            printingButton.addActionListener(new PrintingCode());
        }

        class PrintingCode implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!phoneText.getText().matches(phoneRegex)) {
                    phoneMessage.setText("Enter a Valid Phone Number");
                } else {
                    phoneMessage.setText("");
                    File th = new File(dirname + "\\AeroTripGUI\\Databases\\TransactionHistories\\" + username + ".txt");
                    String str;
                    String[] details;
                    boolean refIDFound = false, phoneFound = false;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(th));
                        while ((str = reader.readLine()) != null) {
                            details = str.split(",");
                            if (phoneText.getText().equals(details[1])) {
                                phoneFound = true;
                                if (refIDText.getText().equals(details[8])) {
//                                    createPDF(details[11], details[5], details[6], details[0], details[1], Integer.parseInt(details[2]),
//                                            Integer.parseInt(details[3]), Integer.parseInt(details[4]), Float.parseFloat(details[9]),
//                                            Float.parseFloat(details[10]));
                                    refIDFound = true;
                                    break;
                                }
                            }
                        }
                        reader.close();
                        if (!phoneFound) {
                            msg.setText("Phone Number not found");
                        } else if (!refIDFound) {
                            msg.setText("Reference ID not Found");
                        } else {
                            msg.setText("Your ticket is stored at " + dirname + "\\Tickets\\" + refID + ".pdf\"");
                        }
                    } catch (Exception exception) {
                        msg.setText("Sorry, Some Error Occurred. Couldn't print your Ticket.");
                    }
                }
            }
        }
    }
}
