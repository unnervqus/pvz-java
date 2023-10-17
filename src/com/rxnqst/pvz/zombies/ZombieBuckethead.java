package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

public class ZombieBuckethead extends Zombie {
    public static int COST = 400;
    public ZombieBuckethead(int posX, int line) {
        super(posX, line, 1000, 1, 1, ImageManager.ImgName.ZOMBIE_BUCKETHEAD);
        this.hitbox = new Rect(posX, line * 150 + 100, 58, 100);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
    }
}
