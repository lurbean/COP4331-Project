package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {

    public Paddle paddle;

    //Default Constructor
    Controller()
    {

    }

    Controller(PongGame game)
    {
        this.paddle = new Paddle(game, true, false);
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

}
