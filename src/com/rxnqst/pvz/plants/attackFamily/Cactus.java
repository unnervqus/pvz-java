package com.rxnqst.pvz.plants.attackFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class Cactus extends Plant {
    public static final int RELOAD_TIME = 1000 * SEED_RELOAD_MODIFIER;
    public static final int COST = 600;
    public static int RELOAD = RELOAD_TIME;
    public Cactus(int column, int line) {
        super(400, column, line, GameEngine.SeedSlot.CACTUS, true);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+40, line * 150+125 , 78, 72);
    }

    @Override
    public void levelUP() {
        super.levelUP();
        hp += 100;
    }
}
