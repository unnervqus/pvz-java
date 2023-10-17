package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

public class Sun {
    public Sun(int posX, int posY, int targetY, int value) {
        this.hitbox = new Rect(posX, posY, 64, 64);
        this.targetY = targetY;
        this.value = value;
    }
    public Rect hitbox;
    public int value;
    public int targetY;
}
