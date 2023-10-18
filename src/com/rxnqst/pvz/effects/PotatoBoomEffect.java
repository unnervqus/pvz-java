package com.rxnqst.pvz.effects;

import com.rxnqst.pvz.AnimationManager;

public class PotatoBoomEffect extends Effect {
    public PotatoBoomEffect(int posX, int posY) {
        super(posX-70, posY-40,200, 128, 7,
                AnimationManager.getTexture(AnimationManager.AtlasName.POTATO_BOOM_EFFECT));
    }

    @Override
    public void after() {}
}
