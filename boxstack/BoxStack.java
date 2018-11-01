package boxstack;

import java.util.EmptyStackException;

public class BoxStack<T> {
	private Box<T> head;
	private int count;
	
	public BoxStack() {
		count = 0;
	}
	
	public void push(T x, T y) {
		head = new Box(x, y, head);
	}
	
	public T peek() {
		if(head == null)
			throw new EmptyStackException();

		return head;
	}
	
	public T pop() {
		if(head == null)
			throw new EmptyStackException();
		
		T top = head;
		
		head = head.getLink();
		
		return top;
	}
}
