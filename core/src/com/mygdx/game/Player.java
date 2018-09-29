package com.mygdx.game;


public class Player extends Control{

    private Character character;
    private Ball ball;

    public Player(PongGame game, boolean side, Ball ball)
    {
        super(game, side);
        this.ball = ball;
        this.character = new Character(game, ball, paddle,15, 5, 7, side);

    }

    public void render()
    {
        character.render();
        if (character.getSide()) {
            xbox();
            keyboard();
        }
        if (!character.getSide()) {
            xbox2();
            keyboard2();
        }
    }


    public void dispose()
    {
        paddle.dispose();
    }

   public Character getCharacter()
   {
       return character;
   }
}

