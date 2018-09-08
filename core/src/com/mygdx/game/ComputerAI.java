package com.mygdx.game;

public class ComputerAI extends Controller{


    private Ball ball;
    private Character character;


    public ComputerAI(PongGame game, boolean side, Ball ball)
    {
        super(game, side);

        this.character = new Character(game, ball, paddle,50, 5, 5, side);
        this.ball = ball;

    }

    public void render()
    {
        character.render();
        controlAI();

    }

    public void controlAI()
    {
        if(paddle.getRectangle().y > ball.getRectangle().y)
        {
            paddle.movePaddleDown();
        }
        if(paddle.getRectangle().y < ball.getRectangle().y)
        {
            paddle.movePaddleUp();
        }
    }

    public void dispose()
    {
        paddle.dispose();
    }

}

