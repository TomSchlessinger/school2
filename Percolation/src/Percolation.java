package Percolation.src;

import static util.MathFuncs.range;
import Princeton.src.*;

import java.awt.*;
import java.util.Random;
public class Percolation{
    private boolean[][] grid;
    private int steps = 0;
    public Percolation(boolean[][] grid){
        this.grid = grid;
    }
    public void generate(){
        Random random = new Random();
        int x = grid.length;
        int y = grid[x-1].length;
        for (int i : range(x)){
            for(int j : range(y)){
                grid[i][j] = random.nextBoolean();
            }
        }
    }
    public boolean[][] run() {
        int n = grid.length;
        boolean[][] ret = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            tick(ret, 0, j);
        }
        return ret;
    }
    public void tick(boolean[][] init, int i, int j) {
        steps++;
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j>=n || !grid[i][j] || !init[i][j]) return;
        init[i][j] = true;
        tick(init, i+1, j);
        tick(init, i, j+1);
        tick(init, i, j-1);
        tick(init, i-1, j);
    }
    public boolean percolates() {
        int n = grid.length;
        boolean[][] full = run();
        for (int j = 0; j < n; j++) {
            if (full[n-1][j]) return true;
        }
        return false;
    }
    public boolean[][] get(){return grid;}
    public String toString(){
        StringBuilder j = new StringBuilder();
        for(boolean[] row : grid){
            for(boolean i : row){
                j.append(i?"🟦":"⬛");
            }
            j.append("\n");
        }
        return j.toString();
    }
    public void reset(){
        grid = new boolean[grid.length][grid[0].length];
    }
    public int getSteps(){return steps;}
    public static Percolation create(int i, int j){
        return new Percolation(new boolean[i][j]);
    }
    public void display() {
        int x = grid.length;
        int y = grid[0].length;
        StdDraw.setXscale(-1, x);
        StdDraw.setYscale(-1, x);
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (grid[i][j]){
                    StdDraw.setPenColor(Color.gray);
                    StdDraw.filledSquare(j, x-i-1, 0.4);
    }}}}
    public static void main(String[] args){
        Percolation b = create(10,10);
        System.out.println(b);
        b.generate();
        System.out.println(b);
        System.out.println(b.percolates());
        System.out.println("in " + b.getSteps() + " steps");
        b.display();
    }
}