package com.mygdx.game;

public class ComputerAI extends Control{


    private Ball ball;
    private Character character;
    private MiniPID pid;

    public ComputerAI(PongGame game, boolean side, Ball ball)
    {
        super(game, side);

        this.character = new Character(game, ball, paddle,15, 5, 5, side, new SETTINGS());
        this.ball = ball;
        this.pid = new MiniPID(0.7, 0.04, 0.5);
    }

    public void render()
    {
        character.render();
        controlAI();

    }

    public void controlAI()
    {
        double output = pid.getOutput(paddle.getRectangle().y + (paddle.getRectangle().height / 2), ball.getRectangle().y);

        // Reduce Jitter

        if (Math.abs(output) < 20)
        {
            return;
        }

        if (output > 0)
        {
            paddle.movePaddleUp();
        }
        else
        {
            paddle.movePaddleDown();
        }

        /*
        if(paddle.getRectangle().y > ball.getRectangle().y)
        {
            paddle.movePaddleDown();
        }
        if(paddle.getRectangle().y < ball.getRectangle().y)
        {
            paddle.movePaddleUp();
        }
        */
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

