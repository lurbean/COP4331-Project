package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

public class Character {


    private Ball ball;
    private Paddle paddle;
    private PongGame game;
    private Texture healthBar;
    private SpriteBatch batch;
    private int width = 270;
    private int hitPoints;
    private int damage;
    private int speed;
    private boolean side;
    private float barX;
    private float barSize;
    private float modifier;
    Sound sound;

    Character(PongGame game, Ball ball, Paddle paddle, int hP, int damage, int speed, boolean side, SETTINGS settings)
    {
        if (side)
        {
            this.hitPoints = (hP * settings.P1HealthMod) / 100;
            this.damage = (damage * settings.P1Damage) / 100;
            this.speed = (speed * settings.P1HitSpeed) / 100;
        }
        else
        {
            this.hitPoints = (hP * settings.P2HealthMod ) / 100;
            this.damage = (damage * settings.P2Damage) / 100;
            this.speed = (speed * settings.P2HitSpeed) / 100;
        }
        this.hitPoints = (this.hitPoints * settings.AllHealthMod) / 100;
        this.paddle = paddle;
        this.game = game;
        this.ball = ball;
        this.side = side;

        barSize = width;

        batch = new SpriteBatch();
        healthBar = new Texture("healthBar.jpg");
        sound = Gdx.audio.newSound(Gdx.files.internal("pongHit.wav"));
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

    public void render()
    {
        batch.begin();
        batch.draw(healthBar, barX, game.getHeight() + 20, barSize, 25);
        batch.end();

        paddle.render();

        //If the ball hits the paddle, it will bounce back.
        if(Intersector.overlaps(paddle.getRectangle(), ball.getRectangle()))
        {
            sound.play(1.0f);

            if ((ball.xv > 0 && !side) || (ball.xv < 0 && side))
                ball.switchVelocity(speed, paddle.momentum);
        }

        //If a character dies, the game ends.
        if(hitPoints <= 0)
        {
            game.endGame();
        }

    }

    public void gotHit(int dmg)
    {
        hitPoints -= dmg;
        barSize -= dmg*modifier;

        if(side == true)
        {
            barX += dmg*modifier; //Moves bar as it's getting smaller.
        }
    }

    public int getDamage()
    {
        return damage;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public boolean getSide() { return side; }
}
