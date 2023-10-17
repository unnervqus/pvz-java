package com.rxnqst.pvz;

import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.plants.attackFamily.*;
import com.rxnqst.pvz.zombies.Zombie;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameSettings.PEA_RELOAD_TIME;

public class PeaCreator {
    public static void createPea(Plant plant, Zombie zombie) {
        if (plant.type == GameEngine.SeedSlot.TRIPLE_PEASHOOTER) triplePeashooter((TriplePeashooter) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.CACTUS) cactus((Cactus) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.PUFFSHROOM) puffshroom((Puffshroom) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.PEASHOOTER) peashooter((Peashooter) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.SNOW_PEASHOOTER) snowPeashooter((SnowPeashooter) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.WATERMELON_PULT) watermelonPult((WatermelonPult) plant, zombie);
        else if (plant.type == GameEngine.SeedSlot.CABBAGE_PULT) cabbagePult((CabbagePult) plant, zombie);
    }
    private static void triplePeashooter(TriplePeashooter plant, Zombie zombie) {
        {
            if (zombie.line - 1 == plant.line || zombie.line + 1 == plant.line || zombie.line == plant.line) {
                plant.upLinePea = new Pea(plant.hitbox.x, plant.hitbox.y - 150, plant.level);
                plant.downLinePea = new Pea(plant.hitbox.x, plant.hitbox.y + 150, plant.level);
                plant.ammo = new Pea(plant.hitbox.x, plant.hitbox.y, plant.level );
                plant.reloadCooldown = PEA_RELOAD_TIME;
                peaList.add(plant.ammo);
                peaList.add(plant.upLinePea);
                peaList.add(plant.downLinePea);
                if(isServer && isMultiplayerOn) {
                    serverPeaQueue.add(plant.ammo);
                    serverPeaQueue.add(plant.upLinePea);
                    serverPeaQueue.add(plant.downLinePea);
                }
            }
        }
    }

    private static void cactus(Cactus plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            plant.ammo = new SpikePea(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void peashooter(Peashooter plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            plant.ammo = new Pea(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void snowPeashooter(SnowPeashooter plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            plant.ammo = new SnowPea(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void puffshroom(Puffshroom plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            if (Math.abs(plant.hitbox.x - zombie.hitbox.x) < 450) {
                plant.ammo = new Puff(plant.hitbox.x, plant.hitbox.y, plant.level);
                plant.reloadCooldown = PEA_RELOAD_TIME;
                peaList.add(plant.ammo);
                if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
            }
        }
    }

    private static void watermelonPult(WatermelonPult plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            plant.ammo = new Watermelon(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void cabbagePult(CabbagePult plant, Zombie zombie) {
        if (zombie.line == plant.line) {
            plant.ammo = new Cabbage(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }
}

