package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

import static com.rxnqst.pvz.AnimationManager.AtlasName.*;

public class ZombieConehead extends Zombie {
    public static final int COST = 200;
    public static final Point frameSizeEat = new Point(62, 108);
    public static final Point frameSizeWalk = new Point(58, 110);
    public ZombieConehead(int posX, int line){
        super(posX, line, 500, 1, 1,
                ImageManager.ImgName.ZOMBIE_CONEHEAD,
                AnimationManager.getTexture(ZOMBIE_CONE_WALK),
                AnimationManager.getTexture(ZOMBIE_CONE_EAT));
        this.hitbox = new Rect(posX, line * 150 + 100, 54, 110);
        this.head = new Rect(posX, line * 150 + 100, 45, 60);
    }
    public ZombieConehead(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_CONEHEAD,
                AnimationManager.getTexture(ZOMBIE_CONE_WALK),
                AnimationManager.getTexture(ZOMBIE_CONE_EAT));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 54, 110);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 45, 60);
    }
}
