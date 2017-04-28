package com.vinaygaba.rubberstamp;

import android.util.Pair;

import static com.vinaygaba.rubberstamp.RubberStamp.BOTTOMCENTER;
import static com.vinaygaba.rubberstamp.RubberStamp.BOTTOMLEFT;
import static com.vinaygaba.rubberstamp.RubberStamp.BOTTOMRIGHT;
import static com.vinaygaba.rubberstamp.RubberStamp.CENTER;
import static com.vinaygaba.rubberstamp.RubberStamp.CENTERLEFT;
import static com.vinaygaba.rubberstamp.RubberStamp.CENTERRIGHT;
import static com.vinaygaba.rubberstamp.RubberStamp.TOPCENTER;
import static com.vinaygaba.rubberstamp.RubberStamp.TOPLEFT;
import static com.vinaygaba.rubberstamp.RubberStamp.TOPRIGHT;

public class PositionCalculator {


    public static Pair<Integer, Integer> getCoordinates(@RubberStamp.RubberStampPosition int location,
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

            case CENTERLEFT:
                return new Pair<>(0, (bitmapHeight / 2) + (rubberstampHeight / 2));

            case CENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        (bitmapHeight / 2) + (rubberstampHeight / 2));
            case CENTERRIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth,
                        (bitmapHeight / 2) + (rubberstampHeight / 2));

            case BOTTOMLEFT:
                return new Pair<>(0, bitmapHeight);

            case BOTTOMCENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        bitmapHeight);
            case BOTTOMRIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth, bitmapHeight);

            default:
                return new Pair<>(0,0);
        }
    }
}
