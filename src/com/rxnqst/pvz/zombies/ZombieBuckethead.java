package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

import static com.rxnqst.pvz.AnimationManager.AtlasName.*;

public class ZombieBuckethead extends Zombie {
    public static int COST = 400;
    public static final Point frameSizeEat = new Point(64, 100);
    public static final Point frameSizeWalk = new Point(64, 102);
    public ZombieBuckethead(int posX, int line) {
        super(posX, line, 1000, 1, 1,
                ImageManager.ImgName.ZOMBIE_BUCKETHEAD,
                AnimationManager.getTexture(ZOMBIE_BUCKET_WALK),
                AnimationManager.getTexture(ZOMBIE_BUCKET_EAT));
        this.hitbox = new Rect(posX, line * 150 + 100, 58, 100);
        this.head = new Rect(posX, line * 150 + 100, 50, 50);
    }
    public ZombieBuckethead(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_BUCKETHEAD,
                AnimationManager.getTexture(ZOMBIE_BUCKET_WALK),
                AnimationManager.getTexture(ZOMBIE_BUCKET_EAT));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 58, 100);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 50, 50);
    }
}
