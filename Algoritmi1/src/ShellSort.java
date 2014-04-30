/**
 * Implementation of the shell sort algorithm.
 */
public class ShellSort extends Sorting {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;

        show(a);

        //  3x + 1 increment sequence = 1, 4, 13, 40, 121, 364, ...
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        //  h sort the array
        while (h >= 1) {
            System.out.println("h=" + h);
            for (int i = h; i < N; i++) {
                System.out.println("i=" + i);
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    System.out.println("exchange " + a[j] + " with " + a[j - h]);
                    exch(a, j, j - h);
                }
                show(a);
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(a);
        shellSort.show(a);
    }
}
