package percolation;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * This file is created by @Muffin_C on 1/22/15 16:06.
 * This file is part of Project algs4partI.
 */
public class PercolationStats {
    int N;
    int T;
    double[] openSites;

    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.T = T;
        openSites = new double[T];

        for (; T > 0; T--) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                if (!perc.isFull(i, j) && !perc.isOpen(i, j)) {
                    perc.open(i, j);
                    openSites[T - 1]++;
                }
            }
            openSites[T - 1] = openSites[T - 1] / N / N;
        }
    } // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(openSites);
    } // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(openSites);
    } // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    } // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int N;
        int T;
        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
            N = 200;
            T = 100;
            PercolationStats perc = new PercolationStats(N, T);
            System.out.println("mean                    = " + perc.mean());
            System.out.println("stddev                  = " + perc.stddev());
            System.out.println("95% confidence interval = "
                  + perc.confidenceLo() + ", "
                  + perc.confidenceHi());
        }


    } // test client (described below)
}
