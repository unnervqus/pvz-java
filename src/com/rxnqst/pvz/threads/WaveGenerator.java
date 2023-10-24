package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.GameObjectSpawner;
import com.rxnqst.pvz.GameObjectType;
import com.rxnqst.pvz.zombies.Zombie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.rxnqst.pvz.GameEngine.isGameRunning;
import static com.rxnqst.pvz.GameEngine.zombieList;

public class WaveGenerator implements Runnable {
    private int zombiesSpawned = 0;
    private int[] zpointsArray = new int[]{1, 3, 5, 7, 12, 18, 24, 36, 45, 56, 77};
    private int iteration = 0;
    private int iterationIndex = 0;
    private final Random rndm = new Random();
    private final HashMap<GameObjectType, Integer> zombieCost = new HashMap<>(){{
        put(GameObjectType.ZBasic,      1);
        put(GameObjectType.ZConehead,   3);
        put(GameObjectType.ZBuckethead, 5);
        put(GameObjectType.ZImp,        6);
        put(GameObjectType.ZDoor,       7);
        put(GameObjectType.ZBalloon,    11);
        put(GameObjectType.ZJackbox,    14);
        put(GameObjectType.ZZomboni,    25);
        put(GameObjectType.ZGrave,      50);
        put(GameObjectType.ZYeti,       75);
        put(GameObjectType.ZFlag,       150);
    }};
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                while (isGameRunning) {
                    int zpoints = zpointsArray[iterationIndex++];
                    if(iterationIndex == 10) {
                        iterationIndex = 0;
                        ++iteration;
                        for (int i = 0; i < zpointsArray.length; i++) {
                            zpointsArray[i] += 50;
                        }
                    }
                    if(zombieList.size() == 0) Thread.sleep(15);
                    else Thread.sleep(10000);
                    while (zpoints > 0) {
                        switch (rndm.nextInt(0, 11)) {
                            case 0 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZBasic)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZBasic, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZBasic);
                                }
                            }
                            case 1 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZConehead)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZConehead, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZConehead);
                                }
                            }
                            case 2 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZBuckethead)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZBuckethead, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZBuckethead);
                                }
                            }
                            case 3 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZDoor)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZDoor, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZDoor);
                                }
                            }
                            case 4 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZImp)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZImp, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZImp);
                                }
                            }
                            case 5 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZJackbox)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZJackbox, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZJackbox);
                                }
                            }
                            case 6 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZBalloon)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZBalloon, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZBalloon);
                                }
                            }
                            case 7 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZZomboni)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZZomboni, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZZomboni);
                                }
                            }
                            case 8 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZYeti)) {
                                    GameObjectSpawner.newZombie(GameObjectType.ZYeti, rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZYeti);
                                }
                            }
                            case 9 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZGrave)) {
                                    GameObjectSpawner.newMisc(GameObjectType.ZGrave, rndm.nextInt(9, 12), rndm.nextInt(0, 6));
                                    zpoints -= zombieCost.get(GameObjectType.ZGrave);
                                }
                            }
                            case 10 -> {
                                if(zpoints >= zombieCost.get(GameObjectType.ZFlag)) {
                                    GameObjectSpawner.newMisc(GameObjectType.ZFlag, 0,0);
                                    zpoints -= zombieCost.get(GameObjectType.ZFlag);
                                }
                            }
                        }
                    }

                }
            }
        } catch (InterruptedException ignored) {
        }
    }
}
