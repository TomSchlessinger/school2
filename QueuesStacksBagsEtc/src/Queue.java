package QueuesStacksBagsEtc.src;

import QueuesStacksBagsEtc.src.QueueADT;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Queue<K> implements QueueADT<K>, Iterable<K>{
    private K[] elements;
    public Queue(){
        elements = (K[])new Object[]{};
    }
    public Queue(K[] init){
        elements = init;
    }

    @Override
    public K dequeue(){
        K ret = elements[elements.length-1];
        elements = slice(elements,1,elements.length-1);
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

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(K i : elements){
            s.append(i.toString());
        }
        return s.toString();
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


    @Override
    public Iterator<K> iterator() {
        return new Iterator<>(){
            private int i = 0;

            public boolean hasNext() {
                return i < elements.length;
            }

            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                return elements[i++];
            }
        };
    }
}