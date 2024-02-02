package Hadamard.src;

import util.MathFuncs;
import java.util.function.Consumer;
import java.util.function.Function;

public class WalshHadamard {


    static Integer[][] walshArray(int i){
        Function<Integer[][],Integer[][]> gneerate_hagramragrg_matrax_step = (Integer[][] arr) -> MathFuncs.join(
                MathFuncs.mapFunc(
                        (Integer[] p) -> MathFuncs.flatten2d(
                                MathFuncs.mapFunc((Integer s) -> new Integer[]{s,s},p)),arr),
                MathFuncs.mapFunc(
                        (Integer[] p) -> MathFuncs.flatten2d(
                                MathFuncs.mapFunc((Integer s) -> new Integer[]{s,-1*s},p)),MathFuncs.reverse(arr))
        );
        return MathFuncs.nest(gneerate_hagramragrg_matrax_step,new Integer[][]{{1}}, i);
    }


    public static void main(String[] args) {
        int n;
        n = args.length > 0 ? Integer.parseInt(args[0]):8;
        Integer[][] a = walshArray(n);
        System.out.println("walsh matrix");
        for(Integer[] i : a){

            for(Integer b : i){
                System.out.print(b<=0?"   ":"███");//doesnt matter what it prints, this is just so it looks cool. Ideally, I write some openGL or js stuff or that will display it in a cool way.
            }
            System.out.println();
        };
    }
}

/*
Credit to:
Weisstein, Eric W. "Hadamard Matrix." From MathWorld--A Wolfram Web Resource. https://mathworld.wolfram.com/HadamardMatrix.html
for the Walsh Array algorithm(although I did modify it a bit so that it would use fewer functions

original algorithm(written in mathematica):
WalshArray[s_] := With[{g = Reverse},
    Nest[ (*Nest[<function> f,<initial> x,<cyc> i] nests the function f starting on the initial state x i times*)
       Join[ (*Join just joins lists together*)
          Map[ (*Map[<function> f,<list> list] maps function f on the elements of list l*)
           Flatten[ (*Flatten turns an n-dimensional list to a 1-dimensional list*)
             Map[{#, #} &, #] (* # is a placeholder for a pure function indicated by the '&' *)
             ] &, #
           ],
          Map[
           Flatten[
             Map[
              {#, -#} &, #
              ]
             ] &, g[#]]
         ] &, {{1}}, s
       ]
      ]

*/
















//I kinda stole these function from Wolfram, but I programmed them by myself and it made me lose my mind!!!