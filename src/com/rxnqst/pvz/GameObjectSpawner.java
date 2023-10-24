package com.rxnqst.pvz;

import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.plants.attackFamily.*;
import com.rxnqst.pvz.plants.helpFamily.*;
import com.rxnqst.pvz.plants.defenceFamily.*;
import com.rxnqst.pvz.plants.boomFamily.*;
import com.rxnqst.pvz.utils.Utils;
import com.rxnqst.pvz.zombies.*;

import java.util.Arrays;
import java.util.Collection;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameEngine.zombieList;

public class GameObjectSpawner {
    public static void newPlant(GameObjectType type, int tileX, int tileY) {

        if(pvzContainers.get(type).COST <= sunAmount) {
            Plant plant = null;
            switch (type) {
                case Peashooter -> plant = new Peashooter(tileX, tileY);
                case Sunflower -> plant = new Sunflower(tileX, tileY);
                case Wallnut -> plant = new Wallnut(tileX, tileY);
                case PotatoMine -> plant = new PotatoMine(tileX, tileY);
                case Puffshroom -> plant = new Puffshroom(tileX, tileY);
                case SnowPeashooter -> plant = new SnowPeashooter(tileX, tileY);
                case TriplePeashooter -> plant = new TriplePeashooter(tileX, tileY);
                case CherryBomb -> plant = new CherryBomb(tileX, tileY);
                case TorchWood -> plant = new TorchWood(tileX, tileY);
                case Jalapeno -> plant = new Jalapeno(tileX, tileY);
                case IceMushroom -> plant = new IceMushroom(tileX, tileY);
                case TallWallnut -> plant = new TallWallnut(tileX, tileY);
                case Cactus -> plant = new Cactus(tileX, tileY);
                case CabbagePult -> plant = new CabbagePult(tileX, tileY);
                case WatermelonPult -> plant = new WatermelonPult(tileX, tileY);
                case Pumpkin -> plant = new Pumpkin(tileX, tileY);
                case SpikeRock -> plant = new SpikeRock(tileX, tileY);
                case SpikeWeed -> plant = new SpikeWeed(tileX, tileY);
            }
            sunAmount -= pvzContainers.get(type).COST;
            plantList.add(plant);
            serverPlantQueue.add(plant);
            pvzContainers.get(type).reloading = pvzContainers.get(type).RELOAD_TIME;
            selectedObject = null;
        }
    }
    public static void newZombie(GameObjectType type, int line) {
        newZombie(type, 0, line);
    }
    public static void newZombie(GameObjectType type, int posX, int line) {
        if(posX == 0) posX = 2000;
        Zombie zombie = null;
        if(pvzContainers.get(type).COST <= brainBonus || !isMultiplayerOn) {
            switch (type) {
                case ZBasic -> zombie = new BasicZombie(posX, line);
                case ZConehead -> zombie = new ZombieConehead(posX, line);
                case ZBuckethead -> zombie = new ZombieBuckethead(posX, line);
                case ZDoor -> zombie = new ZombieDoor(posX, line);
                case ZJackbox -> zombie = new ZombieJackbox(posX, line);
                case ZBalloon -> zombie = new BalloonZombie(posX, line);
                case ZZomboni -> zombie = new Zomboni(posX, line);
                case ZImp -> zombie = new ZombieImp(posX, line);
                case ZYeti -> zombie = new Yeti(posX, line);
            }
            if (isMultiplayerOn) {
                if (isServer) serverZombieQueue.add(zombie);
                else clientZombieQueue.add(zombie);
            } else zombieList.add(zombie);
            if(isMultiplayerOn) selectedObject = null;
            brainsAmount -= pvzContainers.get(type).COST;
        }
    }
    public static void newMisc(GameObjectType type, int posX, int posY) {
        if(pvzContainers.get(type).COST <= brainBonus || !isMultiplayerOn) {
            Grave object;
            Zombie[] zList;
            switch (type) {
                case ZGrave -> {
                    object = new Grave(posX, posY);
                    for (int p = 0; p < plantList.size(); p++) {
                        if(Utils.checkBoxesOverlap(plantList.get(p).hitbox, object.hitbox)) plantList.get(p).hp = 0;
                    }
                    if (isMultiplayerOn) {
                        if (isServer) serverGraveQueue.add(object);
                        else clientGraveQueue.add(object);
                        selectedObject = null;
                    } else graveList.add(object);
                }
                case ZFlag -> {
                    zList = ZombieActions.flag();
                    if (isMultiplayerOn) {
                        if (isServer) serverZombieQueue.addAll(Arrays.stream(zList).toList());
                        else clientZombieQueue.addAll(Arrays.stream(zList).toList());
                        selectedObject = null;
                    } else zombieList.addAll(Arrays.stream(zList).toList());
                }
            }
            brainsAmount -= pvzContainers.get(type).COST;
        }
    }
}
