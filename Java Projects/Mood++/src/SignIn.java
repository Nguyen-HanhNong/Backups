/**
 * Class: SignIn
 * Author: Nguyen-Hanh Nong
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: The class is the first GUI that the users sees when entering the program and displays the title of the program and also two boxes for
 * signing into the program with a username and password. It also has a button for creating an account and opens an instance of the CreateAccount GUI.
 * The program uses a text file to read in the password and username of a established account.
 */

//Importing the necessary elements for the class to run
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//Making the class inherit from JFrame to get its methods
public class SignIn extends JFrame {

    //Creating the instance variable for the objects the user will see and interact with in the GUI
    JButton btnSubmit;
    JLabel lblTitle;
    JLabel lblUserName;
    JTextArea txtUserName;
    JPasswordField passwordField;
    JLabel lblPassword;
    JButton btnCreate;

    /** //Author: Nguyen-Hanh Nong
     * A default constructor for the SignIn class
     */
    public SignIn()
    {
        //Setting the layout to null
        getContentPane().setLayout(null);
        setupGUI(); //Calling the method that sets up the different elements in the GUI and adds them to the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Making it so the program closes when you close the program
    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that sets up the different elements that the user will see as he opens the GUI like the labels and text boxes
     */
    public void setupGUI()
    {
        //Creating an instance of the object, setting the locaiton and size for most objects
        lblTitle = new JLabel("Welcome to Mood++");
        lblTitle.setLocation(71,26);
        lblTitle.setSize(257,67);
        getContentPane().add(lblTitle);

        lblUserName = new JLabel("Username: ");
        lblUserName.setLocation(141,139);
        lblUserName.setSize(100,50);
        getContentPane().add(lblUserName);

        lblPassword = new JLabel("Password: ");
        lblPassword.setLocation(141,241);
        lblPassword.setSize(100,50);
        getContentPane().add(lblPassword);

        txtUserName = new JTextArea("");
        txtUserName.setLocation(103,196);
        txtUserName.setSize(184,25);
        txtUserName.setRows(5);
        txtUserName.setColumns(5);
        getContentPane().add(txtUserName);

        //Using a password field to get user input for the password for the user since its safer and more secure than a normal tet field
        passwordField = new JPasswordField("");
        passwordField.setLocation(103,292);
        passwordField.setSize(184,25);
        passwordField.setEchoChar('*');
        passwordField.setColumns(5);
        getContentPane().add(passwordField);

        btnCreate = new JButton("Create a new account!");
        btnCreate.setLocation(95,388);
        btnCreate.setSize(206,61);
        getContentPane().add(btnCreate);
        btnCreate.addActionListener(this::performButtonCreate);

        btnSubmit = new JButton("<html> Click this button once you have put in your information to sign in!");
        btnSubmit.setLocation(92,473);
        btnSubmit.setSize(206,61);
        getContentPane().add(btnSubmit);
        btnSubmit.addActionListener(this::performCheckAccount);

        setTitle("Mood++"); //Setting the title to the name of the program
        setSize(401,580);
        setVisible(true);
        setResizable(true);
    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that happens when the user clicks the CreateAccount button and calls an instance of the CreateAccount class to make a GUI appear
     * to make an account
     * @param ae
     */
    public void performButtonCreate(ActionEvent ae)
    {
        //Checking if an account is already made by checking the text file to see if its empty or not, if its empty, the program just
        //returns out of the method and displays a message
        if(!checkEmpty()) {
            JOptionPane.showMessageDialog(null, "An account has already been made! Put the credentials of the account into the username and password section! ");
            return;
        }

        //Creating an instance of the CreateAccount class which contains all the information and code for making an account
        CreateAccount newAccount = new CreateAccount();
        dispose(); //Disposing the current so it disappears and wastes less memory
    }

    /** Author: Nguyen-Hanh Nong
     * A method that returns a boolean if the AccountList text file has nothing in it
     * @return a boolean representing if the file is empty or not, true if it is empty, false if it isnt empty
     */
    public boolean checkEmpty()
    {
        //Creating a variable to store the file
        File newFile = new File("AccountList.txt");

        //Checking if the files length is 0 or not, 0 represneting an empty file and returning a variable for both cases
        if (newFile.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** //Author: Nguyen-Hanh Nong
     * A method that checks if the textboxes have valid inputs or not with a boolean returning representing whether it has valid inputs or not
     * @return a boolean representing the validity of the input, false if its invalid, true if it is valid.
     */
    public boolean checkTextBoxes()
    {
        //Checking if the inputs of the text box are empty or not
        if(txtUserName.getText().equals("") || CreateAccount.concatenatePassword(passwordField.getPassword()).equals(""))
        {
            JOptionPane.showMessageDialog(null, "You cannot have an empty password or username. Try again!"); //Return as false and displays an error message
            return false;
        }

        //Checking if the inputs of the text are null
        if(txtUserName.getText() == null || CreateAccount.concatenatePassword(passwordField.getPassword()) == null)
        {
            JOptionPane.showMessageDialog(null, "You cannot have an invalid name, password or username. Try again!"); //Return as false and displays an error message
            return false;
        }

        return true; //Return as true if the program makes it thorugh all of the checks
    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that activates when the user clicks the button that checks if the account information they input matches the one they created
     * @param ae an instance of the action event class
     */
    public void performCheckAccount(ActionEvent ae) {

        //Checking if the text boxes have valid inputs with the checktextboxes method, if it returns false, returns out of the method, doing nothing
        if(!checkTextBoxes()) {
            return;
        }

        //Creating a temporary user variable to store the user inputs of the user
        User tempUser = new User();
        tempUser.setUserName(txtUserName.getText());
        tempUser.setPassword(CreateAccount.concatenatePassword(passwordField.getPassword()));

        //Using the check account method to see if the user has the right password and username
        checkAccount(tempUser);

    }

    /** //Author: Nguyen-Hanh Nong
     * A void method that checks if the credentials of the user input matches with the account they created
     * @param tempUser the variable containing the user input of the user
     */
    public void checkAccount(User tempUser) {
        //Using a try and catch statements to catch errors in reading the inforamtion from the text file
        try {
            //Creating an instance of the objectinputstream to read in the user object in the text file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("AccountList.txt"));
            User obj = (User) ois.readUnshared(); //Reading in the object into a temporary user variabel

            //Comparing the two user objects to see if they have matching username and passwords
            if (tempUser.getUserName().equals(obj.getUserName()) && tempUser.getPassword().equals(obj.getPassword())) {
                tempUser = obj; //Setting the temporary user object to the one that was fully defined in the text file
                MainGUI tempGUI = new MainGUI(tempUser); //Opening the mainGUi class which has the information for the main gui of the program
                ois.close(); //Closing reading the file
                dispose(); //Disposing the current frame
                return; //returning to exit out of the method;
            }

            //Closing reading the file if the two users dont match
            ois.close();

            JOptionPane.showMessageDialog(null, "The accounts do not match! Try again!"); //Displaying a message to say that the two users dont match

        } catch (IOException | ClassNotFoundException e) { //Catch statement if an error occurs
            e.printStackTrace();
        }

    }

    //The main of all of the classes which creates an instance of the SignIn object
    public static void main(String[] args) {
        SignIn temp = new SignIn();
    }

}
