/*Setup by Abby; Edited by Shang & Emily
* Date obj saving date info according to our desired format and variable type*/

import java.util.Calendar;
import javax.swing.JOptionPane;

public class Date {

    int year;
    byte month;
    byte day;
    byte dayOfWeek;
    byte time;

    //Shang: default constructor give current date
    public Date(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = (byte) (calendar.get(Calendar.MONTH) +1);
        this.day = (byte)calendar.get(Calendar.DAY_OF_MONTH);
        this.dayOfWeek = (byte)(calendar.get(Calendar.DAY_OF_WEEK)-1);
        this.time=0;
    }
	//Abby
    public Date(byte m, byte day, int year, byte weekDay, byte time){
        this.month = m;
        this.day = day;
        this.year = year;
        this.time=time;

        for(int i = weekDay; i > 7; i-=7){
            weekDay -= 7;
        }
        this.dayOfWeek = weekDay;
    }

    //Shang: Convert the number representation of months to string
    public String convertMonth(){

        switch (this.month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
        }
        return "December";
    }

    //Shang
    public boolean equals(Date that){
        return this.year == that.year &&
                this.month== that.month &&
                this.day == that.day&&
                this.dayOfWeek == that.dayOfWeek &&
        		this.time == that.time;
    }

    //Shang: used to determine which of two dates is earlier, if this is earlier than that, return true
    public boolean earlier(Date that){
    	
    	if (this.year<that.year)
    		return true;
    	if (this.year==that.year && this.month<that.month)
    		return true;
    	if (this.year==that.year && this.month==that.month && this.day<that.day)
    		return true;
    	if (this.year==that.year && this.month==that.month && this.day==that.day && this.time<that.time)
    		return true;
    	
    	return false;
    }

    public String toString()
    {
        String weekDay="", timeOfDay="";
        byte formattedTime=0;
        
        switch(this.dayOfWeek) {
            case 1:
            	weekDay="Monday"; break;
            case 2:
            	weekDay="Tuesday"; break;
            case 3:
            	weekDay="Wednesday"; break;
            case 4:
            	weekDay="Thursday"; break;
            case 5:
            	weekDay="Friday"; break;
            case 6:
            	weekDay="Saturday"; break;
            case 7:
            	weekDay="Sunday"; break;
            default:
            	weekDay=null;
        }
        
        //formatting time output
        if (this.time<11)
        {
        	timeOfDay=" AM";
        	formattedTime=this.time;
        } else
		{
			timeOfDay=" PM";
			formattedTime=(byte) (this.time-12);
		}
        
        return weekDay+ ", " + this.convertMonth() + " " + this.day + ", " + this.year + ", at " + formattedTime + timeOfDay;
    }

    //method which uses user input to create an instance of Date, by Emily
    public static Date createDate()
    {
    	 int year=0; 
    	 byte month=0;
    	 byte day=0;
    	 byte dayOfWeek=0;
    	 byte time=0;
    	 
    	try //day of the week user input
      	{
  			dayOfWeek = Byte.parseByte(JOptionPane.showInputDialog(null, "Please enter the day of the week (1-7)"));
  			
  			if (dayOfWeek>7 || dayOfWeek<1) //in case user inputs invalid week day, ask again
  			{
  				JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value between 1 to 7."); 
  	  			return createDate();
  			}
  		}
      	catch (NumberFormatException e)
  		{
  			//if user's input is invalid, show message and restart method
  			JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value."); 
  			return createDate();
  		}
  		catch (NullPointerException e)
  		{
  			//if user does not input anything, show message and restart method
  			JOptionPane.showMessageDialog(null, "Please input a number value.");
  			return createDate();
  		}
     	
      	try //month user input
      	{
  			month = Byte.parseByte(JOptionPane.showInputDialog(null, "Please enter the number of the month (1-12)"));
  			
  			if (month>12 || month<1) //if month input is not valid, ask again
  			{
  				JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value between 1 to 12."); 
  	  			return createDate();
  			}
  		}
      	catch (NumberFormatException e)
  		{
  			//if user's input is invalid, show message and restart method
  			JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value."); 
  			return createDate();
  		}
  		catch (NullPointerException e)
  		{
  			//if user does not input anything, show message and restart method
  			JOptionPane.showMessageDialog(null, "Please input a number value.");
  			return createDate();
  		}
    
      	try //date user input
      	{
  			day = Byte.parseByte(JOptionPane.showInputDialog(null, "Please enter the date (1-31)"));
  			
  			if (day>31 || day<1)//if date is not valid, ask again
  			{
  				JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value between 1 to 31."); 
  	  			return createDate();
  			}
  		}
      	catch (NumberFormatException e)
  		{
  			//if user's input is invalid, show message and restart method
  			JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value."); 
  			return createDate();
  		}
  		catch (NullPointerException e)
  		{
  			//if user does not input anything, show message and restart method
  			JOptionPane.showMessageDialog(null, "Please input a number value.");
  			return createDate();
  		}
    
    	try //year user input
       	{
   			year = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the year"));
   		}
       	catch (NumberFormatException e)
   		{
   			//if user's input is invalid, show message and restart method
   			JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value."); 
   			return createDate();
   		}
   		catch (NullPointerException e)
   		{
   			//if user does not input anything, show message and restart method
   			JOptionPane.showMessageDialog(null, "Please input a number value.");
   			return createDate();
   		}
    	
    	try //time user input 
    	{
			time = Byte.parseByte(JOptionPane.showInputDialog(null, "Please enter the time your task must be done (1-24)."));
			
			if (time>24 || time<0) //if time input is not valid, ask again
  			{
  				JOptionPane.showMessageDialog(null, "Input invalid. Please input a number value between 1 to 24."); 
  	  			return createDate();
  			}
		}
    	catch (NumberFormatException e)
		{
			//if user's input is invalid, show message and restart method
			JOptionPane.showMessageDialog(null, "Input invalid. Please input a time value."); 
			return createDate();
		}
		catch (NullPointerException e)
		{
			//if user does not input anything, show message and restart method
			JOptionPane.showMessageDialog(null, "Please input a price value.");
			return createDate();
		}
    	//creating and returning new Date instance
    	return new Date(month,day, year, dayOfWeek, time);
    }
}