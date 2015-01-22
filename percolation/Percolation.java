package percolation;


import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.introcs.StdRandom;

/**
 * This file is created by @Muffin_C on 1/21/15 20:55.
 * This file is part of Project algs4partI.
 */
public class Percolation {

    private UF disjointSet;
    private int N;
    private int RAND = StdRandom.uniform(N);

    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        this.N = N;
        disjointSet = new UF(N * N + 2);  // [N * N] as top node, [N * N + 1] as bottom node

        for (int i = 0; i < N; i++) {
            disjointSet.union(i, N * N);
            int j = i +
        }

    }

    public void open(int i, int j) throws IndexOutOfBoundsException { // open site (row i, column j) if it is not open already

    }

    public boolean isOpen(int i, int j)  throws IndexOutOfBoundsException { // is site (row i, column j) open?
        return true;
    }

    public boolean isFull(int i, int j) throws IndexOutOfBoundsException { // is site (row i, column j) full?
        return true;
    }

    public boolean percolates() { // does the system percolate?
        return true;
    }

    public static void main(String[] args) { // test client (optional)

    }
}