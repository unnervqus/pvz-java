package com.rxnqst.pvz.plants.defenceFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class Pumpkin extends Plant {
    public static final int RELOAD_TIME = 700 * SEED_RELOAD_MODIFIER;
    public static final int COST = 250;
    public static int RELOAD = RELOAD_TIME;
    public Pumpkin(int tileX, int tileY) {
        super(2000, tileX, tileY, GameEngine.SeedSlot.PUMPKIN , false);
        hitbox = new Rect(column * 150+45, line * 150+135 , 80, 65);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;

    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 1700;
    }
}
