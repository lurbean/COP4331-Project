package com.mygdx.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinWindow extends PongGame{
    private static WinWindow W_or_L = new WinWindow();
    private JPanel panel1;
    private JButton YESButton;
    private JButton NOButton;

    public WinWindow() {
        YESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "TODO: implement restart"); // TODO - implement restart
            }
        });
        NOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "TODO: thing should have closed the game"); // TODO - have this close the program
            }
        });
    }

    public static void lose_window(int LorW) {

        JFrame frame = new JFrame("Player 1 Won!");
        frame.setSize(500, 500);
        if (LorW==0)
            frame.setTitle("Player 2 Won!");
        frame.setContentPane(W_or_L.panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
