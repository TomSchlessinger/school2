package Sorting.src;

import java.util.*;

import Princeton.src.Stopwatch;
import util.MathFuncs;

public class RadixSort {
    public static Stopwatch watch = new Stopwatch();
    public static int getMax(Integer[] arr){
        int max = arr[0];
        for (int i : arr)max = Math.max(i, max);
        return max;
    }
    public static Integer[] inPlaceRadixSort(Integer[] arr, int base){
        int max = getMax(arr);
        int digits = (int) Math.ceil(Math.log10(max));
        int len = arr.length;
        int[] buckets = new int[base];
        for(int i = 0; i < digits; i++){
            int deg = (int)Math.pow(base,i);
            Arrays.fill(buckets,0);
            for(Integer j : arr){
                buckets[(j/deg)%base]++;
            }
            for(int j = 1; j < base; j++){
                buckets[j]+=buckets[j-1];
            }
            System.out.println(Arrays.toString(buckets));
            int iter = len-1;
            ArrayList<Integer> seen = new ArrayList<>();
            for(int u = 0; u < len; u++){
                while(MathFuncs.in(iter,seen))iter--;
                int index = --buckets[(arr[iter]/deg)%base];
                seen.add(index);
                System.out.println("ITER: " + iter + "   CURRENT: " + arr[iter] + "   INDEX: " + index);
                int temp = arr[index];
                arr[index] = arr[iter];
                arr[iter] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
    public static Integer[] radixSort(Integer[] arr, int base){
        int max = getMax(arr);
        int digits = (int) Math.ceil(Math.log10(max));
        for(int i = 0; i < digits; i++){
            ArrayList<Integer>[] buckets = new ArrayList[base];
            for(int a = 0; a < base; a++ ){
                buckets[a] = new ArrayList<>();
            }
            int deg = (int)Math.pow(base,i);
            for(int j : arr){
                buckets[(j/deg)%base].add(j);
            }
            //ArrayList<Integer>[] --> Integer[][] --> Integer[]
            arr = MathFuncs.flatten2d(MathFuncs.mapFunc(t->t.toArray(new Integer[]{}),buckets));
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i : MathFuncs.range(10)){
            if(i%2==0)continue;
            System.out.println(i);
        }
//        int n = args[0]!=null ? Integer.parseInt(args[0]) : 100;
//        Integer[] arr = new Integer[n];
//        Random r = new Random();
//        for(int i = 0; i < n; i++) {
//            arr[i] = Math.abs(r.nextInt(0,100));
//        }
//
//        System.out.println("Unsorted Array: " + Arrays.toString(arr));
//        //System.out.println("Sorted Array:" + Arrays.toString(radixSort(arr,10)));
//        System.out.println("Sorted Array:" + Arrays.toString(inPlaceRadixSort(arr,10)));
    }
}
