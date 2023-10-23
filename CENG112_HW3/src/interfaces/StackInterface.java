package interfaces;

public interface StackInterface<T> {

	public void push(T newEntry);
	
	public T peek();
	
	public T pop();
	
	public boolean isEmpty();
	
	public void clear();
}
