/**
* @author Ayra Babar
* @file TreeNode.java
* @brief This file contains binary search tree node structure implementations
* 
* 06/28/2025 - Initial creation & complete implementation of all methods
*/

public class TreeNode {
	
	private Task task;
	private TreeNode left, right;
	
	 /**
     * Constructs a new TreeNode with given task data and default links
     *
     * @param task the task object to add to BST
     */
	public TreeNode(Task task) {
		this.task = task;
		this.left = null;
		this.right = null;
	}
	
	/**
	* Returns the task object
	*
	* @return the task object
	*/
	public Task getTask() {
		return this.task;
	}
	
	/**
	* Sets the task object of the TreeNode
	* 
	* @param newTask the task object to set in the tree node
	*/
	public void setTask(Task newTask) {
		this.task = newTask;
	}
	
	/**
	* Returns the right child of the node
	*
	* @return the right TreeNode, or null if none
	*/
	public TreeNode getRight() {
		return right;
	}
	
	/**
	* Sets the right child of the TreeNode
	* 
	* @param newRight the TreeNode that will become the right child 
	*/
	public void setRight(TreeNode newRight) {
		this.right = newRight;
	}
	
	/**
	* Returns the left child of the node
	*
	* @return the left TreeNode, or null if none
	*/
	public TreeNode getLeft() {
		return left;
	}
	
	/**
	* Sets the left child of the TreeNode
	* 
	* @param newLeft the TreeNode that will become the left child 
	*/
	public void setLeft(TreeNode newLeft) {
		this.left = newLeft;
	}
}

// END OF TreeNode.java