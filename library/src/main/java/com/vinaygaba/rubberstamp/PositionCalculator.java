/*
  Copyright (C) 2017 Vinay Gaba

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.vinaygaba.rubberstamp;

import android.util.Pair;

public class PositionCalculator {

    /**
     * Utility method to calculate the coordinates of the rubberstamp based on the params passed to
     * it.
     * @param location The RubberStampPosition which denotes the position of the watermark
     * @param bitmapWidth The width of the bitmap where the rubberstamp will be drawn
     * @param bitmapHeight The height of the bitmap where the rubberstamp will be drawn
     * @param rubberstampWidth The width of the rubberstamp
     * @param rubberstampHeight The height of the rubberstamp
     * @return Returns a Pair object which has the x-coordinate and the y-coordinate
     */
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
