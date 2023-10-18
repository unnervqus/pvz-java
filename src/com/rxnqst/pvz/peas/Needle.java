package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.ImageManager;
import com.rxnqst.pvz.zombies.Zombie;

public class Needle extends Pea {
    public int zombieHits = 0;
    public final int maxZombieHits;
    public Zombie[] damagedZombies;
    public Needle(int posX, int posY, int level) {
        super(posX, posY+10, level, ImageManager.getTexture(ImageManager.ImgName.NEEDLE));
        this.maxZombieHits = level+2;
        damagedZombies = new Zombie[maxZombieHits];
        dmg = 30 + (level-1)*15;
        hitbox.height = 16;
        hitbox.width = 24;
    }
}
