package com.rxnqst.pvz;

import com.rxnqst.pvz.effects.CherryActivationEffect;
import com.rxnqst.pvz.effects.PotatoBoomEffect;
import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.boomFamily.CherryBomb;
import com.rxnqst.pvz.plants.boomFamily.IceMushroom;
import com.rxnqst.pvz.plants.boomFamily.Jalapeno;
import com.rxnqst.pvz.plants.boomFamily.PotatoMine;
import com.rxnqst.pvz.plants.helpFamily.SpikeRock;
import com.rxnqst.pvz.plants.helpFamily.SpikeWeed;
import com.rxnqst.pvz.plants.helpFamily.TorchWood;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.utils.Utils.checkBoxesOverlap;

public class PlantActions {
    public static void cherryBomb(CherryBomb plant) {
        Sound.playCherryBombBoomSound();
        effectList.add(new CherryActivationEffect(plant.hitbox.x, plant.hitbox.y));
        for (int z = 0; z < zombieList.size(); ++z) {
            if(checkBoxesOverlap(zombieList.get(z).hitbox, plant.boomArea)) {
                zombieList.get(z).hp -= 1500;
            }
        }
        plant.hp = 0;
    }
    public static void potatoMine(PotatoMine plant) {
        if(plant.type == GameObjectType._PotatoMine_unready) {
            if (plant.armoringTime > 0) --plant.armoringTime;
            if (plant.armoringTime == 0) plant.type = GameObjectType.PotatoMine;
        } else {
            boolean isDetonated = false;
            for (int z = 0; z < zombieList.size(); ++z) {
                if (checkBoxesOverlap(zombieList.get(z).hitbox, plant.hitbox)) {
                    isDetonated = true;
                    break;
                }
            }
            if (isDetonated) {
                Sound.playPotatoMineBoomSound();
                effectList.add(new PotatoBoomEffect(plant.hitbox.x, plant.hitbox.y));
                for (int z = 0; z < zombieList.size(); ++z)
                    if (checkBoxesOverlap(zombieList.get(z).hitbox, plant.boomArea))
                        zombieList.get(z).hp -= 1500;
                plant.hp = 0;
            }
        }
    }
    public static void iceMushroom(IceMushroom plant) {
        for (int z = 0; z < zombieList.size(); ++z) {
            zombieList.get(z).freezeDelay = plant.freezeTime;
        }
        plant.hp = 0;
    }
    public static void jalapeno(Jalapeno plant) {
        for (int z = 0; z < zombieList.size(); ++z)
            if(zombieList.get(z).line == plant.line)
                zombieList.get(z).hp -= 1500;
        plant.hp = 0;
    }
    public static void spikeRock(SpikeRock plant) {
        int zombiesDamaged = 0;
        for (int z = 0; z < zombieList.size(); ++z) {
            if (checkBoxesOverlap(zombieList.get(z).hitbox, plant.hitbox)) {
                zombieList.get(z).hp -= plant.dmg;
                ++zombiesDamaged;
                if(zombiesDamaged == plant.maxZombie) break;
            }
        }
    }

    public static void spikeWeed(SpikeWeed plant) {
        int zombiesDamaged = 0;
        for (int z = 0; z < zombieList.size(); ++z) {
            if (checkBoxesOverlap(zombieList.get(z).hitbox, plant.hitbox)) {
                zombieList.get(z).hp -= plant.dmg;
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
                        pea.image = ImageManager.getTexture(GameObjectType.FireNeedle);
                    else
                        pea.image = ImageManager.getTexture(GameObjectType.FirePea);
                    pea.isFired = true;
                }
            }
        }
    }
}
