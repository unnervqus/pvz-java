package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class SnowPeashooter extends Plant {
    public static final int RELOAD_TIME = 500 * SEED_RELOAD_MODIFIER;
    public static final int COST = 300;
    public static int RELOAD = RELOAD_TIME;
    public int freezeTime = 100;
    public SnowPeashooter(int posX, int posY) {
        super(140, posX, posY, GameEngine.SeedSlot.SNOW_PEASHOOTER, true);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+40, line * 150+135 , 58, 60);
    }

    @Override
    public void levelUP() {
        super.levelUP();
        freezeTime += 50;
    }
}
