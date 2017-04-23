package com.vinaygaba.rubberstamp;

import android.support.annotation.IntDef;
import android.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PositionCalculator {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT,
            BOTTOMCENTER, BOTTOMRIGHT, DIAGONAL})
    public @interface Location {}

    public static final int TOPLEFT = 0;
    public static final int TOPCENTER = 1;
    public static final int TOPRIGHT = 2;
    public static final int MIDDLELEFT = 3;
    public static final int MIDDLECENTER = 4;
    public static final int MIDDLERIGHT = 5;
    public static final int BOTTOMLEFT = 6;
    public static final int BOTTOMCENTER = 7;
    public static final int BOTTOMRIGHT = 8;
    public static final int DIAGONAL = 9;

    public static Pair<Integer, Integer> getCoordinates(@Location int location,
                                                        int bitmapWidth, int bitmapHeight,
                                                        int rubberstampWidth, int rubberstampHeight) {
        switch(location){

            case TOPLEFT:
                return new Pair<>(0, rubberstampHeight);

            case TOPCENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        rubberstampHeight);
            case TOPRIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth, rubberstampHeight);

            case MIDDLELEFT:
                return new Pair<>(0, (bitmapHeight / 2) - (rubberstampHeight / 2));

            case MIDDLECENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        (bitmapHeight / 2) - (rubberstampHeight / 2));
            case MIDDLERIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth,
                        (bitmapHeight / 2) - (rubberstampHeight / 2));

            case BOTTOMLEFT:
                return new Pair<>(0, bitmapHeight);

            case BOTTOMCENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        bitmapHeight);
            case BOTTOMRIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth, bitmapHeight);

            case DIAGONAL:
                return new Pair<>(0, bitmapHeight);

            default:
                return new Pair<>(0,0);
        }
    }
}
