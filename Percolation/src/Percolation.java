//some ideas taken from: https://stackoverflow.com/questions/61396690/how-to-handle-the-backwash-problem-in-percolation-without-creating-an-extra-wuf
// 0 --> closed; 4 --> open; 6 --> open on top; 5 --> open on bottom; 7 --> open on top | open on bottom --- connected to/open on both
// 3rd bit --> open or closed; 2nd bit --> connected to top; 1st bit --> connected to bottom
package Percolation.src;

import Princeton.src.In;
import Princeton.src.StdOut;
import util.MathFuncs;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Percolation {

    private static final byte OPEN = 4;
    private static final byte TOP = 2;
    private static final byte BOTTOM = 1;

    private boolean percolates = false;
    private final byte[][] grid;
    private final WeightedQuickUnionUF uf;
    private int opened = 0;//number of open
    private final int n;//size of grid
    public Percolation(int n) {
        this.n=n;
        uf = new WeightedQuickUnionUF(n*n);
        grid = new byte[n][n];
    }
    public void open(int r, int c){
        if(isOpen(r, c))return;
        byte delta = OPEN;
        opened++;
        if(r == 0) delta |= TOP;
        if(r == n-1) delta |= BOTTOM;
        int ind1 = r*n+c;
        if (c + 1 < n && isOpen(r, c + 1)) {
            int p = uf.find(r*n+c+1);
            delta |= grid[p/n][p%n];
            uf.union(r*n+c, r*n+c+1);
        }
        if (c > 0 && isOpen(r, c - 1)) {
            int p = uf.find((r)*n+c-1);
            delta |= grid[p/n][p%n];
            uf.union(r*n+c, (r)*n+c-1);
        }
        if (r + 1 < n && isOpen(r+1, c)) {
            int p = uf.find((r+1)*n+c);
            delta |= grid[p/n][p%n];
            uf.union(r*n+c, (r+1)*n+c);
        }
        if (r > 0 && isOpen(r-1, c)) {
            int p = uf.find((r-1)*n+c);
            delta |= grid[p/n][p%n];
            uf.union(r*n+c, (r-1)*n+c);
        }
        //System.out.println(delta);
        percolates = percolates || delta == 7;//last 2 bits of delta are both 1 --> delta ^3 == 4 or 0; abs (4-2) = abs(0-2) = 2
        int p = uf.find(r*n+c);
        grid[p / n][p % n] |= delta;
    }

    private boolean validate(int row, int col){
        return row >= 0 && col >= 0 && row < n && col < n;
    }
    private boolean inBounds(int row, int col){
        return row >= 0 && col >= 0 && row < n && col < n;
    }
    public boolean isOpen(int row, int col){return getFull(row,col) >> 2 == 1;}
    public boolean isFull(int row, int col) {
        int i = uf.find(row*n+col);
        return (grid[i/n][i%n] >=6);
    }
    public byte getFull(int row, int col) {
        int t = uf.find(row*n+col);
        return grid[t/n][t%n];
    }
    public int getOpened(){return opened;}
    public boolean percolates(){
        return percolates;
    }
    public byte get(int row, int col){return grid[row][col];}
    public int getSize(){return n;}
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i : MathFuncs.range(n*n)){
            if(i%n == 0){
                builder.append("\n");
            }
            byte b = (byte) uf.find(i);


        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                byte b = getFull(i,j);
                builder.append(b >> 2 == 1 ? (b == 5 ? "🟦": b==6 ? "🟥": b==7?"🟪":"⬜") : "⬛");//i love the ternary operator
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);
        boolean isPercolated = false;
        int count = 0;
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            if (!percolation.isOpen(row, col)) {
                count++;
            }
            percolation.open(row, col);
            isPercolated = percolation.percolates();
            if (isPercolated)break;
        }
        StdOut.println(count + " open sites");
        if (isPercolated) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }
        StdOut.println("grid: ");
        for(int i = 0; i < percolation.getSize(); i++){
            for(int j = 0; j < percolation.getSize(); j++){
                byte b = percolation.get(i,j);
                StdOut.print(b >> 2 >= 1 ? (b >> 2 == 3 ? "🟦":"⬜") : "⬛");//i love the ternary operator
            }
            StdOut.println();
        }

    }
}


