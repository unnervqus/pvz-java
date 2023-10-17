package com.rxnqst.pvz.peas;

public class Watermelon extends Pea {
    public Watermelon(int posX, int posY, int level) {
        super(posX, posY, level);
        size = 25;
        dmg = 150 + (level-1) * 125;
        hitbox.height = size;
        hitbox.width = size;
    }
}
