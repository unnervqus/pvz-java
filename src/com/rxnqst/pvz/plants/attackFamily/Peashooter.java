package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Peashooter extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.Peashooter).RELOAD_TIME;
    public Peashooter(int posX, int posY) {
        super(120, posX, posY, true);
        hitbox = new Rect(column * 150+45, line * 150+135 , 52, 58);
        type = GameObjectType.Peashooter;
    }
}
