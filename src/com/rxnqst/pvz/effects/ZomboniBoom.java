package com.rxnqst.pvz.effects;

import com.rxnqst.pvz.AnimationManager;

public class ZomboniBoom extends Effect {
    public ZomboniBoom(int posX, int posY) {
        super(posX, posY, 186, 132, 9,
                AnimationManager.getTexture(AnimationManager.AtlasName.ZOMBONI_BOOM_EFFECT));
    }

    @Override
    public void after() {

    }
}
