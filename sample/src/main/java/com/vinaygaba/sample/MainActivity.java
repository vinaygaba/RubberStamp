package com.vinaygaba.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampPosition;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Sample";
    private ImageView mImageView;
    private RadioGroup mRadioGroup;
    private Bitmap mBaseBitmap;
    private Button mGenerateButton;
    private SeekBar mAlphaSeekBar;
    private SeekBar mRotationSeekBar;
    private SeekBar mTextSizeSeekBar;
    private Spinner mRubberStampPosition;
    private LinearLayout mTextLayoutWrapper;
    private TextView mTextColor, mTextBackgroundColor;
    private EditText mRubberStampText;
    private ColorPicker mColorPicker;
    private RubberStamp mRubberStamp;
    @ColorInt private int mTextColorValue = Color.RED;
    @ColorInt private int mTextBackgroundColorValue = Color.WHITE;
    private Switch mShaderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    public void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mBaseBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.lenna);
        mGenerateButton = (Button) findViewById(R.id.generateRubberStamp);
        mAlphaSeekBar = (SeekBar) findViewById(R.id.alphaSeekBar);
        mRotationSeekBar = (SeekBar) findViewById(R.id.rotationSeekBar);
        mTextSizeSeekBar = (SeekBar) findViewById(R.id.textSizeSeekBar);
        mRubberStampPosition = (Spinner) findViewById(R.id.rubberStampPositions);
        mTextLayoutWrapper = (LinearLayout) findViewById(R.id.textLayoutWrapper);
        mTextColor = (TextView) findViewById(R.id.textColor);
        mTextBackgroundColor = (TextView) findViewById(R.id.textBackgroundColor);
        mRubberStampText = (EditText) findViewById(R.id.rubberStampEditText);
        mShaderSwitch = (Switch) findViewById(R.id.shaderSwitch);
        mColorPicker = new ColorPicker(this, 255, 0, 0);
        mRubberStamp = new RubberStamp(this);
    }

    public void setListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.textRubberStamp) {
                    mTextLayoutWrapper.setVisibility(View.VISIBLE);
                } else {
                    mTextLayoutWrapper.setVisibility(View.GONE);
                }
            }
        });

        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRubberStamp();
            }
        });

        mTextColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mColorPicker.show();
                mColorPicker.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int color) {
                        Drawable background = mTextColor.getCompoundDrawables()[2];
                        GradientDrawable gradientDrawable = (GradientDrawable) background;
                        gradientDrawable.setColor(color);
                        mTextColorValue = color;
                        mColorPicker.dismiss();
                    }
                });
                return false;
            }
        });

        mTextBackgroundColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mColorPicker.show();
                mColorPicker.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int color) {
                        Drawable background = mTextBackgroundColor.getCompoundDrawables()[2];
                        GradientDrawable gradientDrawable = (GradientDrawable) background;
                        gradientDrawable.setColor(color);
                        mTextBackgroundColorValue = color;
                        mColorPicker.dismiss();
                    }
                });
                return false;
            }
        });
    }

    public void generateRubberStamp() {
        RubberStampConfig config;
        int alpha = mAlphaSeekBar.getProgress();
        int rotation = mRotationSeekBar.getProgress();
        RubberStampPosition rubberStampPosition =
                convertToRubberStampPosition(mRubberStampPosition.getSelectedItemPosition());

        if(mRadioGroup.getCheckedRadioButtonId() == R.id.bitmapRubberStamp) {
            Bitmap logo = BitmapFactory.decodeResource(getResources(),
                    R.drawable.logo);

            config = new RubberStampConfig.RubberStampConfigBuilder()
                    .base(mBaseBitmap)
                    .rubberStamp(logo)
                    .alpha(alpha)
                    .rotation(rotation)
                    .rubberStampPosition(rubberStampPosition)
                    .build();
        } else {
            Shader shader = getShader();
            config = new RubberStampConfig.RubberStampConfigBuilder()
                    .base(mBaseBitmap)
                    .alpha(alpha)
                    .rotation(rotation)
                    .rubberStampPosition(rubberStampPosition)
                    .rubberStamp(mRubberStampText.getText().toString())
                    .textColor(mTextColorValue)
                    .textBackgroundColor(mTextBackgroundColorValue)
                    .textShader(shader)
                    .textSize(mTextSizeSeekBar.getProgress())
                    .build();
        }


        Observable<Bitmap> observable = getBitmap(mRubberStamp, config);
        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .single()
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.getMessage());
                    }
                });
    }

    private RubberStampPosition convertToRubberStampPosition(int selectedItemPosition) {
        switch (selectedItemPosition) {
            case 0: return RubberStampPosition.TOP_LEFT;

            case 1: return RubberStampPosition.TOP_CENTER;

            case 2: return RubberStampPosition.TOP_RIGHT;

            case 3: return RubberStampPosition.CENTER_LEFT;

            case 4: return RubberStampPosition.CENTER;

            case 5: return RubberStampPosition.CENTER_RIGHT;

            case 6: return RubberStampPosition.BOTTOM_LEFT;

            case 7: return RubberStampPosition.BOTTOM_CENTER;

            case 8: return RubberStampPosition.BOTTOM_RIGHT;

            case 9: return RubberStampPosition.CUSTOM;

            case 10: return RubberStampPosition.TILE;

            default: return RubberStampPosition.CENTER;
        }
    }

    public Observable<Bitmap> getBitmap(final RubberStamp rubberStamp,
                                        final RubberStampConfig config) {
        return Observable.defer(new Func0<Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                return Observable.just(rubberStamp.addStamp(config));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Shader getShader() {
        if (mShaderSwitch.isChecked()) {
            int[] rainbow = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
            Shader shader = new LinearGradient(0, 0, 0, 100, rainbow,
                    null, Shader.TileMode.MIRROR);

            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            shader.setLocalMatrix(matrix);

            return shader;
        }
        return null;
    }
}