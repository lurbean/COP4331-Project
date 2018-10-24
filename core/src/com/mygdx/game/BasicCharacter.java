package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;


public class BasicCharacter extends Character{

    BasicCharacter(PongGame game, Ball ball, Paddle paddle, boolean side, SETTINGS settings)
    {
        // Change stats INSIDE the super call:
        // hP = 15;
        // damage = 5;
        // speed = 7
        // paddleSpeed = 8
        super(game, ball, paddle, 15, 5, 7, 8, side, settings);
        characterAssetSetup();
    }

    public void characterAssetSetup()
    {
        // healthBar = new Texture("healthBar.jpg");
        // sound = Gdx.audio.newSound(Gdx.files.internal("pongHit.wav"));
    }

    public void render()
    {
        drawHealth();

        activeAbility();
        passiveAbility(); // note: these may need to happen after paddle.render(); for your character!
        ultimateAbility(); // but they probably come first

        controlPaddle();

        paddle.render();
        checkCollision();

        if(hitPoints <= 0)
        {
            game.endGame();
        }

        checkPauseRequest();
    }

    private void controlPaddle()
    {
        switch(controlMode)
        {
            case 0: //Controller
                if (controller.isLeftStickUp())
                    paddle.movePaddleUp();
                else if (controller.isLeftStickDown())
                    paddle.movePaddleDown();
                break;
            case 1: //Keyboard
                if (keyboard.isUpPressed() || keyboard.isWPressed())
                    paddle.movePaddleUp();
                else if (keyboard.isDownPressed() || keyboard.isSPressed())
                    paddle.movePaddleDown();
                break;
            case 9:
                break;
        }
    }

    public void checkCollision()
    {
        // If the ball is moving away from you, don't hit it again
        if ( !( (ball.xv > 0 && !side) || (ball.xv < 0 && side) ) )
            return;
        if(Intersector.overlaps(paddle.getRectangle(), ball.getRectangle()))
        {
            ball.switchVelocity(hitSpeed, paddle.momentum);
            paddleHitSound.play(1.0f);
        }
    }

    public void gotHit(int dmg)
    {
        hitPoints -= dmg;
        barSize -= dmg*modifier;
        if(side == true)
        {
            barX += dmg*modifier; //Moves bar as it's getting smaller.
        }
    }

    void activeAbility()
    {}
    void passiveAbility() // Basic character has none of these
    {}
    void ultimateAbility()
    {}

    public void checkPauseRequest()
    {
        switch(controlMode)
        {
            case 0:
                if (controller.isStartButtonPressed())
                    game.pause(true);
                break;
            case 1:
                if (keyboard.isSpacePressed())
                    game.pause(true);
                break;
            case 9:
                return;
        }
    }

    public boolean doUnpauseGame()
    {
        if (controlMode==0)
            return controller.isStartButtonPressed();
        else if (controlMode==1)
            return keyboard.isSpacePressed();
        else // idle mode
            ;
        return false;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }
}
