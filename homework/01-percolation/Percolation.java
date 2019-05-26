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

    public Percolation(int num){
        if (num <= 0){
            throw new IllegalArgumentException();
        }
        n = num;
        uf = new WeightedQuickUnionUF(n*n+2);
        blocked = new boolean[n*n+2];

        // block the grid leaving the virtual cells empty and filled
        for (int i=1; i<=n*n; i++){
            blocked[i] = true;
        }

        numOpenSites = 0;
    }

    private boolean isInvalid(int row, int col){
        return row < 1 || col < 1 || row > n || col > n;
    }

    private boolean isValid(int row, int col){
        return !isInvalid(row, col);
    }

    private int index(int row, int col){
        // return the index of the cell at row, col
        return (row - 1) * n + col;
    }

    public void open(int row, int col){
        if (isInvalid(row, col)){
            throw new IllegalArgumentException();
        }

        blocked[index(row, col)] = false;
        numOpenSites++;

        int[] adjacent_cols = {col-1, col+1};
        int[] adjacent_rows = {row-1, row+1};

        // check surrounding cells
        for (int c: adjacent_cols){
            for (int r: adjacent_rows){
                // If adjacent cell is open, connect to current cell
                if (isValid(row, col) && isOpen(row, col)){
                    uf.union(index(row, col), index(r, c));
                }
            }
        }
    }

    public boolean isOpen(int row, int col){
        if (isInvalid(row, col)){
            throw new IllegalArgumentException();
        }
        return !blocked[index(row, col)];
    }

    public boolean isFull(int row, int col){
        if (isInvalid(row, col)){
            throw new IllegalArgumentException();
        }
        // check that cell is connected to top virtual cell at index 0
        return uf.connected(0, index(row, col));
    }

    public boolean percolates(){
        return uf.connected(0, n+1);
    }

    public int getNumOpenSites(){
        return numOpenSites;
    }

}
