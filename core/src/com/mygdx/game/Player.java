package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player {

    private Paddle paddle;
    private PongGame game;
    private Ball ball;
    private Character character;


    public Player(PongGame game, Ball ball)
    {
        this.paddle = new Paddle(game, true, false);
        this.character = new Character(game, ball, paddle,50, 5);

    }

    public void render()
    {
        character.render();

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

