/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetpirates;

/**
 *
 * @author DWR4418A
 */
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image bg;

    public BackgroundPanel() {
        bg = new ImageIcon("oceantexture.jpg").getImage();
        setLayout(new GridLayout(2, 2)); // your grid here
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
