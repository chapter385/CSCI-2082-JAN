package data;

public class BoxSequence implements Cloneable{
	private Box head;
	private int count;
		
	public BoxSequence() {
		count = 0;
	}
	
	public void add(Box element) {
		element.setLink(head);
		head = element;
		count++;
	}
	
	public void add(int x, int y) {
		head = new Box(x, y, head);
		count++;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		BoxSequence clone = new BoxSequence();
		for(Box cursor = head; cursor != null; cursor = cursor.getLink() )
			clone.add((Box)cursor.clone());
		return clone;
	}

	public void remove() {
		Box temp = head;
		head = temp.getLink();
		temp = null;
	}
	
	public void clear() {
		while(head != null)
			remove();
	}
	
	public Box getHead() {
		return head;
	}

	public void setHead(Box head) {
		this.head = head;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}