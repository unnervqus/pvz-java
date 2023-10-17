package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;
import java.io.Serializable;

public class Pea implements Serializable {
    public int dmg;
    public int size;
    public Color color;
    public boolean isFired = false;
    public Rect hitbox;
    public Pea(int posX, int posY, int level) {
        this.dmg = 25 + (level-1) * 15;
        this.size = 15;
        hitbox = new Rect(posX, posY, size, size);
        color = Color.GREEN;
    }
}
