package percolation;

import edu.princeton.cs.introcs.StdRandom;

/**
 * This file is created by @Muffin_C on 1/22/15 16:06.
 * This file is part of Project algs4partI.
 */
public class PercolationStats {
    int N;
    int T;
    int[] openSites;

    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.T = T;
        openSites = new int[T];

        for ( ; T > 0; T--) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int i = StdRandom.uniform(N)+1;
                int j = StdRandom.uniform(N)+1;

                if (!perc.isFull(i,j) && !perc.isOpen(i,j)) {
                    perc.open(i, j);
                    openSites[T - 1]++;
                }
            }
        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
        double sum = 0;
        for (int i = 0; i < T; i++) {
            sum += ((double) openSites[i]) / N / N;
        }
        return sum / T;
    } // sample mean of percolation threshold

    public double stddev()  {
        if (T == 1) {
            return Double.NaN;
        }

        double sum = 0;
        for (int i = 0; i < T; i++) {
            sum += Math.pow(((((double) openSites[i]) / N / N) - mean()),2);
        }
        return sum / (T - 1);
    } // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    } // low  endpoint of 95% confidence interval

    public double confidenceHi()    {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationStats perc = new PercolationStats(200,10);
        System.out.println(perc.mean());
        System.out.println(perc.stddev());
        System.out.println(perc.confidenceLo());
        System.out.println(perc.confidenceHi());
    } // test client (described below)
}
