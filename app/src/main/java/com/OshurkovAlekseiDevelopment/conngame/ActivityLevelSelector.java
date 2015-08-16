package com.OshurkovAlekseiDevelopment.conngame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;

public class ActivityLevelSelector extends Activity {

    private SharedPreferences LEVEL;
    private int C = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selection);

        ParameterApplication.initParam();
        Typeface tapeface = Typeface.createFromAsset(getAssets(), "fonts/comissans.ttf");

        LEVEL = getSharedPreferences(ParameterApplication.PREFERENCES_GAME, Activity.MODE_PRIVATE);
        if (LEVEL.contains(ParameterApplication.LEVEL_COMPLITE)) {
            C = LEVEL.getInt(ParameterApplication.LEVEL_COMPLITE, 1);
        }

        TextView ttx = (TextView) findViewById(R.id.txtWarred);
        ttx.setTypeface(tapeface);
        ttx.setText(R.string.Buck);

        ViewGroup viewgr = (ViewGroup) findViewById(R.id.setTxtLevel);

        for (int i = 0; i < viewgr.getChildCount(); i++) {
            View v = viewgr.getChildAt(i);

            int time = ParameterApplication.param.get(i).get("time") / 1000;

            ((TextView) v).setText("X " + ParameterApplication.param.get(i).get("limit") + "\n " + (String.format("%02d", time / 60) + ":" +
                    String.format("%02d", time % 60)));
            ((TextView) v).setGravity(Gravity.CENTER_HORIZONTAL);

            if (i < C) {
                ((TextView) v).setBackgroundResource(R.drawable.level_open);
                ((TextView) v).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        onClickLevel(v);
                    }
                });

            } else {
                ((TextView) v).setBackgroundResource(R.drawable.level_close);
            }
        }
    }

    void onClickLevel(View v) {
        Intent intent = new Intent(ActivityLevelSelector.this, GameScreen.class);

        switch (v.getId()) {
            case R.id.imgOne: {
                intent.putExtra("time", ParameterApplication.param.get(0).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(0).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(0).get("level"));
                break;
            }
            case R.id.imgTwo: {
                intent.putExtra("time", ParameterApplication.param.get(1).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(1).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(1).get("level"));
                break;
            }
            case R.id.imgThree: {
                intent.putExtra("time", ParameterApplication.param.get(2).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(2).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(2).get("level"));
                break;
            }
            case R.id.imgFore: {
                intent.putExtra("time", ParameterApplication.param.get(3).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(3).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(3).get("level"));
                break;
            }
            case R.id.imgFive: {
                intent.putExtra("time", ParameterApplication.param.get(4).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(4).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(4).get("level"));
                break;
            }
            case R.id.imgSix: {
                intent.putExtra("time", ParameterApplication.param.get(5).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(5).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(5).get("level"));
                break;
            }
            case R.id.imgEigh: {
                intent.putExtra("time", ParameterApplication.param.get(6).get("time"));
                intent.putExtra("limit", ParameterApplication.param.get(6).get("limit"));
                intent.putExtra("level", ParameterApplication.param.get(6).get("level"));
                break;
            }
        }
        intent.putExtra("passage", 1);
        startActivity(intent);
    }

    public void Buck(View v) {
        startActivity(new Intent(ActivityLevelSelector.this, ActivityPlaySelector.class));
    }
}
