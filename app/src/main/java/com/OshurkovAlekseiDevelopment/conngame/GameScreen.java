package com.OshurkovAlekseiDevelopment.conngame;

import com.OshurkovAlekseiDevelopment.conngame.gameElement.Square;
import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;
import com.OshurkovAlekseiDevelopment.conngame.gameElement.timer.MyDownTimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GameScreen extends Activity {
    private static Context context;

    public static long stime = 0;
    private static TextView txtT;
    private ImageView imgSound;
    private static Typeface Mtapeface;
    static MyDownTimer dTime;
    private static boolean timeIsStop = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Mtapeface = Typeface.createFromAsset(getAssets(), "fonts/comissans.ttf");

        if (!ParameterApplication.continiumGame) {
            ParameterApplication.timeAll = (long) getIntent().getIntExtra("time", 300000);
            ParameterApplication.limit = getIntent().getIntExtra("limit", 5);
            ParameterApplication.passage = getIntent().getIntExtra("passage", 0);
            ParameterApplication.level = getIntent().getIntExtra("level", 0);
        }
        setContentView(R.layout.activity_main);

        txtT = (TextView) findViewById(R.id.textViewV);
        imgSound = (ImageView) findViewById(R.id.imgSound1);
        shengS();

        if (stime == 0) {
            dTime = new MyDownTimer(ParameterApplication.timeAll, 1000);
        } else {
            dTime = new MyDownTimer(stime, 1000);
        }
    }

    public void onPause() {
        super.onPause();

        if (!timeIsStop) {
            stime = dTime.mills * 1000;
            dTime.cancel();
            dTime = new MyDownTimer(stime, 1000);
        }
    }

    public void onStart() {
        super.onStart();
        timeIsStop = false;
        dTime.start();
    }

    public void onDestroy() {
        super.onDestroy();
        timeIsStop = true;
        dTime.cancel();
    }

    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.imgPause: {
                showWindow(timeStop(), R.string.Text_Title_Pause, R.string.Text_Pause, 1);
                break;
            }
            case R.id.imgQuite: {
                showWindow(timeStop(), R.string.Text_Title_Quit, R.string.Text_Quit, 0);
                break;
            }
            case R.id.imgSound1: {
                ParameterApplication.changeSound();
                shengS();
                break;
            }
        }
    }

    void shengS() {
        if (ParameterApplication.isSound) {
            imgSound.setImageResource(R.drawable.sound_on);
        } else {
            imgSound.setImageResource(R.drawable.sound_off);
        }
    }

    public static void Upd(String times) {
        txtT.setText(times);
    }

    MyDownTimer timeStop() {
        timeIsStop = true;
        stime = dTime.mills * 1000;
        dTime.cancel();
        return dTime = new MyDownTimer(stime, 1000);
    }

    public void onBackPressed() {
        showWindow(timeStop(), R.string.Text_Title_Quit, R.string.Text_Quit, 0);
    }

    //0-quit
    //1-pause
    private static void showWindow(final MyDownTimer dTime, int title, int mainText, int flag) {
        final AlertDialog builder = new AlertDialog.Builder(context).create();
        LayoutInflater factory = LayoutInflater.from(context);
        View layout;

        if (flag == 0) {
            layout = factory.inflate(R.layout.dialog_quite, null);

            TextView txtTitle = (TextView) layout.findViewById(R.id.txtWarred);
            txtTitle.setText(title);
            txtTitle.setTypeface(Mtapeface);

            TextView txtMsg = (TextView) layout.findViewById(R.id.textView4);
            txtMsg.setText(mainText);
            txtMsg.setTypeface(Mtapeface);

            TextView txtYes = (TextView) layout.findViewById(R.id.txtLikeBtnOk);
            txtYes.setTypeface(Mtapeface);
            txtYes.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    stime = 0;
                    Square.uniqueSquare = null;
                    ParameterApplication.continiumGame = false;
                    context.startActivity(new Intent(context, com.OshurkovAlekseiDevelopment.conngame.ActivityPlaySelector.class));
                }
            });

            TextView txtNo = (TextView) layout.findViewById(R.id.textView3);
            txtNo.setTypeface(Mtapeface);
            txtNo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    builder.dismiss();
                    dTime.start();
                    timeIsStop = false;
                }
            });

        } else {
            layout = factory.inflate(R.layout.dialog_pause, null);
            ParameterApplication.inPause = true;
            ImageView img = (ImageView) layout.findViewById(R.id.imgPause1);
            img.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    builder.dismiss();
                    dTime.start();
                    timeIsStop = false;
                }
            });
        }

        builder.setCancelable(false);
        builder.setCanceledOnTouchOutside(false);
        builder.setCustomTitle(layout);
        builder.show();
    }

    public static void showWindow(int title, int message, final int flagWin) {
        //flagWin=1 - lose
        //flagWin=0 - win

        Square.uniqueSquare = null;
        ParameterApplication.continiumGame = false;

        final AlertDialog builder = new AlertDialog.Builder(context).create();
        LayoutInflater factory = LayoutInflater.from(context);
        View layout;
        layout = factory.inflate(R.layout.dialog_end, null);

        TextView txtTitle = (TextView) layout.findViewById(R.id.txtWarred);
        txtTitle.setText(title);
        txtTitle.setTypeface(Mtapeface);

        TextView txtMsg = (TextView) layout.findViewById(R.id.txtMessageW);
        txtMsg.setText(message);
        txtMsg.setTypeface(Mtapeface);

        TextView txtOk = (TextView) layout.findViewById(R.id.txtLikeBtnOk);
        txtOk.setTypeface(Mtapeface);
        txtOk.setClickable(true);
        txtOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ParameterApplication.passage == 0) {
                    context.startActivity(new Intent(context, ActivityPlaySelector.class));
                } else {
                    if (flagWin == 0) {
                        SharedPreferences mGamePreferences = context.getSharedPreferences(ParameterApplication.PREFERENCES_GAME,
                                Context.MODE_PRIVATE);
                        int tmp_level = mGamePreferences.getInt(ParameterApplication.LEVEL_COMPLITE, 1);
                        if (ParameterApplication.level == tmp_level) {
                            Editor editor = mGamePreferences.edit();
                            editor.putInt(ParameterApplication.LEVEL_COMPLITE, tmp_level + 1);
                            editor.apply();
                        }
                        context.startActivity(new Intent(context, ActivityLevelSelector.class));
                    } else {
                        context.startActivity(new Intent(context, ActivityLevelSelector.class));
                    }
                }
            }
        });

        builder.setCancelable(false);
        builder.setCanceledOnTouchOutside(false);
        builder.setCustomTitle(layout);
        builder.show();
    }
}