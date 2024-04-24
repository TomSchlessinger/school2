package Percolation.src;

import Princeton.src.StdOut;
import Princeton.src.StdRandom;
import Princeton.src.StdStats;

import java.util.Random;

public class PercolationStats {
    private final double[] frac;
    public PercolationStats(int n, int trials, boolean printStep) {
        Random r = new Random(1000);
        frac = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int temp = 0;
            while (!percolation.percolates()) {
                int row = r.nextInt(n);  // base-1
                int col = r.nextInt(n);  // base-1
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    temp++;
                    if(printStep){
                        System.out.println("\n-------------------------------\nStep " + temp + ": ");
                        System.out.println("placed: (" + (col+1) +", " + (n-row) + ")");
                        System.out.println(percolation);
                    }
                }
            }
//            percolation.open(0, 1);
//            System.out.println(percolation);
//            percolation.open(2, 1);
//            System.out.println(percolation);
//            percolation.open(1, 1);
//            System.out.println(percolation);

            frac[i] = (double)percolation.getOpened() / (n * n);
            System.out.println("------------------------------------------\n Percolation has finished after " + temp + "steps\n" + percolation + "\n");
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
        PercolationStats stats = new PercolationStats(n, trials,false);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.low() + ", " + stats.high() + "]");
    }

}