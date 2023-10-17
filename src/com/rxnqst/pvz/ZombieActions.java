package com.rxnqst.pvz;

import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.zombies.ZombieJackbox;

import java.util.Random;

import static com.rxnqst.pvz.GameEngine.plantList;

public class ZombieActions {
    public static void jackInTheBox(ZombieJackbox zombie) {
        int boomChance = new Random().nextInt(0, 1000);
        if (boomChance < 2) {
            Sound.playExplosionSound();
            for (int p = 0; p < plantList.size(); p++) {
                Plant plant = plantList.get(p);
                if (plant.line - 1 == zombie.line) {
                    if (plant.column - 1 == zombie.hitbox.x / 150
                            || plant.column == zombie.hitbox.x / 150
                            || plant.column + 1 == zombie.hitbox.x / 150) {
                        plant.hp = 0;
                    }
                } else if (plant.line == zombie.line) {
                    if (plant.column - 1 == zombie.hitbox.x / 150
                            || plant.column == zombie.hitbox.x / 150
                            || plant.column + 1 == zombie.hitbox.x / 150) {
                        plant.hp = 0;
                    }
                } else if (plant.line + 1 == zombie.line) {
                    if (plant.column - 1 == zombie.hitbox.x / 150
                            || plant.column == zombie.hitbox.x / 150
                            || plant.column + 1 == zombie.hitbox.x / 150) {
                        plant.hp = 0;
                    }
                }
            }
            zombie.hp = 0;
        }
    }
}
