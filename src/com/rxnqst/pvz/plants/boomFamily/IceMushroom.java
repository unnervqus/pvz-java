package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class IceMushroom extends Plant {
    public static final int RELOAD_TIME = 1400 * SEED_RELOAD_MODIFIER;
    public static final int COST = 500;
    public static int RELOAD = RELOAD_TIME;
    public int freezeTime = 600;
    public IceMushroom(int tileX, int tileY) {
        super(250, tileX, tileY, GameEngine.SeedSlot.ICE_MUSHROOM, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+45, line * 150+155 , 70, 64);
    }
}
