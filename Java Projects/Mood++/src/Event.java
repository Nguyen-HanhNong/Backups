/*Event class, saves individual events in users' toDoLists and acts like a Node with
 * more instance variables than just cargo
 * By: Emily
 */
public class Event 
{
	Event next; //Event behaves as a Node

	private String name;
	private String description;
	private Date date;
	
	//default constructor
	public Event()
	{
		this.name=null;
		this.description=null;
		this.date=null;
		this.next=null;
	}
	
	//constructor that takes all instance variables
	public Event(String name, String description, Date date,Event next)
	{
		this.name=name;
		this.description=description;
		this.date=date;
		this.next=next;
	}
	
	//constructor that takes all instance variables except next
	public Event(String name, String description, Date date)
	{
		this.name=name;
		this.description=description;
		this.date=date;
		this.next=null;
	}
	
	//toString
	public String toString()
	{
		return this.date + "\n" + this.name+ ": " + this.description;
	}
	
	//toString
	public String getName()
	{
		return this.name;
	}
	
	//getters
	public String getDescription()
	{
		return this.description;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	//setters
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	public void setDate(Date date)
	{
		this.date=date;
	}
	
}
