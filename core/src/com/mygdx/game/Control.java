package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.*;

public class Control {

    public Paddle paddle;

    Control()
    {

    }

    Control(PongGame game, boolean side)
    {
        this.paddle = new Paddle(game, side);
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

    public void xbox()
    {
        Controller pad = Controllers.getControllers().get(0);
        if (pad != null)
        {
            if (pad.getAxis(XBox.AXIS_LEFT_Y) > 0.15)
            {
                paddle.movePaddleDown();
            }
            if (pad.getAxis(XBox.AXIS_LEFT_Y) < -0.15)
            {
                paddle.movePaddleUp();
            }

        }
    }

}
