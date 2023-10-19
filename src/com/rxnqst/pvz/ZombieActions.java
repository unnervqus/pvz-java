package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Utils;
import com.rxnqst.pvz.zombies.ZombieJackbox;

import java.util.Random;

import static com.rxnqst.pvz.GameEngine.plantList;

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
}
