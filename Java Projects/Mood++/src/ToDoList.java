import javax.swing.JOptionPane;
//1/25, 2021
//By: Shang-Ru and Emily
/*This class is the ToDoList for the user
* Implemented by a LL Queue ADT
*   FIFO: head is the first out. Whenever we add an Event add to tail of queue*/

public class ToDoList {

    Date date;
    Event head;
    Prescription prescription;

    //Shang: default constructor
    public ToDoList() {
        this.date = new Date();
        this.head = new Event();
    }

    //Shang: constructor
    public ToDoList(Date date, Event head) {
        this.date = date;
        this.head = head;
    }
    //Shang: equivalent of enQ in a Q adt
    public void addEvent(Event e) {
    	
    	if (this.head == null) {
            this.head = e;
            return;
        }

        this.getLast().next = e;
    }
    
    //method that takes user input to create instance of Event, by Emily
	public static Event createEvent()
    {
    	String name="";
    	String description="";
    	Date date=null;
    
    	//asking user for their name
    	name=JOptionPane.showInputDialog(null, "Please enter the name of task.");
    	if(name==null)
		{
			JOptionPane.showMessageDialog(null, "Please input a name."); 
			return createEvent();
		}
    	
    	//asking user for their task description
    	description=JOptionPane.showInputDialog(null, "Please enter the description of task.");
    	if(description==null)
		{
			JOptionPane.showMessageDialog(null, "Please input a description."); 
			return createEvent();
		}
    	
    	//asking user for the date of their Event
    	date=Date.createDate();
    	
    	//create and return new Event instance
    	return new Event(name, description, date);
    }
	
	//method that creates new Instance of todolist, by Emily
	public static ToDoList createList()
	{
		return new ToDoList(new Date(),createEvent());
	}
	
	//by Shang-Ru: delete an event by isolating the pointers pointing to it and from it
    public void deleteEvent(Event e) {
        Event ogNext = e.next;
        e.next = null;
        findPrev(e).next = ogNext;
    }

    //Shang: insert an event to a sorted todolist without messing the sort up
    public void insertEventSorted(Event e) {
        //dummy node to traverse the LL
        Event node = head;
        //traverse till e is earlier than the next
        while (node.next.getDate().earlier(e.getDate())) {
            node = node.next;
        }

        //insert event
        Event ogNext = node.next.next;
        node.next = e;
        e.next = ogNext;
    }

    //Shang: self-explanatory
    public Event peek() {
        return this.head;
    }

    //basically deQ
    public void completeEvent() {

        this.head = this.head.next;
    }
    
    //by Emily
    public void sortByTime() {
        this.head = mergeAndSort(this.head);
    }

   
    public Event mergeAndSort(Event head) 
    {
        Event leftEnd = head, rightStart;
        Event high = head, sortedListHead;

        //base case, if list is null or contains one element
        if (head == null || head.next == null)
            return head;
        //finding middle of the list
        while (high.next != null && high.next.next != null) 
        {
            high = high.next.next;
            leftEnd = leftEnd.next;
        }
        //splitting list into two halves
        rightStart = leftEnd.next;
        leftEnd.next = null;

        //recursion to keep splitting
        head = mergeAndSort(head);
        rightStart = mergeAndSort(rightStart);
        
        //merging the lists
        sortedListHead = merge(head, rightStart);
        
        //return head of sortedList
        return sortedListHead;
    }
    
    //by Emily
    public Event merge(Event leftNode, Event rightNode) 
    {
        Event merged = new Event();

        //base cases, if either halves are null
        if (leftNode == null)
            return rightNode;
        if (rightNode == null)
            return leftNode;

       //comparison and setting the next Event node using recursion 
        if (leftNode.getDate().earlier(rightNode.getDate()))
        {
            merged = leftNode;
            merged.next = merge(leftNode.next, rightNode);
        } else {
            merged = rightNode;
            merged.next = merge(leftNode, rightNode.next);
        }
        
        //return the sorted Event node
        return merged;
    }

    //by Shang-Ru: finds the previous event to Event e
    private Event findPrev(Event e) {

        if (e == this.head) {
            return e;
        }

        Event node = this.head;

        while (node.next != e) {
            node = node.next;
        }
        return node;

    }

    //Shang: finds the last Event to of the LL
    private Event getLast() {
        if (isEmpty()){
            return null;
        }

        Event node = this.head;

        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    //Shang: find how many events there r
    public int size(){

        if (isEmpty()){
            return 0;
        }

        int size = 1;

        Event node = head;
        while (node.next!= null){
            node = node.next;
            size++;
        }
        return size;
    }

    //Shang
    public String toString() {
        String result = "";
        Event node = head;
        while (node != null) {
            result += node.toString() + "\n\n";
            node = node.next;
        }
        return result;
    }

    //SHang: self-explanatory
    public boolean isEmpty()
    {
    	return this.head == null;
    }
    
    //method that shows the user their To do list, by Emily
    public void showList()
    {
    	String[] mainOptions = new String[] {"Add Task", "Complete Top Task","Sort by Time", "Create new list", "Exit"};
	    int mainResponse;
	    
	    //Displaying JOptionPane with the user's list + what their options are
	    mainResponse= JOptionPane.showOptionDialog(null,this.toString(),"This is your to-do list "+ this.date,JOptionPane.DEFAULT_OPTION, 
	    											JOptionPane.PLAIN_MESSAGE, null, mainOptions, mainOptions[0]);
	    
	    if (mainResponse==0) //if user clicks "Add task", call addEvent and show the list again
	    {
	    	this.addEvent(ToDoList.createEvent());
	    	this.showList();
	    	return;
	    } 
	    else if (mainResponse==1) //if user clicks "Complete Top task", call completeEvent and show the list
	    {
	    	if (this.isEmpty()) //if the list is empty, show message and create new list
	    	{
	    		JOptionPane.showMessageDialog(null, "Your list is empty. Please create a new list");
	    		ToDoList.createList().showList();
		    	return;
	    	}
	    	this.completeEvent();
	    	this.showList();
	    	return;
	    }
	    else if (mainResponse ==2) //if user clicks "sort by time", call method and show list
	    {
	    	this.sortByTime();
	    	this.showList();
	    	return;
	    }
	    else if (mainResponse ==3) //if user clicks "Create new list", call method
	    {
	    	ToDoList.createList().showList();
	    	return;
	    }
    }
}