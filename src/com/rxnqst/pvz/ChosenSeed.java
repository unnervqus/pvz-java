package com.rxnqst.pvz;

import com.rxnqst.pvz.utils.Rect;

public class ChosenSeed {
    public GameObjectType seedType;
    public GameObjectType objType;
    public Rect box;

    public ChosenSeed(GameObjectType type, Rect box) {
        this.seedType = type;
        if(type.toString().startsWith("Z")) {
            objType = GameObjectType.valueOf("Z" + type.toString().substring(6));
        } else {
            if(type != GameObjectType.Shovel)
                objType = GameObjectType.valueOf(type.toString().substring(5));
            else
                objType = GameObjectType.Shovel;
        }
        this.box = box;
    }
}
