/**
* @author Ayra Babar
* @file BinarySearchTree.java
* @brief This file contains the implementation of a binary search tree with the use of 
*        tree node objects
* 
* 06/28/2025 - Initial creation & complete implementation of all methods
*/

public class BinarySearchTree {

	private TreeNode root;
	
	/**
    * Constructs a new Binary Search Tree with default values
    */
	public BinarySearchTree() {
		this.root = null;
	}
	
	/**
    * Checks if BST is empty or not
    *
    * @return true if the root is null (BST is empty), false if otherwise
    */
	public boolean isEmpty() {
		return (this.root == null);
	}
	
	/**
    * Adds a new task to BST
    * 
    * @param newTask the new task object to add to tree
    */	
	public void add(Task newTask) {
		this.root = addHelper(this.root, newTask);
	}
	
	/**
    * Recursive helper method for task addition
    * 
    * @param root the root of the entire task tree to add to
    * @param newTask the new task object to add
    *
    * @return the root TreeNode
    */
	private TreeNode addHelper(TreeNode root, Task newTask) {
		
		if(root == null) {
			root = new TreeNode(newTask);
			return root;
		}
		
		if(newTask.getID() < root.getTask().getID()) {
			root.setLeft(addHelper(root.getLeft(), newTask));
			
		} else if(newTask.getID() > root.getTask().getID()) {
			root.setRight(addHelper(root.getRight(), newTask));
			
		}
		
		return root;
	}
	
	/**
    * Searches for a specific task based on ID value
    * 
    * @param searchID the integer ID to find
    * 
    * @return the task object if found, null if it doesn't exist
    */
	public Task search(int searchID) {
		return searchHelper(this.root, searchID);
	}
	
	/**
    * Recursive helper method for task search based on ID
    * 
    * @param root the root of the entire task tree
    * @param searchID the integer ID to find
    *
    * @return the task object if found, null if it doesn't exist
    */
	private Task searchHelper(TreeNode root, int searchID) {
		
	    while (root != null) {
	        
	    	if (root.getTask().getID() == searchID) {
	    		System.out.println("Task Found successfully!");
	            return root.getTask();  
	            
	        } else if (root.getTask().getID() > searchID) {
	        	root = root.getLeft(); 
	        	
	        } else {
	        	root = root.getRight();  
	        }
	    }
	    return null; 
	}
	
	/**
    * Deletes a task from tree
    *
    * @param taskToDelete the task that needs to be deleted
    */
	public void delete(Task taskToDelete) {
		
		int deleteID = taskToDelete.getID();
		if(root == null) {
			return;
		}

		TreeNode current = root;
		TreeNode parent = null;

		while (current != null) {
			if (deleteID == current.getTask().getID()) {
				break;

			} else if(deleteID < current.getTask().getID()) {
				parent = current;
				current = current.getLeft();

			} else {
				parent = current;
				current = current.getRight();
			}
		}

		// Case 1: If the node to delete is a leaf node
		if ((current.getRight() == null) && (current.getLeft() == null)) {
			if (current == root) return;

			if(parent.getRight() == current) {
				parent.setRight(null);
			} else {
				parent.setLeft(null);
			}

			return;
		}

		// Case 2: If the node to delete has one child
		if((current.getRight() == null) || (current.getLeft() == null)) {

			TreeNode child = (current.getLeft() == null) ? current.getRight() : current.getLeft();

			if(current == root) {
				root = child;
			} else if (parent.getRight() == current) {
				parent.setRight(child);
			} else {
				parent.setLeft(child);
			}
			
			return;
		}

		// Case 3: If the node to delete has two children
		TreeNode parentOfSuccessor = current;
		TreeNode successor = current.getRight();

		// Find the in-order successor (smallest in the right subtree)
		while (successor.getLeft() != null) {
		    parentOfSuccessor = successor;
		    successor = successor.getLeft();
		}

		// Replace current's task with successor's task
		current.setTask(successor.getTask());

		// Remove the successor node
		if (successor.getRight() == null) { // Successor has no children
		    if (successor != current.getRight()) {
		        parentOfSuccessor.setLeft(null);
		    } else {
		        current.setRight(null);
		    }
		} else if (successor == current.getRight()) {	// Successor is current's immediate right child
		    current.setRight(successor.getRight());
		} else {
		    parentOfSuccessor.setLeft(successor.getRight());	// Successor has right child
		}
	}
	
	/**
    * Prints tasks in a range from a beginning date to an end date
    * 
    * @param low due date
    * @param high due date
    */
	public void printRange(int low, int high) {
		
		int yearL = low / 10000;
		int monthL = (low % 10000) / 100;
		int dayL = low % 100;
		
		int yearH = high / 10000;
		int monthH = (high % 10000) / 100;
		int dayH = high % 100;
		
	    System.out.println("Printing Range from " + yearL + "-" +
	    monthL + "-" + dayL + " to " + yearH + "-" + monthH + "-" + dayH);
	    printRangeHelper(root, low, high);
	}

	/**
    * Prints tasks in a range from a beginning date to an end date
    * 
    * @param node is the root node of the entire task BST
    * @param low due date
    * @param high due date
    */
	private void printRangeHelper(TreeNode node, int low, int high) {
	    
		if (node == null) return;
	    
	    printRangeHelper(node.getLeft(), low, high);
	    
	    int due = node.getTask().getDueDate();
	    if(due >= low && due <= high) {
	    	node.getTask().showOneTask();
	    }
	    
	    printRangeHelper(node.getRight(), low, high);
	}
	
	/**
    * Clears the entire task BST
    */
	public void clear() {
		
		if(root == null) {
			return;
		} else {
			root = null;
		}
	}
	
	/**
    * Prints tasks from task BST in ascending order
    */
	public void inorder() {
		System.out.println("In-Order Traversal: ");
		inorderHelper(this.root);
		System.out.println();
	}
	
	/**
    * Helper function to print tasks in ascending order
    *
    * @param root to the task BST
    */
	private void inorderHelper(TreeNode root) {
		
		if(root == null) {
			return;
		}
		
		inorderHelper(root.getLeft());
		root.getTask().showOneTask();
		inorderHelper(root.getRight());
	}
}

// END OF BinarySearchTree.java