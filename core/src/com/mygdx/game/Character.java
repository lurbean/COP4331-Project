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

    }

    public void render()
    {

        paddle.render();

        //If the ball hits the paddle, it will bounce back.

        if(Intersector.overlaps(paddle.getRectangle(), ball.getRectangle()))
        {
            if(paddle.getRectangle().x == ball.getRectangle().x)
            {
                ball.switchVelocity(speed);
            }
        }

    }
}
