package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.ImageManager;

public class Watermelon extends Pea {
    public Watermelon(int posX, int posY, int level) {
        super(posX, posY, level,  ImageManager.getTexture(GameObjectType.Watermelon));
        dmg = 150 + (level-1) * 125;
        hitbox.width = 42;
        hitbox.height = 32;
    }
}
