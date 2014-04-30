/**
 * Takes a command-line integer k; reads in a sequence of N strings from standard input and prints out exactly
 * k of them, uniformly at random. Each item from the sequence can be printed out at most once.
 */
public class Subset {

    private static final String STOP_CHAR = "|";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Subset <no_of_items_to_print>");
            System.exit(1);
        }
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        String s;
        while (!(s = StdIn.readString()).equals(STOP_CHAR)) {
            randomizedQueue.enqueue(s);
        }

        show(randomizedQueue);

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }

    private static <T> void show(RandomizedQueue<T> randomizedQueue){
        System.out.println("---");
        for(T item : randomizedQueue) {
            System.out.print(item + " ");
        }
        System.out.println("\n---");
    }
}