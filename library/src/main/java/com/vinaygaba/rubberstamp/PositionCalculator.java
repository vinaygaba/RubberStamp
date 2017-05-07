package com.vinaygaba.rubberstamp;

import android.util.Pair;

public class PositionCalculator {

    public static Pair<Integer, Integer> getCoordinates(RubberStampPosition location,
                                                        int bitmapWidth, int bitmapHeight,
                                                        int rubberstampWidth, int rubberstampHeight) {
        switch(location){

            case TOP_LEFT:
                return new Pair<>(0, rubberstampHeight);

            case TOP_CENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        rubberstampHeight);
            case TOP_RIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth, rubberstampHeight);

            case CENTER_LEFT:
                return new Pair<>(0, (bitmapHeight / 2) + (rubberstampHeight / 2));

            case CENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        (bitmapHeight / 2) + (rubberstampHeight / 2));
            case CENTER_RIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth,
                        (bitmapHeight / 2) + (rubberstampHeight / 2));

            case BOTTOM_LEFT:
                return new Pair<>(0, bitmapHeight);

            case BOTTOM_CENTER:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        bitmapHeight);
            case BOTTOM_RIGHT:
                return new Pair<>(bitmapWidth - rubberstampWidth, bitmapHeight);

            default:
                return new Pair<>((bitmapWidth / 2) - (rubberstampWidth / 2),
                        (bitmapHeight / 2) + (rubberstampHeight / 2));
        }
    }
}
