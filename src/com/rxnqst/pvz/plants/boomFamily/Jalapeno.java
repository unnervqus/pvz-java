package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class Jalapeno extends Plant {
    public static final int RELOAD_TIME = 1000 * SEED_RELOAD_MODIFIER;
    public static final int COST = 200;
    public static int RELOAD = RELOAD_TIME;
    public Jalapeno(int tileX, int tileY) {
        super(300, tileX, tileY, GameEngine.SeedSlot.JALAPENO, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+65, line * 150+155 , 30, 56);
    }
}
