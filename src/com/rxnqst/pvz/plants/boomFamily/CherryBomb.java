package com.rxnqst.pvz.plants.boomFamily;

import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.utils.Rect;

import static com.rxnqst.pvz.GameEngine.pvzContainers;
public class CherryBomb extends Plant {
    public GameObjectType type = GameObjectType.CherryBomb;
    public static int RELOAD = pvzContainers.get(GameObjectType.CherryBomb).RELOAD_TIME;
    public Rect boomArea;
    public CherryBomb(int posX, int posY) {
        super(250, posX, posY, false);
        hitbox = new Rect(column * 150+45, line * 150+155 , 65, 54);
        boomArea = new Rect(hitbox.x-150, hitbox.y-150, hitbox.width+150, hitbox.height+150);
    }
}
