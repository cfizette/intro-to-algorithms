/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] blocked;
    private boolean[] full;
    private int n, numOpenSites;
    private WeightedQuickUnionUF uf;

    public Percolation(int num){
        if (num <= 0){
            throw new IllegalArgumentException();
        }

        n = num;
        uf = new WeightedQuickUnionUF(n*n+2);
        blocked = new boolean[n*n+2];
        full = new boolean[n*n+2];

        // block the grid leaving the virtual cells empty and filled
        for (int i=1; i<=n*n; i++){
            blocked[i] = true;
        }

        numOpenSites = 0;
    }

    public void open(int row, int col){

    }

    public boolean isOpen(int row, int col){
        return False;
    }

    public boolean isFull(int row, int col){
        return False;
    }

    public boolean percolates(){
        return False;
    }

    public int getNumOpenSites(){
        return numOpenSites;
    }

}
