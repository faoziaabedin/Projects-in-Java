public class ArrayStack<T> implements StackADT<T> {

	private T[] array;
	private int top;
	
	public ArrayStack() {
		array = (T[])(new Object[10]);
		top = -1;
	}

	public void push(T element) {
		// Check if the capacity should be expanded, and if so, expand it!
		expandCapacity();
		
		array[top+1] = element;
		top++;
	}

	public T pop() throws CollectionException {
		if (isEmpty()) {
			throw new CollectionException("Stack is empty");
		}
		// Check if the capacity should be shrunk, and if so, shrink it!
		shrinkCapacity();
		
		T result = array[top];
		
		array[top] = null;
		top--;
		
		return result;
	}

	public T peek() throws CollectionException {
		if (isEmpty()) {
			throw new CollectionException("Stack is empty");
		}
		T result = array[top];
		return result;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return top + 1;
	}
	
	public int getCapacity() {
		return array.length;
	}
	
	public int getTop() {
		return top;
	}

	public void clear() {
		array = (T[])(new Object[10]);
		top = -1;
	}
	
	public String toString () {
		
		if (isEmpty()) {
			return "Empty stack.";
		}
		
		String str = "Stack: ";
		
		for (int i = top; i >= 0; i--) {
			str += array[i];
			if (i > 0) {
				str += ", ";
			} else {
				str += ".";
			}
		}
		
		return str;
	}

	private void expandCapacity () {
		
		double pc = (double)size() / (double)array.length;
		
		// If 75% or more of the array is used, add 10 more spaces. 
		if (pc >= 0.75) {

			T[] newArr = (T[])(new Object[array.length + 10]);
		
			for (int i = 0; i < array.length; i++) {
				newArr[i] = array[i];
			}

			array = newArr;
		}
	}
	
	private void shrinkCapacity () {
		
		double pc = (double)size() / (double)array.length;
		
		// If 25% or less of the array is used and the length is 20 or greater, remove 10 spaces. 
		if (pc <= 0.25 && array.length >= 20) {
		
			T[] newArr = (T[])(new Object[array.length - 10]);
		
			for (int i = 0; i < newArr.length; i++) {
				newArr[i] = array[i];
			}

			array = newArr;
		}
	}

}
