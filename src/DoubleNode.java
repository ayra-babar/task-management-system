/**
* @author Ayra Babar
* @file DoubleNode.java
* @brief This file contains doubly linked list node structure implementations
*
* 06/28/2025 - Initial creation & complete implementation of all methods
*/

public class DoubleNode {

	private String historyEntry;
	private DoubleNode next;
	private DoubleNode prev;
	
	/**
    * Constructs a new DoubleNode with given data and links
    *
    * @param historyEntry the string message for this node
    * @param prev the previous node in the list
    * @param next the next node in the list
    */
	public DoubleNode(String historyEntry, DoubleNode prev, DoubleNode next) {
		this.historyEntry = historyEntry;
		this.prev = prev;
		this.next = next;
	}
	
	/**
    * Constructs an empty DoubleNode with default values.
    */
	public DoubleNode() {
		this("", null, null);
	}
	
	/**
	* Returns the history entry message stored in this node
	*
	* @return the string message of this node
	*/
	public String getHistoryEntry() {
		return this.historyEntry;
	}
	
	/**
	* Returns the next node in the doubly linked list
	*
	* @return the next DoubleNode, or null if none
	*/
	public DoubleNode getNext() {
		return this.next;
	}
	
	/**
	* Returns the previous node in the doubly linked list
	*
	* @return the previous DoubleNode, or null if none
	*/
	public DoubleNode getPrev() {
		return this.prev;
	}
	
	/**
	* Updates the history entry of this node
	*
	* @param newHistoryEntry the new history entry to store
	*/
	public void setHistoryEntry(String newHistoryEntry) {
		this.historyEntry = newHistoryEntry;
	}
	
	/**
	* Sets the next node reference
	*
	* @param newNext the node to link as next
	*/
	public void setNext(DoubleNode newNext) {
		this.next = newNext;
	}
	
	/**
	* Sets the previous node reference
	*
	* @param newPrev the node to link as previous
	*/
	public void setPrev(DoubleNode newPrev) {
		this.prev = newPrev;
	}
}

// END OF DoubleNode.java