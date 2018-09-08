package com.mygdx.game;


public class Player extends Controller{

    private Character character;
    private Ball ball;

    public Player(PongGame game, boolean side, Ball ball)
    {
        super(game, side);
        this.ball = ball;
        this.character = new Character(game, ball, paddle,50, 5, 7, side);
    }

    public void render()
    {
        character.render();
        keyboard();
    }


    public void dispose()
    {
        paddle.dispose();
    }

}

