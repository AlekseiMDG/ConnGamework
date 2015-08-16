package com.OshurkovAlekseiDevelopment.conngame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;


public class ActivityPlaySelector extends Activity implements OnClickListener {

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ParameterApplication.sound = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        ParameterApplication.soundID = ParameterApplication.sound.load(getApplicationContext(), R.raw.cut3, 1);
        GameScreen.stime = 0;
        View v;

        Typeface tapeface = Typeface.createFromAsset(getAssets(), "fonts/comissans.ttf");

        ViewGroup viewgr = (ViewGroup) findViewById(R.id.items);

        for (int i = 0; i < viewgr.getChildCount(); i++) {
            v = viewgr.getChildAt(i);
            ((TextView) v).setTypeface(tapeface);
            ((TextView) v).setOnClickListener(this);
            ((TextView) v).setGravity(Gravity.RIGHT);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtPlay: {
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);

                intent.putExtra("time", ParameterApplication.defaultTime);
                intent.putExtra("limit", 15);
                intent.putExtra("passage", 0);
                intent.putExtra("level", 0);

                startActivity(intent);
                break;
            }
            case R.id.txtLevel: {
                startActivity(new Intent(ActivityPlaySelector.this, ActivityLevelSelector.class));
                break;
            }
            case R.id.txtInstruction: {
                startActivity(new Intent(ActivityPlaySelector.this, ActivityInstruction.class));
                break;
            }
            case R.id.txtExit: {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            }
        }
    }
}
