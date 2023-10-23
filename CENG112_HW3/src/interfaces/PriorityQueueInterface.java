package interfaces;

// Comparable represents list of T or its super types such as Object.
// PriorityQueue represents a list of Comparable or its sub types.
public interface PriorityQueueInterface <T extends Comparable<? super T>> {
	
	/* Adds a new entry to this priority queue.
	 * @param newEntry an object to be added.
	 */
	public void add(T newEntry);
	
	/* 
	 * Removes  and returns the entry having the highest priority.
	 * @return Either the object having the highest priority, or if the
	 * priority queue is empty before the operation, null.
	 */
	public T dequeue();
	
	/*
	 * Retrieves the entry having the highest priority.
	 * @return Either the object having the highest priority, or if the
	 * priority queue is empty, null.
	 */
	public T peek();
	
	/*
	 * Detects whether this priority queue is empty or not.
	 * @return True if this priority queue is empty, False if this queue has any objects.
	 */
	public boolean isEmpty();
	
	/*
	 * Gets the size of this priority queue.
	 * @return The number of entries in this priority queue.
	 */
	public int getSize();
	
	/*
	 * Removes all entries from this priority queue.
	 */
	public void clear();
	
	/*
	 * Gets entry in given position
	 * @return the entry that is located in givenPosition.
	 * @param givenPosition that is the entry where it is located.
	 * @throw IndexOutOfBound if givenPosition is out of boundries of this queue.
	 */
	public T getEntry(int givenPosition);
}

