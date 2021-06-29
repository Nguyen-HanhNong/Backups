/*
 * Class: Medication
 * Author: Tharusha
 * Start: Tuesday, January 19th, 2021
 * End: Monday, January 25th, 2021
 * Explanation: Class for specific medicines that the user uses. Stored in a prescription class that is used as an instance variable in the
 * User class.
 * Holds the name, daily doses, weekly frequency, and amount of tablets taken.
 */
public class Medication {

    //Instances variables
	String name;
	byte dailyDoses;
    byte weeklyFreq;
    byte amountOfTablets;

    //Default constructors
    public Medication()
    {
    	this.name="";
        this.dailyDoses=0;
        this.weeklyFreq =0;
        amountOfTablets=0;
    }

    //Overloaded constructor that passes the name, dailydoses, frequency, and amount of tablets
    public Medication(String name, byte dailyDoses, byte frequency, byte amountOfTablets)
    {
    	this.name=name;
        this.dailyDoses=dailyDoses;
        this.weeklyFreq = frequency;
        this.amountOfTablets=amountOfTablets;
    }

    //toString
    public String toString()
    {
         return "\nMedication name: " + this.name + "\nDose per week: " + this.weeklyFreq + "\nDose per day: " + this.dailyDoses + "\nAmount of tablets: " + this.amountOfTablets;
    }
}