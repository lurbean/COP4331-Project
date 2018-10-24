package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public int damage;
    public int hitSpeed;
    public boolean side;
    public float barX;
    public float barSize;
    public float modifier;
    public int controlMode; // 0 = controller, 1 = keyboard, 2 = uncontrolled
    public int abilityCooldownModifier = 6000; // (100 = base 100%, 60 = frames)
    private int passiveCooldown;
    private int activeCooldown;
    private int ultimateCooldown;
    private boolean passiveOn;
    private boolean activeOn;
    private boolean ultimateOn;
    Sound paddleHitSound;

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

        barSize = width;

        batch = new SpriteBatch();
        healthBar = new Texture("healthBar.jpg");
        paddleHitSound = Gdx.audio.newSound(Gdx.files.internal("pongHit.wav"));
        modifier = width/hP;

        if(side)
        {
            barX = 60;
        }
        else
        {
            barX = game.getWidth() - 60 - barSize;
        }
    }
    public void drawHealth()
    {
        batch.begin();
        batch.draw(healthBar, barX, game.getHeight() + 20, barSize, 25);
        batch.end();
    }

    public int getNewCooldownInFrames(int baseCooldownInSeconds)
    {
        if (abilityCooldownModifier == 0)
            return -100;
        return baseCooldownInSeconds * abilityCooldownModifier / 100;
    }

    abstract boolean doUnpauseGame();
    abstract void activeAbility();
    abstract void passiveAbility();
    abstract void ultimateAbility();
    abstract void checkCollision();
    abstract void characterAssetSetup();
    abstract void render();
    abstract void gotHit(int dmg);
    abstract int getDamage();
    abstract int getHitPoints();
    abstract void checkPauseRequest();
}
