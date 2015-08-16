package com.OshurkovAlekseiDevelopment.conngame.gameElement.timer;

import android.os.CountDownTimer;

import com.OshurkovAlekseiDevelopment.conngame.GameScreen;

public class MyDownTimer extends CountDownTimer {

    long mills;

    public MyDownTimer(long millisInFuture, long countDownInterval) {

        super(millisInFuture, countDownInterval);
        this.mills = millisInFuture;
    }

    @Override
    public void onFinish() {
        GameScreen.showWindow(R.string.Text_Title_Lose, R.string.Text_Lose, 1);
    }

    @Override
    public void onTick(long millisUntilFinished) {

        mills = millisUntilFinished / 1000;
        GameScreen.Upd(String.format("%02d", mills / 60) + ":" +
                String.format("%02d", mills % 60));

    }

    public long returnSecond() {
        return mills;
    }
}
