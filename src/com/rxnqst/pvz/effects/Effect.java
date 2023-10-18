package com.rxnqst.pvz.effects;

import com.rxnqst.pvz.utils.Rect;

import java.awt.image.BufferedImage;

public abstract class Effect {
    public int frameIndex = 0;
    public Rect hitbox;
    public int framesAmount;
    public BufferedImage atlas;
    public Effect(int posX, int posY, int width, int height, int framesAmount, BufferedImage atlas) {
        hitbox = new Rect(posX, posY, width, height);
        this.framesAmount = framesAmount;
        this.atlas = atlas;
    }
    public abstract void after();
}
