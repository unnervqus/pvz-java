package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

public class ZombieDoor extends Zombie {
    public static int COST = 550;
    public static final Point frameSizeEat = new Point(62, 94);
    public static final Point frameSizeWalk = new Point(68, 100);
    public ZombieDoor(int posX, int line) {
        super(posX, line, 1200, 3, 1, ImageManager.ImgName.ZOMBIE_DOOR,
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBIE_DOOR_WALK),
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBIE_DOOR_EAT));
        this.hitbox = new Rect(posX, line * 150 + 100, 62, 92);
        this.head = new Rect(posX+10, line * 150 + 100, 35, 40);
    }
    public ZombieDoor(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBIE_DOOR,
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBIE_DOOR_WALK),
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBIE_DOOR_EAT));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 62, 92);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 35, 40);
    }
}
