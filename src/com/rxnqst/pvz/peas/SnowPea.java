package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.ImageManager;

import java.awt.*;

public class SnowPea extends Pea {
    public int freezeTime;
    public SnowPea(int posX, int posY, int level) {
        super(posX, posY, level,  ImageManager.getTexture(ImageManager.ImgName.SNOW_PEA));
        dmg = 35 + (level-1) * 25;
        this.freezeTime = 100 + (level-1)*50;
        hitbox.width = 20;
        hitbox.height = 20;
    }
}
