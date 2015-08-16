package com.OshurkovAlekseiDevelopment.conngame.gameElement;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;

public class Element {

    private Drawable faceImage;
    final Point mPoint = new Point(0, 0);

    boolean isActive = true;
    private boolean isFrame = false;
    boolean isChecked = false;
    final Integer ID;
    int PositionX;
    int PositionY;
    final ArrayList<Integer> xArray;
    final ArrayList<Integer> yArray;

    public Element(Drawable image, Integer id) {
        this.ID = id;
        xArray = new ArrayList<Integer>();
        yArray = new ArrayList<Integer>();
        faceImage = image;
    }

    public void Check() {
        this.isChecked = true;
        isFrame = true;
    }

    public void deCheck() {
        this.isChecked = false;
        isFrame = false;
    }

    public void draw(Canvas canvs) {
        faceImage.setBounds(mPoint.x, mPoint.y, mPoint.x + ParameterApplication.widthPicture, mPoint.y + ParameterApplication.heightPicture);
        faceImage.draw(canvs);

        if (isFrame) {
            Paint fpaint = new Paint();
            fpaint.setColor(Color.RED);
            fpaint.setStrokeWidth(2);
            fpaint.setStyle(Style.STROKE);

            Rect frame = new Rect(mPoint.x, mPoint.y, (mPoint.x - 2) + ParameterApplication.widthPicture, (mPoint.y - 2) + ParameterApplication.heightPicture);
            canvs.drawRect(frame, fpaint);
        }
    }

    public void reset() {
        faceImage = new ColorDrawable(Color.TRANSPARENT);
        this.isActive = false;
        deCheck();
    }

    public int getCentralX() {
        return mPoint.x + (ParameterApplication.widthPicture / 2);
    }

    public int getCentralY() {
        return mPoint.y + (ParameterApplication.heightPicture / 2);
    }
}
