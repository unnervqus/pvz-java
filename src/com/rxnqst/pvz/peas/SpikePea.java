package com.rxnqst.pvz.peas;

import com.rxnqst.pvz.zombies.Zombie;

public class SpikePea extends Pea {
    public int zombieHits = 0;
    public final int maxZombieHits;
    public Zombie[] damagedZombies;
    public SpikePea(int posX, int posY, int level) {
        super(posX, posY+10, level);
        this.maxZombieHits = level+2;
        damagedZombies = new Zombie[maxZombieHits];
        dmg = 30 + (level-1)*15;
        size = 12;
        hitbox.height = size;
        hitbox.width = size;
    }
}
