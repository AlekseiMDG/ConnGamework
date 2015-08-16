package com.OshurkovAlekseiDevelopment.conngame.gameElement;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.OshurkovAlekseiDevelopment.conngame.GameScreen;
import com.OshurkovAlekseiDevelopment.conngame.gameElement.Line;
import com.OshurkovAlekseiDevelopment.conngame.gameElement.Square;
import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;

public class Drawing extends View {

    private final Square square;
    private final Context context;
    private final Handler mHandler;
    private int x;
    private int y;
    private boolean checkTouch = true;

    public Drawing(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        mHandler = new Handler();
        initPosition();
        ParameterApplication.forContext = context;
        square = Square.getSquare();
        square.initPosition(x, y);
        ParameterApplication.continiumGame = true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable mBackground2 = new ColorDrawable(Color.TRANSPARENT);
        mBackground2.draw(canvas);

        square.draw(canvas);
        Line.draw(canvas);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (checkTouch) {
                timetosleep(square.toushed((int) event.getX(), (int) event.getY()));
            }
        }
        return true;
    }

    void timetosleep(int h) {

        if (h == 1) invalidate();
        if (h == 2) {
            checkTouch = false;
            invalidate();

            mHandler.postDelayed(new Runnable() {
                public void run() {

                    square.alldel();
                    if (ParameterApplication.isSound) {
                        ParameterApplication.sound.play(ParameterApplication.soundID, 1, 1, 1, 0, 1f);
                    }
                    invalidate();
                    checkTouch = true;
                }
            }, 700);

            ParameterApplication.COUNT--;
            if (ParameterApplication.COUNT == 0) {
                GameScreen.dTime.cancel();
                GameScreen.showWindow(R.string.Text_Title_Win, R.string.Text_Win, 0);
            }
        }
    }

    void initPosition() {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            ParameterApplication.widthPicture = ParameterApplication.heightPicture = display.getWidth() / 10;

            this.y = (display.getHeight() / 2) - ((ParameterApplication.heightPicture * 10) / 2);
            this.x = (display.getWidth() / 2) - ((ParameterApplication.heightPicture * 10) / 2);
        }
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            ParameterApplication.widthPicture = ParameterApplication.heightPicture = display.getHeight() / 10;

            this.x = (display.getWidth() / 2) - ((ParameterApplication.heightPicture * 10) / 2);
            this.y = (display.getHeight() / 2) - ((ParameterApplication.heightPicture * 10) / 2);
        }
    }
}
