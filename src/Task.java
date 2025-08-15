import java.time.LocalDateTime;

/**
* @author Ayra Babar
* @file Task.java
* @brief This file contains the implementation of a task object
* 
* 06/28/2025 - Initial creation & complete implementation of all methods
*/

public class Task {
		
	private final int ID;	// unique, unchangeable ID for each task
	private String name;
	private String description;
	private int manualPriority;
	private int dueDate;
	private LocalDateTime createdAt;
		
	/**
    * Constructs a new task object with a unique and random ID and given data
    * 
    * @param name string name of task
    * @param description explanation of the task
    * @param dueDate integer due date in the form: YYYYMMDD
    * @param priority manual integer priority of task
    */
	public Task(String name, String description, int priority, int dueDate) {
		this.ID = (int) (Math.random() * 9000) + 1000;
		this.name = name;
		this.description = description;
		this.manualPriority = priority;
		this.dueDate = dueDate;
		this.createdAt = LocalDateTime.now();
	}
	
	/**
	* Returns the integer ID of the task
	*
	* @return the unique and random number ID
	*/
	public int getID() {
		return this.ID;
	}
	
	/**
	* Returns the name of the task object
	*
	* @return string name of object
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	* Returns the task's description
	*
	* @return the entire description
	*/
	public String getDescription() {
		return this.description;
	}
	
	/**
	* Returns the task object's due date
	*
	* @return the due date of the task (format: YYYYMMDD)
	*/
	public int getDueDate() {
		return this.dueDate;
	}
	
	/**
	* Returns the integer priority of the task set by user input
	*
	* @return the number priority (between and including 1-5)
	*/
	public int getPriority() {
		return this.manualPriority;
	}
	
	/**
	* Returns the exact time when the task was made
	*
	* @return the time-stamp at which the task was created
	*/
	public LocalDateTime getCreatedAt() {
	    return this.createdAt;
	}
	
	/**
	* Sets the task object's name
	* 
	* @param newName the task object's new string name
	*/
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	* Sets the task object's description
	* 
	* @param newDescription the task object's new description
	*/
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	/**
	* Sets the task object's priority
	* 
	* @param newPriority the task object's new priority number value (between and including 1-5)
	*/
	public void setPriority(int newPriority) {
		this.manualPriority = newPriority;
	}
	
	/**
	* Sets the task object's due date
	* 
	* @param newDate the task object's new date (format: YYYYMMDD)
	*/
	public void setDueDate(int newDate) {
		this.dueDate = newDate;
	}
	
	/**
	* Prints all the details of one task
	*/
	public void showOneTask() {
		
		int year = dueDate / 10000;
		int month = (dueDate % 10000) / 100;
		int day = dueDate % 100;
		
		System.out.println("ID: " + this.ID + ", Name: \"" 
			    + this.name + "\", Description: \"" + this.description 
			    + "\", Due Date: " + year + "-" + month + "-" + day 
			    + ", Priority: " + this.manualPriority);		
	}
}

// END OF Task.java