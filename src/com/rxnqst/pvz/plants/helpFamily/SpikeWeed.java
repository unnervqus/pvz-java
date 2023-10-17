package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

public class SpikeWeed extends Plant {
    public static final int RELOAD_TIME = 200;
    public static final int COST = 150;
    public static  int RELOAD = RELOAD_TIME;
    public int maxZombie;
    public int dmg;
    public SpikeWeed(int tileX, int tileY) {
        super(150, tileX, tileY, GameEngine.SeedSlot.SPIKE_WEED, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+25, line * 150+175 , 90, 36);
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
