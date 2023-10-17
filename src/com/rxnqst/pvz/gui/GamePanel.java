package com.rxnqst.pvz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private BufferedImage scene;
    GamePanel() {
        setBackground(Color.GRAY);
        setBorder(null);
        setBounds(0,0,1920,1080);
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scene,0,0,null);
    }

    public void setScene(BufferedImage newScene) {
        scene = newScene;
        repaint();
    }
}
