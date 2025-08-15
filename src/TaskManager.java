import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
* @author Ayra Babar
* @file TaskManager.java
* @brief This file contains the implementation of a Task Manager using a doubly linked list for history,
*        a binary search tree for fast & easy task lookup and query based ranges, and a priority queue to
*        sort tasks based on date and/or user input priority
*        
* 06/29/2025 - Initial creation & complete implementation of all methods
* 07/02/2025 - Changes and updates to methods for edge case verification
* 08/13/2025 - Final changes made
*/

public class TaskManager {
	
	private PriorityQueue<Task> taskQueue;
    private DoublyLinkedList historyLog;
    private BinarySearchTree taskTree;

    /**
    * Constructs a Task Manager with an empty history log, task priority queue, and task BST
    */
    public TaskManager() {
        taskQueue = new PriorityQueue<>(new TaskComparator());
        historyLog = new DoublyLinkedList();
        taskTree = new BinarySearchTree();
    }

    /**
    * Displays Task Manager Menu Options
    */
    public void displayMenu() {
		System.out.println("\n------------------------ Task Menu ------------------------");
		System.out.println("0. Add New Task");
		System.out.println("1. Search for Task");
		System.out.println("2. Delete Task");
		System.out.println("3. Edit Task");
		System.out.println("4. Clear Task List");
		System.out.println("5. View All Tasks (by Priority)");
		System.out.println("6. View Task by Date Range");
		System.out.println("7. View Task History Log (Latest to Oldest)");
		System.out.println("8. View Task History Log (Oldest to Latest)");
		System.out.println("9. View a Specific Task History Log");
		System.out.println("10. Exit Program");
		System.out.println("------------------------------------------------------------");
	}
    
	Scanner input = new Scanner(System.in);
	
	/**
    * Executes task menu for user 
    * 
    * @param manager TaskManager object to pass into
    */
    public void menuExecution() {

    	int choice = -1;
    	
    do {
    	
		displayMenu();

    	System.out.println("\nEnter your choice (0-10): ");
		if(!input.hasNextInt()) {
			System.out.println("Invalid input. Enter a number from 0 to 10: ");
			input.next();
			continue;
		}
		
		choice = input.nextInt();
		input.nextLine();
		
    	switch(choice) {
    	
    	case 0:
    		addTask();
    		break;
    		
    	case 1:
        	System.out.print("Enter ID value to search: ");
    		Task found = search();
    		found.showOneTask();
    		break;
    		
    	case 2:
    	    deleteTask();
    	    break;

    	case 3:
    	    editTask();
    	    break;

    	case 4:
    	    clear();
    	    break;

    	case 5:
    		printByPriority();
    		break;
    		
    	case 6:
    		range();
    	    break;

    	case 7:
    	    historyLog.printInOrderHistory();
    		break;
    	
    	case 8:
    	    historyLog.printReverseHistory();
    		break;
    		
    	case 9:
    		printOneLog();
    		break;
    	
    	case 10:
    		break;
    		
    	default:
    		System.out.println("Invalid choice, please try again");
    	}
		
    } while(choice != 10);
    
    System.out.println("Exiting...");
    input.close();
    
    }
    
    /**
	* Creates a new task by taking user input and inserts into BST, P.Q., and DLL history log
	*/
    public void addTask() {
    	
		System.out.print("Enter Task Name: ");
		String name = input.nextLine();
		
		while(name.isBlank()) {
			System.out.print("Invalid input. Please enter a task name: ");
		    name = input.nextLine();
			System.out.println();
		}
		
		System.out.println();
		
		System.out.print("Enter Task Description: ");
		String description = input.nextLine();
		
		while(description.isBlank()) {
			System.out.print("Invalid input. Please enter a task description: ");
		    description = input.nextLine();
			System.out.println();
		}
		
		System.out.println();
		
		System.out.print("Enter Task Priority (1-5): ");
		int priorityNum = input.nextInt();
	    input.nextLine();

		priorityNum = readValidPriority(priorityNum);
		
		System.out.println();

		int dueDate = 0;
		dueDate = readValidDueDate(dueDate);

		Task task = new Task(name, description, priorityNum, dueDate);
		int ID = task.getID();
		
		taskTree.add(task);
		historyLog.insertion(ID, name);
		taskQueue.add(task);
		
		System.out.println("\nTask added successfully!");
		task.showOneTask();
    }
    
    /**
    * Searches for a specific task from task BST
    * 
    * @return returns the task object if found, otherwise null
  	*/
    public Task search() {
    			
		while (!input.hasNextInt()) {
		    System.out.print("Invalid input. Enter a numeric ID: ");
		    input.next();
		}
		
		int searchID = input.nextInt(); input.nextLine();
		
		Task found = taskTree.search(searchID);
		
		while (found == null) {
			
		    System.out.print("Invalid ID entry. Insert again: ");
		    
		    while (!input.hasNextInt()) {
			    System.out.print("Invalid input. Enter a numeric ID: ");
			    input.next();
			}
		    
		    searchID = input.nextInt(); input.nextLine();
		    System.out.println();
		    found = taskTree.search(searchID);
		}
		
		return found;
    }
    
    /**
    * Deletes task from BST & P.Q. and logs the activity
  	*/
    public void deleteTask() {
    	
    	System.out.print("Enter ID value to delete: ");
		
		Task taskToDelete = search();
    	
		historyLog.deletion(taskToDelete);
		taskTree.delete(taskToDelete);
    	taskQueue.remove(taskToDelete);
		System.out.println("Task deleted successfully!");
    }
    
    /**
    * Allows user to make edits to existing tasks
  	*/
    public void editTask() {
    	
    	System.out.print("Enter a valid ID in order to make edits: ");
    	Task taskToEdit = search();
    	System.out.println("What would you like to edit?");
    	System.out.println("Enter 0 to edit Task Name");
    	System.out.println("Enter 1 to edit Description");
    	System.out.println("Enter 2 to edit Due Date");
    	System.out.println("Enter 3 to edit Priority");
    	
    	String fieldChanged = null;

    	int choice = input.nextInt();
    	input.nextLine();
    	
    	switch(choice) {
    	
    	case 0:
    		System.out.print("Enter new task name: ");
    		String newName = input.nextLine();
    		
    		while(newName == null || newName.isBlank()) {
    			System.out.print("Enter a valid name: ");
    			newName = input.nextLine();
    		}
    		
    		taskToEdit.setName(newName);
    		fieldChanged = "Task Name";
    		
    		break;
    		
    	case 1:
    		System.out.print("Enter new task description: ");
    		String newDescription = input.nextLine();
    		
    		while(newDescription == null || newDescription.isBlank()) {
    			System.out.print("Enter a valid description: ");
    			newDescription = input.nextLine();
    		}
    		
    		taskToEdit.setDescription(newDescription);
    		fieldChanged = "Description";
    		
    		break;
    		
    	case 2:
    		int newDueDate = readValidDueDate(0);    		
    		taskQueue.remove(taskToEdit);
    		taskToEdit.setDueDate(newDueDate);
    		taskQueue.add(taskToEdit);
    		fieldChanged = "Due Date";
    		
    		break;
    		
    	case 3:
    		System.out.print("Enter new task priority (1-5 inclusive): ");
    		int newPriority = input.nextInt(); input.nextLine();
    		
    		newPriority = readValidPriority(newPriority);
    		
    		taskQueue.remove(taskToEdit);
    		taskToEdit.setPriority(newPriority);
    		taskQueue.add(taskToEdit);
    		fieldChanged = "Task Priority";
    		
    		break;
    		
    	default:
    		System.out.println("Invalid Choice Entry");
    		return;
    	}
    	
    	historyLog.taskUpdate(fieldChanged, taskToEdit.getID(), taskToEdit.getName());
    } 
    
    /**
    * Validates if user input for due date is within boundaries
    * 
    * @param newDueDate integer value input by the user initially
    * 
    * @return int value of approved due date
    */
    private int readValidDueDate(int newDueDate) {
    	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    	while (true) {
		    System.out.print("Enter Due Date (YYYYMMDD): ");
		    
		    String dateInput = input.nextLine().trim();

		    if (dateInput.length() != 8 || !dateInput.matches("\\d{8}$")) {
		        System.out.println("Please enter exactly 8 numeric digits in YYYYMMDD format.");
		        continue;
		    }

		    try {
		        LocalDate.parse(dateInput, formatter);
		        newDueDate = Integer.parseInt(dateInput);
		        break;
		        
		    } catch (DateTimeParseException e) {
		        System.out.println("That date is invalid. Try again.");
		    }
		}
    	
    	return newDueDate;
    }
    
    /**
    * Validates if user input for priority is within boundaries
    * 
    * @param newPriority integer value input by the user initially
    * 
    * @return int value of approved priority number
    */
    private int readValidPriority(int newPriority) {
    	
    	while (newPriority < 1 || newPriority > 5) {
		    System.out.print("Invalid input. Please enter a number between 1 and 5 (inclusive): ");
		    newPriority = input.nextInt();
		    input.nextLine();
		}
    	
    	return newPriority;
    }
    
    /**
    * Clears the task list in P.Q. & BST and logs the activity
  	*/
    public void clear() {
    	taskTree.clear();
    	historyLog.clear();
    	taskQueue.clear();
    	System.out.println("Entire Task List cleared");
    }
    
    /**
    * Prints the task list by priority (1st by due date, 2nd by manual priority, 
    * 3rd by time-stamp at which task was created)
   	*/
    public void printByPriority() {
    	
    	if(taskQueue.isEmpty()) {
    		System.out.println("No Tasks in the list");
    		return;
    	}
    	
    	System.out.println("\n--- Tasks Sorted by Priority ---");
    	System.out.println("Order: Earliest due date → Lowest priority number → Earliest created");
    	System.out.println("(Priority scale: 1 = Highest priority, 5 = Lowest priority)\n");
    	PriorityQueue<Task> copy = new PriorityQueue<>(taskQueue);
    	while(!copy.isEmpty()) {
    		copy.poll().showOneTask();
    	}
    }
     
    /**
    * Print tasks in a range from a lower date to a higher date
  	*/
    public void range() {
    	
    	int low, high;
    	
    	while(true) {
    		System.out.print("Enter a beginning date (format: YYYYMMDD): ");
    		low = readValidDueDate(0);
    	
    		System.out.print("Enter an end date (format: YYYYMMDD): ");
    		high = readValidDueDate(0);
    	
    		if(low > high) {
    			System.out.println("Lower due date cannot be bigger than higher due date. Try again.");
    			continue;
    		}
    		break;
    	}
    	
    	taskTree.printRange(low, high);
    } 
    
    /**
    * Prints a specific log in history 
  	*/
     public void printOneLog() {
     	
     	if (historyLog.getSize() == 0) {
             System.out.println("Nothing in Task History Log");
             return;
         }
     	
         System.out.print("Enter an integer value from 1 to " + historyLog.getSize() + ": ");
         int logValue = input.nextInt();

         while (logValue < 1 || logValue > historyLog.getSize()) {
             System.out.print("Invalid input. Please enter a number between 1 and " + historyLog.getSize() + " (inclusive): ");
             logValue = input.nextInt();
         }

         historyLog.printSpecificHistoryLog(logValue);
     } 
}

// END OF TaskManager.java