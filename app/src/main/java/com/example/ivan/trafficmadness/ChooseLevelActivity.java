package com.example.ivan.trafficmadness;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

public class ChooseLevelActivity extends AppCompatActivity {
    LevelCreator levelCreator = new LevelCreator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        ImageButton button = (ImageButton) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setLevelCreatorParametrs();

        int id;

        button = (ImageButton) findViewById(R.id.level1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLevel(1);
            }
        });


        button = (ImageButton) findViewById(R.id.level2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLevel(2);
            }
        });


        button = (ImageButton) findViewById(R.id.level3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLevel(3);
            }
        });
    }

    private void startLevel(int id){
        Level level = levelCreator.createLevel(id);
        if(level != null){
            Intent intent = new Intent(this,GameActivity.class);
            intent.putExtra("Level", level);
            startActivity(intent);
        }

    }

    private void setLevelCreatorParametrs(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float screenWidth = displaymetrics.widthPixels;
        float screenHeight = displaymetrics.heightPixels;

        float relativeWidth = screenWidth / 100;
        float relativeHeight = screenHeight / 100;

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.common_car);

        levelCreator.setCommonCar(bitmap);
        levelCreator.setRelativeHeight(relativeHeight);
        levelCreator.setRelativeWidth(relativeWidth);
        bitmap = BitmapFactory.decodeResource(res, R.drawable.background);

        levelCreator.setBackground(adjustTexture(bitmap,screenWidth,screenHeight));    }

    private Bitmap adjustTexture(Bitmap texture, float newWidth, float newHigh) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHigh / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix scaleMatrix = new Matrix();
        // RESIZE THE BIT MAP
        scaleMatrix.postScale(scaleWidth, scaleHeight);
        scaleMatrix.postRotate(90);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                texture, 0, 0, width, height, scaleMatrix, true);

        return resizedBitmap;
    }
}
