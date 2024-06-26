package QueuesStacksBagsEtc.src;

import util.MathFuncs;

import java.lang.reflect.Array;
import java.util.*;

public class Queue<K> implements QueueADT<K>, Iterable<K>{
    private K[] elements;
    public Queue(){
        elements = (K[])new Object[]{};
    }
    public Queue(K[] init){
        elements = MathFuncs.reverse(init);
    }

    @Override
    public K dequeue(){
        if(elements.length == 0)return null;
        K ret = elements[elements.length-1];
        elements = slice(elements,0,elements.length-1);
        return ret;
    }

    @Override
    public K first(){
        return elements[elements.length-1];
    }

    @Override
    public void enqueue(K item){
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(item.getClass(),elements.length+1);
        ret[0] = item;
        /*for(int i = 1; i <= elements.length; i++){
            ret[i] = elements[i-1];
        }ONCE AGAIN intellij recommended me to do this*/
        System.arraycopy(elements, 0, ret, 1, elements.length);
        elements=ret;
    }
    public void set(Iterable<K> items){
        clear();
        for(K i : items)enqueue(i);
    }
    public void set(K[] items){
        clear();
        for(K i : items)enqueue(i);
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder().append("[");
        for(int i = 0; i < elements.length; i++){
            s.append(elements[i].toString()).append(i==elements.length-1?"":", ");
        }
        return s.append("]").toString();
    }

    public static <K> K[] slice(K[] init, int start, int end){
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init[0].getClass(), end - start);
        if (end - start >= 0) System.arraycopy(init, start, ret, 0, end - start);
        return ret;
    }
    public int size(){
        return elements.length;
    }
    @Override
    public boolean isEmpty(){
        return elements.length==0;
    }
    public K[] asArray(){return elements;}
    public void clear(){
        elements =  (K[])new Object[]{};
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<>(){
            private int i = elements.length-1;

            public boolean hasNext() {
                return i >= 0;
            }

            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                return elements[i--];
            }
        };
    }
    public static void main(String[] args){
        Queue<String> queue1 = new Queue<>();
        queue1.enqueue("hello");
        queue1.enqueue("nothing");
        queue1.enqueue("goodbye");
        System.out.println(queue1);
        int n = queue1.size();
        for(int i = 0; i < n; i++){
            System.out.println(queue1.dequeue());
        }
        queue1 = new Queue<>(new String[]{"hello","nothing","goodbye"});
        System.out.println(queue1);
    }
}