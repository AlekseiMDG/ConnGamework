package com.OshurkovAlekseiDevelopment.conngame.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.media.SoundPool;


public class ParameterApplication {

    public static final String PREFERENCES_GAME = "mysettings";
    public static final String LEVEL_COMPLITE = "level";

    public static int COUNT;
    public static Context forContext;
    public static SoundPool sound;
    public static int soundID;

    public static boolean continiumGame = false;

    public static long timeAll;
    public static int limit, passage, level = 0;
    public static boolean inPause = false;
    public static int widthDisplay = 100;
    public static int heightDisplay = 100;
    public static int widthPicture = 50;
    public static int heightPicture = 50;

    public final static long defaultTime = 600000;
    public final static int defaultRow = 10;
    public final static int defaultColums = 10;

    public static boolean isSound = true;

    public static int ImageForSound() {
        if (isSound) {
            return R.drawable.sound_on;
        } else {
            return R.drawable.sound_off;
        }
    }

    public static void changeSound() {
        if (isSound) {
            isSound = false;
        } else {
            isSound = true;
        }
    }

    static ArrayList<HashMap<String, Integer>> param = new ArrayList<HashMap<String, Integer>>();

    static HashMap<String, Integer> firstLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> secondLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> therdLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> fourthLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> fifthLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> sixthLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> seventhLevel = new HashMap<String, Integer>();
    static HashMap<String, Integer> eighthLevel = new HashMap<String, Integer>();

    public static void initParam() {

        firstLevel.put("limit", 6);
        firstLevel.put("time", 120000);
        firstLevel.put("level", 1);

        secondLevel.put("limit", 6);
        secondLevel.put("time", 90000);
        secondLevel.put("level", 2);

        therdLevel.put("limit", 10);
        therdLevel.put("time", 120000);
        therdLevel.put("level", 3);

        fourthLevel.put("limit", 10);
        fourthLevel.put("time", 90000);
        fourthLevel.put("level", 4);

        fifthLevel.put("limit", 14);
        fifthLevel.put("time", 90000);
        fifthLevel.put("level", 5);

        sixthLevel.put("limit", 14);
        sixthLevel.put("time", 60000);
        sixthLevel.put("level", 6);

        seventhLevel.put("limit", 17);
        seventhLevel.put("time", 60000);
        seventhLevel.put("level", 7);

        eighthLevel.put("limit", 17);
        eighthLevel.put("time", 40000);
        eighthLevel.put("level", 8);

        param.add(firstLevel);
        param.add(secondLevel);
        param.add(therdLevel);
        param.add(fourthLevel);
        param.add(fifthLevel);
        param.add(sixthLevel);
        param.add(seventhLevel);
        param.add(eighthLevel);
    }
}
