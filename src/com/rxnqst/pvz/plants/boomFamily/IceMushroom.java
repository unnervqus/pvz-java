package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class IceMushroom extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.IceMushroom).RELOAD_TIME;
    public int freezeTime = 600;
    public IceMushroom(int tileX, int tileY) {
        super(250, tileX, tileY, false);
        hitbox = new Rect(column * 150+45, line * 150+155 , 70, 64);
        type = GameObjectType.IceMushroom;
    }
}
