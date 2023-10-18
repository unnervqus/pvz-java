package com.rxnqst.pvz;

import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.boomFamily.CherryBomb;
import com.rxnqst.pvz.plants.boomFamily.IceMushroom;
import com.rxnqst.pvz.plants.boomFamily.Jalapeno;
import com.rxnqst.pvz.plants.boomFamily.PotatoMine;
import com.rxnqst.pvz.plants.helpFamily.SpikeRock;
import com.rxnqst.pvz.plants.helpFamily.SpikeWeed;
import com.rxnqst.pvz.plants.helpFamily.TorchWood;
import com.rxnqst.pvz.zombies.Zombie;

import java.awt.*;

import static com.rxnqst.pvz.GameEngine.peaList;
import static com.rxnqst.pvz.GameEngine.zombieList;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;

public class PlantActions {
    public static void cherryBomb(CherryBomb plant) {
        Sound.playCherryBombBoomSound();
        for (Zombie zombie : zombieList) {
            if (zombie.line - 1 == plant.line) {
                if (zombie.hitbox.x / 150 == plant.column
                        || zombie.hitbox.x / 150 - 1 == plant.column
                        || zombie.hitbox.x / 150 + 1 == plant.column
                ) zombie.hp -= 1500;
            } else if (zombie.line == plant.line) {
                if (zombie.hitbox.x / 150 == plant.column
                        || zombie.hitbox.x / 150 - 1 == plant.column
                        || zombie.hitbox.x / 150 + 1 == plant.column
                ) zombie.hp -= 1500;
            } else if (zombie.line + 1 == plant.line) {
                if (zombie.hitbox.x / 150 == plant.column
                        || zombie.hitbox.x / 150 - 1 == plant.column
                        || zombie.hitbox.x / 150 + 1 == plant.column
                ) zombie.hp -= 1500;
            }
        }
        plant.hp = 0;
    }
    public static void potatoMine(PotatoMine plant) {
        if(plant.type == GameEngine.SeedSlot.POTATO_MINE_UNREADY) {
            if (plant.armoringTime > 0) --plant.armoringTime;
            if (plant.armoringTime == 0) plant.type = GameEngine.SeedSlot.POTATO_MINE;
        } else {
            boolean isDetonated = false;
            for (int z = 0; z < zombieList.size(); ++z) {
                Zombie zombie = zombieList.get(z);
                if (checkBoxesOverlap(zombie.hitbox, plant.hitbox)) {
                    isDetonated = true;
                    break;
                }
            }
            if (isDetonated) {
                Sound.playPotatoMineBoomSound();
                for (int z = 0; z < zombieList.size(); ++z) {
                    Zombie zombie = zombieList.get(z);
                    if (checkBoxesOverlap(zombie.hitbox, plant.boomArea))
                        zombie.hp -= 1500;
                }
                plant.hp = 0;
            }
        }
    }

    public static void iceMushroom(IceMushroom plant) {
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            zombie.freezeDelay = plant.freezeTime;
        }
        plant.hp = 0;
    }

    public static void jalapeno(Jalapeno plant) {
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if(zombie.line == plant.line) {
                zombie.hp -= 1500;
            }
        }
        plant.hp = 0;
    }

    public static void spikeRock(SpikeRock plant) {
        int zombiesDamaged = 0;
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if (checkBoxesOverlap(zombie.hitbox, plant.hitbox)) {
                zombie.hp -= plant.dmg;
                ++zombiesDamaged;
                if(zombiesDamaged == plant.maxZombie) break;
            }
        }
    }

    public static void spikeWeed(SpikeWeed plant) {
        int zombiesDamaged = 0;
        for (int z = 0; z < zombieList.size(); ++z) {
            Zombie zombie = zombieList.get(z);
            if (checkBoxesOverlap(zombie.hitbox, plant.hitbox)) {
                zombie.hp -= plant.dmg;
                ++zombiesDamaged;
                if(zombiesDamaged == plant.maxZombie) break;
            }
        }
    }
    public static void torchWood(TorchWood plant) {
        for (int p = 0; p < peaList.size(); p++) {
            Pea pea = peaList.get(p);
            if(!(pea instanceof Watermelon) && !(pea instanceof Cabbage) && !(pea instanceof Puff)) {
                if (checkBoxesOverlap(pea.hitbox, plant.hitbox) && !pea.isFired) {
                    pea.dmg += plant.extraDmg;
                    if(pea instanceof Needle)
                        pea.image = ImageManager.getTexture(ImageManager.ImgName.FIRE_NEEDLE);
                    else
                        pea.image = ImageManager.getTexture(ImageManager.ImgName.FIRE_PEA);
                    pea.isFired = true;
                }
            }
        }
    }
}
