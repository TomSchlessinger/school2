

/*
Recursive graphics. Koch.java (Links to an external site.)  (Links to an external site.)  takes a command-line argument n and draws a Koch curve of order n. A Koch curve of order 0 is a line segment. To form a Koch curve of order n: Draw a Koch curve of order n−1 Rotate 60° counterclockwise Draw a Koch curve of order n−1 Rotate 120° clockwise Draw a Koch curve of order n−1 Rotate 60° counterclockwise Draw a Koch curve of order n−1 Below are the Koch curves of order 0, 1, 2, and 3.

As the order of the curve increases, how is the area inside the curve affected? Modify the Koch ADT to include a method that implements the area computation to assist you in answering the question. Document clearly your proof. Find these classes in these links:
Tom Schlessinger
11/14/23


area of koch fractal of degree 0: 0.0
area of koch fractal of degree 1: 0.04811252243246881
area of koch fractal of degree 2: 0.06949586573578828
area of koch fractal of degree 3: 0.07899957387059693
area of koch fractal of degree 4: 0.08322344415273411
area of koch fractal of degree 5: 0.08510071983368397
area of koch fractal of degree 6: 0.0859350645807728
area of koch fractal of degree 7: 0.08630588446836783
area of koch fractal of degree 8: 0.08647069330729895
area of koch fractal of degree 9: 0.08654394168015724
area of koch fractal of degree 10: 0.0865764965125387
area of koch fractal of degree 11: 0.08659096532693045
area of koch fractal of degree 12: 0.08659739591110456
area of koch fractal of degree 13: 0.08660025394851528
area of koch fractal of degree 14: 0.08660152418736448
area of koch fractal of degree 15: 0.08660208873796413
area of koch fractal of degree 16: 0.08660233964934175
area of koch fractal of degree 17: 0.08660245116550958
area of koch fractal of degree 18: 0.08660250072825083
area of koch fractal of degree 19: 0.08660252275613584
area of koch fractal of degree 20: 0.08660253254630695
area of koch fractal of degree 21: 0.08660253689749411
area of koch fractal of degree 22: 0.08660253883135507
area of koch fractal of degree 23: 0.08660253969084883
area of koch fractal of degree 24: 0.08660254007284605
area of koch fractal of degree 25: 0.0866025402426226
area of koch fractal of degree 26: 0.08660254031807883
area of koch fractal of degree 27: 0.08660254035161494
area of koch fractal of degree 28: 0.08660254036651988
area of koch fractal of degree 29: 0.0866025403731443
area of koch fractal of degree 30: 0.08660254037608849
area of koch fractal of degree 31: 0.08660254037739701
area of koch fractal of degree 32: 0.08660254037797857
area of koch fractal of degree 33: 0.08660254037823704
area of koch fractal of degree 34: 0.08660254037835192
area of koch fractal of degree 35: 0.08660254037840298
area of koch fractal of degree 36: 0.08660254037842567
area of koch fractal of degree 37: 0.08660254037843576
area of koch fractal of degree 38: 0.08660254037844024
area of koch fractal of degree 39: 0.08660254037844224
area of koch fractal of degree 40: 0.08660254037844313
area of koch fractal of degree 41: 0.08660254037844352
area of koch fractal of degree 42: 0.0866025403784437
area of koch fractal of degree 43: 0.08660254037844378
area of koch fractal of degree 44: 0.08660254037844381
area of koch fractal of degree 45: 0.08660254037844382
area of koch fractal of degree 46: 0.08660254037844382
area of koch fractal of degree 47: 0.08660254037844382
area of koch fractal of degree 48: 0.08660254037844382
area of koch fractal of degree 49: 0.08660254037844382
area of koch fractal of degree 50: 0.08660254037844382
(java cant process that many digits so it cuts off, but you get the gist of it)

for every additional degree of n, we add 4^n triangles that all have 1/9th the area of the previous triangle. we can represent this as
A(n) = A(n-1) + 4^(n) * 1/9^(n+1) * Area of equilateral triangle w side length of 1 = (4^(n)/9^(n+1) * sqrt(3)/4) + A(n-1) where A(0) = 0
*/

package RecursiveGraphics.src;

import Princeton.src.*;

class KochArea_TS {
    public static double kochArea(int n){
        if(n == 0){
            return 0;
        }
        return Math.pow(4.0,n-1)*Math.pow(1/9.0, n) * Math.sqrt(3)/4 + kochArea(n-1);
    }
    public static void main(String[] args) {
        Koch.koch(8, 1.0/Math.pow(3.0,8), new Turtle(0,0,0));
        for(int i = 0; i < 50; i++){
            System.out.println("area of koch fractal of degree " + i + ": " + kochArea(i));
        }

    }
}
