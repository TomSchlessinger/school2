package Percolation.src;

import static util.MathFuncs.range;

import java.util.Random;
public record Percolation(boolean[][] grid){
    public void generate(){
        Random random = new Random(13059810);
        int x = grid.length;
        int y = grid[x-1].length;
        for (int i : range(x)){
            for(int j : range(y)){
                grid[i][j] = random.nextBoolean();
            }
        }
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
    public static Percolation create(int i, int j){
        return new Percolation(new boolean[i][j]);
    }
    public static void main(String[] args){
        Percolation b = create(10,10);
        System.out.println(b);
        b.generate();
        System.out.println(b);
    }
}