package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

public class GameObject {
    public Rect hitbox;
    public int hp;
    public int line;
    public int column;
    public GameObject(int line, int column, int width, int height, int hp) {
        hitbox = new Rect(column * 150, line * 150 + 90, width, height);
        this.hp = hp;
    }
}
