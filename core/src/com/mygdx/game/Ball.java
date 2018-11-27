package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.Random;

public class Ball extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 25; //Dimensions of the Ball.
    private static final int FRAME_COLS = 4, FRAME_ROWS = 2;
    public float xv = 5, yv = 5; //The ball's default velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle pongBall;
    private Texture pingPong;
    Animation<TextureRegion> ballAnimation; // Must declare frame type (TextureRegion)
    Texture ballSheet;
    // A variable for tracking elapsed time for the animation
    float stateTime;

    private Random RNG = new Random();
    public int SpeedMultiplier;
    public int additiveSpeed = 0;

    public Ball(PongGame game, SETTINGS settings)
    {
        this.SpeedMultiplier = settings.AllBallSpeed;
        batch = new SpriteBatch();
        this.game = game;
        pingPong = new Texture("pongBall.png");

        //Animation
        ballSheet = new Texture("ballSpriteSheet.png");
        TextureRegion[][] tmp = TextureRegion.split(ballSheet,
                ballSheet.getWidth() / FRAME_COLS,
                ballSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] ballFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                ballFrames[index++] = tmp[i][j];
            }
        }

        ballAnimation = new Animation<TextureRegion>(0.025f, ballFrames);
        stateTime = 0f;

        //Rectangle (float x, float y, float width, float height)
        pongBall = new Rectangle(game.getWidth()/2, game.getHeight()/2, WIDTH, HEIGHT);

    }

    @Override
    public void render()
    {
        if (game.getPaused())
        {
            return;
        }

        if (xv > 0) {
            if (yv > 0)
                pongBall.setPosition(pongBall.x + additiveSpeed + (xv * SpeedMultiplier) / 100,
                                     pongBall.y + additiveSpeed + (yv * SpeedMultiplier) / 100);
            else // yv < 0
                pongBall.setPosition(pongBall.x + additiveSpeed + (xv * SpeedMultiplier) / 100,
                                     pongBall.y - additiveSpeed + (yv * SpeedMultiplier) / 100);
        }
        else { // xv < 0
            if (yv > 0)
                pongBall.setPosition(pongBall.x - additiveSpeed + (xv * SpeedMultiplier) / 100,
                                     pongBall.y + additiveSpeed + (yv * SpeedMultiplier) / 100);
            else // yv < 0
                pongBall.setPosition(pongBall.x - additiveSpeed + (xv * SpeedMultiplier) / 100,
                                     pongBall.y - additiveSpeed + (yv * SpeedMultiplier) / 100);
        }

        //If the ball hits the top or bottom of the screen, it'll bounce off.
        if (pongBall.y < 0)
        {
            pongBall.y = 0 - pongBall.y; // Conservation of kinetic energy - it bounces back at the same speed
            yv = Math.abs(yv); // Invert y. abs() used here to ensure a ball at the bottom always goes up
            game.player1.character.paddleHitSound.play(.35f, 1.5f, 0f); // Play a quieter hit sound
        }
        if (pongBall.y > game.getHeight() - HEIGHT)
        {
            float distanceOutOfBounds = (float)HEIGHT - ((float)game.getHeight() - pongBall.y);
            pongBall.y = (float)game.getHeight() - distanceOutOfBounds - (float)HEIGHT;
            yv = - Math.abs(yv);
            game.player1.character.paddleHitSound.play(.35f, 1.5f, 0f);
        }

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = ballAnimation.getKeyFrame(stateTime, true);
        batch.begin();
        //batch.draw(pingPong, pongBall.x, pongBall.y);
        batch.draw(currentFrame, pongBall.x, pongBall.y); // Draw current frame at (50, 50)
        batch.end();
    }

    @Override
    public void dispose ()
    {
        pingPong.dispose();
        ballSheet.dispose();
    }

    public void reset()
    {
        // Center the ball
        pongBall.setPosition(game.getWidth()/2, game.getHeight()/2);

        // Randomize ball velocity
        // X velocity range of 5-6, Y velocity range of 3-7 for test purposes
        this.xv = (RNG.nextInt(2) + 5);
        this.yv = (RNG.nextInt(5) + 3);
        switch (RNG.nextInt(4))
        {
            case 0: break;
            case 1: this.xv *= -1; break;
            case 2: this.yv *= -1; break;
            case 3: this.xv *= -1; this.yv *= -1; break;
        }
    }

    public void switchVelocity(int speed, float momentum, boolean side)
    {
        if (Math.abs(xv) > speed)
            xv = (Math.abs(xv) + speed) / 2;
        else
            xv = speed;

        yv += (int)(momentum/3);

        if (!side)
        {
           xv *= -1;
        }
    }

    public void powerShot()
    {
        float x = game.getBall().getXSpeed();
        float y = game.getBall().getYSpeed();
        float z = x/y;

        game.getBall().setXSpeed(12f);
        game.getBall().setYSpeed(12f/z);

    }
    // Under Construction: Patrick
    // Currently buggy as hell
    public void directionChange()
    {
        //todo

    }




    //Getters/Setters
    public Rectangle getRectangle()
    {
        return pongBall;
    }

    public float getXSpeed() { return xv; }
    public float getYSpeed() { return yv; }
    public void setXSpeed(float speed) { xv = speed; }
    public void setYSpeed(float speed) { yv = speed; }
    public float getX() { return pongBall.x; }
    public float getY() { return pongBall.y; }

}
