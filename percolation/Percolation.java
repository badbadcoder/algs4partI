package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This file is created by @Muffin_C on 1/21/15 20:55.
 * This file is part of Project algs4partI.
 */
public class Percolation {

    private WeightedQuickUnionUF disjointSet;
    private int N;
    private int[] sites;  // 0 blocked; 1 opened; 2 full (connected to topIndex);
    private int topIndex;

    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        this.N = N;
        disjointSet = new WeightedQuickUnionUF(N * N + 2);  // [N * N] as topIndex node, [N * N + 1] as bottom node
        topIndex = N * N;

        for (int i = 0; i < N; i++) {
            disjointSet.union(i, topIndex);
//            disjointSet.union(i + N * (N - 1) , bottomIndex);
        }

        sites = new int[N * N];

    }

    public static void main(String[] args) { // test client (optional)

    }

    private boolean checkIndex(int i, int j) throws IndexOutOfBoundsException {
        if (j < 1 || j > N || i < 1 || i > N) {
            return false;
        }
        return true;
    }

    private int coorToIndex(int i, int j) {
        return (j - 1) + (i - 1) * N;
    }

    public void open(int i, int j) throws IndexOutOfBoundsException { // open site (row i, column j) if it is not open already
        if (!checkIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        int index = coorToIndex(i, j);

        if (sites[index] == 0) {
            sites[index] = 1;

            for (int iShift = -1; iShift <= 1; iShift++) {
                for (int jShift = -1; jShift <= 1; jShift++) {
                    if (checkIndex(i + iShift, j + jShift) && iShift * jShift == 0) {
                        if (isOpen(i + iShift, j + jShift) || isFull(i + iShift, j + jShift)) {
                            disjointSet.union(coorToIndex(i + iShift, j + jShift), coorToIndex(i, j));
                        }
                        scanSet();
                    }
                }
            }
        }
    }

    private void scanSet() {
        for (int i = 0; i < N * N; i++) {
            if (sites[i] == 1) {
                if (disjointSet.connected(i, topIndex)) {
                    sites[i] = 2;
                }
            }
        }
    }

    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException { // is site (row i, column j) open?
        if (!checkIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return sites[coorToIndex(i, j)] == 1;
    }

    public boolean isFull(int i, int j) throws IndexOutOfBoundsException { // is site (row i, column j) full?
        if (!checkIndex(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return sites[coorToIndex(i, j)] == 2;
    }

    public boolean percolates() { // does the system percolate?
        for (int i = N * (N - 1); i < N * N; i++) {
            if (disjointSet.connected(i, topIndex)) {
                return true;
            }
        }
        return false;
//        return disjointSet.connected(topIndex,bottomIndex);
    }
}