package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

import static com.rxnqst.pvz.AnimationManager.AtlasName.*;

public class ZombieBuckethead extends Zombie {
    public static int COST = 400;
    public static final Point frameSizeEat = new Point(64, 100);
    public static final Point frameSizeWalk = new Point(64, 108);
    public ZombieBuckethead(int posX, int line) {
        super(posX, line, 1000, 1, 1,
                ImageManager.ImgName.ZOMBIE_BUCKETHEAD,
                AnimationManager.getTexture(ZOMBIE_BASIC_WALK),
                AnimationManager.getTexture(ZOMBIE_BASIC_EAT));
        this.hitbox = new Rect(posX, line * 150 + 100, 58, 100);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
    }
}
