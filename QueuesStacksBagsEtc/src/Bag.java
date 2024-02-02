package QueuesStacksBagsEtc.src;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<K> implements Iterable<K>{

    private K[] elements;
    public Bag(){
        elements = (K[])new Object[]{};
    }
    public Bag(K[] init){
        elements = init;
    }
    public void add(K item){
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

    public boolean isEmpty(){return elements.length==0;}
    public int size(){return elements.length;}

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
