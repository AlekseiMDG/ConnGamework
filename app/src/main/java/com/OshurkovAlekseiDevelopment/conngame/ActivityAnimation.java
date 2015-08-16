package com.OshurkovAlekseiDevelopment.conngame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;

import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;
import com.google.android.gms.ads.*;

public class ActivityAnimation extends Activity {

    private InterstitialAd interstitial;
    private Thread mSplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        ParameterApplication.widthDisplay = metrics.widthPixels;
        ParameterApplication.heightDisplay = metrics.heightPixels;

        // Создание межстраничного объявления.
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(ParameterApplication.AD_UNIT_ID_MEJSTRANICHNI);

        // Создание запроса объявления.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Запуск загрузки межстраничного объявления.
        interstitial.loadAd(adRequest);

        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Ждем некоторое время, или выход по прикосновению  
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }

                finish();

                // Запускаем основную форму  
                startActivity(new Intent(ActivityAnimation.this, ActivityPlaySelector.class));
                ActivityAnimation.this.finish();

            }
        };

        mSplashThread.start();
    }

    public void onDestroy() {
        super.onDestroy();
        displayInterstitial();
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }

    // Вызовите displayInterstitial(), когда будете готовы показать межстраничное объявление.
    void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
