package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class TorchWood extends Plant {
    public static final int RELOAD_TIME = 600 * SEED_RELOAD_MODIFIER;
    public static final int COST = 550;
    public static int RELOAD = RELOAD_TIME;
    public int extraDmg;
    public TorchWood(int tileX, int tileY) {
        super(1200, tileX, tileY, GameEngine.SeedSlot.TORCH_WOOD, false);
        hitbox = new Rect(column * 150+45, line * 150+125 , 56, 74);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        extraDmg = 25;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 300;
        extraDmg += 15;
    }
}
