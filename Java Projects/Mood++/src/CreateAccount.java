/**
 * Class: CreateAccount
 * Author: Nguyen-Hanh Nong
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: The class that creates a GUI that allows the user to create an account for the user to sign into. It also puts the account into a textfile
 * which allows the user to sign in at anytime, even if the user closes and re opens the program. The program uses a method to concatenate the password
 * into a String which is used by the user class in the password variable.
 */


//Importing the necessary elements into the program for it to run.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


//Making the class inherit from the JFrame class
public class CreateAccount extends JFrame {

    //Creating the necessary elements of the GUI like the password box and labels
    JPasswordField passPassword;
    JTextArea txtFirstName;
    JTextArea txtUsername;
    JTextArea txtLastName;
    JTextArea txtAge;
    JLabel lblLastName;
    JLabel lblPassword;
    JLabel lblFirstName;
    JLabel lblUserName;
    JLabel lblAge;
    JButton btnCreate;

    /** //Author: Nguyen-Hanh Nong
     * A default constructor for the creating account GUI
     */
    public CreateAccount() {
        //Setting the layout to null
        getContentPane().setLayout(null);
        setupGUI(); //Calling the method that sets up the different elements in the GUI and adds them to the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Making it so the program closes when you close the program
    }

    /** Author: Nguyen-Hanh Nong
     * A void method that sets up the different elements of the GUI like the text boxes and the labels and adding them to the JFrame
     */
    public void setupGUI() {
        passPassword = new JPasswordField("");
        passPassword.setLocation(65, 161);
        passPassword.setSize(125, 25);
        passPassword.setEchoChar('*');
        passPassword.setColumns(5);
        getContentPane().add(passPassword);

        txtFirstName = new JTextArea("");
        txtFirstName.setLocation(63, 257);
        txtFirstName.setSize(125, 25);
        txtFirstName.setRows(5);
        txtFirstName.setColumns(5);
        getContentPane().add(txtFirstName);

        txtUsername = new JTextArea("");
        txtUsername.setLocation(66, 56);
        txtUsername.setSize(125, 25);
        txtUsername.setRows(5);
        txtUsername.setColumns(5);
        getContentPane().add(txtUsername);

        txtLastName = new JTextArea("");
        txtLastName.setLocation(63, 354);
        txtLastName.setSize(125, 25);
        txtLastName.setRows(5);
        txtLastName.setColumns(5);
        getContentPane().add(txtLastName);

        txtAge = new JTextArea("");
        txtAge.setLocation(63, 460);
        txtAge.setSize(125, 25);
        txtAge.setRows(5);
        txtAge.setColumns(5);
        getContentPane().add(txtAge);

        lblLastName = new JLabel("Input last name:");
        lblLastName.setLocation(62, 309);
        lblLastName.setSize(100, 35);
        getContentPane().add(lblLastName);

        lblPassword = new JLabel();
        lblPassword.setLocation(65, 115);
        lblPassword.setSize(154, 35);
        lblPassword.setText("Input password below:");
        getContentPane().add(lblPassword);

        lblFirstName = new JLabel("Input first name:");
        lblFirstName.setLocation(65, 213);
        lblFirstName.setSize(110, 35);
        getContentPane().add(lblFirstName);

        lblUserName = new JLabel("Input username below:");
        lblUserName.setLocation(66, 14);
        lblUserName.setSize(141, 35);
        getContentPane().add(lblUserName);

        lblAge = new JLabel("Input age below:");
        lblAge.setLocation(66, 400);
        lblAge.setSize(141, 35);
        getContentPane().add(lblAge);

        btnCreate = new JButton();
        btnCreate.setLocation(59, 600);
        btnCreate.setSize(179, 79);
        btnCreate.setText("Create this account!");
        getContentPane().add(btnCreate);
        btnCreate.addActionListener(this::performCreateAccount);

        setTitle("Create an Account!");
        setSize(292, 700);
        setVisible(true);
        setResizable(true);
    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that activates when the CreateAccount button is pressed which
     * creates a user and writes the user to the AccountList text file. It also closes
     * the CreateAccount GUI and opens up the SignIn Gui for the user to sign in
     *
     * @param ae an instance of the action event class
     */
    public void performCreateAccount(ActionEvent ae) {
        //Checking if the inputs are valid or not, returning out of the method if the user has invalid inputs
        if (!checkInputs()) {
            return;
        }

        //Creating a temporary user variable
        User temp = new User();
        temp.setAge(Byte.parseByte(txtAge.getText())); //Storing all the different inputs of the person into the temporary user variable
        temp.setfName(txtFirstName.getText());
        temp.setlName(txtLastName.getText());
        temp.setUserName(txtUsername.getText());
        temp.setPassword(concatenatePassword(passPassword.getPassword())); //Concatenating the password because the password field box can only get text in an array of chars

        //Calling the method that writes the user object into the text file
        writeUser(temp);

        dispose(); //Disposing or closing the current Gui
        SignIn signInGUI = new SignIn(); //Opening the SignIN gui for the user to sign in

    }

    /** //Author: Nguyen-Hanh Nong
     * A static method that returns the String representation of the user inputs into the password box in the GUI
     * the reason it's static is that I reuse the method in the SignIn Gui class for that password box so I want to be able
     * to use the method without making an instance of the CreateAccount object
     * @param charArray the inputs of the user into the password box
     * @return
     */
    public static String concatenatePassword(char[] charArray) {
        //Creating a temporary string variable to hold the concatenated password;
        String temp = "";

        //Looping through every element in the character array and adding it to the String
        for (int i = 0; i < charArray.length; i++) {
            temp += charArray[i];
        }

        return temp; //Returning the String;
    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that writes the object representing the user's account for the program into a text file so that it can be read in at anytime
     * @param tempUser the variable that is going to be written into the text file
     */
    public void writeUser(User tempUser) {
        try { //Using a try and catch statement to check for exception and errors
            ObjectOutputStream writeUser = new ObjectOutputStream(new FileOutputStream("AccountList.txt", true)); //Creating an objectoutput stream to write the object into the file
            writeUser.writeUnshared(tempUser); //Writing the object into the file
            writeUser.close();//Closing the file once the information is added

        } catch (FileNotFoundException e) { //Checking for errors and writing error statements in the console if they occur
            System.out.println("Error: Cannot open file for writing");
        } catch (IOException e) {
            System.out.println("Error: Cannot write to file");
        }

    }

    /** //Author: Nguyen-Hanh Nong
     * A method that returns a variable if they're any invalid entries in the users information
     * @return a boolean representing errors or no errors in the user's input, false if they're erorrs and true if they aren't any
     */
    public boolean checkInputs() {
        try { //Using a try and catch statement to detect if they're any errors in the age input like using letters
            byte tempAge = Byte.parseByte(txtAge.getText()); //Trying to parse the information and assign the information to a byte
        } catch (Exception e) { //Catching if hte information in the input would crash the program like inputting an invalid number or too large
            JOptionPane.showMessageDialog(null, "You inputted an invalid age! Try again!"); //Return as false and displays an error message
            return false;
        }

        //Checking if the user inputted nothing
        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtUsername.getText().equals("") || concatenatePassword(passPassword.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "You cannot have an empty name, password or username. Try again!"); //Return as false and displays an error message
            return false;
        }

        //Checking if the user inputted a null entry
        if (txtFirstName.getText() == null || txtLastName.getText() == null || txtUsername.getText() == null || concatenatePassword(passPassword.getPassword()) == null) {
            JOptionPane.showMessageDialog(null, "You cannot have an invalid name, password or username. Try again!"); //Return as false and displays an error message
            return false;
        }

        //Checking for any digits in the first and last name entries
        for (int i = 0; i < txtFirstName.getText().length(); i++) {
            Character tempChar = txtFirstName.getText().charAt(i);

            if (Character.isDigit(tempChar)) {
                JOptionPane.showMessageDialog(null, "You cannot have a number in your first name! Try again!"); //Return as false and displays an error message
                return false;
            }
        }

        for (int i = 0; i < txtLastName.getText().length(); i++) {
            Character tempChar = txtLastName.getText().charAt(i);

            if (Character.isDigit(tempChar)) {
                JOptionPane.showMessageDialog(null, "You cannot have a number in your last name! Try again!"); //Return as false and displays an error message
                return false;
            }
        }

        return true; //Returning true if the program makes it thorugh all the checks
    }


}
