public abstract class Sorting {

    public abstract void sort(Comparable[] a);

    /**
     * @return true if v < w false otherwise
     */
    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Exchange between a[i] and a[j]
     */
    public void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void show(Comparable[] a){
        for (Comparable i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
