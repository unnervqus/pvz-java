package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

public class ChosenSeed {
    public GameEngine.SeedSlot seedSlot;
    public Rect box;

    public ChosenSeed(GameEngine.SeedSlot seedSlot, Rect box) {
        this.seedSlot = seedSlot;
        this.box = box;
    }
}
