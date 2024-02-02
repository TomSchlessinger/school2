package RecursiveGraphics.src;

/*
Your program shall take one integer command-line argument N, to control the depth of the recursion. All of the drawings should fit snugly inside the equilateral triangle with endpoints (0, 0), (1, 0), and (1/2, √3/2). Do NOT change the scale of the drawing window. First, make sure that your program draws a single filled equilateral triangle when N equals 1. Then, check that it draws four filled equilateral triangles when N equals 2. Your program will be nearly (or completely) debugged when you get to this point.
Tom Schlessinger
something like 10/10/23; I finished this ages ago I just hate readme's
Input: 1 num, 1 string, 1 num, then 2 bool values representing(in order):
1st number: degree of sierpinski (5 by default)
1st string: S for sierpinski triangle, anything else is the recursive graphic I invented (S by default)
2nd num: amount of colors (255 by default), if grayscale it's 256 colors, otherwise its 256^3 colors (all of them)
1st boolean: whether or not the color list should be shuffled
2nd boolean: whether or not the color list should be in grayscale
*/

import StdDraw.src.StdDraw;
import util.MathFuncs;
import java.lang.Math;
import java.awt.*;

public class Sierpinski_TS {
    private static double c;
    private static boolean g;
    public static Color[] colors;
    public static double mid(double a, double b){return (a+b)/2;}

    public static void recursiveGraphic(int n, double[] x, double[] y){
        if(n>-1) {
            StdDraw.setPenColor(colors[n % (int) Math.pow(c, g ? 1 : 3)]);
            StdDraw.filledPolygon(x, y);
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] / 2.0;
                y[i] = y[i] / 2.0;
            }
            recursiveGraphic(n - 1, y, x);
        }
    }
    public static void sierpinski(int n){//(0, 0), (1, 0), and (1/2, √3/2)
        sierpinski(new double[]{0,1,0.5},new double[]{0,0,Math.sqrt(3)/2}, false, n);
    }
    public static void sierpinski(double[] x, double[] y,boolean filled, int n){//(0, 0), (1, 0), and (1/2, √3/2)

        StdDraw.setPenColor(colors[n%(int)Math.pow(c,g ? 1:3)]);
        StdDraw.filledPolygon(x,y);
        if (n > 0) {
            sierpinski(
                    new double[]{x[0],mid(x[0],x[1]),mid(x[0],x[2])},
                    new double[]{y[0],mid(y[0],y[1]),mid(y[0],y[2])},
                    !filled,n-1
            );
            sierpinski(
                    new double[]{x[1],mid(x[0],x[1]),mid(x[1],x[2])},
                    new double[]{y[1],mid(y[0],y[1]),mid(y[1],y[2])},
                    !filled,n-1
            );
            sierpinski(
                    new double[]{x[2],mid(x[2],x[1]),mid(x[0],x[2])},
                    new double[]{y[2],mid(y[2],y[1]),mid(y[0],y[2])},
                    !filled,n-1
            );
        }
    }

    public static void main(String[] args){
        c = args.length > 2 ? Double.parseDouble(args[2]) : 255;//colors
        boolean r = args.length > 3 ? Boolean.parseBoolean(args[3]) : true;//randomized
        g = args.length > 4 ? Boolean.parseBoolean(args[4]) : false;//grayscale
        String t = args.length > 1 ? args[1]:"S";
        colors = MathFuncs.flatten2d(
                MathFuncs.mapFunc(
                        k->MathFuncs.flatten2d(
                                MathFuncs.mapFunc(
                                        j->MathFuncs.mapFunc(
                                                i->Color.getHSBColor(
                                                        Color.RGBtoHSB(
                                                                (int)(i * 255/(c-1)), (int)(j * 255/(c-1)), (int)(k * 255/(c-1)),
                                                                null)[0],
                                                        Color.RGBtoHSB(
                                                                (int)(i * 255/(c-1)), (int)(j * 255/(c-1)), (int)(k * 255/(c-1)),
                                                                null)[1],
                                                        Color.RGBtoHSB(
                                                                (int)(i * 255/(c-1)), (int)(j * 255/(c-1)), (int)(k * 255/(c-1)),
                                                                null)[2]
                                                ),MathFuncs.range((int)c)
                                        ),MathFuncs.range((int)c))
                        ),MathFuncs.range((int)c)));
        if(g)colors = MathFuncs.mapFunc(k->Color.getHSBColor(Color.RGBtoHSB((int)(k * 255/(c-1)),(int)(k * 255/(c-1)),(int)(k * 255/(c-1)),null)[0],Color.RGBtoHSB((int)(k * 255/(c-1)),(int)(k * 255/(c-1)),(int)(k * 255/(c-1)),null)[1],Color.RGBtoHSB((int)(k * 255/(c-1)),(int)(k * 255/(c-1)),(int)(k * 255/(c-1)),null)[2]),MathFuncs.range((int)c));
        if(r)colors=MathFuncs.randomSample(colors);//disregard the disgusting code that I used to declare colors
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 5;


        System.out.println("amount of colors: " + colors.length);
        if(t.equals("S")){
            sierpinski(n);
        }else{
            //new double[]{0,1,0,1},new double[]{0,0,1,1}
            double[] shapex = new double[]{0,1,0.5};
            double[] shapey = new double[]{0,0,Math.sqrt(3)/2};
            recursiveGraphic(n,shapex,shapey);
        }
    }
}
