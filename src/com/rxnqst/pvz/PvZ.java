package com.rxnqst.pvz;

import com.rxnqst.pvz.gui.GameFrame;

import java.io.IOException;

import static com.rxnqst.pvz.GameEngine.sunAmount;

public class PvZ {
    public static void main(String[] args) throws IOException {
        ImageManager.loadTextures();
        AnimationManager.loadAnimationAtlases();
        new GameFrame();
        sunAmount = 10230;
    }
}
