package com.vinaygaba.rubberstamp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import static com.vinaygaba.rubberstamp.RubberStamp.CENTER;

public class RubberStampConfig {

    private Bitmap mBaseBitmap;
    @DrawableRes private int mBaseDrawable;
    private int mSize;
    @ColorInt private int mColor;
    private String mTypeFacePath;
    private @RubberStamp.RubberStampPosition int mRubberStampPosition;
    private String mRubberStampString;
    private Bitmap mRubberStampBitmap;
    private int mAplha;

    private RubberStampConfig(Bitmap baseBitmap,
                              @DrawableRes int baseDrawable,
                              int size,
                              int color,
                              String typeFacePath,
                              int rubberStampPosition,
                              String rubberStampString,
                              Bitmap rubberStampBitmap,
                              int alpha) {
        this.mBaseBitmap = baseBitmap;
        this.mBaseDrawable = baseDrawable;
        this.mSize = size;
        this.mColor = color;
        this.mTypeFacePath = typeFacePath;
        this.mRubberStampPosition = rubberStampPosition;
        this.mRubberStampString = rubberStampString;
        this.mRubberStampBitmap = rubberStampBitmap;
        this.mAplha = alpha;
    }

    public Bitmap getBaseBitmap() {
        return mBaseBitmap;
    }

    protected int getBaseDrawable() {
        return mBaseDrawable;
    }

    public int getSize() {
        return mSize;
    }

    public int getColor() {
        return mColor;
    }

    public String getTypeFacePath() {
        return mTypeFacePath;
    }

    public int getRubberStampPosition() {
        return mRubberStampPosition;
    }

    public String getRubberStampString() {
        return mRubberStampString;
    }

    protected Bitmap getRubberStampBitmap() {
        return mRubberStampBitmap;
    }

    public int getAplha() {
        return mAplha;
    }

    public static class RubberStampConfigBuilder {

        private Bitmap mBaseBitmap;
        @DrawableRes private int mBaseDrawable;
        private int mSize = 10;
        @ColorInt private int mColor = Color.WHITE;
        private String mTypeFacePath;
        private @RubberStamp.RubberStampPosition
        int mRubberStampPosition = CENTER;
        private String mRubberStampString;
        private Bitmap mRubberStampBitmap;
        private int mAlpha = 255;

        public RubberStampConfigBuilder base(final Bitmap bitmap) {
            this.mBaseBitmap = bitmap;
            return this;
        }

        public RubberStampConfigBuilder base(@DrawableRes int drawable) {
            this.mBaseDrawable = drawable;
            return this;
        }

        public RubberStampConfigBuilder size(final int size) {
            this.mSize = size;
            return this;
        }

        public RubberStampConfigBuilder color(final int color) {
            this.mColor = color;
            return this;
        }

        public RubberStampConfigBuilder typeFacePath(final String typeFacePath) {
            this.mTypeFacePath = typeFacePath;
            return this;
        }

        public RubberStampConfigBuilder rubberStampPosition(final @RubberStamp.RubberStampPosition
                                                                    int position) {
            this.mRubberStampPosition = position;
            return this;
        }

        public RubberStampConfigBuilder rubberStamp(final String rubberStampString) {
            this.mRubberStampString = rubberStampString;
            return this;
        }

        public RubberStampConfigBuilder rubberStamp(final Bitmap rubberStampBitmap) {
            this.mRubberStampBitmap = rubberStampBitmap;
            return this;
        }

        public RubberStampConfigBuilder alpha(final int alpha) {
            this.mAlpha = alpha;
            return this;
        }

        public RubberStampConfig build() {
            return new RubberStampConfig(mBaseBitmap, mBaseDrawable, mSize, mColor, mTypeFacePath,
                            mRubberStampPosition, mRubberStampString, mRubberStampBitmap, mAlpha);

        }
    }
}
