/**
 * Implementation of the insertion sort algorithm.
 */
public class InsertionSort extends Sorting {

    @Override
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else break;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{6, 7, 4, 4, 2, 3, 1, 1, 7, 9};
        new InsertionSort().sort(a);

        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
