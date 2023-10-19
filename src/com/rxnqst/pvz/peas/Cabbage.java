package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.ImageManager;

public class Cabbage extends Pea {
    public Cabbage(int posX, int posY, int level) {
        super(posX, posY, level, ImageManager.getTexture(GameObjectType.Cabbage));
        dmg = 75 + (level-1) * 50;
        hitbox.width = 24;
        hitbox.height = 25;
    }
}
