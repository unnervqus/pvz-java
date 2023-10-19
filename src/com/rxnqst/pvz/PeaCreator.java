package com.rxnqst.pvz;

import com.rxnqst.pvz.peas.*;
import com.rxnqst.pvz.plants.*;
import com.rxnqst.pvz.plants.attackFamily.*;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameSettings.PEA_RELOAD_TIME;

public class PeaCreator {
    public static void check(Plant plant, GameObject obj) {
        if (plant.type == GameObjectType.TriplePeashooter) triplePeashooter((TriplePeashooter) plant, obj);
        else if (plant.type == GameObjectType.Cactus) cactus((Cactus) plant, obj);
        else if (plant.type == GameObjectType.Puffshroom) puffshroom((Puffshroom) plant, obj);
        else if (plant.type == GameObjectType.Peashooter) peashooter((Peashooter) plant, obj);
        else if (plant.type == GameObjectType.SnowPeashooter) snowPeashooter((SnowPeashooter) plant, obj);
        else if (plant.type == GameObjectType.WatermelonPult) watermelonPult((WatermelonPult) plant, obj);
        else if (plant.type == GameObjectType.CabbagePult) cabbagePult((CabbagePult) plant, obj);
    }
    private static void triplePeashooter(TriplePeashooter plant, GameObject obj) {
        {
            if (obj.line - 1 == plant.line || obj.line + 1 == plant.line || obj.line == plant.line) {
                plant.upLinePea = new Pea(plant.hitbox.x, plant.hitbox.y - 150, plant.level, null);
                plant.downLinePea = new Pea(plant.hitbox.x, plant.hitbox.y + 150, plant.level, null);
                plant.ammo = new Pea(plant.hitbox.x, plant.hitbox.y, plant.level, null);
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

    private static void cactus(Cactus plant, GameObject obj) {
        if (obj.line == plant.line) {
            plant.ammo = new Needle(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void peashooter(Peashooter plant, GameObject obj) {
        if (obj.line == plant.line) {
            plant.ammo = new Pea(plant.hitbox.x, plant.hitbox.y, plant.level, null);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void snowPeashooter(SnowPeashooter plant, GameObject obj) {
        if (obj.line == plant.line) {
            plant.ammo = new SnowPea(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void puffshroom(Puffshroom plant, GameObject obj) {
        if (obj.line == plant.line) {
            if (Math.abs(plant.hitbox.x - obj.hitbox.x) < 450) {
                plant.ammo = new Puff(plant.hitbox.x, plant.hitbox.y, plant.level);
                plant.reloadCooldown = PEA_RELOAD_TIME;
                peaList.add(plant.ammo);
                if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
            }
        }
    }

    private static void watermelonPult(WatermelonPult plant, GameObject obj) {
        if (obj.line == plant.line) {
            plant.ammo = new Watermelon(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }

    private static void cabbagePult(CabbagePult plant, GameObject obj) {
        if (obj.line == plant.line) {
            plant.ammo = new Cabbage(plant.hitbox.x, plant.hitbox.y, plant.level);
            plant.reloadCooldown = PEA_RELOAD_TIME;
            peaList.add(plant.ammo);
            if(isServer && isMultiplayerOn) serverPeaQueue.add(plant.ammo);
        }
    }
}

