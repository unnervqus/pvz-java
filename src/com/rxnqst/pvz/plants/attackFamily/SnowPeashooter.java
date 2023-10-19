package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class SnowPeashooter extends Plant {
    public GameObjectType type = GameObjectType.SnowPeashooter;
    public static int RELOAD = pvzContainers.get(GameObjectType.SnowPeashooter).RELOAD_TIME;
    public int freezeTime = 100;
    public SnowPeashooter(int posX, int posY) {
        super(140, posX, posY, true);
        hitbox = new Rect(column * 150+40, line * 150+135 , 58, 60);
    }

    @Override
    public void levelUP() {
        super.levelUP();
        freezeTime += 50;
    }
}
