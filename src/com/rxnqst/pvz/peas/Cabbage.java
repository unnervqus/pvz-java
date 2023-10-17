package com.rxnqst.pvz.peas;

public class Cabbage extends Pea {
    public Cabbage(int posX, int posY, int level) {
        super(posX, posY, level);
        dmg = 75 + (level-1) * 50;
        size = 20;
        hitbox.width = size;
        hitbox.height = size;
    }
}
