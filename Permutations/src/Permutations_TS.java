/*
4. Every program has to have a header:
Write a program PermutationsK_RK.java that two command-line arguments n and k (Scanner is an option), and prints out all

P(n,k)=n!/(n−k)!

permutations that contain exactly k of the n elements. Below is the desired output when k = 2 and n = 4 (again, do not worry about the order):

ab ac ad ba bc bd ca cb cd da db dc

Tom Schlessinger
10/6/23
Input: 2-3 numbers representing n, k, and the index of the characters

Testing:
n = 4, k = 2, o = 21000 -->
Available Characters:[刈, 刉, 刊, 刋]
Permutations: 12
Correct amount of permutations: 12
Available Characters:[刈, 刉, 刊, 刋]
Permutations:[刉刈, 刊刈, 刋刈, 刈刉, 刊刉, 刋刉, 刈刊, 刉刊, 刋刊, 刈刋, 刉刋, 刊刋]

n = 10, k = 1, o = 7000
Available Characters:[᭘, ᭙, ᭚, ᭛, ᭜, ᭝, ᭞, ᭟, ᭠, ᭡]
Permutations: 10
Correct amount of permutations: 10
Available Characters:[᭘, ᭙, ᭚, ᭛, ᭜, ᭝, ᭞, ᭟, ᭠, ᭡]
Permutations:[᭘, ᭙, ᭚, ᭛, ᭜, ᭝, ᭞, ᭟, ᭠, ᭡]

n = 5, k = 5, o = 12321
Available Characters:[〡, 〢, 〣, 〤, 〥]
Permutations: 120
Correct amount of permutations: 120
Available Characters:[〡, 〢, 〣, 〤, 〥]
Permutations:[〥〤〣〢〡, 〤〥〣〢〡, 〥〣〤〢〡, 〣〥〤〢〡, 〤〣〥〢〡, 〣〤〥〢〡, 〥〤〢〣〡, 〤〥〢〣〡, 〥〢〤〣〡, 〢〥〤〣〡, 〤〢〥〣〡, 〢〤〥〣〡, 〥〣〢〤〡, 〣〥〢〤〡, 〥〢〣〤〡, 〢〥〣〤〡, 〣〢〥〤〡, 〢〣〥〤〡, 〤〣〢〥〡, 〣〤〢〥〡, 〤〢〣〥〡, 〢〤〣〥〡, 〣〢〤〥〡, 〢〣〤〥〡, 〥〤〣〡〢, 〤〥〣〡〢, 〥〣〤〡〢, 〣〥〤〡〢, 〤〣〥〡〢, 〣〤〥〡〢, 〥〤〡〣〢, 〤〥〡〣〢, 〥〡〤〣〢, 〡〥〤〣〢, 〤〡〥〣〢, 〡〤〥〣〢, 〥〣〡〤〢, 〣〥〡〤〢, 〥〡〣〤〢, 〡〥〣〤〢, 〣〡〥〤〢, 〡〣〥〤〢, 〤〣〡〥〢, 〣〤〡〥〢, 〤〡〣〥〢, 〡〤〣〥〢, 〣〡〤〥〢, 〡〣〤〥〢, 〥〤〢〡〣, 〤〥〢〡〣, 〥〢〤〡〣, 〢〥〤〡〣, 〤〢〥〡〣, 〢〤〥〡〣, 〥〤〡〢〣, 〤〥〡〢〣, 〥〡〤〢〣, 〡〥〤〢〣, 〤〡〥〢〣, 〡〤〥〢〣, 〥〢〡〤〣, 〢〥〡〤〣, 〥〡〢〤〣, 〡〥〢〤〣, 〢〡〥〤〣, 〡〢〥〤〣, 〤〢〡〥〣, 〢〤〡〥〣, 〤〡〢〥〣, 〡〤〢〥〣, 〢〡〤〥〣, 〡〢〤〥〣, 〥〣〢〡〤, 〣〥〢〡〤, 〥〢〣〡〤, 〢〥〣〡〤, 〣〢〥〡〤, 〢〣〥〡〤, 〥〣〡〢〤, 〣〥〡〢〤, 〥〡〣〢〤, 〡〥〣〢〤, 〣〡〥〢〤, 〡〣〥〢〤, 〥〢〡〣〤, 〢〥〡〣〤, 〥〡〢〣〤, 〡〥〢〣〤, 〢〡〥〣〤, 〡〢〥〣〤, 〣〢〡〥〤, 〢〣〡〥〤, 〣〡〢〥〤, 〡〣〢〥〤, 〢〡〣〥〤, 〡〢〣〥〤, 〤〣〢〡〥, 〣〤〢〡〥, 〤〢〣〡〥, 〢〤〣〡〥, 〣〢〤〡〥, 〢〣〤〡〥, 〤〣〡〢〥, 〣〤〡〢〥, 〤〡〣〢〥, 〡〤〣〢〥, 〣〡〤〢〥, 〡〣〤〢〥, 〤〢〡〣〥, 〢〤〡〣〥, 〤〡〢〣〥, 〡〤〢〣〥, 〢〡〤〣〥, 〡〢〤〣〥, 〣〢〡〤〥, 〢〣〡〤〥, 〣〡〢〤〥, 〡〣〢〤〥, 〢〡〣〤〥, 〡〢〣〤〥]

n = 10, k = 5, o = 1488
check longoutput.txt

n = 6, k = 3
Available Characters:[a, b, c, d, e, f]
Permutations: 120
Correct amount of permutations: 120
Available Characters:[a, b, c, d, e, f]
Permutations:[cba, dba, eba, fba, bca, dca, eca, fca, bda, cda, eda, fda, bea, cea, dea, fea, bfa, cfa, dfa, efa, cab, dab, eab, fab, acb, dcb, ecb, fcb, adb, cdb, edb, fdb, aeb, ceb, deb, feb, afb, cfb, dfb, efb, bac, dac, eac, fac, abc, dbc, ebc, fbc, adc, bdc, edc, fdc, aec, bec, dec, fec, afc, bfc, dfc, efc, bad, cad, ead, fad, abd, cbd, ebd, fbd, acd, bcd, ecd, fcd, aed, bed, ced, fed, afd, bfd, cfd, efd, bae, cae, dae, fae, abe, cbe, dbe, fbe, ace, bce, dce, fce, ade, bde, cde, fde, afe, bfe, cfe, dfe, baf, caf, daf, eaf, abf, cbf, dbf, ebf, acf, bcf, dcf, ecf, adf, bdf, cdf, edf, aef, bef, cef, def]
*/

package Permutations.src;
import util.MathFuncs;
import java.util.*;

public class Permutations_TS {
    //list of EVERY character lol -> 65535 bytes 😬😬😬
    public static Character[] chars = MathFuncs.mapFunc((i->(char)i.intValue()),MathFuncs.range(65535));
    public static String[] getPerms(String[] init, Character[] chars){
        String[][] ret = new String[chars.length][init.length];
        for(int i = 0; i < chars.length; i++){
            for(int j = 0; j < init.length; j++){
                ret[i][j] = init[j] + chars[i];
            }
        }
        ArrayList<String> finalRet = new ArrayList<>();
        for(String i : MathFuncs.flatten2d(ret)) if(MathFuncs.deleteDuplicates(i).length() == i.toCharArray().length)finalRet.add(i);
                //if deleting duplicate characters doesn't change its size, add it to the final list
        return finalRet.toArray(new String[0]);
    }
    public static String[] perms(int k, int n, int o){
        Character[] subArr = MathFuncs.slice(chars, o, o + n);
        System.out.println("Available Characters:" + Arrays.toString(subArr));
        return MathFuncs.nest(i -> getPerms(i,subArr),new String[]{""},k);
    }
    public static String[] perms(int k, int n){return perms(k,n,97);}
    public static void main(String[] args) {
        //System.out.println(new ArrayList<>(List.of(chars)).indexOf('א'));
        int n = args.length > 1 ? Integer.parseInt(args[0]) : 4;
        int k = args.length > 0 ? Integer.parseInt(args[1]) : 2;
        //0 = 1488 -> hebrew
        //o~7000 gives you chinese characters
        int o = args.length > 2 ? Integer.parseInt(args[2]) : -1;//65 for capital letters; I don't suggest o < 65 because some characters are kinda  or 
        System.out.println("Permutations: " + (o != -1 ? perms(k,n,o):perms(k,n)).length);
        System.out.println("Correct amount of permutations: " + MathFuncs.factorial(n)/MathFuncs.factorial(n-k));//k!/(k-p)!
        System.out.println("Permutations:" + Arrays.toString(o != -1 ? perms(k,n,o):perms(k,n)));

    }

}
