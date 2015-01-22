package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This file is created by @Muffin_C on 1/21/15 20:55.
 * This file is part of Project algs4partI.
 */
public class Percolation {

    private WeightedQuickUnionUF disjointSet;
    private int N;
    private boolean[] sitesStatus;
    private int topIndex;

    public Percolation(int N) {
        // create N-by-N grid, with all sitesStatus blocked
        this.N = N;
        disjointSet = new WeightedQuickUnionUF(N * N + 1);  // [N * N] as topIndex node, [N * N + 1] as bottom node
        topIndex = N * N;

        for (int i = 0; i < N; i++) {
            disjointSet.union(i, topIndex);
        }

        sitesStatus = new boolean[N * N];

    }

    public static void main(String[] args) { // test client (optional)

    }

    private boolean isLegalIndex(int i, int j) {
        return !(j < 1 || j > N || i < 1 || i > N);
    }

    private int ijToIndex(int i, int j) {
        return (j - 1) + (i - 1) * N;
    }

    public void open(int i, int j) throws IndexOutOfBoundsException {
        // open site (row i, column j) if it is not open already
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int index = ijToIndex(i, j);

        if (!sitesStatus[index]) {
            sitesStatus[index] = true;

            for (int iShift = -1; iShift <= 1; iShift++) {
                for (int jShift = -1; jShift <= 1; jShift++) {
                    if (isLegalIndex(i + iShift, j + jShift) && iShift * jShift == 0) {
                        if (isOpen(i + iShift, j + jShift) || isFull(i + iShift, j + jShift)) {
                            disjointSet.union(ijToIndex(i + iShift, j + jShift), ijToIndex(i, j));
                        }
                    }
                }
            }
        }
    }

    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {
        // is site (row i, column j) open?
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return sitesStatus[ijToIndex(i, j)];
    }

    public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
        // is site (row i, column j) full?
        if (!isLegalIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(i, j)) {
            return disjointSet.connected(ijToIndex(i, j), topIndex);
        } else {
            return false;
        }
    }

    public boolean percolates() { // does the system percolate?
        for (int i = N * (N - 1); i < N * N; i++) {
            if (disjointSet.connected(i, topIndex)) {
                return true;
            }
        }
        return false;
    }
}