package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.Sound;
import com.rxnqst.pvz.zombies.*;

import java.util.Random;

import static com.rxnqst.pvz.GameEngine.isGameRunning;
import static com.rxnqst.pvz.GameEngine.zombieList;

public class WaveGenerator implements Runnable {
    private int zombiesSpawned = 0;
    private final Random r = new Random();
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (isGameRunning) {
                zombiesSpawned++;
                int zombieChance = r.nextInt(0, 100);
                int line = r.nextInt(0, 6);
                int sleepTime = 0;
                int posX = r.nextInt(2000, 5000);
                if (zombieList.size() < 1000) {
                    if (zombiesSpawned < 1) {
                        sleepTime = 5000;
                        zombieList.add(new BasicZombie(6500, line));
                    } else if (zombiesSpawned < 3) {
                        sleepTime = 4000;
                        zombieList.add(new BasicZombie(posX, line));
                    } else if (zombiesSpawned < 25) {
                        sleepTime = 3000;
                        if (zombieChance < 50) zombieList.add(new BasicZombie(2000, line));
                        else zombieList.add(new ZombieConehead(posX, line));
                    } else if (zombiesSpawned < 50) {
                        sleepTime = 2000;
                        if (zombieChance < 40) zombieList.add(new BasicZombie(posX, line));
                        else if (zombieChance < 80) zombieList.add(new ZombieConehead(posX, line));
                        else zombieList.add(new ZombieBuckethead(posX, line));
                    } else if (zombiesSpawned < 150) {
                        sleepTime = 1000;
                        if (zombieChance < 30) zombieList.add(new BasicZombie(posX, line));
                        else if (zombieChance < 50) zombieList.add(new ZombieConehead(posX, line));
                        else if (zombieChance < 70) zombieList.add(new ZombieBuckethead(posX, line));
                        else zombieList.add(new ZombieImp(posX, line));
                    } else if (zombiesSpawned < 300) {
                        sleepTime = 800;
                        if (zombieChance < 25) zombieList.add(new BasicZombie(posX, line));
                        else if (zombieChance < 40) zombieList.add(new ZombieConehead(posX, line));
                        else if (zombieChance < 60) zombieList.add(new ZombieBuckethead(posX, line));
                        else if (zombieChance < 80) zombieList.add(new ZombieDoor(posX, line));
                        else zombieList.add(new ZombieImp(posX, line));
                    } else if (zombiesSpawned < 750) {
                        sleepTime = 600;
                        if (zombieChance < 15) zombieList.add(new BasicZombie(posX, line));
                        else if (zombieChance < 30) zombieList.add(new ZombieConehead(posX, line));
                        else if (zombieChance < 55) zombieList.add(new ZombieBuckethead(posX, line));
                        else if (zombieChance < 75) zombieList.add(new ZombieDoor(posX, line));
                        else zombieList.add(new ZombieImp(2000, line));
                    } else {
                        Sound.stopBgMusic();
                        sleepTime = 400;
                        if (zombieChance < 10) zombieList.add(new BasicZombie(posX, line));
                        else if (zombieChance < 30) zombieList.add(new ZombieConehead(posX, line));
                        else if (zombieChance < 50) zombieList.add(new ZombieBuckethead(posX, line));
                        else if (zombieChance < 80) zombieList.add(new ZombieDoor(posX, line));
                        else if (zombieChance < 99) zombieList.add(new ZombieImp(posX, line));
                        else zombieList.add(new Yeti(posX, line));
                    }

                    if (zombiesSpawned % 50 == 0) {
                        GameEngine.wavesCompleted++;
                        Sound.playHugeWaveSound();
                        int jakeboxAmount = r.nextInt(1, 16);
                        Sound.playJackintheboxSound();
                        for (int i = 0; i < jakeboxAmount; i++) {
                            zombieList.add(new ZombieJackbox(posX, r.nextInt(0, 6)));
                        }
                        for (int i = 0; i < zombiesSpawned / 5; ++i) {
                            int zombieChance2 = r.nextInt(0, 6);
                            switch (zombieChance2) {
                                case 0 -> zombieList.add(new BasicZombie(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                                case 1 -> zombieList.add(new ZombieConehead(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                                case 2 -> zombieList.add(new ZombieBuckethead(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                                case 3 -> zombieList.add(new ZombieDoor(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                                case 4 -> zombieList.add(new ZombieImp(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                                case 5 -> zombieList.add(new ZombieImp(r.nextInt(2000, 4000), r.nextInt(0, 6)));
                            }
                        }
                    }
                }
                if(zombieList.size() <= 1) sleepTime = 0;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
