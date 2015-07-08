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
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WatermarkView extends ImageView {

    private int mWatermark;
    static Context mContext;
    private int mImageWidth;
    private int mImageHeight;
    private int mWatermarkWidth;
    private int mWatermarkHeight;


    public WatermarkView(Context context) {
        this(context, null);
    }

    public WatermarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (context != null) {
            this.mContext = context;
        } else {
            this.mContext = getContext();
        }

        init();
        loadAttributes(attrs);
        initDefaults();
    }

    /**
     * Initialize various views and variables
     */
    private void init() {


    }

    private void loadAttributes(@Nullable AttributeSet attrs) {

        final TypedArray a = mContext.getTheme().obtainStyledAttributes(attrs,
                R.styleable.WatermarkView, 0, 0);
        try {
            mWatermark = a.getResourceId(R.styleable.WatermarkView_watermark, 0);

        }
        finally {
            a.recycle();
        }
    }

    private void initDefaults() {

        mImageWidth = getWidth();
        mImageHeight = getHeight();


    }

    private void redrawViews() {
        invalidate();
        requestLayout();
    }

    @DrawableRes
    public int getWatermark() {
        return mWatermark;
    }

    public void setWatermark(@DrawableRes int waterMark) {
        mWatermark = waterMark;
        redrawViews();
    }




}
