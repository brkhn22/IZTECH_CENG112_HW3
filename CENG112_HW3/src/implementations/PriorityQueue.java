package implementations;

import interfaces.Comparable;
import interfaces.PriorityQueueInterface;

public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	
	private Node firstNode;
	private int numberOfEntries;
	
	public PriorityQueue() {
		this.firstNode = null;
		this.numberOfEntries = 0;
	}
	
	
	private Node getNodeBefore(T newEntry) {
		if(isEmpty())
			return null;
		else {
			Node currentNode = firstNode;
			Node nodeBefore = null;
			while((currentNode != null) && (newEntry.compareTo(currentNode.getData()) > 0)) {
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			}
			return nodeBefore;
		}
	}
	
	@Override
	public void add(T newEntry) { // OutOfMemoryError possible!!
		Node newNode = new Node(newEntry);
		Node before = getNodeBefore(newEntry);
		if(isEmpty() || before == null) {
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		}else {
			newNode.setNextNode(before.getNextNode());
			before.setNextNode(newNode);
		}
		numberOfEntries++;
	}

	@Override
	public T dequeue() {
		if(!isEmpty()) {
			T firstData = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			return firstData;
		}else
			throw new EmptyQueueException();
	}

	@Override
	public T peek() {
		if(!isEmpty()) {
			T firstData = firstNode.getData();
			return firstData;
		}else
			throw new EmptyQueueException();
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}
	
	@Override
	public void clear() {
		this.firstNode = null;
	}
	
	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T desiredEntry = desiredNode.getData();
			return desiredEntry;
		}else
			throw new IndexOutOfBoundsException("Illegal position is given.");
	}
	
	private Node getNodeAt(int givenPosition) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			Node currentNode = firstNode;
			int position = 1;
			while(currentNode != null && position < givenPosition) {
				currentNode = currentNode.getNextNode();
				position++;
			}
			return currentNode;
		}else
			throw new IndexOutOfBoundsException("Illegal position is given.");
	}
	
	private class Node {
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}
		
		private Node(T dataPortion, Node next) {
			this.data = dataPortion;
			this.next = next;
		}
		public T getData() {
			return this.data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node getNextNode() {
			return this.next;
		}
		public void setNextNode(Node next) {
			this.next = next;
		}
	}
}
