package com.rxnqst.pvz.plants.helpFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;
import static com.rxnqst.pvz.GameSettings.SUNFLOWER_PROD_TIME;

public class Sunflower extends Plant {
    public static GameObjectType type = GameObjectType.Sunflower;
    public static int RELOAD = pvzContainers.get(GameObjectType.Sunflower).RELOAD_TIME;
    public int sunReload = SUNFLOWER_PROD_TIME;
    public Sunflower(int posX, int posY) {
        super(50, posX, posY, false);
        hitbox = new Rect(column * 150+45, line * 150+135 , 56, 61);
    }
    @Override
    public void levelUP() {
        super.levelUP();
        hp += 25;
    }
}
