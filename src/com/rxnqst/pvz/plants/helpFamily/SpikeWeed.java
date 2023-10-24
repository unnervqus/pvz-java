package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class SpikeWeed extends Plant {
    public static int RELOAD = pvzContainers.get(GameObjectType.SpikeWeed).RELOAD_TIME;
    public int maxZombie;
    public int dmg;
    public SpikeWeed(int tileX, int tileY) {
        super(150, tileX, tileY, false);
        hitbox = new Rect(column * 150+25, line * 150+175 , 90, 36);
        type = GameObjectType.SpikeWeed;
        maxZombie = 3;
        dmg = 1;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        ++maxZombie;
        ++dmg;
        hp += 50;
    }
}
