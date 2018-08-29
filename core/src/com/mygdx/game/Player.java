package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player {

    private Paddle paddle;
    private PongGame game;

    public Player(PongGame game)
    {
        this.game = game;
        this.paddle = new Paddle(game, true, false);
    }

    public void render()
    {
        paddle.render();
        keyboard();
    }

    public void  keyboard()
    {
        //Paddle only moves up and down, so we only need to update the y value.
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            paddle.movePaddleUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            paddle.movePaddleDown();
        }
    }

    public void dispose()
    {
        paddle.dispose();
    }

    public Paddle getPaddle()
    {
        return paddle;
    }
}

