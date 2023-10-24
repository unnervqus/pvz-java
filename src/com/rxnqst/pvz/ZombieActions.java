package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Utils;
import com.rxnqst.pvz.zombies.*;

import java.util.Random;

import static com.rxnqst.pvz.GameEngine.*;

public class ZombieActions {
    public static void jackInTheBox(ZombieJackbox zombie) {
        int boomChance = new Random().nextInt(0, 500);
        if (boomChance < 1) {
            Sound.playExplosionSound();
            for (int p = 0; p < plantList.size(); p++) {
                if(Utils.checkBoxesOverlap(plantList.get(p).hitbox, zombie.boomArea))
                    plantList.get(p).hp -= 1500;
            }
            zombie.hp = 0;
        }
    }
    public static Zombie[] flag() {
        Zombie[] zombieList = new Zombie[graveList.size()];
        for (int g = 0; g < graveList.size(); g++) {
            Grave grave = graveList.get(g);
            int r = randomizer.nextInt(0, 6);
            switch (r) {
                case 0 -> zombieList[g] = new BasicZombie(grave.column*150,grave.line*150);
                case 1 -> zombieList[g] = new ZombieConehead(grave.column*150,grave.line*150);
                case 2 -> zombieList[g] = new ZombieBuckethead(grave.column*150,grave.line*150);
                case 3 -> zombieList[g] = new ZombieDoor(grave.column*150,grave.line*150);
                case 4 -> zombieList[g] = new ZombieImp(grave.column*150,grave.line*150);
                case 5 -> zombieList[g] = new ZombieJackbox(grave.column*150,grave.line*150);
            }
        }
        return zombieList;
    }
}
