package com.OshurkovAlekseiDevelopment.conngame.gameElement;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Line {

    private static final Paint mPaint = new Paint();
    static boolean active = false;
    private static float[] points;
    private static ArrayList<Float> poin;

    public Line() {
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(2);
        poin = new ArrayList<Float>();
    }

    public static void addpoint(int x, int y) {
        poin.add((float) x);
        poin.add((float) y);
    }

    public static void clearpoints() {
        poin.clear();
    }

    public static void draw(Canvas canvas) {
        if (active) {
            points = new float[poin.size()];

            for (int i = 0; i < poin.size(); i++) {
                points[i] = poin.get(i);
            }
            canvas.drawLines(points, mPaint);
        }
    }
}
