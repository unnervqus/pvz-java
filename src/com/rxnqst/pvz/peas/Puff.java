package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.ImageManager;

public class Puff extends Pea {
    public Puff(int posX, int posY, int level) {
        super(posX, posY+10, level, ImageManager.getTexture(GameObjectType.Puff));
        dmg = 15 + (level-1)*10;
        hitbox.height = 8;
        hitbox.width = 8;
    }
}
