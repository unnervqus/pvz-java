package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Jalapeno extends Plant {
    public GameObjectType type = GameObjectType.Jalapeno;
    public static int RELOAD = pvzContainers.get(GameObjectType.Jalapeno).RELOAD_TIME;
    public Jalapeno(int tileX, int tileY) {
        super(300, tileX, tileY, false);
        hitbox = new Rect(column * 150+65, line * 150+155 , 30, 56);
    }
}
