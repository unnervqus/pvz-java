package com.rxnqst.pvz.threads;

import com.rxnqst.pvz.effects.Effect;
import com.rxnqst.pvz.zombies.Zombie;

import static com.rxnqst.pvz.GameEngine.*;
import static com.rxnqst.pvz.GameEngine.plantList;

public class Animator implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            while (isGameRunning) {
                for (int z = 0; z < zombieList.size(); z++) {
                    Zombie zombie = zombieList.get(z);
                    if(zombie.freezeDelay == 0)
                        zombie.frameIndex = (++zombie.frameIndex) % 6;
                }
                for (int p = 0; p < plantList.size(); p++) {
                    plantList.get(p).frameIndex = (++plantList.get(p).frameIndex) % 6;
                }
                for (int e = 0; e < effectList.size(); e++) {
                    Effect effect = effectList.get(e);
                    effect.frameIndex++;
                    if(effect.frameIndex == effect.framesAmount) {
                        effect.after();
                        effectList.remove(effect);
                    }
                }
                try {Thread.sleep(120);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
