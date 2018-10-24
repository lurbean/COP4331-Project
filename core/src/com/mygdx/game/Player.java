package com.mygdx.game;

public class Player{

    public Character character;
    public Ball ball;
    private int charSelect;
    public Paddle paddle;

    public Player(PongGame game, boolean side, Ball ball, SETTINGS settings)
    {
        this.ball = ball;
        selectCharacter(side, settings, game);
        this.paddle = character.paddle;

        if (side)
            paddle.yv = (paddle.yv * settings.P1PaddleSpeed) / 100;
        else
            paddle.yv = (paddle.yv * settings.P2PaddleSpeed) / 100;
    }

    public void render()
    {
        character.render();
    }

    private void selectCharacter(boolean side, SETTINGS settings, PongGame game)
    {
        if (side)
            charSelect = settings.P1Character;
        else
            charSelect = settings.P2Character;
        switch (charSelect)
        {
            case (0):
                this.character = new BasicCharacter(game, ball, paddle, side, settings);
                break;
            case (1):
                this.character = new AdamCharacter(game, ball, paddle, side, settings);
                break;
            case (2):
                this.character = new MagicianCharacter(game, ball, paddle, side, settings);
                break;
            case (3):
                break;
        }
    }

    public boolean doUnpause()
    {
        return this.character.doUnpauseGame();
    }
    public void dispose()
    {
        paddle.dispose();
    }
}

