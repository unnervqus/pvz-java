package com.rxnqst.pvz.gui;

import com.rxnqst.pvz.GameEngine;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class GameFrame extends JFrame {
    public static GamePanel gamePanel;
    private static final GameEngine gameEngine = new GameEngine();
    public GameFrame() throws FileNotFoundException {
        add(gamePanel = new GamePanel());
        setUndecorated(true);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                GameEngine.gameOver();
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(gameEngine);
        addMouseListener(gameEngine);
        addMouseMotionListener(gameEngine);
        GameEngine.createGame();
    }
}
