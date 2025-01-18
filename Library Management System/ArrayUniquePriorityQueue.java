public class ArrayUniquePriorityQueue<T> {
    private T[] queue;
    private double[] priority;
    private int count;

    @SuppressWarnings("unchecked")
    public ArrayUniquePriorityQueue() {
        queue = (T[]) new Object[10];
        priority = new double[10];
        count = 0;
    }

    public void add(T data, double prio) {
        if (contains(data)) {
            return;
        }
    
        if (count == queue.length) {
            expandCapacity();
        }
    
        int i;
        for (i = count - 1; i >= 0 && prio < priority[i]; i--) {
            queue[i + 1] = queue[i];
            priority[i + 1] = priority[i];
        }
        queue[i + 1] = data;
        priority[i + 1] = prio;
        count++;
    }
    private void expandCapacity() {
        int newSize = queue.length + 5; 
        T[] newQueue = (T[]) new Object[newSize];
        double[] newPriority = new double[newSize];
    
        for (int i = 0; i < count; i++) {
            newQueue[i] = queue[i];
            newPriority[i] = priority[i];
        }
    
        queue = newQueue;
        priority = newPriority;
    }



    public boolean contains(T data) {
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    public T peek() throws CollectionException {
        if (count == 0) {
            throw new CollectionException("PQ is empty");
        }
        int minIndex = findMinIndex();
        return queue[minIndex];
    }

    public T removeMin() throws CollectionException {
        if (count == 0) {
            throw new CollectionException("PQ is empty");
        }
        int minIndex = findMinIndex();
        T minElement = queue[minIndex];
        removeAt(minIndex);
        return minElement;
    }

    public void updatePriority(T data, double newPrio) throws CollectionException {
        int itemIndex = -1;
        for (int i = 0; i < count; i++) {
            if (queue[i].equals(data)) {
                itemIndex = i;
                break;
            }
        }
    
        // If the item is not found, throw an exception
        if (itemIndex == -1) {
            throw new CollectionException("Item not found in PQ");
        }
    
        // Since the item is found, update its priority by removing and re-adding it
        // This could be optimized but follows the hint for simplicity
        removeAt(itemIndex); // Remove the item at the found index
        add(data, newPrio); // Re-add the item with the new priority
    }
        

    private void removeAt(int index) {
        System.arraycopy(queue, index + 1, queue, index, count - index - 1);
        System.arraycopy(priority, index + 1, priority, index, count - index - 1);
        count--;
    }

    private int findMinIndex() {
        int minIndex = 0;
        for (int i = 1; i < count; i++) {
            if (priority[i] < priority[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public int getLength() {
        return queue.length;
    }

    public String toString() {
        if (isEmpty()) {
            return "The PQ is empty";
        }
    
        String result = "";
        for (int i = 0; i < count; i++) {
            result += queue[i] + " [" + priority[i] + "]";
            if (i < count - 1) {
                result += ", ";
            }
        }
    
        return result;
    }
}    