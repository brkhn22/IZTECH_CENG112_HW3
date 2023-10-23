package implementations;

import interfaces.ListInterface;

public class LinkedList<T> implements ListInterface<T> {

	
	private int numberOfEntries;
	private Node firstNode;
	private Node lastNode;
	
	public LinkedList() {
		initializeDataFields();
	}
	
	private Node getNodeAt(int givenPosition) {
		assert (firstNode != null) && (givenPosition >= 1) && (givenPosition<=numberOfEntries);
		Node currentNode = firstNode;
		for(int i = 1; i < givenPosition;i++) 
			currentNode = currentNode.getNextNode();
		assert currentNode != null;
		
		return currentNode;
	}
	private void initializeDataFields(){
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}
	
	@Override
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if(isEmpty()) {
			firstNode = newNode;
		}else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
		numberOfEntries++;
	}

	@Override
	public void add(int givenPosition, T newEntry) {
		if(givenPosition >=1 && (givenPosition<=numberOfEntries+1)) {
			Node newNode = new Node(newEntry);
			if(isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			}
			else if(givenPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}else if (givenPosition == numberOfEntries +1) {
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition-1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}

	@Override
	public T remove(int givenPosition) {
		T result = null;
		
		if(givenPosition >= 1 && givenPosition<=numberOfEntries) {
			assert !isEmpty();
			if(givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
				if(numberOfEntries == 1)
					lastNode = null;
			}else {
				Node nodeBefore = getNodeAt(givenPosition-1);
				Node nodeToRemove = getNodeAt(givenPosition);
				result = nodeToRemove.getData();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				if(givenPosition == numberOfEntries)
					lastNode = nodeBefore;
			}
			numberOfEntries--;
			return result;
		}else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if(givenPosition >= 1 && givenPosition<=numberOfEntries) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalData = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalData;
		}else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}

	@Override
	public void clear() {
		initializeDataFields();
		
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >= 1 && givenPosition <= numberOfEntries) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T desiredEntry = desiredNode.getData();
			return desiredEntry;
		}else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T result[] = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while(currentNode != null && index < numberOfEntries) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}

	@Override
	public boolean contains(T entry) {
		boolean result = false;
		Node currentNode = firstNode;
		while(currentNode != null && !result) {
			if(entry.equals(currentNode.getData()))
				result = true;
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		boolean result;
		if(numberOfEntries == 0) {
			assert firstNode == null;
			result = true;
		}else {
			assert firstNode != null;
			result = false;
		}
		return result;
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
