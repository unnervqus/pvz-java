package com.rxnqst.pvz.peas;

import java.awt.*;

public class Puff extends Pea {
    public Puff(int posX, int posY, int level) {
        super(posX, posY+10, level);
        dmg = 15 + (level-1)*10;
        size = 10;
        color = Color.MAGENTA;
        hitbox.height = size;
        hitbox.width = size;
    }
}
