package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


// To make a new character, create a new class extending this one. Existing AdamCharacter supplies a good model.
// The Render method will be called each frame, so anything that needs updates each frame can be contained within,
// but your abilities will be managed by the abstract methods displayed below - _Ability
// This can be extended as needed.
// To add a new character in the UI, you need to add an element to the String arrays in DesktopLaucnher.Java,
// Strings "CharSelect" through "XBoxControls" - their meanings are obvious.
// Then, edit "Temp > [number]", also in DesktopLauncher, to be "Temp > [number]+1"
// Finally, let Adam know (or go into the character select UI element) to include your character's name in the dropdown boxes.
abstract class Character {

    public Ball ball;
    public Paddle paddle;
    public PongGame game;
    public Texture healthBar;
    public SpriteBatch batch;
    public Keyboard keyboard;
    public XBoxController controller;
    public int width = 270;
    public int hitPoints;
    public int maxHitPoints;
    public int damage;
    public int hitSpeed;
    public boolean side;
    public float barX;
    public float barXInitial;
    public float barSize;
    public float barSizeInitial;
    public int controlMode; // 0 = controller, 1 = keyboard, 2 = uncontrolled
    public int abilityCooldownModifier = 6000; // (100 = base 100%, 60 = frames)
    private int activeCooldown;
    private int ultimateCooldown;
    private boolean activeOn;
    private boolean ultimateOn;
    public Sound paddleHitSound;

    Character(PongGame game, Ball ball, Paddle paddle, int hP, int damage, int speed, int paddleSpeed, boolean side, SETTINGS settings)
    {
        if (side)
        {
            this.hitPoints = (hP * settings.P1HealthMod) / 100;
            this.damage = (damage * settings.P1Damage) / 100;
            this.hitSpeed = (speed * settings.P1HitSpeed) / 100;
            this.abilityCooldownModifier *= settings.P1Cooldown / 100;
            paddleSpeed = (paddleSpeed * settings.P1PaddleSpeed) / 100;
        }
        else
        {
            this.hitPoints = (hP * settings.P2HealthMod ) / 100;
            this.damage = (damage * settings.P2Damage) / 100;
            this.hitSpeed = (speed * settings.P2HitSpeed) / 100;
            this.abilityCooldownModifier *= settings.P2Cooldown / 100;
            paddleSpeed = (paddleSpeed * settings.P2PaddleSpeed) / 100;
        }
        this.hitPoints = (this.hitPoints * settings.AllHealthMod) / 100;
        this.paddle = new Paddle(game, side, paddleSpeed);
        this.abilityCooldownModifier *= settings.AllCooldown / 100;
        this.game = game;
        this.ball = ball;
        this.side = side;

        maxHitPoints = hitPoints;
        barSize = width;
        barSizeInitial = barSize;

        batch = new SpriteBatch();
        healthBar = new Texture("healthBar.jpg");
        paddleHitSound = Gdx.audio.newSound(Gdx.files.internal("pongHit.wav"));

        if(side)
        {
            barX = 50;
        }
        else
        {
            barX = game.getWidth() - 50 - barSize;
        }
        barXInitial = barX;
    }
    public void drawHealth()
    {
        batch.begin();
        batch.draw(healthBar, barX, game.getHeight() + 5, barSize, 25);
        batch.end();
    }

    public int getNewCooldownInFrames(int baseCooldownInSeconds)
    {
        if (abilityCooldownModifier == 0)
            return -100;
        return baseCooldownInSeconds * abilityCooldownModifier / 100;
    }

    public void gotHit(int dmg)
    {
        hitPoints = hitPoints - dmg;
        float HPfraction = 1f - ((float)maxHitPoints - (float)hitPoints) / (float)maxHitPoints;
        barSize = barSizeInitial * HPfraction;
        if(side)
        {
            barX = barXInitial + (barSizeInitial - barSize); //Moves bar as it's getting smaller.
        }
    }

    abstract boolean doUnpauseGame();
    abstract void activeAbility();
    abstract void ultimateAbility();
    abstract void checkCollision();
    abstract void characterAssetSetup();
    abstract void render();
    abstract int getDamage();
    abstract int getHitPoints();
    abstract void checkPauseRequest();
}
