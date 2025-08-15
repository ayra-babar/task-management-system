/**
* @author Ayra Babar
* @file TaskManagerProjectExecution.java
* @brief This file runs the task manager
* 
* 06/29/2025 - Initial creation & complete implementation of all methods
*/

public class TaskManagerProjectExecution {

	public static void main(String[] args) {
		
		// Create new task manager object in order to access task manager itself
		TaskManager manager = new TaskManager();	
		
		// Run the personal planner program
		manager.menuExecution();
		
	}
}