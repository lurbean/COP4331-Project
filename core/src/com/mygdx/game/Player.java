package com.mygdx.game;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;

public class Player extends Controller{

    private Character character;


    public Player(PongGame game, Ball ball)
    {
        super(game);
        this.character = new Character(game, ball, paddle,50, 5, 7);

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

    public Paddle getPaddle()
    {
        return paddle;
    }

}

