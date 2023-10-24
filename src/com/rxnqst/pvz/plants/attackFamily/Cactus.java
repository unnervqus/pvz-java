package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class Cactus extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.Cactus).RELOAD_TIME;
    public Cactus(int column, int line) {
        super(400, column, line, true);
        hitbox = new Rect(column * 150+40, line * 150+125 , 78, 72);
        type = GameObjectType.Cactus;
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 100;
    }
}
