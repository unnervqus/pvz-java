package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Pumpkin extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.Pumpkin).RELOAD_TIME;
    public Pumpkin(int tileX, int tileY) {
        super(2000, tileX, tileY, false);
        hitbox = new Rect(column * 150+45, line * 150+135 , 80, 65);
        type = GameObjectType.Pumpkin;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 1700;
    }
}
