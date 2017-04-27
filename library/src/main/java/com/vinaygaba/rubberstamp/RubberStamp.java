/*
 * Copyright (C) 2015 Vinay Gaba
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vinaygaba.rubberstamp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RubberStamp {

    static Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, CENTER, MIDDLERIGHT, BOTTOMLEFT,
            BOTTOMCENTER, BOTTOMRIGHT, DIAGONAL})
    public @interface RubberStampPosition {}

    public static final int TOPLEFT = 0;
    public static final int TOPCENTER = 1;
    public static final int TOPRIGHT = 2;
    public static final int MIDDLELEFT = 3;
    public static final int CENTER = 4;
    public static final int MIDDLERIGHT = 5;
    public static final int BOTTOMLEFT = 6;
    public static final int BOTTOMCENTER = 7;
    public static final int BOTTOMRIGHT = 8;
    public static final int DIAGONAL = 9;
    
    public RubberStamp(Context context){
        mContext = context;
    }

    public Bitmap addStamp(RubberStampConfig config) {
        Bitmap baseBitmap = getBaseBitmap(config);
        if (baseBitmap == null) return null;

        int baseBitmapWidth = baseBitmap.getWidth();
        int baseBitmapHeight = baseBitmap.getHeight();
      
        Bitmap result = Bitmap.createBitmap(baseBitmapWidth, baseBitmapHeight, baseBitmap.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(baseBitmap, 0, 0, null);
      
        if (!TextUtils.isEmpty(config.getRubberStampString())) {
            addTextToBitmap(config, canvas, baseBitmapWidth, baseBitmapHeight);
        }

        if (config.getRubberStampBitmap() != null) {
            addBitmapToBitmap(config.getRubberStampBitmap(), config, canvas,
                    baseBitmapWidth, baseBitmapHeight);
        }
        return result;
    }
  
    @Nullable
    private Bitmap getBaseBitmap(RubberStampConfig config) {
        Bitmap baseBitmap = config.getBaseBitmap();
        @DrawableRes int drawable = config.getBaseDrawable();
      
        if (baseBitmap == null) {
            baseBitmap = BitmapFactory.decodeResource(mContext.getResources(), drawable);
            if (baseBitmap == null) return null;
        }
        return baseBitmap;
    }

    public Bitmap addStamp(int src, int rubberstamp, int wMarkWidth, int wMarkHeight,
                           @RubberStampPosition int pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);
        Bitmap tempWatermarkBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                rubberstamp);
        Bitmap watermarkBitmap = getResizedBitmap(tempWatermarkBitmap, wMarkHeight, wMarkWidth);

        int baseBitmapWidth = srcBitmap.getWidth();
        int baseBitmapHeight = srcBitmap.getHeight();

        int rubberStampWidth = watermarkBitmap.getWidth();
        int rubberStampHeight = watermarkBitmap.getHeight();

        Bitmap result = Bitmap.createBitmap(baseBitmapWidth, baseBitmapHeight, srcBitmap.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        Pair<Integer, Integer> pair = PositionCalculator.getCoordinates(pos,
                baseBitmapWidth, baseBitmapWidth, rubberStampWidth, rubberStampHeight);
        canvas.drawBitmap(watermarkBitmap,pair.first,pair.second - rubberStampHeight,
                null);

        return result;
    }

    private static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
                false);

        return resizedBitmap;
    }

    private void addTextToBitmap(RubberStampConfig config, Canvas canvas, int baseBitmapWidth,
                                 int baseBitmapHeight) {
        Rect bounds = new Rect();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);

        paint.setTextSize(config.getSize());
        paint.setColor(config.getColor());

        String typeFacePath = config.getTypeFacePath();
        if(!TextUtils.isEmpty(typeFacePath)) {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), typeFacePath);
            paint.setTypeface(typeface);
        }

        int alpha = config.getAplha();
        if (alpha >= 0 && alpha <= 255) {
            paint.setAlpha(alpha);
        }
      
        Shader shader = config.getShader();
        if (shader != null) paint.setShader(shader);

        String rubberStampString = config.getRubberStampString();
        paint.getTextBounds(rubberStampString,0,rubberStampString.length(),bounds);

        int rubberStampWidth = bounds.width();
        int rubberStampHeight = bounds.height();

        Pair<Integer, Integer> pair = PositionCalculator
                .getCoordinates(config.getRubberStampPosition(),
                        baseBitmapWidth, baseBitmapHeight,
                        rubberStampWidth, rubberStampHeight);

        canvas.drawText(rubberStampString, pair.first , pair.second, paint);
    }

    private void addBitmapToBitmap(Bitmap rubberStampBitmap, RubberStampConfig config, Canvas canvas,
                                   int baseBitmapWidth, int baseBitmapHeight) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);

        int alpha = config.getAplha();
        if (alpha >= 0 && alpha <= 255) {
            paint.setAlpha(alpha);
        }
      
        Shader shader = config.getShader();
        if (shader != null) paint.setShader(shader);
      
        Pair<Integer, Integer> pair =
                PositionCalculator.getCoordinates(config.getRubberStampPosition(), baseBitmapWidth,
                baseBitmapWidth, rubberStampBitmap.getWidth(), rubberStampBitmap.getHeight());
        canvas.drawBitmap(rubberStampBitmap,pair.first,
                pair.second - rubberStampBitmap.getHeight(), paint);
    }
}
