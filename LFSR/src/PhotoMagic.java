package LFSR.src;

import Princeton.src.*;
import util.MathFuncs;

import java.awt.*;


public class PhotoMagic {

    public static Picture transform(Picture picture, LFSR lfsr){
        int w = picture.width(),h = picture.height();
        Picture ret = new Picture(w,h);
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                int num = MathFuncs.binaryToInt(lfsr.generate(8));
                int num2 = MathFuncs.binaryToInt(lfsr.generate(8));
                int num3 = MathFuncs.binaryToInt(lfsr.generate(8));
                Color color = picture.get(i,j);
                int red = color.getRed() ^ num;
                int green = color.getGreen() ^ num2;
                int blue = color.getBlue() ^ num3;
                ret.set(i,j,new Color(red,green,blue));
            }
        }
        return ret;
    }

}
