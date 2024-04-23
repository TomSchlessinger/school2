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
        if(!validate(r, c) || isOpen(r, c))return;
        grid[r][c] |= 4;//turn the 3rd bit to a 1
        opened++;
        if(r == n-1)grid[r][c] |= 1;//turn the 1st bit to a 1
        if(r == 0){
            uf.union(0, c);
            grid[0][0] |= 2;//turn the 2nd bit to a 1
        }
        tick(r, c,0,1);
        tick(r, c,0,-1);
        tick(r, c,1,0);
        tick(r, c,-1,0);
    }
    private void tick(int row, int col, int dRow, int dCol) {
        if(!validate(row+dRow,col+dCol))return;
        if(!isOpen(row+dRow,col+dCol))return;
        int ind1 = row*n+col;//i reused this a couple of times so ima just store this here
        int ind2 = (row+dRow)*n+(col+dCol);
        int p = uf.find(ind1);
        int q = uf.find(ind2);
        uf.union(ind1, ind2);
        if (isOpen(p / n,p % n) || isOpen(q / n, q % n)) {//see if 3rd bit is 1
            int t = uf.find(ind2);
            grid[t / n][t % n] |= 1;
        }
    }
    private boolean validate(int row, int col){
        return row >= 0 && col >= 0 && row < n && col < n;
    }
    public boolean isOpen(int row, int col){return grid[row][col] >> 2 == 1;}
    public int getOpened(){return opened;}
    public boolean percolates(){
        return grid[uf.find(0)/n][uf.find(0)%n] >= 4;
    }
    public byte get(int row, int col){return grid[row][col];}
    public int getSize(){return n;}
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                byte b = get(i,j);
                builder.append(b >> 2 >= 1 ? (b >> 2 == 3 ? "🟦":"⬜") : "⬛");//i love the ternary operator
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static int[] generate(int[] usedx, int[] usedy, int max){//generate new pair of indexes
        //max should be >= max(usedx) and >= max(usedy); usedx and usedy should only contain positive integers
        Set<Integer> uniqueX = new HashSet<>(List.of(MathFuncs.toInteger(usedx)));
        Set<Integer> uniqueY = new HashSet<>(List.of(MathFuncs.toInteger(usedy)));
        Set<Integer> rangeX = new HashSet<>(List.of(MathFuncs.range(max)));
        Set<Integer> rangeY = new HashSet<>(rangeX);
        rangeX.removeAll(uniqueX);
        rangeY.removeAll(uniqueY);
        Random random = new Random();
        return new int[]{
                MathFuncs.toInt(rangeX)[random.nextInt(rangeX.size())],
                MathFuncs.toInt(rangeY)[random.nextInt(rangeY.size())]
        };
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
                byte b = percolation.get(i+1,j+1);
                StdOut.print(b >> 2 >= 1 ? (b >> 2 == 3 ? "🟦":"⬜") : "⬛");//i love the ternary operator
            }
            StdOut.println();
        }

    }
}


