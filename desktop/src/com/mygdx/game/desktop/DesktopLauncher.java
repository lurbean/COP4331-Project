package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesktopLauncher {
    private JButton settingsButton;
    private JPanel panel1;
    private JButton playButton;
    private JButton button2;

    SETTINGS settings = new SETTINGS();

    public DesktopLauncher() {

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


                config.title = "Pong"; //Title of Window
                config.width = 800;
                config.height = 580;
                new LwjglApplication(new PongGame(settings), config);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are in the Settings");
            }
        });
    }

    public static void main (String[] arg) {
        JFrame frame = new JFrame("DesktopLauncher");
        frame.setContentPane(new DesktopLauncher().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

	}
}
