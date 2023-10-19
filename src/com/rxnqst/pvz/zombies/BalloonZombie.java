package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

import static com.rxnqst.pvz.AnimationManager.AtlasName.*;

public class BalloonZombie extends Zombie {
    public static int COST = 300;
    public static final Point frameSizeEat = new Point(70, 114);
    public static final Point frameSizeWalk = new Point(70, 114);
    public BalloonZombie(int posX, int line) {
        super(posX, line, 300, 1, 5, ImageManager.ImgName.ZOMBIE_BALLOON,
                AnimationManager.getTexture(ZOMBIE_BALLOON_FLY),
                AnimationManager.getTexture(ZOMBIE_BALLOON_FLY));
        hitbox = new Rect(posX, line * 150 + 40, 64, 106);
        head = new Rect(posX, line * 150 + 75, 35, 40);
    }
    public BalloonZombie(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_BALLOON,
                AnimationManager.getTexture(ZOMBIE_BALLOON_FLY),
                AnimationManager.getTexture(ZOMBIE_BALLOON_FLY));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 64, 106);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 35, 40);
    }
}
