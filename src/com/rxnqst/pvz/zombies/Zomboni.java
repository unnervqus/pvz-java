package com.rxnqst.pvz.zombies;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.utils.Rect;

import java.awt.*;

public class Zomboni extends Zombie {
    public static int COST = 1000;
    public static final Point frameSizeWalk  = new Point(120, 122);
    public static final Point frameSizeEat = new Point(120, 122);
    public Zomboni(int posX, int line) {
        super(posX, line, 2000, 1000, 1, ImageManager.ImgName.ZOMBONI,
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBONI_RIDE),
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBONI_RIDE));
        hitbox = new Rect(posX, line * 150 + 100, 118, 118);
        head = new Rect(posX, line * 150 + 100, 118, 118);
    }
    public Zomboni(Zombie zombie) {
        super(zombie.hitbox.x, zombie.line, zombie.hp, zombie.dmg, zombie.speed,
                ImageManager.ImgName.ZOMBONI,
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBONI_RIDE),
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBONI_RIDE));
        this.hitbox = new Rect(zombie.hitbox.x, zombie.hitbox.y, 118, 118);
        this.head = new Rect(zombie.hitbox.x, zombie.hitbox.y, 118, 118);
    }
}
