public class DLNode<T> {

	private DLNode<T> front;
	private DLNode<T> rear;
	private T element;

    public DLNode () {
        rear = null;
        element = null;
    }

    public DLNode (T elem) {
        rear = null;
        element = elem;
    }
    
    public DLNode<T> getFront () {
        return front;
    }
    
    public void setFront (DLNode<T> node) {
        front = node;
    }

    public DLNode<T> getRear () {
        return rear;
    }
    
    public void setRear (DLNode<T> node) {
        rear = node;
    }

    public T getElement () {
        return element;
    }

    public void setElement (T elem) {
        element = elem;
    }

}
