package QueuesStacksBagsEtc.src;

import util.MathFuncs;

public class QSPalindrome_YI {
    public static Character[] toChars(String s){
        Character[] ret = new Character[s.length()];
        for(int i = 0; i < s.length(); i++){
            ret[i]=s.charAt(i);
        }
        return ret;
    }
    public static boolean isPalindrome(String s, boolean b){
        Queue<Character> q = new Queue<>(toChars(s));
        Stack<Character> stack = new Stack<>(toChars(s));
        for(int i =0; i < q.size(); i++){
            if(b) {
                if (Character.toLowerCase(q.dequeue()) != Character.toLowerCase(stack.pop())) {
                    return false;
                }
            }else{
                if (!q.dequeue().equals(stack.pop())) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        String s = args.length > 0 && args[0] != null ? args[0] : "thisIsNotAPalindrome";
        boolean b = args.length > 1 && args[1] != null && Boolean.parseBoolean(args[1]);//ignore caps

        System.out.println('"'+s+'"' + " is" + (isPalindrome(s,b) ? "":"n't") + " a palindrome");

    }
}
