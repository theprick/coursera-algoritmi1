public class PercolationStats {

    private Percolation percolation;
    private double[] x;
    private double mean = 0;
    private double stddev = 0;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param N size of the grid
     * @param T number of experiments
     */
    public PercolationStats(int N, int T) {
        this.x = new double[T];
        for (int i = 0; i < T; i++) {
            percolation = new Percolation(N);
            int noOfOpenSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    noOfOpenSites++;
                }
            }
            this.x[i] = (double) noOfOpenSites / (N * N);
            System.out.println(this.x[i]);
            this.mean = mean();
            this.stddev = stddev();
        }
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        return sum / x.length;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i] - this.mean, 2);
        }
        return Math.sqrt(sum / (x.length - 1));
    }

    /**
     * @return lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return this.mean - (1.96 * this.stddev) / Math.sqrt(x.length);
    }

    /**
     * @return upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        double mean = mean();
        double stdev = stddev();

        return this.mean + (1.96 * stdev) / Math.sqrt(x.length);
    }

    /**
     * test client
     */
    public static void main(String[] args) {
        Input input = extractAndValidateInput(args);
        PercolationStats percolationStats = new PercolationStats(input.sizeOfTheGrid, input.noOfExperiments);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stdev                   = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }

    public static Input extractAndValidateInput(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: Percolation <grid_size> <number_of_experiments>");
            System.exit(1);
        }

        Input input = new Input();
        try {
            int size = Integer.parseInt(args[0]);
            if (size <= 0) {
                throw new NumberFormatException(">=0");
            }
            input.sizeOfTheGrid = size;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Grid size must be >=0");
        }
        try {
            int times = Integer.parseInt(args[1]);
            if (times <= 0) {
                throw new NumberFormatException(">=0");
            }
            input.noOfExperiments = times;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number of experiments must be >=0");
        }
        return input;
    }

    private static class Input {
        int noOfExperiments;
        int sizeOfTheGrid;
    }
}