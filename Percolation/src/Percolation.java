
public class Percolation {

    private final static int CLOSED = 0;
    private final static int OPEN = 1;

    private int[][] grid;
    private WeightedQuickUnionUF uf;


    /**
     * create N-by-N grid, with all sites blocked
     *
     * @param N size of the grid
     */
    public Percolation(int N) {
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = CLOSED;
            }
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            uf.union(0, i + 1);
            uf.union(this.getId(N - 1, i), N * N + 1);
        }
    }

    /**
     * open site (row row, col col) if it is not already!
     *
     * @param row row
     * @param col col
     */

    public void open(int row, int col) {
        if (grid[row][col] == CLOSED) {
            grid[row][col] = OPEN;

            if (row > 0 && this.isOpen(row - 1, col)) {
                uf.union(this.getId(row, col), this.getId(row - 1, col));
            }
            if (row < grid.length - 1 && this.isOpen(row + 1, col)) {
                uf.union(this.getId(row, col), this.getId(row + 1, col));
            }
            if (col > 0 && this.isOpen(row, col - 1)) {
                uf.union(this.getId(row, col), this.getId(row, col - 1));
            }
            if (col < grid.length - 1 && this.isOpen(row, col + 1)) {
                uf.union(this.getId(row, col), this.getId(row, col + 1));
            }
        }
    }

    /**
     * @param row row
     * @param col column
     * @return if site (row row, column col) is open
     */
    public boolean isOpen(int row, int col) {
        return this.grid[row][col] == OPEN;
    }

    /**
     * @return if site (row row, column col) is full or connected to top
     */
    public boolean isFull(int row, int col) {
        return uf.connected(0, getId(row, col));
    }

    /**
     * @return if the system percolate
     */
    public boolean percolates() {
        return uf.connected(0, grid.length * grid.length + 1);
    }

    /**
     * @return the position in the id array of the union find
     */
    private int getId(int row, int col) {
        return row * this.grid.length + col + 1;
    }
}
