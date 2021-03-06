/* *****************************************************************************
 *  Name: Cody Fizette
 *  Date:
 *  Description: Homework 1 for Princeton Intro to Algorithms
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] blocked;
    private int n, numOpenSites;
    private WeightedQuickUnionUF uf;
    private int topIndex;
    private int bottomIndex;

    public Percolation(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        n = num;
        uf = new WeightedQuickUnionUF(n*n+2);
        blocked = new boolean[n*n+2];
        topIndex = 0;
        bottomIndex = n*n+1;

        // block the grid leaving the virtual cells empty and filled
        for (int i = 1; i <= n * n; i++) {
            blocked[i] = true;
        }

        // connect top row to top virtual cell
        for (int col = 1; col <= n; col++) {
            uf.union(topIndex, index(1, col));
        }

        // connect bottom row to bottom virtual cell
        for (int col = 1; col <= n; col++) {
            uf.union(bottomIndex, index(n, col));
        }

        numOpenSites = 0;
    }

    private boolean isInvalid(int row, int col) {
        return row < 1 || col < 1 || row > n || col > n;
    }

    private boolean isValid(int row, int col) {
        return !isInvalid(row, col);
    }

    private int index(int row, int col) {
        // return the index of the cell at row, col
        return (row - 1) * n + col;
    }

    public void open(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException();
        }

        if (!isOpen(row, col)) {
            blocked[index(row, col)] = false;
            numOpenSites++;

            // check surrounding cells
            tryUnion(row, col, row - 1, col);
            tryUnion(row, col, row + 1, col);
            tryUnion(row, col, row, col - 1);
            tryUnion(row, col, row, col + 1);
        }
    }

    private void tryUnion(int rowA, int colA, int rowB, int colB) {
        if (isValid(rowB, colB) && isOpen(rowB, colB)) {
            uf.union(index(rowA, colA), index(rowB, colB));
        }
    }

    public boolean isOpen(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        return !blocked[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException();
        }
        // check that cell is connected to top virtual cell at index 0
        return uf.connected(topIndex, index(row, col)) && isOpen(row, col);
    }

    public boolean percolates() {
        return uf.connected(topIndex, bottomIndex);
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

}
