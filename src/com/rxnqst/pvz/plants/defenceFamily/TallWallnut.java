package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class TallWallnut extends Plant {
    public static final int RELOAD_TIME = 1400 * SEED_RELOAD_MODIFIER;
    public static final int COST = 150;
    public static int RELOAD = RELOAD_TIME;
    public TallWallnut(int tileX, int tileY) {
        super(10000, tileX, tileY, GameEngine.SeedSlot.TALL_WALLNUT, false);
        hitbox = new Rect(column * 150+65, line * 150+105, 62, 90);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 8500;
    }
}
