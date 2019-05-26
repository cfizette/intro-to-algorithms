import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double[] fractions;

    public PercolationStats(int n, int trials){
        if (n <= 0){
            throw new IllegalArgumentException("n <= 0");
        }
        if (trials <= 0){
            throw new IllegalArgumentException("trials <= 0");
        }

        // hold the data for each trial
        fractions = new double[trials];

        for (int i=0; i<trials; i++){
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()){
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }

            double frac = percolation.numberOfOpenSites() * 1.0 / (n*n);
            fractions[i] = frac;
        }
    }

    public double mean(){
        return StdStats.mean(fractions);
    }

    public double stddev(){
        return StdStats.stddev(fractions);
    }

    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt((args[1]));
        PercolationStats stats =  new PercolationStats(n, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = "
                               + stats.confidenceLo() + ", "
                               + stats.confidenceHi());
    }
}
