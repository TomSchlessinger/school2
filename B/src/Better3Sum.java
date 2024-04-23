package B.src;

import util.MathFuncs;

import java.util.*;

//ima make it O(n^2) instead
public class Better3Sum {
    public static void main(String[] args){
        Set<Triple<Integer,Integer,Integer>> answers = new HashSet<>();
        int[] nums = MathFuncs.toInt(MathFuncs.randomList(25,-100,100));
        Map<Integer,Set<Pair<Integer,Integer>>> added = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            for(int j = 0; j < nums.length; j ++){
                if(!added.containsKey(nums[i] + nums[j])){
                    added.put(nums[i] + nums[j], new HashSet<>());
                }
                if(i!=j){
                    added.get(nums[i] + nums[j]).add(new Pair<>(i,j));
                }
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(added.containsKey(-1 * nums[i])){
                for(Pair<Integer,Integer> pair : added.get(-1 * nums[i])){
                    if(pair.getLeft()!=i && pair.getRight()!=i){
                        answers.add(Triple.sorted(new Triple<>(pair.getLeft(),pair.getRight(),i)));
                    }
                }
            }
        }

        System.out.println("original array: " + Arrays.toString(nums));
        for(Triple<Integer,Integer,Integer> answer:answers){
            System.out.println("indexes " + answer + " sum up to 0");
        }
    }
    public static class Triple<A,B,C>{
        private final A a;
        private final B b;
        private final C c;
        public Triple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public A getLeft() {
            return a;
        }
        public B getMiddle() {
            return b;
        }
        public C getRight() {
            return c;
        }
        public boolean equals(Triple<A,B,C> t){
            return t.getRight() == a && t.getMiddle() == b && t.getLeft() == c;
        }
        public String toString(){return "(" + a + ", " + b + ", " + c + ")";}
        public static <A extends Number,B extends Number,C extends Number> Triple<A,B,C> sorted(Triple<A,B,C> triple){
            Number[] r = new Number[]{triple.getLeft(),triple.getMiddle(),triple.getRight()};
            Arrays.sort(r);
            return (Triple<A,B,C>) new Triple<>(r[0],r[1],r[2]);
        }
    }
    public static class Pair<A,B>{
        private final A a;
        private final B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
        public final A getLeft() {
            return a;
        }
        public final B getRight() {
            return b;
        }
    }
}
