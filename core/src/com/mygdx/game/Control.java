package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.utils.Array;

public class Control {

    public Paddle paddle;




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

        if(!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            paddle.zeroMomentum();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1))
        {
            paddle.useAbilityOneP2();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2))
        {
            paddle.useAbilityTwoP2();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            paddle.pause();
        }
    }

    public void  keyboard()
    {
        //Paddle only moves up and down, so we only need to update the y value.
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            paddle.movePaddleUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            paddle.movePaddleDown();
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S))
        {
            paddle.zeroMomentum();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
        {
            paddle.useAbilityOneP1();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
        {
            paddle.useAbilityTwoP1();
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
                if (pad.getButton(XBox.BUTTON_START))
                    paddle.pause();

                if (pad.getAxis(XBox.AXIS_LEFT_Y) > 0.15)
                    paddle.movePaddleDown();

                if (pad.getAxis(XBox.AXIS_LEFT_Y) < -0.15)
                    paddle.movePaddleUp();

                if (pad.getButton(XBox.BUTTON_A))
                    paddle.useAbilityOneP1();
                if (pad.getButton(XBox.BUTTON_B))
                    paddle.useAbilityTwoP2();


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
                if (pad.getButton(XBox.BUTTON_START))
                    paddle.pause();

                if (pad.getAxis(XBox.AXIS_LEFT_Y) > 0.15)
                {
                    paddle.movePaddleDown();
                }
                if (pad.getAxis(XBox.AXIS_LEFT_Y) < -0.15)
                {
                    paddle.movePaddleUp();
                }
                if (pad.getButton(XBox.BUTTON_A))
                    paddle.useAbilityOneP2();
                if (pad.getButton(XBox.BUTTON_B))
                    paddle.useAbilityTwoP2();
            }
        }
    }
}
