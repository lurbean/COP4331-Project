package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DesktopLauncher {
    private JPanel MainMenu;
    private JTabbedPane Tabbed;
    private JButton playButton;
    private JPanel SettingsWindow;
    private JComboBox GameLength;
    private JComboBox GlobalHealthMultiplier;
    private JComboBox GlobalBallSpeedMod;
    private JComboBox GlobalCooldownMod;
    private JComboBox P2HealthMultiplier;
    private JComboBox P2PaddleSpeedMod;
    private JComboBox P2AttackDamageMod;
    private JComboBox P2HitSpeedMod;
    private JComboBox P2CooldownMod;
    private JComboBox P1HealthMultiplier;
    private JComboBox P1PaddleSpeedMod;
    private JComboBox P1AttackDamageMod;
    private JComboBox P1HitSpeedMod;
    private JComboBox P1CooldownMod;
    private JLabel LeftCharacterImage;
    private JComboBox CharOneSelect;
    private JComboBox CharTwoSelect;
    private JPanel CharacterSelect;
    private JLabel LeftCharacterPassive;
    private JLabel LeftCharacterAbility;
    private JLabel LeftCharacterUltimate;
    private JLabel RightCharacterPassive;
    private JLabel RightCharacterAbility;
    private JLabel RightCharacterUltimate;
    private JLabel RightCharacterImage;
    private JLabel LeftKeyboardControls;
    private JLabel LeftXBoxControls;
    private JLabel RightKeyboardControls;
    private JLabel RightXBoxControls;
    private JRadioButton LeftRBXBox;
    private JRadioButton LeftRBKeyboard;
    private JRadioButton RightRBXBox;
    private JRadioButton RightRBKeyboard;
    private JLabel MenuImageBox;
    private JPanel MenuHouse;
    private BufferedImage mainMenuImage;

    private ImageIcon CharSelect[] = {new ImageIcon("1.png"), new ImageIcon("2.png"), new ImageIcon("3.png"), new ImageIcon("4.png")};
    private String abilities[] = {"The basic character has no ability",
            "Press 'E/right trigger' to deploy your shield; control it with 'arrow keys/right thumb stick'",
            "Press 'E/right trigger' to create a fake ball the next time the paddle hits the ball."};
    private String passives[] = {"And no passive",
            "No passive yet",
            "The Magician has no passives."};
    private String ultimates[] = {"And no ultimate",
            "Press 'R'/left trigger' to summon two companions",
            "When it's implemented, the magician will summon up to 10 fake balls as decoys."};
    private String KBControls[] = {"Keyboard controls: WS to move, Spacebar to pause",
            "Keyboard controls: WS to move, Spacebar to pause",
            "Keyboard controls: WS to move, Spacebar to pause"};
    private String XBoxControls[] = {"XBox controls: Left stick up and down to move, Start to pause",
            "XBox controls: Left stick up and down to move, Start to pause",
            "XBox controls: Left stick up and down to move, Start to pause"};

    private int PlayerAttack[] = {50, 75, 100, 125, 150, 200};
    private int PlayerHealth[] = {0, 25, 50, 100, 150, 200, 300, 500};
    private int PlayerPaddle[] = {50, 75, 100, 125, 150, 200};
    private int PlayerBallHit[] = {50, 75, 100, 125, 150, 175, 200};
    private int PlayerCooldown[] = {50, 75, 100, 125, 150, 0};

    private int GameDuration[] = {0, 30, 45, 60, 75, 99};
    private int GlobalHealth[] = {0, 50, 100, 150, 200, 300, 500};
    private int GlobalBall[] = {50, 75, 100, 150, 200};
    private int GlobalCooldown[] = {50, 75, 100, 125, 150, 0};
    private int leftControlSelect;
    private int rightControlSelect;
    private int temp;

    public SETTINGS settings = new SETTINGS();

    public DesktopLauncher() {
        addBackground();

        addPlayListener();

        addSettingsListeners();
    }



    private void addPlayListener() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

                config.backgroundFPS = -1;
                config.title = "Pong"; //Title of Window
                config.width = 800;
                config.height = 580;
                //settings.DisplaySettings(); // Settings test function
                settings.State = leftControlSelect + rightControlSelect;
                new LwjglApplication(new PongGame(settings), config);
            }
        });
    }

    private void addBackground() {
        try {
            this.mainMenuImage = ImageIO.read(new File("menu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image img = mainMenuImage.getScaledInstance(mainMenuImage.getWidth()/2, mainMenuImage.getHeight()/2, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(img);
        MenuImageBox.setIcon(imgIcon);
    }

    public void KeyboardOverlap()
    {
        switch (PopUpQuestion.TwoOptions(MainMenu, "Disable Player Two", "Player Two can use a controller", "Only one player can use the keyboard! How would you like to resolve this?", " "))
        {
            case 0:
                break;
            case 1:
                RightRBXBox.setSelected(true);
                RightRBXBox.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }

    public static void main (String[] arg) {
        JFrame frame = new JFrame("DesktopLauncher");
        frame.setContentPane(new DesktopLauncher().Tabbed);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}

    private void addSettingsListeners() {
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
                settings.AllCooldown = temp;
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
        CharOneSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = CharOneSelect.getSelectedIndex();
                if (temp > 2) //TODO: once you implement a character, add your portrait and ability descriptions and then add " || temp != 1", etc.
                    return;
                LeftCharacterImage.setIcon(CharSelect[temp]);
                LeftCharacterAbility.setText(abilities[temp]);
                LeftCharacterPassive.setText(passives[temp]);
                LeftCharacterUltimate.setText(ultimates[temp]);
                LeftKeyboardControls.setText(KBControls[temp]);
                LeftXBoxControls.setText(XBoxControls[temp]);
                settings.P1Character = temp;
            }
        });
        CharTwoSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temp = CharTwoSelect.getSelectedIndex();
                if (temp > 2) //TODO: once you implement a character, add your portrait and ability descriptions and then add " || temp != 1", etc.
                    return;
                RightCharacterImage.setIcon(CharSelect[temp]);
                RightCharacterAbility.setText(abilities[temp]);
                RightCharacterPassive.setText(passives[temp]);
                RightCharacterUltimate.setText(ultimates[temp]);
                RightKeyboardControls.setText(KBControls[temp]);
                RightXBoxControls.setText(XBoxControls[temp]);
                settings.P2Character = temp;
            }
        });
        ActionListener LeftRadio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LeftRBKeyboard.isSelected() && RightRBKeyboard.isSelected())
                    KeyboardOverlap();
                else
                    leftControlSelect = LeftRBKeyboard.isSelected()?1:0;
            }
        };
        LeftRBXBox.addActionListener(LeftRadio);
        LeftRBKeyboard.addActionListener(LeftRadio);
        ActionListener RightRadio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LeftRBKeyboard.isSelected() && RightRBKeyboard.isSelected())
                    KeyboardOverlap();
                else
                    rightControlSelect = RightRBKeyboard.isSelected()?2:0;
            }
        };
        RightRBXBox.addActionListener(RightRadio);
        RightRBKeyboard.addActionListener(RightRadio);
    }
}
