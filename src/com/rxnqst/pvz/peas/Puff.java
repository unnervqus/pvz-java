package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.ImageManager;

import java.awt.*;

public class Puff extends Pea {
    public Puff(int posX, int posY, int level) {
        super(posX, posY+10, level, ImageManager.getTexture(ImageManager.ImgName.PUFF));
        dmg = 15 + (level-1)*10;
        hitbox.height = 8;
        hitbox.width = 8;
    }
}
