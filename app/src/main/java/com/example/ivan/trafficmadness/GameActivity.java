package com.example.ivan.trafficmadness;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private float relativeHeight;
    private float relativeWidth;
    private Bitmap commonCar;


    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGameParametrs();
        this.gamePanel = new GamePanel(this);
        setContentView(R.layout.activity_game);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        relativeLayout.addView(gamePanel);
        findViewById(R.id.button).setOnClickListener(this);
    }

    private void setGameParametrs(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float screenWidth = displaymetrics.widthPixels;
        float screenHeight = displaymetrics.heightPixels;
        relativeWidth = screenWidth / 100;
        relativeHeight = screenHeight / 100;

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.common_car);
        commonCar = bitmap;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        gamePanel.getGameManager().setPause(true);
        AlertDialog alert = createDialog();
        alert.show();
    }

    public void onStop() {
        super.onStop();
        gamePanel.getGameManager().setPause(true);
    }

    public void onRestart(){
        super.onRestart();
        gamePanel.getGameManager().setPause(false);
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Do you want to exit?")
                .setCancelable(false)
                .setNegativeButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gamePanel.getGameManager().setPause(false);
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
        return builder.create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            gamePanel.getGameManager().setPause(true);
            AlertDialog alertDialog = createDialog();
            alertDialog.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public float getRelativeHeight() {
        return relativeHeight;
    }

    public float getRelativeWidth() {
        return relativeWidth;
    }

    public Bitmap getCommonCar() {
        return commonCar;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
