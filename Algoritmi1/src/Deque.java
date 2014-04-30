import java.util.Iterator;

public class Deque<T> implements Iterable<T> {

    private class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> prev;

        public Node(T item) {
            this.item = item;
        }
    }

    private class AscendingDequeIterator<T> implements Iterator<T> {

        private Node<T> current;

        //FIXME check why this doesn't work: this.current = head
        private AscendingDequeIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;// && current.next != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported");
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    /**
     * construct an empty deque
     */
    public Deque() {
    }

    /**
     * @return true if the deque empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * insert the item at the front
     */
    public void addFirst(T item) {
        if (isEmpty()) {
            head = tail = new Node<T>(item);
        } else {
            Node<T> oldHead = head;
            head = new Node<T>(item);
            head.next = oldHead;
            oldHead.prev = head;
        }
        size++;
    }

    /**
     * insert the item at the end
     */
    public void addLast(T item) {
        if (isEmpty()) {
            head = tail = new Node<T>(item);
        } else {
            Node<T> oldTail = tail;
            tail = new Node<T>(item);
            tail.prev = oldTail;
            oldTail.next = tail;
        }
        size++;
    }

    /**
     * delete and return the item at the front
     */
    public T removeFirst() {
        if (size == 0)
            throw new UnsupportedOperationException("Deque is empty");

        //  save item
        T item = head.item;
        //  remove node
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        if (size-- == 1) {
            tail = null;
        }

        //TODO: check if further cleanup is needed
        return item;
    }

    /**
     * delete and return the item at the end
     */
    public T removeLast() {
        if (size == 0) {
            throw new UnsupportedOperationException("Deque is empty");
        }

        T item = tail.item;

        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        if (size-- == 1) {
            head = null;
        }

        //TODO: check if further cleanup is needed
        return item;
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<T> iterator() {
        return new AscendingDequeIterator<T>(head);
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<Integer>();
        deq.addFirst(1);
        deq.addFirst(2);
        deq.addFirst(3);
        deq.addLast(4);
        deq.addFirst(5);
        System.out.println("Removing item "+ deq.removeFirst());

        System.out.println("Size = " + deq.size());
        for (Integer i : deq) {
            System.out.print(i + " ");
        }

        System.out.println("\nEmpty the deque");
        while (!deq.isEmpty()) {
            System.out.println("Removing item " + deq.removeLast());
        }

        System.out.println("Size = " + deq.size());
        for (Integer j : deq) {
            System.out.print(j + " ");
        }
    }
}
