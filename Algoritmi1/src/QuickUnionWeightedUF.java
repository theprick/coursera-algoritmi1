/**
 * Created by Adrian on 02.02.2014.
 */
public class QuickUnionWeightedUF {
    private int[] id;
    private int[] size;

    public QuickUnionWeightedUF(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ)
            return;

        if (size[rootP] > size[rootQ]) {
            id[rootQ] = rootP;
            size[rootQ] += size[rootP];
        } else {
            id[rootP] = rootQ;
            size[rootP] += size[rootQ];
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // flatten the tree, make every node in  the path to point to his grandfather
            i = id[i];
        }
        return i;
    }
}
