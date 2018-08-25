package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

//So far, made a simple code where the ball just bounces around the screen.

public class Ball extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 25; //Dimensions of the Ball.
    private int xv = 5, yv = 5; //The ball's velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle pongBall;
    private Texture pingPong;


    public Ball(PongGame game)
    {
        batch = new SpriteBatch();
        this.game = game;
        pingPong = new Texture("pongBall.jpg");
        pongBall = new Rectangle();


        //Starts the ball in the middle of the screen.
        pongBall.x = game.getWidth()/2;
        pongBall.y = game.getHeight()/2;
        pongBall.width = 25;
        pongBall.height = 25;
    }

    @Override
    public void render()
    {
        batch.begin();
        batch.draw(pingPong, pongBall.x, pongBall.y);
        batch.end();

        //THING TO DO: Make the ball move a random direction every time the game starts
        //ans every time the ball is reset.
        pongBall.x += xv;
        pongBall.y += yv;

        //If the ball hits the top and bottom of the screen, it'll bounce off.
        if(pongBall.y < 0|| pongBall.y > game.getHeight() - HEIGHT)
        {
            yv = -yv; //Invert y when it does.
        }
        if(pongBall.x < 0 || pongBall.x > game.getWidth() - WIDTH)
        {
            xv = -xv;
        }
    }

    @Override
    public void dispose ()
    {
        pingPong.dispose();
    }

}
