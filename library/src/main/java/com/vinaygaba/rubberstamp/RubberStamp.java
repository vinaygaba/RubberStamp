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
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Pair;

public class RubberStamp {

    static Context mContext;
    private int x;
    private int y;
    private int srcWidth;
    private int srcHeight;
    private int rubberstampWidth;
    private int rubberstampHeight;


    public RubberStamp(Context context){
        mContext = context;
    }

    public Bitmap addStamp(Bitmap src, String rubberstamp, int size, int color, String typeFacePath,
                           @PositionCalculator.Location int pos) {
        srcWidth = src.getWidth();
        srcHeight = src.getHeight();

        Rect bounds = new Rect();
        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, color, Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, src.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Paint paint = new Paint();
        paint.setTextSize(size);

        if(typeFacePath != null && typeFacePath!="") {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), typeFacePath);
            paint.setTypeface(typeface);
        }
        paint.getTextBounds(rubberstamp,0,rubberstamp.length(),bounds);
        rubberstampHeight=bounds.height();
        rubberstampWidth=bounds.width();

        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);

        Pair<Integer, Integer> pair = PositionCalculator.getCoordinates(pos,
                srcWidth, srcHeight, rubberstampWidth, rubberstampHeight);

        canvas.drawText(rubberstamp,pair.first , pair.second, paint);

        return result;
    }

    public Bitmap addStamp(int src, String watermark, int size, int color, String typeFacePath,
                           @PositionCalculator.Location int pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);

        srcWidth = srcBitmap.getWidth();
        srcHeight = srcBitmap.getHeight();


        Rect bounds = new Rect();
        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, color,
                Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, srcBitmap.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(srcBitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setTextSize(size);

        if(typeFacePath != null && typeFacePath!="") {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), typeFacePath);
            paint.setTypeface(typeface);
        }

        paint.getTextBounds(watermark,0,watermark.length(),bounds);

        rubberstampHeight=bounds.height();
        rubberstampWidth=bounds.width();

        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);

        Pair<Integer, Integer> pair = PositionCalculator.getCoordinates(pos,
                srcWidth, srcHeight, rubberstampWidth, rubberstampHeight);

        canvas.drawText(watermark, pair.first, pair.second, paint);

        return result;
    }

    public Bitmap addStamp(int src, int rubberstamp, int wMarkWidth, int wMarkHeight,
                           @PositionCalculator.Location int pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);

        Bitmap tempWatermarkBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                rubberstamp);

        Bitmap watermarkBitmap = getResizedBitmap(tempWatermarkBitmap,wMarkHeight,wMarkWidth);

        srcWidth = srcBitmap.getWidth();
        srcHeight = srcBitmap.getHeight();

        rubberstampHeight = watermarkBitmap.getHeight();
        rubberstampWidth = watermarkBitmap.getWidth();


        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, srcBitmap.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        Pair<Integer, Integer> pair = PositionCalculator.getCoordinates(pos,
                srcWidth, srcHeight, rubberstampWidth, rubberstampHeight);
        canvas.drawBitmap(watermarkBitmap,pair.first,pair.second-rubberstampHeight,null);

        return result;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }

}
