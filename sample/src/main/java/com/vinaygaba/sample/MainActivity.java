package com.vinaygaba.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampConfig.RubberStampConfigBuilder;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.rectangle);
        Bitmap logo = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo);
        Bitmap lenna = BitmapFactory.decodeResource(getResources(),
                R.drawable.lenna);
       RubberStamp rubberStamp = new RubberStamp(this);
        int[] rainbow = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        Shader shader = new LinearGradient(0, 0, 0, logo.getWidth(), rainbow,
                null, Shader.TileMode.MIRROR);

        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);

       RubberStampConfig config = new RubberStampConfigBuilder()
               .base(lenna)
               .rubberStamp("Watermark")
               .alpha(100)
               .textcolor(Color.BLACK)
               .backgroundcolor(Color.WHITE)
               .shadow(0.1f,  5, 5, Color.BLUE)
               .size(90)
               .rotation(-45)
               .rubberStampPosition(RubberStamp.CENTER)
               .build();

        imageView.setImageBitmap(rubberStamp.addStamp(config));
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
}
