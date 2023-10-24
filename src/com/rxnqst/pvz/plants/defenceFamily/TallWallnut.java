package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class TallWallnut extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.TallWallnut).RELOAD_TIME;
    public TallWallnut(int tileX, int tileY) {
        super(10000, tileX, tileY,  false);
        hitbox = new Rect(column * 150+65, line * 150+105, 62, 90);
        type = GameObjectType.TallWallnut;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 8500;
    }
}
