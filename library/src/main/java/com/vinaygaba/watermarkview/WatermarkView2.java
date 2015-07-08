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
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class WatermarkView2 {

    public enum Position {
        TOPLEFT, TOPCENTER, TOPRIGHT, MIDDLELEFT, MIDDLECENTER, MIDDLERIGHT, BOTTOMLEFT, BOTTOMCENTER, BOTTOMRIGHT
    }


    static Context mContext;

    public Bitmap addWatermark(Bitmap src, String watermark,Position pos) {
        int w = src.getWidth();
        int h = src.getHeight();

        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);
        canvas.drawText(watermark, 10 , h-15, paint);

        return result;
    }

    /*
    public Bitmap addWatermark(Drawable src, String watermark,Position pos) {

        Bitmap srcBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                src);

        int w = src.getIntrinsicWidth();
        w = w > 0 ? w : 1;
        int h = src.getIntrinsicHeight();
        h = h > 0 ? h : 1;

        Shader shader = new LinearGradient(0, 0, 100, 0, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP);

        Bitmap result = Bitmap.createBitmap(w, h, );
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setUnderlineText(false);
        canvas.drawText(watermark, 10 , h-15, paint);

        return result;
    }*/

}
