package com.vinaygaba.rubberstamp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import static com.vinaygaba.rubberstamp.RubberStampPosition.CENTER;

public class RubberStampConfig {

    private Bitmap mBaseBitmap;
    @DrawableRes private int mBaseDrawable;
    private int mTextSize;
    @ColorInt private int mTextColor;
    @ColorInt private int mTextBackgroundColor;
    private String mTypeFacePath;
    private RubberStampPosition mRubberStampPosition;
    private String mRubberStampString;
    private Bitmap mRubberStampBitmap;
    private int mAplha;
    private Shader mTextShader;
    private float mRotation;
    private int mPositionX, mPositionY;
    private float mTextShadowBlurRadius, mTextShadowXOffset, mTextShadowYOffset;
    private int mTextShadowColor;
    private int mXMargin, mYMargin;

    private RubberStampConfig(RubberStampConfigBuilder builder) {
        this.mBaseBitmap = builder.mBaseBitmap;
        this.mBaseDrawable = builder.mBaseDrawable;
        this.mTextSize = builder.mTextSize;
        this.mTextColor = builder.mTextColor;
        this.mTextBackgroundColor = builder.mTextBackgroundColor;
        this.mTypeFacePath = builder.mTypeFacePath;
        this.mRubberStampPosition = builder.mRubberStampPosition;
        this.mRubberStampString = builder.mRubberStampString;
        this.mRubberStampBitmap = builder.mRubberStampBitmap;
        this.mAplha = builder.mAlpha;
        this.mTextShader = builder.mTextShader;
        this.mRotation = builder.mRotation;
        this.mPositionX = builder.mPositionX;
        this.mPositionY = builder.mPositionY;
        this.mTextShadowColor = builder.mTextShadowColor;
        this.mTextShadowXOffset = builder.mTextShadowXOffset;
        this.mTextShadowYOffset = builder.mTextShadowYOffset;
        this.mTextShadowBlurRadius = builder.mTextShadowBlurRadius;
        this.mXMargin = builder.mXMargin;
        this.mYMargin = builder.mYMargin;
    }
  
    public Bitmap getBaseBitmap() {
        return mBaseBitmap;
    }

    protected int getBaseDrawable() {
        return mBaseDrawable;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getTextBackgroundColor() {
        return mTextBackgroundColor;
    }

    public String getTypeFacePath() {
        return mTypeFacePath;
    }

    public RubberStampPosition getRubberStampPosition() {
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
  
    public Shader getTextShader() {
        return mTextShader;
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

    public float getTextShadowBlurRadius() {
        return mTextShadowBlurRadius;
    }

    public float getTextShadowXOffset() {
        return mTextShadowXOffset;
    }

    public float getTextShadowYOffset() {
        return mTextShadowYOffset;
    }

    public int getTextShadowColor() {
        return mTextShadowColor;
    }

    public int getXMargin() {
        return mXMargin;
    }

    public int getYMargin() {
        return mYMargin;
    }

    public static class RubberStampConfigBuilder {

        private Bitmap mBaseBitmap;
        @DrawableRes private int mBaseDrawable;
        private int mTextSize = 40;
        @ColorInt private int mTextColor = Color.BLACK;
        @ColorInt private int mTextBackgroundColor;
        private String mTypeFacePath;
        private RubberStampPosition mRubberStampPosition = CENTER;
        private String mRubberStampString;
        private Bitmap mRubberStampBitmap;
        private int mAlpha = 255;
        private Shader mTextShader;
        private float mRotation;
        private int mPositionX, mPositionY;
        private float mTextShadowBlurRadius, mTextShadowXOffset, mTextShadowYOffset;
        @ColorInt private int mTextShadowColor = Color.WHITE;
        private int mXMargin, mYMargin;

        public RubberStampConfigBuilder base(final Bitmap bitmap) {
            this.mBaseBitmap = bitmap;
            return this;
        }

        public RubberStampConfigBuilder base(@DrawableRes int drawable) {
            this.mBaseDrawable = drawable;
            return this;
        }

        public RubberStampConfigBuilder textSize(final int size) {
            this.mTextSize = size;
            return this;
        }

        public RubberStampConfigBuilder textColor(final int color) {
            this.mTextColor = color;
            return this;
        }

        public RubberStampConfigBuilder textBackgroundColor(final int color) {
            this.mTextBackgroundColor = color;
            return this;
        }

        public RubberStampConfigBuilder typeFacePath(final String typeFacePath) {
            this.mTypeFacePath = typeFacePath;
            return this;
        }

        public RubberStampConfigBuilder rubberStampPosition(final RubberStampPosition position,
                                                            final int positionX,
                                                            final int positionY) {
            if (position != RubberStampPosition.CUSTOM) {
                throw new IllegalArgumentException("This constructor can only be used when the " +
                        "rubberStampPosition is RubberStamp.CUSTOM");
            }
            this.mRubberStampPosition = position;
            this.mPositionX = positionX;
            this.mPositionY = positionY;
            return this;
        }

        public RubberStampConfigBuilder rubberStampPosition(final RubberStampPosition position) {
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

        public RubberStampConfigBuilder textShader(final Shader shader) {
            this.mTextShader = shader;
            return this;
        }

        public RubberStampConfigBuilder rotation(final float rotation) {
            this.mRotation = rotation;
            return this;
        }

        public RubberStampConfigBuilder textShadow(final float blurRadius, final float shadowXOffset,
                                        final float shadowYOffset,@ColorInt final int shadowColor) {
            this.mTextShadowBlurRadius = blurRadius;
            this.mTextShadowXOffset = shadowXOffset;
            this.mTextShadowYOffset = shadowYOffset;
            this.mTextShadowColor = shadowColor;
            return this;
        }

        public RubberStampConfigBuilder margin(final int xMargin, final int yMargin) {
            this.mXMargin = xMargin;
            this.mYMargin = yMargin;
            return this;
        }

        public RubberStampConfig build() {
            return new RubberStampConfig(this);
        }
    }
}
