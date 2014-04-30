/**
 * Takes a command-line integer k; reads in a sequence of N strings from standard input and prints out exactly
 * k of them, uniformly at random. Each item from the sequence can be printed out at most once.
 */
public class SubsetWithDeque {
    private static final String STOP_CHAR = "|";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SubsetWithDeque <no_of_items_to_print>");
            System.exit(1);
        }
        int k = Integer.parseInt(args[0]);

        Deque<String> deque = new Deque<String>();

        String s;
        while (!(s = StdIn.readString()).equals(STOP_CHAR)) {
            deque.addFirst(s);
        }

        show(deque);

        for (int i = 0; i < k; i++) {
            int rand = StdRandom.uniform(Integer.MAX_VALUE);
            String item;
            if(rand%2==0){
                item = deque.removeFirst();
            } else {
                item = deque.removeLast();
            }
            System.out.println(item);
        }
    }

    private static <T> void show(Deque<T> deque){
        System.out.println("---");
        for(T item : deque) {
            System.out.print(item + " ");
        }
        System.out.println("\n---");
    }
}
