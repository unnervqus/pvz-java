package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class ZombieConehead extends Zombie {
    public static final int COST = 200;
    public ZombieConehead(int posX, int line){
        super(posX, line, 500, 1, 1, ImageManager.ImgName.ZOMBIE_CONEHEAD);
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 110);
        this.head = new Rect(posX, line * 150 + 100, 45, 60);
    }
}
