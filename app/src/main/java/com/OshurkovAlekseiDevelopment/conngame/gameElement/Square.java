package com.OshurkovAlekseiDevelopment.conngame.gameElement;

import android.graphics.Canvas;

import com.OshurkovAlekseiDevelopment.conngame.core.ParameterApplication;

public class Square {

    private final int fieldLength;
    private int fieldSubLenth;
    private final int limit = ParameterApplication.limit;
    private int tmpi;
    private int tmpj;
    private boolean selected = false;
    private final CreaterElement crElement;
    private final Element[][] field;
    private Element tmpElement;

    private Square() {
        crElement = new CreaterElement();
        field = crElement.fullingSquare(ParameterApplication.forContext, limit);
        ParameterApplication.COUNT = crElement.COUNT;
        fieldLength = field.length;
    }

    public static Square uniqueSquare;

    public static Square getSquare() {
        if (uniqueSquare == null || !ParameterApplication.continiumGame) {
            uniqueSquare = new Square();
            ParameterApplication.continiumGame = true;
        }

        return uniqueSquare;
    }

    public void draw(Canvas canvas) {

        for (int i = 0; i < fieldLength; i++) {
            fieldSubLenth = field[i].length;

            for (int j = 0; j < fieldSubLenth; j++) {
                field[i][j].draw(canvas);
            }
        }
    }

    public void initPosition(int left, int top) {
        int newleft = left;
        int newtop = top;

        for (int i = 0; i < fieldLength; i++) {
            fieldSubLenth = field[i].length;

            for (int j = 0; j < fieldSubLenth; j++) {
                field[i][j].mPoint.y = newtop;
                field[i][j].mPoint.x = newleft;
                newleft += ParameterApplication.widthPicture;
            }

            newleft = left;
            newtop += ParameterApplication.heightPicture;
        }
    }

    void removeCheck(Element firstElement, Element secondElement) {
        firstElement.isActive = true;
        firstElement.deCheck();
        firstElement.xArray.clear();
        firstElement.yArray.clear();

        secondElement.isActive = true;
        secondElement.deCheck();
        secondElement.xArray.clear();
        secondElement.yArray.clear();

        Line.clearpoints();
        selected = false;
    }

    boolean slichilka(Element firstElement, Element secondElement) {

        boolean result = false, global = false;
        int tmpXone = 0, tmpYone = 0, tmpXsec = 0, tmpYsec = 0;
        int lenghX = 99, lenghY = 99;

        for (int i : firstElement.xArray) {
            int start = 0;
            int stop = 0;
            if (secondElement.xArray.contains(i)) {
                if (firstElement.PositionY > secondElement.PositionY) {
                    start = secondElement.PositionY;
                    stop = firstElement.PositionY;
                } else {
                    stop = secondElement.PositionY;
                    start = firstElement.PositionY;
                }

                for (int k = start; k <= stop; k++) {
                    if (field[k][i].isActive) {
                        result = false;
                        break;
                    } else {
                        result = true;
                    }
                }

                if (result && lenghX > Math.abs(i - firstElement.PositionX)) {
                    lenghX = Math.abs(i - firstElement.PositionX);
                    global = true;
                    tmpXone = field[firstElement.PositionY][i].getCentralX();
                    tmpYone = field[firstElement.PositionY][i].getCentralY();
                    tmpXsec = field[secondElement.PositionY][i].getCentralX();
                    tmpYsec = field[secondElement.PositionY][i].getCentralY();
                }
            }
        }

        if (!result) {
            for (int i : firstElement.yArray) {
                int start = 0;
                int stop = 0;

                if (secondElement.yArray.contains(i)) {
                    if (firstElement.PositionX > secondElement.PositionX) {
                        start = secondElement.PositionX;
                        stop = firstElement.PositionX;
                    } else {
                        stop = secondElement.PositionX;
                        start = firstElement.PositionX;
                    }

                    for (int k = start; k <= stop; k++) {
                        if (field[i][k].isActive) {
                            result = false;
                            break;
                        } else {
                            result = true;
                        }
                    }

                    if (result && lenghY > Math.abs(i - firstElement.PositionY)) {
                        lenghY = Math.abs(i - firstElement.PositionY);
                        global = true;
                        tmpXone = field[i][firstElement.PositionX].getCentralX();
                        tmpYone = field[i][firstElement.PositionX].getCentralY();

                        tmpXsec = field[i][secondElement.PositionX].getCentralX();
                        tmpYsec = field[i][secondElement.PositionX].getCentralY();
                    }
                }
            }
        }
        if (global) {
            Line.addpoint(tmpXone, tmpYone);
            Line.addpoint(tmpXone, tmpYone);
            Line.addpoint(tmpXsec, tmpYsec);
            Line.addpoint(tmpXsec, tmpYsec);
        }

        return global;
    }

    void searcheFreeArea(Element element) {

        for (int tmpY = 0; tmpY < ParameterApplication.defaultRow; tmpY++) {

            if (field[element.PositionY][tmpY].isActive) {

                if (tmpY < element.PositionX) {
                    field[element.PositionY][element.PositionX].xArray.clear();
                } else {
                    break;
                }
            } else {
                field[element.PositionY][element.PositionX].xArray.add(tmpY);
            }
        }

        for (int tmpJ = 0; tmpJ < ParameterApplication.defaultColums; tmpJ++) {

            if (field[tmpJ][element.PositionX].isActive) {
                if (tmpJ < element.PositionY) {
                    field[element.PositionY][element.PositionX].yArray.clear();
                } else {
                    break;
                }
            } else {
                field[element.PositionY][element.PositionX].yArray.add(tmpJ);
            }
        }
    }

    public boolean helpplayer() {
        boolean control = false;
        for (int i = 1; i < fieldLength - 1; i++) {
            fieldSubLenth = field[i].length;
            for (int j = 1; j < fieldSubLenth - 1; j++) {

                for (int k = 1; k < fieldLength - 1; k++) {
                    for (int l = 1; l < fieldLength - 1; l++) {

                        if (slichilka(field[i][j], field[k][l])) {
                            control = true;
                        }
                    }
                }
            }
        }
        return control;
    }

    public void alldel() {
        tmpElement.reset();
        field[tmpi][tmpj].reset();
        Line.clearpoints();
    }

    public int toushed(int x, int y) {
        int psi = 0;
        for (int i = 0; i < fieldLength; i++) {
            fieldSubLenth = field[i].length;
            for (int j = 0; j < fieldSubLenth; j++) {

                int elementLeft = field[i][j].mPoint.x;
                int elementTop = field[i][j].mPoint.y;

                if (x > elementLeft && x < (elementLeft + ParameterApplication.widthPicture) &&
                        y > elementTop && y < (elementTop + ParameterApplication.heightPicture)) {
                    if (field[i][j].isActive) {
                        if (!selected) {
                            field[i][j].isActive = false;
                            field[i][j].Check();
                            field[i][j].PositionX = j;
                            field[i][j].PositionY = i;
                            searcheFreeArea(field[i][j]);
                            tmpElement = field[i][j];
                            Line.addpoint(field[i][j].getCentralX(), field[i][j].getCentralY());
                            selected = true;

                            psi = 1;
                            break;

                        } else {
                            if (!field[i][j].isChecked && field[i][j].isActive) {
                                if (tmpElement.ID == field[i][j].ID) {
                                    field[i][j].isActive = false;
                                    field[i][j].Check();
                                    field[i][j].PositionX = j;
                                    field[i][j].PositionY = i;
                                    searcheFreeArea(field[i][j]);

                                    if (slichilka(tmpElement, field[i][j])) {
                                        selected = false;
                                        Line.addpoint(field[i][j].getCentralX(), field[i][j].getCentralY());
                                        Line.active = true;
                                        tmpi = i;
                                        tmpj = j;

                                        psi = 2;
                                        break;
                                    } else {
                                        removeCheck(tmpElement, field[i][j]);
                                        psi = 1;
                                        break;
                                    }
                                } else {
                                    removeCheck(tmpElement, field[i][j]);
                                    psi = 1;
                                    break;
                                }
                            } else {
                                removeCheck(tmpElement, field[i][j]);
                                psi = 1;
                                break;
                            }
                        }

                    } else {
                        if (field[i][j].isChecked) {
                            field[i][j].isActive = true;
                            field[i][j].deCheck();
                            field[i][j].xArray.clear();
                            field[i][j].yArray.clear();

                            Line.clearpoints();
                            selected = false;
                            psi = 1;
                            break;
                        }
                    }
                }
            }
        }
        return psi;
    }
}
