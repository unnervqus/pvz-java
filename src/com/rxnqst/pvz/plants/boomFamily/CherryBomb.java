package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class CherryBomb extends Plant {
    public static final int RELOAD_TIME = 1000 * SEED_RELOAD_MODIFIER;
    public static final int COST = 150;
    public static int RELOAD = RELOAD_TIME;
    public CherryBomb(int posX, int posY) {
        super(250, posX, posY, GameEngine.SeedSlot.CHERRY_BOMB, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+45, line * 150+155 , 65, 54);
    }
}
