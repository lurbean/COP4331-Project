package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;

public class Character {

    private int xv, yv;
    private int hitPoints;
    private int damage;
    private int speed;
    private boolean side;
    private Ball ball;
    private Paddle paddle;
    private PongGame game;

    Character(PongGame game, Ball ball, Paddle paddle, int hP, int damage, int speed, boolean side)
    {
        this.hitPoints = hP;
        this.damage = damage;
        this.speed = speed;
        this.paddle = paddle;
        this.game = game;
        this.ball = ball;
        this.side = side;


    }

    public void render()
    {

        paddle.render();

        //If the ball hits the paddle, it will bounce back.
        if(Intersector.overlaps(paddle.getRectangle(), ball.getRectangle()))
        {
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
    }

    public int getDamage()
    {
        return damage;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }
}
