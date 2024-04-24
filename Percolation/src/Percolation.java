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
        int p = uf.find(ind1);
        delta|=update(r,c,r,c+1);//dRow = 0
        delta|=update(r,c,r,c-1);//dRow = 0
        delta|=update(r,c,r+1,c);//dCol = 0
        delta|=update(r,c,r-1,c);//dCol = 0
        percolates = percolates || Math.abs((delta^3)-2) == 2;//last 2 bits of delta are both 1 --> delta ^3 == 4 or 0; abs (4-2) = abs(0-2) = 2
        grid[r][c] |= delta;
        grid[p / n][p % n] |= delta;


    }
    private byte update(int row, int col, int newRow, int newCol) {
        int dCol = 0, dRow = 0;
        if(col > 0 && col < n-1 && row > 0 && row < n-1)return 0;
        if(!validate(newRow,newCol) || !isOpen(newRow,newCol))return 0;
        System.out.println("row is " + row);
        System.out.println("col is " + col);
        System.out.println("new row is " + newRow);
        System.out.println("new col is " + newCol);
        if(col > 0 || col < n-1)dCol = newCol-col;
        if(row > 0 || row < n-1)dRow = newRow-row;
        if(row != newRow && dRow == 0)return 0;
        if(col != newCol && dCol == 0)return 0;
        System.out.println("check 1");
        int ind2 = (row + dRow) * n + (col + dCol);
        int q = uf.find(ind2);
        uf.union(row*n+col, ind2);
        return grid[q/n][q%n];
    }

    private boolean validate(int row, int col){
        return row >= 0 && col >= 0 && row < n && col < n;
    }
    private boolean inBounds(int row, int col){
        return row >= 0 && col >= 0 && row < n && col < n;
    }
    public boolean isOpen(int row, int col){return grid[row][col] >> 2 == 1;}
    public boolean isFull(int row, int col) {
        int i = uf.find(row*n+col);
        return grid[i/n][i%n] >> 2 == 1;
    }
    public int getOpened(){return opened;}
    public boolean percolates(){
        return percolates;
    }
    public byte get(int row, int col){return grid[row][col];}
    public int getSize(){return n;}
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                byte b = grid[i][j];
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


