package com.vinaygaba.rubberstamp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import static com.vinaygaba.rubberstamp.RubberStamp.CENTER;

public class RubberStampConfig {

    private Bitmap mBaseBitmap;
    @DrawableRes private int mBaseDrawable;
    private int mSize;
    @ColorInt private int mTextColor;
    @ColorInt private int mBackgroundColor;
    private String mTypeFacePath;
    private @RubberStamp.RubberStampPosition int mRubberStampPosition;
    private String mRubberStampString;
    private Bitmap mRubberStampBitmap;
    private int mAplha;
    private Shader mShader;
    private float mRotation;
    private int mPositionX, mPositionY;
    private float mShadowBlurRadius, mShadowXOffset, mShadowYOffset;
    private int mShadowColor;

    private RubberStampConfig(RubberStampConfigBuilder builder) {
        this.mBaseBitmap = builder.mBaseBitmap;
        this.mBaseDrawable = builder.mBaseDrawable;
        this.mSize = builder.mSize;
        this.mTextColor = builder.mTextColor;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.mTypeFacePath = builder.mTypeFacePath;
        this.mRubberStampPosition = builder.mRubberStampPosition;
        this.mRubberStampString = builder.mRubberStampString;
        this.mRubberStampBitmap = builder.mRubberStampBitmap;
        this.mAplha = builder.mAlpha;
        this.mShader = builder.mShader;
        this.mRotation = builder.mRotation;
        this.mPositionX = builder.mPositionX;
        this.mPositionY = builder.mPositionY;
        this.mShadowColor = builder.mShadowColor;
        this.mShadowXOffset = builder.mShadowXOffset;
        this.mShadowYOffset = builder.mShadowYOffset;
        this.mShadowBlurRadius = builder.mShadowBlurRadius;
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

    public int getTextColor() {
        return mTextColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
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
  
    public Shader getShader() {
        return mShader;
    }

    public float getRotation() {
        return mRotation;
    }

    public int getPositionX() {
        return mPositionX;
    }

    public int getPositionY() {
        return mPositionY;
    }

    public float getShadowBlurRadius() {
        return mShadowBlurRadius;
    }

    public float getShadowXOffset() {
        return mShadowXOffset;
    }

    public float getShadowYOffset() {
        return mShadowYOffset;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public static class RubberStampConfigBuilder {

        private Bitmap mBaseBitmap;
        @DrawableRes private int mBaseDrawable;
        private int mSize = 10;
        @ColorInt private int mTextColor = Color.BLACK;
        @ColorInt private int mBackgroundColor;
        private String mTypeFacePath;
        private @RubberStamp.RubberStampPosition
        int mRubberStampPosition = CENTER;
        private String mRubberStampString;
        private Bitmap mRubberStampBitmap;
        private int mAlpha = 255;
        private Shader mShader;
        private float mRotation;
        private int mPositionX, mPositionY;
        private float mShadowBlurRadius, mShadowXOffset, mShadowYOffset;
        private int mShadowColor = Color.WHITE;

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

        public RubberStampConfigBuilder textcolor(final int color) {
            this.mTextColor = color;
            return this;
        }

        public RubberStampConfigBuilder backgroundcolor(final int color) {
            this.mBackgroundColor = color;
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

        public RubberStampConfigBuilder shader(final Shader shader) {
            this.mShader = shader;
            return this;
        }

        public RubberStampConfigBuilder rotation(final float rotation) {
            this.mRotation = rotation;
            return this;
        }

        public RubberStampConfigBuilder position(final int positionX, final int positionY) {
            this.mPositionX = positionX;
            this.mPositionY = positionY;
            return this;
        }

        public RubberStampConfigBuilder shadow(final float blurRadius, final float shadowXOffset,
                                        final float shadowYOffset,@ColorInt final int shadowColor) {
            this.mShadowBlurRadius = blurRadius;
            this.mShadowXOffset = shadowXOffset;
            this.mShadowYOffset = shadowYOffset;
            this.mShadowColor = shadowColor;
            return this;
        }

        public RubberStampConfig build() {
            return new RubberStampConfig(this);
        }
    }
}
