package percolation;

/**
 * This file is created by @Muffin_C on 1/21/15 21:26.
 * This file is part of Project algs4partI.
 */
public class DisjointSet {
    int[] array;
    int topIndex;
    int downIndex;

    public DisjointSet(int number) {
        array = new int[number + 2];

        topIndex = number;
        downIndex = number + 1;

        for (int i = 0; i < number + 2; i++) {
            array[i] = -1;
        }
    }

    public int find(int root) {
        if (array[root] < 0) {
            return root;
        } else {
            array[root] = find(array[root]);
            return array[root];
        }
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            System.out.println("attempting to union same set node");
            return;
        } else {
            if (array[root1] < array[root2]) {
                array[root1] += array[root2];
                array[root2] = root1;
            } else {
                array[root2] += array[root1];
                array[root1] = root2;
            }
        }
    }

    public boolean isSameSet(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
