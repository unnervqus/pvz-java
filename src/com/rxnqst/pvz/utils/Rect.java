package com.rxnqst.pvz.utils;

import java.io.Serializable;

public class Rect implements Serializable {
    public int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
