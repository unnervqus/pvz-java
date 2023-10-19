package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

public class ChosenSeed {
    public GameObjectType type;
    public Rect box;

    public ChosenSeed(GameObjectType type, Rect box) {
        this.type = type;
        this.box = box;
    }
}
