package com.rxnqst.pvz.peas;

import java.awt.*;

public class SnowPea extends Pea {
    public int freezeTime;
    public SnowPea(int posX, int posY, int level) {
        super(posX, posY, level);
        color = Color.BLUE;
        dmg = 35 + (level-1) * 25;
        this.freezeTime = 100 + (level-1)*50;
    }
}
