package data;

public class BoxQueue implements Cloneable{
		private Box head;
		private Box tail;
		private int count;
			
		public BoxQueue() {
			count = 0;
			head = tail = null;
		}
		
		public void add(Box element) {
			if(tail == null)
				head = tail = element;
			else {
				tail.setLink(element);
				tail = element;
			}
			element.setLink(null);
			count++;
		}
		
		public void add(int x, int y) {
			Box element = new Box(x, y, null);
			if(tail == null)
				head = tail = element;
			else {
				tail.setLink(element);
				tail = element;
			}
			count++;

		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			BoxQueue cloneQueue = new BoxQueue();
			for(Box cursor = head; cursor != null; cursor = cursor.getLink()) {
				cloneQueue.add((Box)cursor.clone());
			}
			return cloneQueue;
		}
		
		public Box top() {
			Box temp = head;
			remove();
			return temp;
		}
		
		public void remove() {
			if(head == null)
				return;
			
			Box temp = head;
			head = temp.getLink();
			temp = null;
			count--;
			
			if(count == 0) head = tail = null;
		}
		
		public void clear() {
			while(head != null)
				remove();
		}

		public Box[] getArray() {
			Box[] boxList = new Box[this.count];
			Box cursor;
			int index = 0;
			for(cursor = head;cursor!=null;cursor = cursor.getLink(), index++) {
				boxList[index] = cursor;
			}
			return boxList;
		}
		
/*		public Box getHead() {
			return head;
		}

		public void setHead(Box head) {
			this.head = head;
		}
*/
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
