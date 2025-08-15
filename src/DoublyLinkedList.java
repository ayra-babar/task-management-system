/**
* @author Ayra Babar
* @file DoublyLinkedList.java
* @brief This file contains the implementation of a doubly linked list using double node 
* 		 objects keeping track of task history
* 
* 06/28/2025 - Initial creation & complete implementation of all methods
*/

public class DoublyLinkedList {

	private DoubleNode head;
	private DoubleNode tail;
	private int size;
	private final int MAX_HISTORY_LOGS = 10;
	
	/**
    * Constructs a new DoublyLinkedList with default data and links
    */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}
	
	/**
	* Checks whether the list is empty
	*
	* @return true if head is null, otherwise returns false
	*/
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**
	* Returns the number of entries in the list
	*
	* @return number of DoubleNode entries in the list
	*/
	public int getSize() {
		return size;
	}
	
	/**
	* Returns the first node in list
	*
	* @return the first DoubleNode in the list, or null if empty
	*/
	public DoubleNode getFirst() {
		return (isEmpty() ? null : this.head);
	}
	
	/**
	* Returns the last node in list
	*
	* @return the last DoubleNode in the list, or null if empty
	*/
	public DoubleNode getLast() {
		return (isEmpty() ? null : this.tail);
	}
	
	/**
	* Prints the entire task history log from latest entry to oldest entry
	*/
	public void printInOrderHistory() {
		if(isEmpty()) {
			System.out.println("Nothing in Task History Log\n");
			return;
		}
		
		DoubleNode current = this.head;
		System.out.println("Task History Log from Latest to Oldest (Max Capacity of 10 Entries)");
		while(current != null) {
			System.out.println( "- " + current.getHistoryEntry());
			current = current.getNext();
		}
		System.out.println();
	}
	
	/**
	* Prints the entire task history log from oldest entry to latest entry
	*/
	public void printReverseHistory() {
		if(isEmpty()) {
			System.out.println("Nothing in Task History Log\n");
			return;
		}
		
		DoubleNode current = this.tail;
		System.out.println("Task History Log from Oldest to Latest (Max Capacity of 10 Entries)");
		while(current != null) {
			System.out.println("- " + current.getHistoryEntry());
			current = current.getPrev();
		}
		System.out.println();
	}
	
	/**
	* Prints a specific history log (1-based index)
	* 
	* @param logValue the entry number to print (1 = most recent)
	*/
	public void printSpecificHistoryLog(int logValue) {
		
		DoubleNode current = this.head;
		
		for(int i = 0; i < logValue - 1; i++) {
			current = current.getNext();
		}
		
		System.out.println("Task History Entry " + logValue + ": " + current.getHistoryEntry());
	}
	
	/**
	* Inserts new task history log when a new task is created
	* 
	* @param ID number ID of task
	* @param taskName string name of task
	*/
	public void insertion(int ID, String taskName) {
		
		String message = "Task " + ID + ", '" + taskName + "' created and added to task list";		
		DoubleNode newEntry = new DoubleNode(message, null, head);
		
		if(isEmpty()) {
			head = tail = newEntry;
		} else {
			head.setPrev(newEntry);
			head = newEntry;
		}
	
		size++;
		
		if(size > MAX_HISTORY_LOGS) {
			removeLast();
		}
	}
	
	/**
	* Inserts a new task history log when a task is deleted
	* 
	* @param taskToDelete the task that was deleted
	*/
	public void deletion(Task taskToDelete) {
		
		String message = "Task " + taskToDelete.getID() + ", '" + taskToDelete.getName() + "' deleted from task list";		
		DoubleNode newEntry = new DoubleNode(message, null, head);
		
		if(isEmpty()) {
			head = tail = newEntry;
		} else {
			head.setPrev(newEntry);
			head = newEntry;
		}
	
		size++;
		
		if(size > MAX_HISTORY_LOGS) {
			removeLast();
		}
	}

	/**
	* Clears entire task list and documents the action
	*/
	public void clear() {
		
		head = tail = null;
		String message = "Task History and List cleared";
		DoubleNode newEntry = new DoubleNode(message, null, null);
		
		head = tail = newEntry;		
		size = 1;		
	}
	
	/**
	* Inserts a new task history log when a task is updated or changed
	*
	* @param fieldName string name of task quality that was changed
	* @param ID number ID of task
	* @param taskName string name of task
	*/
	public void taskUpdate(String fieldName, int ID, String taskName) {
		
		String message = "Task " + ID + ", '" + taskName + "' had its " + fieldName + " updated";
		DoubleNode newEntry = new DoubleNode(message, null, head);
		
		if(isEmpty()) {
			head = tail = newEntry;
		} else {
			head.setPrev(newEntry);
			head = newEntry;
		}
		
		size++;
		
		if(size > MAX_HISTORY_LOGS) {
			removeLast();
		}
	}
	
	/**
	* Deletes the oldest history entry if max capacity has been reached
	*/
	public void removeLast() {
		
		if(isEmpty()) {
			return;
		}
		
		tail = tail.getPrev();

		if(tail != null) {
			tail.setNext(null);
		} else {
			head = null;
		}

		size--;
	}
}

// END OF DoublyLinkedList.java