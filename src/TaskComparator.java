import java.util.Comparator;

/**
* @author Ayra Babar
* @file TaskComparator.java
* @brief Comparator for task objects, prioritizing due date and then creation time
* 
* 06/29/2025 - Initial creation & complete implementation of all methods
* 08/12/2025 - Method header comments added
*/

public class TaskComparator implements Comparator<Task> {
    
	/**
	* Compares two task objects based on inserted due date, then inserted priority value, 
	* and lastly by time-stamp at which the tasks were created
	* 
	* @param a first task object for comparison
	* @param b second task object
	* 
	* @return integer value indicating if task a comes before or after task b
	*/
	@Override
    public int compare(Task a, Task b) {
        if (a.getDueDate() != b.getDueDate()) {
            return Integer.compare(a.getDueDate(), b.getDueDate());
        }
        
        if(a.getPriority() != b.getPriority()) {
            return Integer.compare(a.getPriority(), b.getPriority());
        }
    
        return a.getCreatedAt().compareTo(b.getCreatedAt());
    }
}

// END OF TaskComparator.java