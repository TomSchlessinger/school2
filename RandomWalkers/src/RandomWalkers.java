package RandomWalkers.src;

import java.util.Arrays;

public class RandomWalkers {
    public static void main(String args[]){
        int N = args[0] != null ? Integer.parseInt(args[0]) : 10;//num of steps
        int T = args[1] != null ? Integer.parseInt(args[1]) : 10;//trials
        int[] trials = new int[T];
        for(int i = 0; i < T; i++){
            RandomWalker walker = new RandomWalker();
            walker.move(N);
            trials[i] = walker.squaredDist();
        }
        System.out.println("mean squared distance with " + T + " trials of " + N + " steps each: " + Arrays.stream(trials).sum()/(float)T);
    }
}
