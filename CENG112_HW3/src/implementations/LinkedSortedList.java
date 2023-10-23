package implementations;

import interfaces.ListInterface;
import interfaces.SortedListInterface;
import interfaces.Comparable;

public class LinkedSortedList <T extends Comparable<? super T>>
implements SortedListInterface<T> {

	private ListInterface<T> list;
	
	public LinkedSortedList() {
		list = new LinkedList<>();
	}
	
	@Override
	public void add(T newEntry) {
		int newPosition = Math.abs(getPosition(newEntry));
		list.add(newPosition, newEntry);
		
	}
	
	@Override
	public int getPosition(T anEntry) {
		int position = 1;
		int length = list.getLength();
		while(position <= length && anEntry.compareTo(list.getEntry(position)) > 0) {
			position++;
		}
		
		if(position > length || anEntry.compareTo(list.getEntry(position)) != 0)
			position = -position;
		
		return position;
	}

	@Override
	public T getEntry(int givenPosition) {
		return list.getEntry(givenPosition);
	}

	@Override
	public boolean contains(T anEntry) {
		return list.contains(anEntry);
	}

	@Override
	public T remove(int givenPosition) {
		return list.remove(givenPosition);
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public int getLength() {
		return list.getLength();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public T[] toArray() {
		return list.toArray();
	}

}
