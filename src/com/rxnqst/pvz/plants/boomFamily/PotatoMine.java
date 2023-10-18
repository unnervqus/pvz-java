package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameSettings.SEED_RELOAD_MODIFIER;

public class PotatoMine extends Plant {
    public int armoringTime = 500;
    public static final int RELOAD_TIME = 1000 * SEED_RELOAD_MODIFIER;
    public static final int COST = 25;
    public static int RELOAD = RELOAD_TIME;
    public Rect boomArea;
    public PotatoMine(int posX, int posY) {
        super(100, posX, posY, GameEngine.SeedSlot.POTATO_MINE_UNREADY, false);
        RELOAD = RELOAD_TIME;
        GameEngine.sunAmount -= COST;
        hitbox = new Rect(column * 150+45, line * 150+135 , 54, 46);
        boomArea = new Rect(hitbox.x - 60, hitbox.y - 50, hitbox.width + 120, hitbox.height+65);
    }
}
