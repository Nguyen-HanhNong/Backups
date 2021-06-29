/**
 * Class: MainGUI
 * Author: Nguyen-Hanh Nong
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: The class is the main GUI that users interacts with to do health-related things in the program like adding prescriptions,
 * keeping track of a to-do lists and create a therapist account.
 */

//Importing the necessary elements
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame { //Making the class extend from JFrame to inherit its methods

    JLabel lblInstruction;
    JTextArea txtDailyNotification;
    JButton btnTherapist;
    JButton btnGoal;
    JButton btnAddMedication;
    JButton btnSeeMedication;
    JButton btnRemoveMedication;
    User tempUser;
    Therapist tempTherapist;

    String[] dailyNotification; //A string array to hold all the different daily messages

    Prescription tempPrescription; //Temporary prescription of the user

    int intCounter = 0; //Counter to hold how many elements

    /** Author: Nguyen-Hanh Nong
     * Constructor of the main GUI class which creates the GUI and sets up the user variable
     * @param tempUser the user account that they entered into the program
     */
    public MainGUI(User tempUser) {
        getContentPane().setLayout(null);
        setupGUI(); //Calling the method that sets up the different elements in the GUI and adds them to the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.tempUser = tempUser; //Setting the user variable of the class, to the one that was passed thorugh in the SignIn GUI
    }

    /** Author: Nguyen-Hanh Nong
     * A void method that sets up the different elements of the GUI like the buttons and the labels and adding them to the JFrame
     */
    public void setupGUI() {
        lblInstruction = new JLabel("<html> What would you like to do with the program today?");
        lblInstruction.setLocation(105, 17);
        lblInstruction.setSize(289, 160);
        getContentPane().add(lblInstruction);

        btnTherapist = new JButton("<html> Turn your user account into a therapist account");
        btnTherapist.setLocation(33, 230);
        btnTherapist.setSize(152, 76);
        getContentPane().add(btnTherapist);
        btnTherapist.addActionListener(this::performTherapist);

        btnGoal = new JButton("<html> Create and view your to-do-list!");
        btnGoal.setLocation(185, 231);
        btnGoal.setSize(152, 77);
        getContentPane().add(btnGoal);
        btnGoal.addActionListener(this::performGoals);

        btnAddMedication = new JButton("<html> Create a prescription and add medication to that prescription.");
        btnAddMedication.setLocation(364, 230);
        btnAddMedication.setSize(152, 79);
        getContentPane().add(btnAddMedication);
        btnAddMedication.addActionListener(this::performAddPrescription);

        btnSeeMedication = new JButton("<html> View your prescription and medication: ");
        btnSeeMedication.setLocation(185, 150);
        btnSeeMedication.setSize(152, 77);
        getContentPane().add(btnSeeMedication);
        btnSeeMedication.addActionListener(this::performSeeMedication);

        btnRemoveMedication = new JButton("<html> Remove an entry in your prescription and medication: ");
        btnRemoveMedication.setLocation(33, 150);
        btnRemoveMedication.setSize(152, 77);
        getContentPane().add(btnRemoveMedication);
        btnRemoveMedication.addActionListener(this::performRemoveMedication);

        //Creating a font variable to change the default font of the text box for the daily notification
        Font font = new Font("Segoe Script", Font.BOLD, 15);

        txtDailyNotification = new JTextArea();
        txtDailyNotification.setLocation(0, 350);
        txtDailyNotification.setSize(500, 100);
        txtDailyNotification.setColumns(10);
        txtDailyNotification.setFont(font);
        txtDailyNotification.setText(createDailyNotification());
        txtDailyNotification.setLineWrap(true);
        getContentPane().add(txtDailyNotification);

        setTitle("Main part of the program!");
        setSize(542, 491);
        setVisible(true);
        setResizable(true);
    }

    /** Author: Nguyen-Hanh Nong
     * A method that returns a String representation of the daily motivational message for the GUI
     * @return a String containing the daily motivational message of the user
     */
    public String createDailyNotification() {
        //A String array containing a variety of messages
        dailyNotification = new String[]{"Life will become much easier when we will finally understand which hands to shake and which ones to Hold.",
                "Be thankful and proud of the struggles you had in your life. They shaped you in the person you are today. They’ll light your life in the darkness.",
                "You’re happy in your life as long as you’re content. Just believe in yourself and dream big. Do not give up on your hopes.",
                "Life is short, live it. Love is rare, grab it. Anger is bad, dump it. Fear is awful, face it. Memories are sweet, cherish it!",
                "When life seems unbearable, hold on. Because you have not yet seen the next page of the book of life. It is full of unknown mysteries, suspense, adventures and unachieved success.",
                "Life asked with Death: Why people love me and hate you, “Death said you are a beautiful lie and I’m a painful truth.",
                "Be happy and grateful for your life and the things you have achieved in this lifetime. There are people who have less than us but much happier than us.",
                "Life is about hard work, endurance and love. If you work hard to make your dreams come true, endure pain while fighting for it; eventually, you will win success and love one day.",
                "I know a man who stops asking ‘why’ and started saying ‘why not’. He is one of the most successful men in the world now. The way we respond to life today will shape our destiny tomorrow.",
                "If you believe in hard work and determination, luck will one day be with you eventually. It is in your hand to make yourself happy and successful.",
                "You are wasting your time if you’re worrying about failures. Worry about the chances you got but you decided not to try. Success is waiting at your doorstep."};

        //Creating two int's to hold a number for each position in the String array
        int max = 10;
        int min = 0;

        //Creating an int to get a random number between 0 and 10
        int intReturn = (int) (Math.random() * (max - min + 1) + min);

        return dailyNotification[intReturn]; //return the item in the String array at that random index
    }

    /** Author: Nguyen-Hanh Nong
     * A void method which creates a therapist when the button is pressed
     * @param ae an instance of the ActionEvent class
     */
    public void performTherapist(ActionEvent ae) {
        createTherapist();
    }

    /** Author: Nguyen-Hanh Nong
     * A method that creates a therapist and asks the user for user input for the address and phone number of a therapist
     * @return usually null, if the program makes it to the end, it returns recursively if they're problems in the user input
     */
    public Therapist createTherapist() {

        boolean blnCheck = false; //Boolean to loop repeatedly
        long phoneNumber = 0; //The variable holding the phone number of the therapist
        String temp = ""; //String to hold the address of the therapist

        tempTherapist = new Therapist(); //Create an instance of the therapist class

        tempTherapist.setAge(tempUser.getAge()); //Get the main characteristics from the user that was passed into the class
        tempTherapist.setUserName(tempUser.getUserName());
        tempTherapist.setPassword(tempTherapist.getPassword());
        tempTherapist.setfName(tempUser.getfName());
        tempTherapist.setlName(tempUser.getlName());

        try { //Using a try and catch statement to determine if the user input was correct
            phoneNumber = Long.parseLong(JOptionPane.showInputDialog("Input the phone number of the therapist: ")); //Parsing the phone number of the user
        } catch (NumberFormatException e) {
            //if user's input is invalid, show message and restart method
            JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value with no hyphens or brackets");
            return createTherapist();
        } catch (NullPointerException e) {
            //if user does not input anything, show message and restart method
            JOptionPane.showMessageDialog(null, "Please input a number value.");
            return createTherapist();
        }

        //Looping the usere input until correct
        while (!blnCheck) {

            blnCheck = true; //Setting the variable to true, or to end the loop by default

            temp = JOptionPane.showInputDialog(null, "Enter address :  "); //Asking the user for the address of the therapist

            if (temp == null || temp.equals("")) { //Checking if the input was empty or null
                JOptionPane.showMessageDialog(null, "Your input is invalid. Try again!"); //Display an error message
                blnCheck = false; //Setting the variable to false, which keeps looping the user input
            }
        }

        //Set the variables of the therapist to the one the user inputs
        tempTherapist.setPhoneNum(Long.toString(phoneNumber));
        tempTherapist.setAddress(temp);

        return null; //Return out of the program null, since the program doesn't do anything with the return value
    }

    /** Author: Nguyen-Hanh Nong
     * A void method that creates and shows a to-do-list
     * @param ae an instance of the action event class
     */
    public void performGoals(ActionEvent ae) {
        ToDoList.createList().showList(); //Create and show a to-do-list from the to-do-list class
    }

    /** Author: Nguyen-Hanh Nong
     * A void method that adds a medication to the prescription variable
     * @param ae an instance of the ActionEvent class
     */
    public void performAddPrescription(ActionEvent ae) {
        addPrescription(); //Calls a method that adds a medication to the prescription variable
    }

    /** Author: Nguyen-Hanh Nong
     * A method that adds a medication to the prescription variable
     * @return usually null, if the program makes it to the end, it returns recursively if they're problems in the user input
     */
    public Prescription addPrescription() {
        //A boolean to loop while the user inputs wrong inputs
        boolean blnCheck = false;

        String strName = ""; //Variables containing different properties of a medication
        byte dailyDoses;
        byte weeklyDoses;
        byte amountofTablets;


        try { //Using a try and catrch statement to get proper inputs from the user
            dailyDoses = Byte.parseByte(JOptionPane.showInputDialog("Input the daily doses of the medication: ")); //Using JOptionPane to get inputs of different characteristics of medication
            weeklyDoses = Byte.parseByte(JOptionPane.showInputDialog("Input the weekly doses of the medication: "));
            amountofTablets = Byte.parseByte(JOptionPane.showInputDialog("Input the amount of tablets of the medication: "));
        } catch (NumberFormatException e) {
            //if user's input is invalid, show message and restart method
            JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value.");
            return addPrescription();
        } catch (NullPointerException e) {
            //if user does not input anything, show message and restart method
            JOptionPane.showMessageDialog(null, "Please input a number value.");
            return addPrescription();
        }

        //Using a while loop to loop until the user inputs a proper name
        while (!blnCheck) {
            blnCheck = true; //Setting the looping boolean value to true, which will exit the loop by default
            strName = JOptionPane.showInputDialog("Input the name of the medication: "); //Getting the name of the medication
            if (strName == null || strName.equals("")) { //Checking if the user input nothing or a null entry (ex. clicking cancel button)
                JOptionPane.showMessageDialog(null, "You cannot input nothing or have a null entry. Try again!"); //Display an error message
                blnCheck = false; //Setting the looping boolean to false, which makes it to continue to loop
            }
        }

        //Creating a new prescription if no prescription exists or adding medication to the established prescirption if one is already made
        if (intCounter == 0) {
            tempPrescription = new Prescription(strName, dailyDoses, weeklyDoses, amountofTablets);
            intCounter++;
        } else {
            tempPrescription.addMedication(strName, dailyDoses, weeklyDoses, amountofTablets);
        }

        return null;
    }

    /** Author: Nguyen-Hanh Nong
     * A void method which allows the user to see all elements in their prescription plan
     * @param ae an instance of the ActionEvent class
     */
    public void performSeeMedication(ActionEvent ae) {

        JOptionPane.showMessageDialog(null, tempPrescription); //Using the toString of the prescription to print out the contents of the prescription

    }

    /** Author: Nguyen-Hanh Nong
     * A void method which removes a medication from the list when this button is clicked
     * @param ae an instance of the ActionEvent class
     */
    public void performRemoveMedication(ActionEvent ae) {
        //Checking if the user has any medication to remove using the medication counter
        if (intCounter == 0) {
            JOptionPane.showMessageDialog(null, "You cannot remove medication if you do not have any!"); //Displaying an error message
            return; //returning out of the method, thus doing nothing
        } else {
            removeMedication(); //using the remove medication method which removes a medication at an index
        }
    }

    /** Author: Nguyen-Hanh Nong
     * A method which removes a medication at an index which the user inputs
     * @return usually null, if the program makes it to the end, it returns recursively if they're problems in the user input
     */
    public Prescription removeMedication() {
        int intIndex = 0; //an int holding the index that the user wants to remove medication at

        try { //Using a try and catch statement to check for invalid inputs from the user
            intIndex = Integer.parseInt(JOptionPane.showInputDialog("Input the index that want to remove the medication at! (the first item starts at index 0)")); //Using the JOptionPane to determine what index in the list of medication you want to remove at
        } catch (NumberFormatException e) {
            //if user's input is invalid, show message and restart method
            JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value.");
            return removeMedication();
        } catch (NullPointerException e) {
            //if user does not input anything, show message and restart method
            JOptionPane.showMessageDialog(null, "Please input a number value.");
            return removeMedication();
        }

        //Checking if the index that the user inputs is within the bounds of the list
        if(intIndex < 0 || intIndex >= tempPrescription.prescription.size())
        {
            JOptionPane.showMessageDialog(null, "The index is invalid. Try again!"); //Displaying an erorr message
            return removeMedication(); //returns the method recursively
        }

        tempPrescription.removeMedication(intIndex); //Using the remove medication method in the prescription class to remove the medication
        return null; //returns null which doesn't matter since the return value is used for nothing
    }

}
