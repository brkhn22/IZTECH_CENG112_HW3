package implementations;

import java.util.Arrays;
import java.util.EmptyStackException;

import interfaces.StackInterface;

public final class ArrayStack<T> implements StackInterface<T> {

	private T[] stack;
	private int topIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		initialized = true;
	}
	
	public void checkInitialization() {
		if(!initialized) throw new ExceptionInInitializerError("Initialization has not yet been done.");
	}
	
	public void push(T newEntry) {
		checkInitialization();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}
	
	private void ensureCapacity() {
		if (topIndex == stack.length - 1) {
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}
	
	public T peek() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else 
			return stack[topIndex];
	}
	
	public T pop() {
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}
	
	public boolean isEmpty() {
		return (topIndex == -1);
	}
	public void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY)
			throw new OutOfMemoryError();
		
	}

	@Override
	public void clear() {
		for(int i = 0; i <= topIndex; i++) 
			stack[i] = null;
		
		topIndex = -1;
	}
}
