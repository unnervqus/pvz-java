package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

import static com.rxnqst.pvz.AnimationManager.AtlasName.ZOMBIE_BASIC_EAT;
import static com.rxnqst.pvz.AnimationManager.AtlasName.ZOMBIE_BASIC_WALK;

public class BasicZombie extends Zombie {
    public static final int COST = 100;
    public static final Point frameSizeWalk  = new Point(58, 94);
    public static final Point frameSizeEat = new Point(62, 88);
    public BasicZombie(int posX, int line) {
        super(posX, line, 250, 3, 1,
                ImageManager.ImgName.ZOMBIE_BASIC,
                AnimationManager.getTexture(ZOMBIE_BASIC_WALK),
                AnimationManager.getTexture(ZOMBIE_BASIC_EAT));
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 88);
        this.head = new Rect(posX, line*150+100, 45, 40);
    }
    public BasicZombie(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_BASIC,
                AnimationManager.getTexture(ZOMBIE_BASIC_WALK),
                AnimationManager.getTexture(ZOMBIE_BASIC_EAT));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 54, 88);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 40);
    }
}
