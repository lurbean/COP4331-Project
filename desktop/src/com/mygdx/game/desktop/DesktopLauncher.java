package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class DesktopLauncher {
    private JPanel MainMenu;
    private JTabbedPane Tabbed;
    private JButton playButton;
    private JPanel SettingsWindow;
    private JSplitPane LeftSplit;
    private JSplitPane RightSplit;
    private JPanel LeftPanel;
    private JPanel GameSettings;
    private JComboBox GameLength;
    private JComboBox GlobalHealthMultiplier;
    private JComboBox GlobalBallSpeedMod;
    private JComboBox GlobalCooldownMod;
    private JPanel RightPanel;
    private JPanel PlayerTwo;
    private JComboBox P2HealthMultiplier;
    private JComboBox P2PaddleSpeedMod;
    private JComboBox P2AttackDamageMod;
    private JComboBox P2HitSpeedMod;
    private JComboBox P2CooldownMod;
    private JPanel PlayerOne;
    private JComboBox P1HealthMultiplier;
    private JComboBox P1PaddleSpeedMod;
    private JComboBox P1AttackDamageMod;
    private JComboBox P1HitSpeedMod;
    private JComboBox P1CooldownMod;

    private int PlayerAttack[] = {50, 75, 100, 125, 150, 200, 300};
    private int PlayerHealth[] = {0, 25, 50, 100, 150, 200, 300, 500};
    private int PlayerPaddle[] = {50, 75, 100, 125, 150, 200};
    private int PlayerBallHit[] = {50, 75, 100, 125, 150, 175, 200};
    private int PlayerCooldown[] = {50, 75, 100, 125, 150, 0};

    private int GameDuration[] = {0, 30, 45, 60, 75, 99};
    private int GlobalHealth[] = {0, 50, 100, 200, 300, 500};
    private int GlobalBall[] = {50, 100, 150, 200};
    private int GlobalCooldown[] = {50, 75, 100, 125, 150, 0};
    private int temp;

    public SETTINGS settings = new SETTINGS();

    public DesktopLauncher() {

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


                config.title = "Pong"; //Title of Window
                config.width = 800;
                config.height = 580;
                settings.DisplaySettings();
                new LwjglApplication(new PongGame(settings), config);
            }
        });

        P1HealthMultiplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P1HealthMultiplier.getSelectedIndex();
                temp = PlayerHealth[temp];
                settings.P1HealthMod = temp;
            }
        });
        P1PaddleSpeedMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P1PaddleSpeedMod.getSelectedIndex();
                temp = PlayerPaddle[temp];
                settings.P1PaddleSpeed = temp;
            }
        });
        P1AttackDamageMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P1AttackDamageMod.getSelectedIndex();
                temp = PlayerAttack[temp];
                settings.P1Damage = temp;
            }
        });
        P1HitSpeedMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P1HitSpeedMod.getSelectedIndex();
                temp = PlayerBallHit[temp];
                settings.P1HitSpeed = temp;
            }
        });
        P1CooldownMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P1CooldownMod.getSelectedIndex();
                temp = PlayerCooldown[temp];
                settings.P1Cooldown = temp;
            }
        });
        GameLength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = GameLength.getSelectedIndex();
                temp = GameDuration[temp];
                settings.gameLength = temp;
            }
        });
        GlobalHealthMultiplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = GlobalHealthMultiplier.getSelectedIndex();
                temp = GlobalHealth[temp];
                settings.AllHealthMod = temp;
            }
        });
        GlobalBallSpeedMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = GlobalBallSpeedMod.getSelectedIndex();
                temp = GlobalBall[temp];
                settings.AllBallSpeed = temp;
            }
        });
        GlobalCooldownMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = GlobalCooldownMod.getSelectedIndex();
                temp = GlobalCooldown[temp];
                settings.AllCooldown = temp; //TODO: cooldowns need to be modifiable values and this needs to be applied to them. If settings.AllCooldown==0, Cooldown takes forever. Otherwise, Cooldown takes normalCooldown * settings.AllCooldown / 100. Same for P1Cooldown and P2Cooldown, for their respective sides
            }
        });
        P2HealthMultiplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P2HealthMultiplier.getSelectedIndex();
                temp = PlayerHealth[temp];
                settings.P2HealthMod = temp;
            }
        });
        P2PaddleSpeedMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P2PaddleSpeedMod.getSelectedIndex();
                temp = PlayerPaddle[temp];
                settings.P2PaddleSpeed = temp;
            }
        });
        P2AttackDamageMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P2AttackDamageMod.getSelectedIndex();
                temp = PlayerAttack[temp];
                settings.P2Damage = temp;
            }
        });
        P2HitSpeedMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P2HitSpeedMod.getSelectedIndex();
                temp = PlayerBallHit[temp];
                settings.P2HitSpeed = temp;
            }
        });
        P2CooldownMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = P2CooldownMod.getSelectedIndex();
                temp = PlayerCooldown[temp];
                settings.P2Cooldown = temp;
            }
        });
    }

    public static void main (String[] arg) {
        JFrame frame = new JFrame("DesktopLauncher");
        frame.setContentPane(new DesktopLauncher().Tabbed);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
