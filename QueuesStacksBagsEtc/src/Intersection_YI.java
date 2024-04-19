package QueuesStacksBagsEtc.src;

import util.MathFuncs;

public class Intersection_YI {
    private final int stage_length;
    private int stage = 0;
    private int step = 0;
    private final Queue<String> arrivals;
    private final Queue<String>[] cars;
    private Intersection_YI(int len){
        stage_length = len;
        arrivals = new Queue<>();
        cars = new Queue[]{
                new Queue<String>(), //North
                new Queue<String>(), //East
                new Queue<String>(), //South
                new Queue<String>()  //West
        };
    }
    public void addArrival(String car,String dir){
        if(car == null)return;
        arrivals.enqueue(car);
        System.out.printf(dir + "-Bound" + " Car \"%s\" goes \n",car);
    }
    public void tick(){//simulate 1 iteration
        // if stage % 2 == 0 --> addArrival(car[0].deq(),North); addArrival(car[2].deq(),South); else --> addArrival(car[1].deq(),East); addArrival(car[3].deq(),West);
        addArrival(cars[stage % 2].dequeue(),new String[]{"North","East"}[stage%2]);//basically an if statement but without the if
        addArrival(cars[2 + stage % 2].dequeue(),new String[]{"South","West"}[stage%2]);
        step++;
        if(step>=stage_length){
            step = 0;
            stage++;
            System.out.println("stage " + stage + " has started.");
        }
    }
    public void simulate(int stages, Queue<String>[] init, boolean reset){
        for(int i = 0; i < 4; i++){
            cars[i].set(MathFuncs.echo(init[i]));
        }

        while(stage <= stages){
            tick();
        }
        System.out.println("Arrivals: " + arrivals);
        printCars();
        if(reset)reset();
    }
    public void reset(){
        cars[0].clear();
        cars[1].clear();
        cars[2].clear();
        cars[3].clear();
        stage = 0;
        step = 0;
        arrivals.clear();
    }
    public void printCars(){//prints whats remaining
        Queue<String>[] cars2 = cars.clone();
        for(int i = 0; i < 4; i++){
            System.out.println("\n" + new String[]{"North","East","South","West"}[i] + ": ");
            while(!cars2[i].isEmpty()){
                System.out.println(cars2[i].dequeue() + " still remains");
            }
        }
    }
    public static void main(String[] args){
        //1st arg: len; 2nd arg: stages; 3rd arg: North cars; 4th arg: East cars; 5th arg: South cars; 6th arg: West cars; 7th arg: should reset after a run (in case you want to do more than 1
        int l = args.length > 0 && args[0] != null ? Integer.parseInt(args[0]):8;
        int s = args.length > 1 && args[1] != null ? Integer.parseInt(args[1]):2;
        String never = args.length > 2 && args[2] != null ? args[2] : "north car";
        String eat = args.length > 3 && args[3] != null ? args[3] : "east car";
        String soggy = args.length > 4 && args[4] != null ? args[4] : "south car";
        String waffles = args.length > 5 && args[5] != null ? args[5] : "west car";
        boolean r = args.length <= 6 || args[6] == null || Boolean.parseBoolean(args[6]);

        Intersection_YI test = new Intersection_YI(l);
        Queue<String>[] init = new Queue[]{
                new Queue<>(never.split(", ")),
                new Queue<>(eat.split(", ")),
                new Queue<>(soggy.split(", ")),
                new Queue<>(waffles.split(", "))
        };
        test.simulate(s,init,r);
    }
}//4 2 "north car 1, north car 2, north car 3, north car 4, north car 5, north car 6, north car 7, north car 8" "east car 1, east car 2, east car 3" "south car 1, south car 2, south car 3, south car 4, south car 5, south car 6, south car 7" "west car 1, west car 2, west car 3, west car 4, west car 5, west car 6, west car 7, west car 8, west car 9, west car 10, west car 11"
