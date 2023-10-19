package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class WatermelonPult extends Plant {
    public static GameObjectType type = GameObjectType.WatermelonPult;
    public static int RELOAD = pvzContainers.get(GameObjectType.WatermelonPult).RELOAD_TIME;
    public WatermelonPult(int tileX, int tileY) {
        super(500, tileX, tileY, true);
        hitbox = new Rect(column * 150+5, line * 150+125 , 96, 84);
    }
}
