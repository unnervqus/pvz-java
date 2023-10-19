package com.rxnqst.pvz;

import com.rxnqst.pvz.plants.Plant;
import com.rxnqst.pvz.plants.attackFamily.*;
import com.rxnqst.pvz.plants.helpFamily.*;
import com.rxnqst.pvz.plants.defenceFamily.*;
import com.rxnqst.pvz.plants.boomFamily.*;
import com.rxnqst.pvz.zombies.*;

import static com.rxnqst.pvz.GameEngine.pvzContainers;

public class GameObjectSpawner {
    public static Plant newPlant(GameObjectType type, int tileX, int tileY) {
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
        GameEngine.sunAmount -= pvzContainers.get(type).COST;
        pvzContainers.get(type).reloading = pvzContainers.get(type).RELOAD_TIME;
        return plant;
    }
    public static Zombie newZombie(GameObjectType type, int line) {
        Zombie zombie = null;
        switch (type) {
            case ZBasic -> zombie = new BasicZombie(2000, line);
            case ZConehead -> zombie = new ZombieConehead(2000, line);
            case ZBuckethead -> zombie = new ZombieBuckethead(2000, line);
            case ZDoor -> zombie = new ZombieDoor(2000, line);
            case ZJackbox -> zombie = new ZombieJackbox(2000, line);
            case ZBalloon -> zombie = new BalloonZombie(2000, line);
            case ZZomboni -> zombie = new Zomboni(2000, line);
            case ZImp -> zombie = new ZombieImp(2000, line);
            case ZYeti -> zombie = new Yeti(2000, line);
        }
        GameEngine.sunAmount -= pvzContainers.get(type).COST;
        return zombie;
    }
}
