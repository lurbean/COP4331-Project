package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class AdamCharacter extends Character{
    private SpriteBatch batch;
    private Texture companionPaddles;
    private Texture chainTexture;
    private Texture shield;
    private Texture shieldOutline;
    private Sound bonk;
    private Sound chainRecoil;

    private int passiveCooldown;
    private int activeCooldown;
    private int ultimateCooldown;
    private int activeTimer;
    private int ultimateTimer;
    private boolean passiveOn;
    private boolean activeOn;
    private boolean ultimateOn;

    private Rectangle activeChildRectangle = new Rectangle(game.getWidth()/2-12.5f + (side?-100f:100f), game.getHeight()/2-50f, 25f, 100f);
    private TopChild topChild = new TopChild();
    private BotChild botChild = new BotChild();

    // Ability cooldown display textures are here @Lavine - these are drawn at 40 x 40
    private Texture abilityTexture;
    private Texture abilityTextureFaded;
    private Texture ultimateTexture;
    private Texture ultimateTextureFaded;

    AdamCharacter(PongGame game, Ball ball, Paddle paddle, boolean side, SETTINGS settings)
    {
        super(game, ball, paddle, 20, 7, 7, 7, side, settings);
        characterAssetSetup();
        ultimateCooldown = getNewCooldownInFrames(10);
    }

    public void characterAssetSetup()
    {
        batch = new SpriteBatch();
        companionPaddles = new Texture("paddleOne.jpg");
        chainTexture = new Texture("chain.png");
        if (side)
        {
            shield = new Texture("AdamPaddleLeft.png");
            shieldOutline = new Texture("ShieldOutlineLeft.png");
        }
        else
        {
            shield = new Texture("AdamPaddleRight.png");
            shieldOutline = new Texture("ShieldOutlineRight.png");
        }
        abilityTexture = new Texture("GuardianActive.png");
        abilityTextureFaded = new Texture("GuardianActiveFaded.png");
        ultimateTexture = new Texture("GuardianUltimate.png");
        ultimateTextureFaded = new Texture("GuardianUltimateFaded.png");
        //bonk = Gdx.audio.newSound(Gdx.files.internal(""));
        //chainRecoil = Gdx.audio.newSound(Gdx.files.internal(""));
        // healthBar = new Texture("healthBar.jpg");
        //paddleHitSound = Gdx.audio.newSound(Gdx.files.internal(""));
    }

    public void render()
    {
        drawHealth();

        passiveAbility(); // note: these may need to happen after paddle.render(); for your character!
        ultimateAbility(); // but they probably come first
        activeAbility();

        controlPaddle();

        paddle.render();
        renderExtraPaddles();
        checkCollision();
        renderCooldowns();
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
                if (keyboard.isWPressed())
                    paddle.movePaddleUp();
                else if (keyboard.isSPressed())
                    paddle.movePaddleDown();
                break;
            case 9:
                break;
        }
        if (ultimateOn)
            ultimateUpdate();
    }

    public boolean checkIndividualCollision(Rectangle paddleRect, float momentum)
    {
        if(Intersector.overlaps(ball.getRectangle(), paddleRect)) {
            ball.switchVelocity(hitSpeed, momentum);
            paddleHitSound.play(1.0f);
            return true;
        }
        return false;
    }

    public void checkCollision()
    {
        // If the ball is moving away from you, don't hit it again
        if ( (ball.xv > 0 && side) || (ball.xv < 0 && !side) )
            return;
        boolean hit = false;

        hit = checkIndividualCollision(paddle.getRectangle(), paddle.momentum);
        if (hit)
            return;
        if (ultimateOn) {
            hit = checkIndividualCollision(topChild.rectangle, topChild.speed);
            if (hit)
                return;
            hit = checkIndividualCollision(botChild.rectangle, botChild.speed);
            if (hit)
                return;
        }
        if (activeOn)
            checkIndividualCollision(activeChildRectangle, 0f);
    }

    void activeAbility()
    {
        if (activeOn)
        {
            activeTimer--;
            if (activeTimer == 0)
                activeOn = false;
        }
        if (activeCooldown > 0)
            activeCooldown--;

        if (activeCooldown==0)
        {
            if (controlMode==0) // Controller-controlled
            {
                if (controller.isRightStickUp())
                    activeChildRectangle.setY(activeChildRectangle.getY() + paddle.yv);
                else if (controller.isRightStickDown())
                    activeChildRectangle.setY(activeChildRectangle.getY() - paddle.yv);
                if (controller.isRightStickLeft())
                    activeChildRectangle.setX(activeChildRectangle.getX() - paddle.yv);
                else if (controller.isRightStickRight())
                    activeChildRectangle.setX(activeChildRectangle.getX() + paddle.yv);
                if (controller.isRightTriggerPressed())
                {
                    activeOn = true;
                    activeTimer = 10 * 60;
                    activeCooldown = getNewCooldownInFrames(20);
                }
            }
            else if (controlMode==1) // Keybaord-controlled
            {
                if (keyboard.isUpPressed())
                    activeChildRectangle.setY(activeChildRectangle.getY() + Math.abs(paddle.yv));
                else if (keyboard.isDownPressed())
                    activeChildRectangle.setY(activeChildRectangle.getY() - Math.abs(paddle.yv));
                if (keyboard.isLeftPressed())
                    activeChildRectangle.setX(activeChildRectangle.getX() - Math.abs(paddle.yv));
                else if (keyboard.isRightPressed())
                    activeChildRectangle.setX(activeChildRectangle.getX() + Math.abs(paddle.yv));
                if (keyboard.isEPressed())
                {
                    activeOn = true;
                    activeTimer = 10 * 60;
                    activeCooldown = getNewCooldownInFrames(20);
                }
            }
            if (activeChildRectangle.getY() > game.getHeight()-activeChildRectangle.getHeight())
                activeChildRectangle.setY(game.getHeight() - activeChildRectangle.getHeight());
            else if (activeChildRectangle.getY() < 0)
                activeChildRectangle.setY(0);
            // set X bounds of ability paddle
            if (activeChildRectangle.getX() > game.getWidth() * 4f/5f - activeChildRectangle.getWidth())
                activeChildRectangle.setX(game.getWidth() * 4f/5f - activeChildRectangle.getWidth());
            else if (activeChildRectangle.getX() < game.getWidth() / 5f)
                activeChildRectangle.setX(game.getWidth() / 5f);
        }
    }
    void passiveAbility() // Basic character has none of these
    {}
    void ultimateAbility() {
        if (ultimateOn) {
            ultimateTimer--;
            if (ultimateTimer == 0)
                ultimateOn = false;
        }
        if (ultimateCooldown > 0)
            ultimateCooldown--;
        else if (ultimateCooldown == 0) {
            if (controlMode == 0) // Controller-controlled
            {
                if (controller.isLeftTriggerPressed()) {
                    ultimateOn = true;
                    ultimateTimer = 10 * 60;
                    ultimateCooldown = getNewCooldownInFrames(30);
                    topChild = new TopChild();
                    botChild = new BotChild();
                }
            } else if (controlMode == 1) // Keybaord-controlled
            {
                if (keyboard.isRPressed()) {
                    ultimateOn = true;
                    ultimateTimer = 12 * 60;
                    ultimateCooldown = getNewCooldownInFrames(30);
                    topChild = new TopChild();
                    botChild = new BotChild();
                }
            }
        }
    }
    void ultimateUpdate()
    {
        topChild.speed *= .93f;
        botChild.speed *= .93f;
        // First, adjust companion speeds if appropriate
        if (paddle.getRectangle().y + paddle.getRectangle().height > topChild.rectangle.y)
            topChild.speed += Math.abs(paddle.yv) * .5;
        if (topChild.rectangle.y - (paddle.getRectangle().y + paddle.getRectangle().height) >= 60f)
            topChild.speed = Math.abs(paddle.yv) * -.45f;
        if (botChild.rectangle.y + botChild.rectangle.height > paddle.getRectangle().y)
            botChild.speed += Math.abs(paddle.yv) * -.5f;
        if (paddle.getRectangle().y - (botChild.rectangle.y + botChild.rectangle.height) >= 60f)
            botChild.speed = Math.abs(paddle.yv) * .45f;

        topChild.rectangle.y += topChild.speed;
        botChild.rectangle.y += botChild.speed;

        // If the companion paddles would hit a wall, stop them there. Rebound is too complicated to be worth
        if (topChild.rectangle.y + topChild.rectangle.height> game.getHeight())
            topChild.rectangle.y = game.getHeight()-topChild.rectangle.height;
        if (botChild.rectangle.y < 0)
            botChild.rectangle.y = 0;

        // If the paddle would occupy the same space as its companion, move it away
        if (paddle.getRectangle().getY() < botChild.rectangle.height)
            paddle.getRectangle().setY(botChild.rectangle.height + 1);
        if (paddle.getRectangle().getY() + paddle.getRectangle().height > game.getHeight() - topChild.rectangle.height)
            paddle.getRectangle().setY(game.getHeight() - topChild.rectangle.height - paddle.getRectangle().height - 1);
    }

    void renderExtraPaddles ()
    {
        batch.begin();
        if (activeOn)
            batch.draw(shield, activeChildRectangle.getX(), activeChildRectangle.getY(), activeChildRectangle.width, activeChildRectangle.height);
        else
            batch.draw(shieldOutline, activeChildRectangle.getX(), activeChildRectangle.getY(), activeChildRectangle.width, activeChildRectangle.height);
        if (ultimateOn)
        {
            //batch.draw(healthBar, barX, game.getHeight() + 20, barSize, 25);
            float chainXStart = paddle.getRectangle().getX() + 5f;
            float chainXLength = 15f;
            float topChainYStart = paddle.getRectangle().getY() + paddle.getRectangle().getHeight();
            float topChainYLength = topChild.rectangle.getY() - topChainYStart;
            float botChainYStart = botChild.rectangle.getY() + botChild.rectangle.getHeight();
            float botChainYLength = paddle.getRectangle().getY() - botChainYStart;
            if (topChainYLength > 0f)
                batch.draw(chainTexture, chainXStart, topChainYStart, chainXLength, topChainYLength);
            if (botChainYLength > 0f)
                batch.draw(chainTexture, chainXStart, botChainYStart, chainXLength, botChainYLength);
            batch.draw(companionPaddles, topChild.rectangle.getX(), topChild.rectangle.getY(), 25f, 80f);
            batch.draw(companionPaddles, botChild.rectangle.getX(), botChild.rectangle.getY(), 25f, 80f);
        }

        batch.end();
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
        if (activeCooldown == 0 || activeOn)
            batch.draw(abilityTexture, activeLocation, baseY, 52, 52);
        else
            batch.draw(abilityTextureFaded, activeLocation, baseY, 52, 52);
        if (ultimateCooldown == 0 || ultimateOn)
            batch.draw(ultimateTexture, ultimateLocation, baseY, 52, 52);
        else
            batch.draw(ultimateTextureFaded, ultimateLocation, baseY, 52, 52);

        if (activeCooldown != 0) {
            if (activeOn) {
                leftNum = (activeTimer + 59) / 600;
                rightNum = ((activeTimer + 59) / 60) % 10;
            } else // on cooldown
            {
                leftNum = (activeCooldown + 59) / 600;
                rightNum = ((activeCooldown + 59) / 60) % 10;
            }
            batch.draw(game.textureWhiteDigitArray[leftNum], activeLocation + 6, baseY + 10, 18, 32);
            batch.draw(game.textureWhiteDigitArray[rightNum], activeLocation + 24 + 4, baseY + 10, 18, 32);
        }
        if (ultimateCooldown != 0) {
            if (ultimateOn) {
                leftNum = (ultimateTimer + 59) / 600;
                rightNum = ((ultimateTimer + 59) / 60) % 10;
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

    class TopChild
    {
        float speed = 0;
        Rectangle rectangle = new Rectangle(paddle.getRectangle().x, paddle.getRectangle().y + paddle.getRectangle().height + 25, 25f, 80f);
    }
    class BotChild
    {
        float speed = 0;
        Rectangle rectangle = new Rectangle(paddle.getRectangle().x, paddle.getRectangle().y - 25, 25f, 80f);
    }
}
