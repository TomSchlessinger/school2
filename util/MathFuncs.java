package util;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public class MathFuncs {
    public static <K> List<K> inPlaceAdd(ArrayList<K> init, K item){
        init.add(item);
        return init;
    }
    public static <K> K[] inPlaceToArray(List<K> init){
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init.get(0).getClass().getComponentType(), 0);
        return init.toArray(ret);
    }
    public static <T> T[] flatten2d(T[] [] arr){
        List<T> streamList = new ArrayList<>();
        for (T[] array : arr) {
            streamList.addAll(Arrays.asList(array));
        }
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(arr[0].getClass().getComponentType(), 0);
        return streamList.toArray(ret);
    }
    public static byte[] flatten2dbyte(byte[][] arr){
        List<Byte> streamList = new ArrayList<>();
        for(byte[] array: arr){
            for(byte i : array){
                streamList.add(i);
            }
        }
        byte[] ret = new byte[streamList.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i]=streamList.get(i);
        }
        return ret;
    }

    public static <T> T[] join(T [] l1, T [] l2){
        List<T> joined = new ArrayList<>(l1.length + l2.length);
        Collections.addAll(joined, l1);
        Collections.addAll(joined, l2);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(l1.getClass().getComponentType(), 0);
        return joined.toArray(ret);
    }
    public static String reverse(String original){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < original.length(); i++){
            ret.append(original.charAt(original.length()-1-i));
        }
        return ret.toString();
    }
    public static char[] reverse(char [] original){
        char[] ret = new char[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static int[] reverse(int [] original){
        int[] ret = new int[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static boolean[] reverse(boolean [] original){
        boolean[] ret = new boolean[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static double[] reverse(double [] original){
        double[] ret = new double[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static float[] reverse(float [] original){
        float[] ret = new float[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static long[] reverse(long [] original){
        long[] ret = new long[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static <T> T[] reverse(T [] original){
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(original[0].getClass(),original.length);
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static <T,R> R[] mapFunc(Function<T,R> f, T [] original){
        final List<R> type = new ArrayList<>();
        type.add(f.apply(original[0]));
        @SuppressWarnings("unchecked")
        R[] ret = (R[]) Array.newInstance(type.get(0).getClass(),original.length);
        for(int i = 0; i < original.length; i++){
            ret[i] = f.apply(original[i]);
        }
        return ret;
    }
    public static <T,R extends T> T nest(Function<T,R> f, R expr, int n){
        assert n > 0;
        R ret = f.apply(expr);
        n--;
        while(n > 0){
            ret = f.apply(ret);
            n--;
        }
        return ret;
    }
    //{a,b,c,d} -> {b,c,d,a}
    public static <T> T[] rotateLeft(T[] initial){
        List<T> list = new ArrayList<>(List.of(initial));
        T temp = list.get(0);
        list.remove(0);
        list.add(temp);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(initial[0].getClass(),initial.length);
        return list.toArray(ret);
    }
    public static <T> T [] rotateLeft(T[] initial, int iter){
        return nest(MathFuncs::rotateLeft,initial,iter);
    }
    //{a,b,c,d} -> {d,a,b,c}
    public static <T> T [] rotateRight(T[] initial){
        List<T> list = new ArrayList<>(List.of(initial));
        T temp = list.get(list.size()-1);
        list.remove(list.size()-1);
        list.add(0,temp);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(initial[0].getClass(),initial.length);
        return list.toArray(ret);
    }
    public static <T> T [] rotateRight(T[] initial, int iter){
        return nest(MathFuncs::rotateRight,initial,iter);
    }

    public static Integer [] range(int i, int start){
        Integer[] ret = new Integer[i];
        for(int j = 0; j < i; j++){
            ret[j] = start + j;
        }
        return ret;
    }

    public static Integer[] range(int i){
        return range(i,0);
    }

    public static <K> K [] slice(K [] init, int start, int end){
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init[0].getClass(), end - start);
        if (end - start >= 0) System.arraycopy(init, start, ret, 0, end - start);
        return ret;
    }
    public static <K> K [] slice(K [] init, int end){
        return slice(init,0,end);
    }

    public static <K> K [] deleteDuplicates(K[] init) {
        List<K> list = new ArrayList<>(List.of(init));
        List<K> ret1 = new ArrayList<>(new HashSet<>(list));
        @SuppressWarnings("unchecked")
        K[] b = (K[]) Array.newInstance(init[0].getClass(), 0);
        return ret1.toArray(b);
    }
    //when I need to ill make a version of this for doubles, bytes, chars, etc.
    public static int[] deleteDuplicates(int[] init) {
        List<Integer> list = new ArrayList<>();
        for(int i : init){
            list.add(i);
        }
        List<Integer> ret = new ArrayList<>(new HashSet<>(list));
        int[] ret1 = new int[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            ret1[i] = ret.get(i);
        }
        return ret1;
    }

    public static String deleteDuplicates(String init){
        Character[] fin = new Character[init.length()];
        for(int i = 0; i < init.length();i++){
            fin[i] = init.charAt(i);
        }
        return new ArrayList<>(List.of(deleteDuplicates(fin))).toString()
                .replaceAll(", ","")
                .replaceAll("]","")
                .replace("[","");
    }
    public static int factorial(int i){
        return i==0?1:i*factorial(i-1);
    }

    public static <K> K[] echoArr(K[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static double[] echoArr(double[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static int[] echoArr(int[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static boolean[] echoArr(boolean[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static long[] echoArr(long[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static <K> K echo(K ret, String s){
        System.out.println(ret.toString() + s);
        return ret;
    }

    public static <K> K echo(String s, K ret){
        System.out.println(s + ret.toString());
        return ret;
    }

    public static <K> K echo(K ret){
        System.out.println(ret.toString());
        return ret;
    }
    public static <K> K[] randomSample(K[] init){
        return randomSample(init,-1,0);
    }
    public static <K> K[] randomSample(K[] init, int n){
        return randomSample(init,n,0);
    }
    public static <K> K[] randomSample(K[] init, int n, int index){
        List<K> shuffled = Arrays.asList(init);
        Collections.shuffle(shuffled);
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init[0].getClass(), 0);
        return n > index ? slice(shuffled.toArray(ret),index,n-1):shuffled.toArray(ret);
    }

    public static int binaryToInt(Boolean[] bools){
        int ret = 0;
        for(int i = 0; i < bools.length; i++){
            ret+=bools[i]?Math.pow(2,bools.length-i-1):0;
        }
        return ret;
    }

    public static String intToBinary(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.insert(0, ((n % 2) == 0 ? "0" : "1"));
            n /= 2;
        }
        return s.toString();
    }
    public static String stringToBinary(String s){
        StringBuilder bin = new StringBuilder();
        for(char i : s.toCharArray()){
            bin.append(Integer.toBinaryString(i));
        }
        return bin.toString();
    }
    public static String XOR(String a, String b){
        StringBuilder ret = new StringBuilder();
        if(a.length() > b.length())a = "0".repeat(a.length() - b.length()) + a;//THIS EXISTS???
        else if(b.length() > a.length()) b = "0".repeat(b.length() - a.length()) + b;//THIS EXISTS???
        for(int i = 0; i < a.length(); i ++) ret.append(!(a.charAt(i)==b.charAt(i)) ? "1":"0");
        return ret.toString();
    }

    public static <R> boolean in(R item, R[] array){
        for(R i: array)if(i.equals(item))return true;
        return false;
    }
    public static <R> boolean in(R item, Collection<R> array){
        for(R i: array)if(i.equals(item))return true;
        return false;
    }

    public static Integer[] toInteger(int[] init){
        Integer[] ret = new Integer[init.length];
        for(int i = 0; i < init.length; i ++){
            ret[i]=init[i];
        }
        return ret;
    }

    public static int[] toInt(Collection<Integer> init){
        int[] ret = new int[init.size()];
        int counter = 0;
        for(Integer i : init)
            ret[counter++] = i;
        return ret;
    }
    public static List<Integer> randomList(int len){
        return randomList(len,Integer.MAX_VALUE);
    }

    public static List<Integer> randomList(int len, int max){
        return randomList(len,0,max);
    }

    public static List<Integer> randomList(int len, int min, int max){
        return randomList(len,-1, min,max);
    }

    public static List<Integer> randomList(int len, int seed, int min, int max){
        Random random;
        random = seed == -1 ? new Random() : new Random(seed);
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < len; i++){
            ret.add(random.nextInt(min,max));
        }
        return ret;
    }
}
