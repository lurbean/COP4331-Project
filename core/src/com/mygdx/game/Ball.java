package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 25; //Dimensions of the Ball.
    private int xv = 5, yv = 5; //The ball's default velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle pongBall;
    private Texture pingPong;


    public Ball(PongGame game)
    {
        batch = new SpriteBatch();
        this.game = game;
        pingPong = new Texture("pongBall.jpg");

        //Rectangle (float x, float y, float width, float height)
        pongBall = new Rectangle(game.getWidth()/2, game.getHeight()/2, WIDTH, HEIGHT);

    }

    @Override
    public void render()
    {
        batch.begin();
        batch.draw(pingPong, pongBall.x, pongBall.y);
        batch.end();

        //THING TO DO: Make the ball move a random direction every time the game starts
        //and every time the ball is reset.
        pongBall.x += xv;
        pongBall.y += yv;

        //If the ball hits the top and bottom of the screen, it'll bounce off.
        if(pongBall.y < 0|| pongBall.y > game.getHeight() - HEIGHT)
        {
            yv = -yv; //Invert y when it does.
        }

        /*if(pongBall.x < 0)
        {
            xv = -xv;
        }*/
        //Every time the ball hits the sides, reset its position.
        if(pongBall.x > game.getWidth() - WIDTH || pongBall.x < 0)
        {
            reset();
        }
    }

    @Override
    public void dispose ()
    {
        pingPong.dispose();
    }

    public void reset()
    {
        pongBall.x = game.getWidth()/2;
        pongBall.y = game.getHeight()/2;
    }

    public void switchVelocity(int speed)
    {
        xv = -speed;
        yv = -speed;
    }

    public void setVelocity(int x, int y)
    {
        if(xv < 0)
            xv = -x;
        else
            xv = x;

        if(yv < 0)
            yv = -x;
        else
            yv = x;

    }

    //Getters/Setters
    public Rectangle getRectangle()
    {
        return pongBall;
    }

}
