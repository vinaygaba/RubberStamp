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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;

import static com.vinaygaba.rubberstamp.RubberStampPosition.CUSTOM;
import static com.vinaygaba.rubberstamp.RubberStampPosition.TILE;


public class RubberStamp {

    private Context mContext;
    private static final int BACKGROUND_MARGIN = 10;
    
    public RubberStamp(@NonNull Context context){
        mContext = context;
    }

    public Bitmap addStamp(@NonNull RubberStampConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("The config passed to this method should never" +
                    "be null");
        }
        Bitmap baseBitmap = getBaseBitmap(config);
        if (baseBitmap == null) {
            return baseBitmap;
        }

        int baseBitmapWidth = baseBitmap.getWidth();
        int baseBitmapHeight = baseBitmap.getHeight();
      
        Bitmap result = Bitmap.createBitmap(baseBitmapWidth, baseBitmapHeight, baseBitmap.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(baseBitmap, 0, 0, null);

        // Either one of the methods(text/bitmap) can be used to add a rubberstamp
        if (!TextUtils.isEmpty(config.getRubberStampString())) {
            addTextToBitmap(config, canvas, baseBitmapWidth, baseBitmapHeight);
        } else if (config.getRubberStampBitmap() != null) {
            addBitmapToBitmap(config.getRubberStampBitmap(), config, canvas,
                    baseBitmapWidth, baseBitmapHeight);
        }
        return result;
    }
  
    @Nullable
    private Bitmap getBaseBitmap(@NonNull RubberStampConfig config) {
        Bitmap baseBitmap = config.getBaseBitmap();
        @DrawableRes int drawable = config.getBaseDrawable();
      
        if (baseBitmap == null) {
            baseBitmap = BitmapFactory.decodeResource(mContext.getResources(), drawable);
            if (baseBitmap == null) return null;
        }
        return baseBitmap;
    }

    /**
     * Method to add text RubberStamp to a canvas based on the provided configuration
     * @param config The RubberStampConfig that specifies how the RubberStamp should look
     * @param canvas The canvas on top of which the RubberStamp needs to be drawn
     * @param baseBitmapWidth The width of the base bitmap
     * @param baseBitmapHeight The height of the base bitmap
     */
    private void addTextToBitmap(@NonNull RubberStampConfig config,
                                 @NonNull Canvas canvas,
                                 int baseBitmapWidth,
                                 int baseBitmapHeight) {
        Rect bounds = new Rect();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);

        paint.setTextSize(config.getTextSize());

        String typeFacePath = config.getTypeFacePath();
        // Add font typeface if its present in the config.
        if(!TextUtils.isEmpty(typeFacePath)) {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), typeFacePath);
            paint.setTypeface(typeface);
        }

        Shader shader = config.getTextShader();
        // Add shader if its present in the config.
        if (shader != null) {
            paint.setShader(shader);
        }

        if (config.getTextShadowXOffset() != 0 || config.getTextShadowYOffset() != 0
        || config.getTextShadowBlurRadius() != 0) {
            // If any shadow property is present, set a shadow layer.
            paint.setShadowLayer(config.getTextShadowBlurRadius(),
                    config.getTextShadowXOffset(),
                    config.getTextShadowYOffset(),
                    config.getTextShadowColor());
        }

        String rubberStampString = config.getRubberStampString();
        paint.getTextBounds(rubberStampString,0,rubberStampString.length(),bounds);

        int rubberStampWidth = bounds.width();
        float rubberStampMeasuredWidth = paint.measureText(rubberStampString);
        int rubberStampHeight = bounds.height();

        int positionX = config.getPositionX();
        int positionY = config.getPositionY();

        if (config.getRubberStampPosition() != CUSTOM) {
            // If the specified RubberStampPosition is not CUSTOM, use calculates its x & y
            // co-ordinates.
            Pair<Integer, Integer> pair = PositionCalculator
                    .getCoordinates(config.getRubberStampPosition(),
                            baseBitmapWidth, baseBitmapHeight,
                            rubberStampWidth, rubberStampHeight);
            positionX = pair.first;
            positionY = pair.second;
        }

        // Add the margin to this position if it was passed to the config.
        positionX += config.getXMargin();
        positionY += config.getYMargin();

        float rotation = config.getRotation();
        // Add rotation if its present in the config.
        if (rotation != 0.0f) {
            canvas.rotate(rotation, positionX + bounds.exactCenterX(),
                    positionY - bounds.exactCenterY());
        }

        // Add the specified text color if its present in the config or it will use the default value.
        paint.setColor(config.getTextColor());

        int alpha = config.getAplha();
        // Add alpha to the rubberstamp if its within range or it uses the default value.
        if (alpha >= 0 && alpha <= 255) {
            paint.setAlpha(alpha);
        }

        if (config.getRubberStampPosition() != TILE) {
            // The textBackgroundColor is only used if the specified RubberStampPosition is not TILE
            // This is because the background is actually a rectangle whose bounds are calcualted
            // below. In the case of TILE, we make use of a bitmap shader and there was no easy way
            // to draw the background rectangle for each tiled rubberstamp.
            int backgroundColor = config.getTextBackgroundColor();
            if (backgroundColor != 0) {
                Paint backgroundPaint = new Paint();
                backgroundPaint.setColor(backgroundColor);
                canvas.drawRect(positionX - BACKGROUND_MARGIN,
                        positionY - bounds.height() - paint.getFontMetrics().descent - BACKGROUND_MARGIN,
                        (positionX + rubberStampMeasuredWidth + config.getTextShadowXOffset() + BACKGROUND_MARGIN),
                        positionY + config.getTextShadowYOffset() + paint.getFontMetrics().descent + BACKGROUND_MARGIN,
                        backgroundPaint);
            }
            canvas.drawText(rubberStampString, positionX , positionY, paint);
        } else {
            // If the specified RubberStampPosition is TILE, it tiles the rubberstamp across
            // the bitmap. In order to generate a tiled bitamp, it uses a bitmap shader.
            Bitmap textImage = Bitmap.createBitmap((int)rubberStampMeasuredWidth,
                    rubberStampHeight,
                    Bitmap.Config.ARGB_8888);
            Canvas textCanvas = new Canvas(textImage);
            textCanvas.drawText(config.getRubberStampString(), 0, rubberStampHeight, paint);
            paint.setShader(new BitmapShader(textImage,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT));
            Rect bitmapShaderRect = canvas.getClipBounds();
            canvas.drawRect(bitmapShaderRect, paint);
        }    
    }

    /**
     * Method to add a bitmap RubberStamp to a canvas based on the provided configuration
     * @param rubberStampBitmap The bitmap which will be used as the RubberStamp
     * @param config The RubberStampConfig that specifies how the RubberStamp should look
     * @param canvas The canvas on top of which the RubberStamp needs to be drawn
     * @param baseBitmapWidth The width of the base bitmap
     * @param baseBitmapHeight The height of the base bitmap
     */
    private void addBitmapToBitmap(@NonNull Bitmap rubberStampBitmap,
                                   @NonNull RubberStampConfig config,
                                   @NonNull Canvas canvas,
                                   int baseBitmapWidth,
                                   int baseBitmapHeight) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);

        int alpha = config.getAplha();
        // Add alpha to the rubberstamp if its within range or it uses the default value.
        if (alpha >= 0 && alpha <= 255) {
            paint.setAlpha(alpha);
        }

        int positionX = config.getPositionX();
        int positionY = config.getPositionY();
        RubberStampPosition rubberStampPosition = config.getRubberStampPosition();
        if (rubberStampPosition != CUSTOM) {
            // If the specified RubberStampPosition is not CUSTOM, use calculates its x & y
            // co-ordinates.
            Pair<Integer, Integer> pair =
                    PositionCalculator.getCoordinates(rubberStampPosition,
                            baseBitmapWidth, baseBitmapHeight,
                            rubberStampBitmap.getWidth(), rubberStampBitmap.getHeight());
            positionX = pair.first;
            positionY = pair.second - rubberStampBitmap.getHeight();
        }

        // Add the margin to this position if it was passed to the config.
        positionX += config.getXMargin();
        positionY += config.getYMargin();

        float rotation = config.getRotation();
        if (rotation != 0.0f) {
            // Add rotation if its present in the config.
            canvas.rotate(rotation, positionX + (rubberStampBitmap.getWidth() / 2),
                    positionY + (rubberStampBitmap.getHeight() / 2));
        }

        if (rubberStampPosition != TILE) {
            canvas.drawBitmap(rubberStampBitmap, positionX, positionY , paint);
        } else {
            // If the specified RubberStampPosition is TILE, it tiles the rubberstamp across
            // the bitmap. In order to generate a tiled bitamp, it uses a bitmap shader.
            paint.setShader(new BitmapShader(rubberStampBitmap,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT));
            Rect bitmapShaderRect = canvas.getClipBounds();
            canvas.drawRect(bitmapShaderRect, paint);
        }
    }
}
