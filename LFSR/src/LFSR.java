package LFSR.src;

import Princeton.src.*;

import util.MathFuncs;

public class LFSR {
    private Boolean[] seedArr;
    private final int tap;
    public LFSR(Boolean[] seed, int tap){
        this.tap = tap;
        this.seedArr = seed;
    }
    public LFSR(String seed, int t){
        Boolean[] finalSeed = new Boolean[seed.length()];
        for(int i = 0; i < seed.length();i++){
            finalSeed[i] = seed.charAt(i)=='1';
        }
        this.tap=t;
        this.seedArr=finalSeed;
    }
    public Boolean[] generate(int n){
        for(int i = 0; i < n; i++){
            step();
        }
        return MathFuncs.slice(seedArr,seedArr.length-n,seedArr.length);//first n bits = last n indexes of array
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(Boolean i : seedArr) ret.append(i ? "1" : "0");
        return ret.toString();
    }

    public int length(){return seedArr.length;}

    // returns bit i as 0 or 1.
    public Boolean bitAt(int i){return seedArr[seedArr.length-i-1];}
    // simulates one step of this LFSR and return the new bit as 0 or 1
    public Boolean step(){
        Boolean tapped = seedArr[seedArr.length-tap];
        Boolean temp = seedArr[0];
        seedArr = MathFuncs.rotateLeft(seedArr);
        return seedArr[seedArr.length-1] = temp ^ tapped;
    }

    public static void main(String[] args){
        /*
        //Picture picture = new Picture();
        LFSR lfsr20 = new LFSR("01101000010100010000", 17);
        StdOut.println(lfsr20);
        for (int i = 0; i < 10; i++) {
            Boolean[] r = lfsr20.generate(8);
            StdOut.println(lfsr20 + " " + MathFuncs.binaryToInt(r));
        }*/
        String password = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        //I'm gonna encrypt the password by xoring it with its reverse (ultra super secret unbreakable encryption 😱😱😱)
        String seedInit = MathFuncs.stringToBinary(password);
        String seedRev = MathFuncs.stringToBinary(MathFuncs.reverse(password));
        System.out.println(seedInit);
        System.out.println(seedRev);
        System.out.println(seedInit.length()==seedRev.length());
        String seed = MathFuncs.XOR(seedInit,seedRev);//doesnt actually matter if seedinit or seedrev is first because a xor b === b xor a (eg. 0100 === 0001 ^ 0101 === 0101 ^ 0001)
        System.out.println(seed);
        Picture picture1 = new Picture("LFSR/resources/lfsr/pipe.png");
        Picture picture2 = PhotoMagic.transform(picture1,new LFSR(seed,1));
        Picture picture3 = PhotoMagic.transform(picture1,new LFSR(seed,1));
        picture1.show();
        picture2.show();
        picture3.show();
        System.out.println(picture2.equals(picture3));
    }

}
