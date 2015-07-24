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


package com.vinaygaba.watermarkview;

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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class WatermarkView2 {
    
    static Context mContext;
    private int x;
    private int y;
    private int srcWidth;
    private int srcHeight;
    private int watermarkWidth;
    private int watermarkHeight;

    public WatermarkView2(Context context){
        mContext = context;
    }

    public Bitmap addWatermark(Bitmap src, String watermark,int size,int color,Position pos) {
        srcWidth = src.getWidth();
        Log.e("Src Width",srcWidth+"");
        srcHeight = src.getHeight();
        Log.e("Src Height",srcHeight+"");
        Rect bounds = new Rect();
        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, color, Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, src.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Paint paint = new Paint();
        paint.setTextSize(size);
        paint.getTextBounds(watermark,0,watermark.length(),bounds);
        watermarkHeight=bounds.height();
        Log.e("Watermark Height",watermarkHeight+"");
        watermarkWidth=bounds.width();
        Log.e("Watermark Width",watermarkWidth+"");
        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);

        getWatermarkCoordinates(pos,srcWidth,srcHeight);

        canvas.drawText(watermark,x , y, paint);
        Log.e("X", x+"");
        Log.e("Y", y+"");



        return result;
    }

    public Bitmap addWatermark(int src, String watermark,int size,int color,Position pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);

        srcWidth = srcBitmap.getWidth();
        Log.e("Src Width",srcWidth+"");
        srcHeight = srcBitmap.getHeight();
        Log.e("Src Height",srcHeight+"");
        Rect bounds = new Rect();
        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, color, Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, srcBitmap.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(srcBitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setTextSize(size);
        paint.getTextBounds(watermark,0,watermark.length(),bounds);
        watermarkHeight=bounds.height();
        Log.e("Watermark Height",watermarkHeight+"");
        watermarkWidth=bounds.width();
        Log.e("Watermark Width",watermarkWidth+"");
        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);

        getWatermarkCoordinates(pos, srcWidth, srcHeight);

        canvas.drawText(watermark, x, y, paint);
        Log.e("X", x + "");
        Log.e("Y", y + "");

        return result;
    }

    public Bitmap addWatermark(int src, int watermark,int wMarkWidth,int wMarkHeight,Position pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);

        Bitmap tempWatermarkBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                watermark);

        Bitmap watermarkBitmap = getResizedBitmap(tempWatermarkBitmap,wMarkHeight,wMarkWidth);

        srcWidth = srcBitmap.getWidth();
        Log.e("Src Width",srcWidth+"");
        srcHeight = srcBitmap.getHeight();
        Log.e("Src Height",srcHeight+"");
        watermarkHeight=watermarkBitmap.getHeight();
        Log.e("Watermark Height",watermarkHeight+"");
        watermarkWidth=watermarkBitmap.getWidth();
        Log.e("Watermark Width",watermarkWidth+"");

        Bitmap result = Bitmap.createBitmap(srcWidth, srcHeight, srcBitmap.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        getWatermarkCoordinates(pos, srcWidth, srcHeight);
        canvas.drawBitmap(watermarkBitmap,x,y-watermarkHeight,null);

        Log.e("X", x + "");
        Log.e("Y", y + "");

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

    public void getWatermarkCoordinates(Position pos,int w,int h){

        switch(pos){

            case TOPLEFT:
                x = 0;
                y = watermarkHeight;
                break;
            case TOPCENTER:
                x = (srcWidth / 2) - (watermarkWidth / 2);
                y = watermarkHeight;
                break;
            case TOPRIGHT:
                x = srcWidth - watermarkWidth;
                y = watermarkHeight;
                break;

            case MIDDLELEFT:
                x = 0;
                y = (srcHeight / 2) - (watermarkHeight / 2);
                break;
            case MIDDLECENTER:
                Log.e("Entered", "Middlecenter");
                x = (srcWidth / 2) - (watermarkWidth / 2);
                y = (srcHeight / 2) - (watermarkHeight / 2);
                break;
            case MIDDLERIGHT:
                x = srcWidth - watermarkWidth;
                y = (srcHeight / 2) - (watermarkHeight / 2);
                break;

            case BOTTOMLEFT:
                x = 0;
                y = srcHeight;
                break;
            case BOTTOMCENTER:
                x = (srcWidth / 2) - (watermarkWidth / 2);
                y = srcHeight ;
                break;
            case BOTTOMRIGHT:
                x = srcWidth - watermarkWidth;
                y = srcHeight;
                break;

            case DIAGONAL:
                x=0;
                y=srcHeight;
                break;

            default:
                break;
        }

    }

}
