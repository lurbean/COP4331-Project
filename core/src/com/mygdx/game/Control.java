package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.utils.Array;

public class Control {

    public Paddle paddle, paddle1;


    Control()
    {

    }

    Control(PongGame game, boolean side)
    {
        this.paddle = new Paddle(game, side);

    }

    public void  keyboard2()
    {
        //Paddle only moves up and down, so we only need to update the y value.
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            paddle.movePaddleUp();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            paddle.movePaddleDown();
        }
        else
        {
            paddle.zeroMomentum();
        }

    }

    public void  keyboard()
    {
        //Paddle only moves up and down, so we only need to update the y value.
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            paddle.movePaddleUp();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            paddle.movePaddleDown();
        }
        else
        {
            paddle.zeroMomentum();
        }

    }

    public void xbox()
    {

        Array<Controller> controllers = Controllers.getControllers();
        if(controllers.size==0){
            //there are no controllers...
        } else {
            Controller pad = Controllers.getControllers().get(0);

            if (pad != null)
            {
                if (pad.getAxis(XBox.AXIS_LEFT_Y) > 0.15)
                    paddle.movePaddleDown();

                if (pad.getAxis(XBox.AXIS_LEFT_Y) < -0.15)
                    paddle.movePaddleUp();

                if (pad.getButton(XBox.BUTTON_A))
                    paddle.useAbilityOne();
                if (pad.getButton(XBox.BUTTON_B))
                    paddle.useAbilityTwo();
            }
        }
    }

    public void xbox2()
    {
        Array<Controller> controllers = Controllers.getControllers();
        if(controllers.size==0){
            //there are no controllers...
        } else if (controllers.size == 2){
            Controller pad = Controllers.getControllers().get(1);

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
                if (pad.getButton(XBox.BUTTON_A))
                    paddle.useAbilityOne();
                if (pad.getButton(XBox.BUTTON_B))
                    paddle.useAbilityTwo();
            }
        }
    }
}
