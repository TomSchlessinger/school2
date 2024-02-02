/*
a) Write a program RandomWalker.java that takes an integer command-line argument N and simulates the motion of a random walker for N steps. After each step, print the location of the random walker, treating the lamp post as the origin (0, 0). Also, print the square of the final distance from the origin.
% java RandomWalker 10             % java RandomWalker 20
(0, -1)                           (0, 1)
(0, 0)                            (-1, 1)
(0, 1)                            (-1, 2)
(0, 2)                            (0, 2)
(-1, 2)                           (1, 2)
(-2, 2)                           (1, 3)
(-2, 1)                           (0, 3)
(-1, 1)                           (-1, 3)
(-2, 1)                           (-2, 3)
(-3, 1)                           (-3, 3)
squared distance = 10             (-3, 2)
                                  (-4, 2)
                                  (-4, 1)
                                  (-3, 1)
                                  (-3, 0)
                                  (-4, 0)
                                  (-4, -1)
                                  (-3, -1)
                                  (-3, -2)
                                  (-3, -3)
                                  squared distance = 18


Write a program RandomWalkers.java that takes two integer command-line arguments N and T. In each of T independent experiments, simulate a random walk of N steps and compute the squared distance. Output the mean squared distance (the average of the T squared distances).
% java RandomWalkers 100 10000         % java RandomWalkers 400 2000
mean squared distance = 101.446         mean squared distance = 383.12

% java RandomWalkers 100 10000         % java RandomWalkers 800 5000
mean squared distance = 99.1674         mean squared distance = 811.8264

% java RandomWalkers 200 1000         % java RandomWalkers 1600 100000
mean squared distance = 195.75          mean squared distance = 1600.13064
As N increases, we expect the random walker to end up farther and farther away from the origin. But how much farther? Use RandomWalkers to formulate a hypothesis as to how the mean squared distance grows as a function of N. Use T = 100,000 trials to get a sufficiently accurate estimate.

Remark: this process is a discrete version of a natural phenomenon known as Brownian motion. It serves as a scientific model for an astonishing range of physical processes from the dispersion of ink flowing in water, to the formation of polymer chains in chemistry, to cascades of neurons firing in the brain.
b) Tom Schlessinger
c) Java 20.0.1
d) 1/22/24
*/

package RandomWalkers.src;

import java.util.Random;

public class RandomWalker {
    public static void main(String args[]){
        int N = args[0] != null ? Integer.parseInt(args[0]) : 10;
        RandomWalker walker = new RandomWalker();
        for(int i = 0; i < N; i++){
            walker.move();
            System.out.println("(" + walker.getX() + ", " + walker.getY() + ")");
        }
        System.out.println("squared distance with " + N + " steps: " + walker.squaredDist());
    }
    private final int[] pos;
    private final Random r;
    public RandomWalker(){
        pos = new int[]{0,0};
        r = new Random();
    }
    public void move(){

        pos[Math.abs(r.nextInt())%2] += 2*(Math.abs(r.nextInt())%2d - 0.5);
        //System.out.println("(" + pos[0] + ", " + pos[1] + ")");
        // I could've used nextBoolean bruh...
    }
    public void move(int n){
        for(int i = 0; i < n; i++){
            move();
        }
        //System.out.println("Squared distance = " + pos[0]*pos[0]+pos[1]*pos[1]);
    }
    public int[] getPos() {
        return pos;
    }
    public int getX(){
        return pos[0];
    }
    public int getY(){
        return pos[1];
    }
    public void setX(int x){
        pos[0] = x;
    }
    public void setY(int y){
        pos[1] = y;
    }
    public int squaredDist(){
        return pos[0]*pos[0]+pos[1]*pos[1];
    }
}
/*
RandomWalker:
10 -> (0, -1)
(0, -2)
(-1, -2)
(-2, -2)
(-2, -1)
(-1, -1)
(-1, 0)
(-1, 1)
(-2, 1)
(-2, 0)
squared distance with 10 steps: 4
10 ->(-1, 0)
(-2, 0)
(-2, 1)
(-2, 0)
(-1, 0)
(0, 0)
(0, -1)
(-1, -1)
(-1, -2)
(-2, -2)
squared distance with 10 steps: 8
10 ->(1, 0)
(1, -1)
(2, -1)
(3, -1)
(3, 0)
(3, -1)
(3, -2)
(3, -1)
(3, 0)
(2, 0)
squared distance with 10 steps: 4

as you can see it consistently works, so I won't write the individual moves for the next test cases (otherwise this file would be millions of lines long)
100 -> squared distance with 100 steps: 130
100 -> squared distance with 100 steps: 116
100 -> squared distance with 100 steps: 306
10000 -> squared distance with 10000 steps: 7956
10000 -> squared distance with 10000 steps: 14386
10000 -> squared distance with 10000 steps: 4234
1000000 -> squared distance with 1000000 steps: 99970
1000000 -> squared distance with 1000000 steps: 20410
1000000 -> squared distance with 1000000 steps: 68500
maximum for 1 million is 2 * 500000^2 so its kinda cool to see that we dont even get over 100000

RandomWalkers:
10 10 -> mean squared distance with 10 trials of 10 steps each: 9.8
10 100 -> mean squared distance with 100 trials of 10 steps each: 10.28
10 1000 -> mean squared distance with 1000 trials of 10 steps each: 10.314
100 10 -> mean squared distance with 10 trials of 100 steps each: 144.4
100 100 -> mean squared distance with 100 trials of 100 steps each: 115.04
100 1000 -> mean squared distance with 1000 trials of 100 steps each: 100.51
1000 10 -> mean squared distance with 10 trials of 1000 steps each: 1160.6
1000 100 -> mean squared distance with 100 trials of 1000 steps each: 1029.8
1000 1000 -> mean squared distance with 1000 trials of 1000 steps each: 1087.94
10000 10 -> mean squared distance with 10 trials of 10000 steps each: 7094.2
10000 100 -> mean squared distance with 100 trials of 10000 steps each: 6932.92
10000 1000 -> mean squared distance with 1000 trials of 10000 steps each: 6717.336
1,1
2,2.00236
3,3.00036
5,4.97512
8,7.97286
13,12.99524
21,21.02276
34,33.95348
55,55.5608
89,90.44584
144,146.30754
233,237.00328
377,382.9492
610,639.66125
987,1010.8512
1597,1547.8851
2584,2190.9146
4180,3132.5144
6765,4873.738
10946,7430.1323
25000,8342.814
50000,9317.58
100000,9061.762
1 100000 -> 1
2 100000 -> 2.00236
3 100000 -> 3.00036
5 100000 -> 4.97512
8 100000 -> 7.97286
13 100000 -> 12.99524
21 100000 -> 21.02276
34 100000 -> 33.95348
55 100000 -> 55.5608
89 100000 -> 90.44584
144 100000 -> 146.30754
233 100000 -> 237.00328
377 100000 -> 382.9492
610 100000 -> 639.66125
987 100000 -> 1010.8512
1597 100000 -> 1547.8851
2584 100000 -> 2190.9146
4181 10000 -> 3132.5144
6765 10000 -> 4983.738
10946 100000 -> 7430.1323
25000 10000 -> 8342.814
50000 10000 -> 9317.58
100000 10000 ->
*/