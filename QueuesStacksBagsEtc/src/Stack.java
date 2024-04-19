package QueuesStacksBagsEtc.src;



import util.MathFuncs;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Stack<K> implements Iterable<K>{
    private K[] elements;
    public Stack(){
        elements = (K[])new Object[]{};
    }
    public Stack(K[] init){
        elements = MathFuncs.reverse(init);
    }
    public K pop(){
        K ret = elements[0];
        if (elements.length > 1){
            elements = slice(elements,1,elements.length);
        }else{
            elements = (K[]) new Object[]{};
        }

        return ret;
    }
    public K peek(){
        return elements[0];
    }
    public void push(K item){
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

    public static void main(String[] args){
        Stack<String> queue1 = new Stack<>();
        queue1.push("hello");
        queue1.push("nothing");
        queue1.push("goodbye");
        System.out.println(queue1);
        int j = queue1.size();
        for(int i = 0; i < j; i++){
            System.out.println(queue1.pop());
        }
        queue1 = new Stack<>(new String[]{"hello","nothing","goodbye"});
        System.out.println(queue1);
    }
}