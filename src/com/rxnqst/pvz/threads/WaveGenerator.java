package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.GameEngine;
import com.rxnqst.pvz.Sound;
import com.rxnqst.pvz.zombies.*;

import java.util.Random;

import static com.rxnqst.pvz.GameEngine.isGameRunning;
import static com.rxnqst.pvz.GameEngine.zombieList;

public class WaveGenerator implements Runnable {
    private int zombiesSpawned = 0;
    private int zombiePoints = 0;
    private final Random r = new Random();
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (isGameRunning) {
                zombiesSpawned++;
                zombiePoints += 12;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
