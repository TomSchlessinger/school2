package Percolation.src;

import Princeton.src.StdOut;
import Princeton.src.StdRandom;
import Princeton.src.StdStats;

import java.util.Random;

public class PercolationStats {
    private final double[] frac;
    public PercolationStats(int n, int trials) {
        Random r = new Random();
        frac = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openedSites = 0;
            while (!percolation.percolates()) {
                int row = r.nextInt(n);  // base-1
                int col = r.nextInt(n);  // base-1
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openedSites++;
                }
            }
            System.out.println(percolation);
            frac[i] = (double)openedSites / (n * n);
        }
    }
    public double mean() {
        return StdStats.mean(frac);
    }
    public double stddev() {
        return StdStats.stddev(frac);
    }
    public double low() {
        return mean() - 1.96 * stddev() / Math.sqrt(frac.length);
    }
    public double high() {
        return mean() + 1.96 * stddev() / Math.sqrt(frac.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean: " + stats.mean());
        StdOut.println("stddev: " + stats.stddev());
        StdOut.println("95% confidence interval: " + stats.low() + ", " + stats.high());
    }
}