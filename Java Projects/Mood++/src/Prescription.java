/*
 * Class: Prescription
 * Author: Tharusha/Emily
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: Holds an array list of Medication objects, that are used to display the User current prescription.
 * Holds the name, daily doses, weekly frequency, and amount of tablets taken.
 */
import java.util.ArrayList;

public class Prescription 
{
    //Arraylist of Medication
    ArrayList<Medication> prescription;

    //default constructor
    public Prescription()
    {
    	prescription = new ArrayList<Medication>();
    	this.prescription=null;
    }
    
    //constructor that creates object with all its instance variables
    public Prescription(String name, byte dailyDoses, byte weeklyDoses, byte amountOfTablets)
    {
    	prescription = new ArrayList<Medication>();
        prescription.add(new Medication(name,dailyDoses,weeklyDoses, amountOfTablets));
    }
    
    //constructor that takes Medication object
    public Prescription(Medication medication)
    {
    	prescription = new ArrayList<Medication>();
    	prescription.add(medication);
    }
    
    //creates and adds instance of Medication to the prescription
    public void addMedication(String name, byte dailyDoses, byte weeklyDoses, byte amountOfTablets)
    {
        prescription.add(new Medication(name,dailyDoses,weeklyDoses, amountOfTablets));
    }
    
    //removes object at given index
    public void removeMedication(int index)
    {
        prescription.remove(index);
    }

    //toString
    public String toString()
    {
        String result="";


        //If the the list is created using the default constructor, have the list output 'is empty' message
        if(this.prescription==null)
            return "List is empty";
        
        //base case, if ArrayList is empty
        if(this.prescription.size()==0)
            return "List is empty";
        
        //iterating through the ArrayList and adding every object to a temporary String
        for(int i = 0; i < this.prescription.size();i++)
        {
            result+=i+1 + ". " + this.prescription.get(i).toString() + "\n";
        }
        return result;
    }

}