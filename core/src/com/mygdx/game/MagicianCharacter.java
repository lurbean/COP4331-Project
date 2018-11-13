package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;


public class MagicianCharacter extends Character{

    private FakeBall fakeBalls[] = new FakeBall[3];
    private Texture magicianUlt;
    private int numFakeBalls = 0;
    private int maxFakeBalls = 3;
    private int activeCooldown;
    private int ultimateCooldown;
    private int ultDuration;
    boolean createABall = false;
    boolean ult = false;

    // Coding some in your character - Adam
    private Texture abilityTexture;
    private Texture abilityTextureFaded;
    private Texture abilityTextureHighlight;
    private Texture ultimateTexture;
    private Texture ultimateTextureFaded;

    MagicianCharacter(PongGame game, Ball ball, Paddle paddle, boolean side, SETTINGS settings)
    {
        // Change stats INSIDE the super call:
        // hP = 15;
        // damage = 5;
        // speed = 7
        // paddleSpeed = 8
        super(game, ball, paddle, 15, 5, 7, 8, side, settings);
        characterAssetSetup();
        ultimateCooldown = 5 * 60;
        for(int i = 0; i<3; i++)
        {
            fakeBalls[i] = new FakeBall(game, settings);
        }


    }

    public void characterAssetSetup()
    {
        magicianUlt = new Texture("magUlt.png");
        abilityTexture = new Texture("MagicianActive.png");
        abilityTextureFaded = new Texture("MagicianActiveFaded.png");
        abilityTextureHighlight = new Texture("MagicianActiveHighlight.png");
        ultimateTexture = new Texture("MagicianUltimate.png");
        ultimateTextureFaded = new Texture("MagicianUltimateFaded.png");

        // healthBar = new Texture("healthBar.jpg");
        // sound = Gdx.audio.newSound(Gdx.files.internal("pongHit.wav"));
    }

    public void render()
    {
        drawHealth();

        activeAbility();
        ultimateAbility();

        if(numFakeBalls != 0)
        {
            for(int i = 0; i < numFakeBalls ; i++)
            {
                fakeBalls[i].render();
            }
        }

        if(ult == true)
        {
            batch.begin();
            batch.draw(magicianUlt, 0, 0);
            batch.end();
        }

        controlPaddle();

        paddle.render();
        checkCollision();

        if(hitPoints <= 0)
        {
            game.endGame();
        }

        checkPauseRequest();

        renderCooldowns();
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

            if(numFakeBalls <= maxFakeBalls && createABall == true)
            {
                numFakeBalls++;
                fakeBalls[numFakeBalls - 1].getRectangle().setPosition(paddle.getRectangle().x, paddle.getRectangle().y);
                createABall = false;
            }

            paddleHitSound.play(1.0f);
        }
    }

    void activeAbility()
    {

        if (activeCooldown > 0 && !createABall)
            activeCooldown--;

        if(controlMode == 0)
        {
            if(controller.isRightTriggerPressed() == true && activeCooldown == 0 && numFakeBalls < maxFakeBalls)
            {
                createABall = true;

                activeCooldown = getNewCooldownInFrames(5);
            }
        }

        if(controlMode == 1)
        {
            if(keyboard.isEPressed() == true && activeCooldown == 0 && numFakeBalls < maxFakeBalls)
            {
                createABall = true;

                activeCooldown = getNewCooldownInFrames(5);
            }
        }

    }

    void ultimateAbility()
    {
        if (ultimateCooldown > 0 && ultDuration==0)
            ultimateCooldown--;

        if(ult == true && ultDuration > 0)
        {
            ultDuration--;
            if(ultDuration == 0)
                ult = false;
        }

        if(controlMode == 0)
        {
            if(controller.isLeftTriggerPressed() == true && ultimateCooldown == 0)
            {
                ult = true;
                ultimateCooldown = getNewCooldownInFrames(3);
                ultDuration = 5 * 60;
            }
        }

        if(controlMode == 1)
        {
            if(keyboard.isRPressed() == true && ultimateCooldown == 0)
            {
                ult = true;
                ultimateCooldown = getNewCooldownInFrames(3);
                ultDuration = 5 * 60;
            }
        }

    }

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

    void renderCooldowns()
    {
        batch.begin();
        int baseY = game.getHeight() + 35;
        int activeLocation = side?75:game.getWidth() - 160;
        int ultimateLocation = side?150:game.getWidth() - 160 - 75;
        int leftNum;
        int rightNum;

        // Draw ability icons
        if (activeCooldown == 0)
            batch.draw(abilityTexture, activeLocation, baseY, 52, 52);
        else if (createABall)
            batch.draw(abilityTextureHighlight, activeLocation, baseY, 52, 52);
        else // On cooldown
            batch.draw(abilityTextureFaded, activeLocation, baseY, 52, 52);
        if (ultimateCooldown == 0 || ult)
            batch.draw(ultimateTexture, ultimateLocation, baseY, 52, 52);
        else
            batch.draw(ultimateTextureFaded, ultimateLocation, baseY, 52, 52);

        if (activeCooldown > 0 && !createABall)
        {
            leftNum = (activeCooldown + 59) / 600;
            rightNum = ((activeCooldown + 59) / 60) % 10;
            batch.draw(game.textureWhiteDigitArray[leftNum], activeLocation + 6, baseY + 10, 18, 32);
            batch.draw(game.textureWhiteDigitArray[rightNum], activeLocation + 24 + 4, baseY + 10, 18, 32);
        }
        if (ultimateCooldown > 0) {
            if (ult) {
                leftNum = (ultDuration + 59) / 600;
                rightNum = ((ultDuration + 59) / 60) % 10;
            } else // on cooldown
            {
                leftNum = (ultimateCooldown + 59) / 600;
                rightNum = ((ultimateCooldown + 59) / 60) % 10;
            }
            batch.draw(game.textureWhiteDigitArray[leftNum], ultimateLocation + 6, baseY + 10, 18, 32);
            batch.draw(game.textureWhiteDigitArray[rightNum], ultimateLocation + 24 + 4, baseY + 10, 18, 32);
        }
        batch.end();
    }
}
