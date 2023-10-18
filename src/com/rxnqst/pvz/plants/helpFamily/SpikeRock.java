package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class SpikeRock extends Plant {
    public static final int RELOAD_TIME = 600 * SEED_RELOAD_MODIFIER;
    public static final int COST = 550;
    public static int RELOAD = RELOAD_TIME;
    public int maxZombie;
    public int dmg;
    public SpikeRock(int tileX, int tileY) {
        super(750, tileX, tileY, GameEngine.SeedSlot.SPIKE_ROCK, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
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
