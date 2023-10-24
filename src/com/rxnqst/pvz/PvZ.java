package com.rxnqst.pvz;

import com.rxnqst.pvz.gui.GameFrame;

import java.io.IOException;

public class PvZ {
    public static void main(String[] args) throws IOException {
        ImageManager.loadTextures();
        AnimationManager.loadAnimationAtlases();
        new GameFrame();
    }
}
