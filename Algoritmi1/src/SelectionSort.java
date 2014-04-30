/**
 * Implementation of the selection sort algorithm.
 */
public class SelectionSort extends Sorting {

    @Override
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{6, 7, 4, 4, 2, 3, 1, 1, 7, 9};
        new SelectionSort().sort(a);

        for (Integer i : a) {
            System.out.print(i + " ");
        }
    }
}
