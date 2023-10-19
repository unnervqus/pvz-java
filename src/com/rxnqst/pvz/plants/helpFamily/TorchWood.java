package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class TorchWood extends Plant {
    public GameObjectType type = GameObjectType.TorchWood;
    public static int RELOAD = pvzContainers.get(GameObjectType.TorchWood).RELOAD_TIME;
    public int extraDmg;
    public TorchWood(int tileX, int tileY) {
        super(1200, tileX, tileY, false);
        hitbox = new Rect(column * 150+45, line * 150+125 , 56, 74);
        extraDmg = 25;
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 300;
        extraDmg += 15;
    }
}
