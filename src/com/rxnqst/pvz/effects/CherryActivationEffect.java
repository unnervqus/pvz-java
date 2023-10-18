package com.rxnqst.pvz.effects;

import com.rxnqst.pvz.AnimationManager;
import com.rxnqst.pvz.GameEngine;


public class CherryActivationEffect extends Effect {
    public CherryActivationEffect(int posX, int posY) {
        super(posX, posY, 50, 35, 6,
                AnimationManager.getTexture(AnimationManager.AtlasName.CHERRY_ACTIVATION_EFFECT));
    }

    @Override
    public void after() {
        GameEngine.effectList.add(new CherryBoomEffect(hitbox.x, hitbox.y));
    }
}
