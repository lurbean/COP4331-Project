package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;



public class AlexCharacter extends Character {
    private SpriteBatch batch;


    private int activeCooldown;
    private int ultimateCooldown;
    private int ultDuration;
    boolean ult = false;

    AlexCharacter(PongGame game, Ball ball, Paddle paddle, boolean side, SETTINGS settings)
    {
        super(game, ball, paddle, 20, 7, 7, 7, side, settings);
        characterAssetSetup();
    }

    public void characterAssetSetup()
    {
//        batch = new SpriteBatch();
//        companionPaddles = new Texture("paddleOne.jpg");
//        chainTexture = new Texture("chain.png");
//        shield = new Texture("paddleOne.jpg");
        //bonk = Gdx.audio.newSound(Gdx.files.internal(""));
        //chainRecoil = Gdx.audio.newSound(Gdx.files.internal(""));
        // healthBar = new Texture("healthBar.jpg");
        //paddleHitSound = Gdx.audio.newSound(Gdx.files.internal(""));
    }

    public void render()
    {
        drawHealth();

        ultimateAbility(); // but they probably come first
        activeAbility();

        controlPaddle();

        paddle.render();
        checkCollision();
        if(hitPoints <= 0) {
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

    void activeAbility() {

        if (activeCooldown > 0)
            activeCooldown--;
        if (controlMode == 0) // Controller-controlled
        {
            if (controller.isRightTriggerPressed() && activeCooldown == 0) {
                ball.reset();
                activeCooldown = getNewCooldownInFrames(10);
            }
        } else if (controlMode == 1) // Keybaord-controlled
        {
            if (keyboard.isEPressed() == true && activeCooldown == 0) {
                ball.reset();
                activeCooldown = getNewCooldownInFrames(3);
            }
        }
    }

    void passiveAbility() // Basic character has none of these
    {}
    void ultimateAbility()
    {
        if (ultimateCooldown > 0)
            ultimateCooldown--;


        if(controlMode == 0)
        {
            if(controller.isLeftTriggerPressed() == true && ultimateCooldown == 0)
                ult = true;
            ultimateCooldown = getNewCooldownInFrames(2);
        }

        else if(controlMode == 1)
        {
            if(keyboard.isRPressed() == true && ultimateCooldown == 0)
                ball.render();
                ult = true;
                ultimateCooldown = getNewCooldownInFrames(2);
        }
    }



    public void checkPauseRequest()
    {
        switch(controlMode)
        {
            case 0:
                if (controller.isStartButtonPressed()) {
                    game.pause(true);
                }
                break;
            case 1:
                if (keyboard.isSpacePressed()) {
                    game.pause(true);
                }
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
