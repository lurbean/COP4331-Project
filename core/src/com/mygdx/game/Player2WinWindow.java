package com.mygdx.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player2WinWindow extends PongGame{
    private static Player2WinWindow W_or_L = new Player2WinWindow();
    private JPanel panel1;
    private JButton YESButton;
    private JButton HELLNOGETMEButton;
    private JLabel player2WinsPlayLabel;


    public Player2WinWindow() {
        YESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are going to play again");
            }
        });
        HELLNOGETMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are a bitch");
            }
        });
    }

    public static void lose_window(int LorW) {
        if (LorW ==1){
            JFrame frame = new JFrame("Player2WinScreen");
            frame.setContentPane(W_or_L.panel1);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

}
