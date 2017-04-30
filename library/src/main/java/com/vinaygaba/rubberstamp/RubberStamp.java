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
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
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
    @IntDef({TOPLEFT, TOPCENTER, TOPRIGHT, CENTERLEFT, CENTER, CENTERRIGHT, BOTTOMLEFT,
            BOTTOMCENTER, BOTTOMRIGHT, CUSTOM, TILE})
    public @interface RubberStampPosition {}

    public static final int TOPLEFT = 0;
    public static final int TOPCENTER = 1;
    public static final int TOPRIGHT = 2;
    public static final int CENTERLEFT = 3;
    public static final int CENTER = 4;
    public static final int CENTERRIGHT = 5;
    public static final int BOTTOMLEFT = 6;
    public static final int BOTTOMCENTER = 7;
    public static final int BOTTOMRIGHT = 8;
    public static final int CUSTOM = 9;
    public static final int TILE = 10;

    public static final int BACKGROUND_MARGIN = 10;
    
    public RubberStamp(Context context){
        mContext = context;
    }

    public Bitmap addStamp(RubberStampConfig config) {
        Bitmap baseBitmap = getBaseBitmap(config);
        if (baseBitmap == null) {
            return baseBitmap;
        }

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

    private void addTextToBitmap(RubberStampConfig config, Canvas canvas, int baseBitmapWidth,
                                 int baseBitmapHeight) {
        Rect bounds = new Rect();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);

        paint.setTextSize(config.getSize());


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
        if (shader != null) {
            paint.setShader(shader);
        }

        String rubberStampString = config.getRubberStampString();
        paint.getTextBounds(rubberStampString,0,rubberStampString.length(),bounds);

        int rubberStampWidth = bounds.width();
        int rubberStampHeight = bounds.height();

        int positionX = config.getPositionX();
        int positionY = config.getPositionY();

        if (config.getRubberStampPosition() != CUSTOM) {
            Pair<Integer, Integer> pair = PositionCalculator
                    .getCoordinates(config.getRubberStampPosition(),
                            baseBitmapWidth, baseBitmapHeight,
                            rubberStampWidth, rubberStampHeight);
            positionX = pair.first;
            positionY = pair.second;
        }


        float rotation = config.getRotation();
        if (rotation != 0.0f) {
            canvas.rotate(rotation, positionX + bounds.exactCenterX(),
                    positionY - bounds.exactCenterY());
        }

        int backgroundColor = config.getBackgroundColor();
        if (backgroundColor != 0) {
            Paint backgroundPaint = new Paint();
            backgroundPaint.setColor(backgroundColor);
            canvas.drawRect(positionX - BACKGROUND_MARGIN,
                    positionY - bounds.height() - BACKGROUND_MARGIN,
                    positionX + paint.measureText(config.getRubberStampString()),
                    positionY + BACKGROUND_MARGIN,
                    backgroundPaint);
        }

        paint.setColor(config.getTextColor());
        //canvas.drawText(rubberStampString, positionX , positionY, paint);
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
        if (shader != null) {
            paint.setShader(shader);
        }

        int positionX = config.getPositionX();
        int positionY = config.getPositionY();
        @RubberStampPosition int rubberStampPosition = config.getRubberStampPosition();
        if (rubberStampPosition != CUSTOM) {
            Pair<Integer, Integer> pair =
                    PositionCalculator.getCoordinates(rubberStampPosition,
                            baseBitmapWidth, baseBitmapHeight,
                            rubberStampBitmap.getWidth(), rubberStampBitmap.getHeight());
            positionX = pair.first;
            positionY = pair.second - rubberStampBitmap.getHeight();
        }

        float rotation = config.getRotation();
        if (rotation != 0.0f) {
            canvas.rotate(rotation, positionX + (rubberStampBitmap.getWidth() / 2),
                    positionY + (rubberStampBitmap.getHeight() / 2));
        }

        if (rubberStampPosition != TILE) {
            canvas.drawBitmap(rubberStampBitmap, positionX, positionY , paint);
        } else {
            paint.setShader(new BitmapShader(rubberStampBitmap,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT));
            Rect bitmapShaderRect = canvas.getClipBounds();
            canvas.drawRect(bitmapShaderRect, paint);
        }
    }
}
