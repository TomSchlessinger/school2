package QueuesStacksBagsEtc.src;

public class Intersection_YI {
    public final Queue<String> arrivals;
    public final Queue<String>[] cars;
    public Intersection_YI(){
        arrivals = new Queue<>();
        cars = new Queue[]{
                new Queue<String>(), //North
                new Queue<String>(), //East
                new Queue<String>(), //South
                new Queue<String>()  //West
        };
    }
}
