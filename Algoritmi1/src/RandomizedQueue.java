import java.util.Iterator;

/**
 * Implements RandomizedQueue using resizable arrays.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomOrderIterator<Item> implements Iterator<Item> {

        private Item[] itemsToIterate;
        private int itemRemaining;

        //FIXME fix unnecessary casts
        private RandomOrderIterator() {
            itemsToIterate = (Item[]) new Object[size()];
            for (int i = 0; i < size(); i++) {
                itemsToIterate[i] = (Item) items[i];
            }
            itemRemaining = size();
        }

        @Override
        public boolean hasNext() {
            return itemRemaining > 0;
        }

        @Override
        public Item next() {
            int index = StdRandom.uniform(0, itemRemaining);
            Item item = (Item) itemsToIterate[index];
            moveToTheEnd(index);

            itemRemaining--;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported");
        }

        private void moveToTheEnd(int i) {
            Item lastItem = itemsToIterate[itemRemaining - 1];
            itemsToIterate[itemRemaining - 1] = itemsToIterate[i];
            itemsToIterate[i] = lastItem;
        }
    }

    private Item[] items = (Item[]) new Object[1];
    private int size = 0;

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * add the item
     */
    public void enqueue(Item item) {
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    /**
     * delete and return a random item
     */
    public Item dequeue() {
        int index = StdRandom.uniform(0, this.size);
        Item item = items[index];
        //  swap with the last one
        moveToTheEnd(index);

        items[--size] = null;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    //FIXME make this method static and use it in iterator
    private void moveToTheEnd(int i) {
        Item lastItem = items[size - 1];
        items[size - 1] = items[i];
        items[i] = lastItem;
    }

    /**
     * @return (but do not delete) a random item
     */
    public Item sample() {
        return items[StdRandom.uniform(0, size)];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomOrderIterator<Item>();
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<Integer>();
        randQueue.enqueue(10);
        randQueue.enqueue(20);
        randQueue.enqueue(30);
        randQueue.enqueue(40);

        System.out.println("Size = " + randQueue.size());
        show(randQueue);

        System.out.println("Give me a sample item : " + randQueue.sample());

        System.out.println("Size = " + randQueue.size());

        System.out.println("Removing item " + randQueue.dequeue());

        System.out.println("Size = " + randQueue.size());
        show(randQueue);
        show(randQueue);
    }

    private static <T> void show(RandomizedQueue<T> randomizedQueue) {
        System.out.println("---");
        for(T i : randomizedQueue) {
            System.out.print(i + " ");
        }
        System.out.println("\n---");
    }
}
