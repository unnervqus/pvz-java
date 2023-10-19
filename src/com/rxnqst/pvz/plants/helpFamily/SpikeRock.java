package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class SpikeRock extends Plant {
    public  GameObjectType type = GameObjectType.SpikeRock;
    public static int RELOAD = pvzContainers.get(GameObjectType.SpikeRock).RELOAD_TIME;
    public int maxZombie;
    public int dmg;
    public SpikeRock(int tileX, int tileY) {
        super(750, tileX, tileY, false);
        hitbox = new Rect(column * 150+15, line * 150+175 , 108, 60);
        maxZombie = 7;
        dmg = 5;
    }

    @Override
    public void levelUP() {
        super.levelUP();
        ++maxZombie;
        dmg += 5;
        hp += 250;
    }
}
