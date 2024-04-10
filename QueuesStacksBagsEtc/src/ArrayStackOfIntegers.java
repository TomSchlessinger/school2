/*

*/

package QueuesStacksBagsEtc.src;

import util.MathFuncs;

public class ArrayStackOfIntegers {
    private final Stack<Float> stack;
    public ArrayStackOfIntegers(){
        stack = new Stack<>();
    }
    public float evaluate(String expr){
        float op1, op2, result = 0;
        for (String s : expr.split(" ")){
            if(s.equals("-") || s.equals("+") || s.equals("*") || s.equals("/")){
                op2 = stack.pop();
                op1 = stack.pop();
                result = switch (s){
                    case "-" -> op1-op2;
                    case "+" -> op1+op2;
                    case "*" -> op1*op2;
                    case "/" -> op1/op2;
                    default -> 0;
                };
                stack.push(result);
            }else {
                stack.push(Float.parseFloat(s));
            }
            System.out.println(stack + "  :  " + result);
        }
        return result;
    }




    public static void main(String[] args){
        //input args by putting underscore between values
        String[] inputs = MathFuncs.mapFunc(i -> i.replace("_"," "),args);//I love this 'library' I made
//        String input = "8 4 -3 * 1 5 + / *";
        ArrayStackOfIntegers evaluator = new ArrayStackOfIntegers();
//        System.out.println(input + " was evaluated as " + evaluator.evaluate(input));
        for (String i : inputs){
            System.out.println(i + " was evaluated as " + evaluator.evaluate(i));
        }
    }
}
