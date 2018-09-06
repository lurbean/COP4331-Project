package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ComputerAI {

    private Paddle paddle;
    private PongGame game;
    private Ball ball;
    private Character character;


    public ComputerAI(PongGame game, Ball ball)
    {
        this.paddle = new Paddle(game, true, true);
        this.character = new Character(game, ball, paddle,50, 5, 5);

    }

    public void render()
    {
        character.render();

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

