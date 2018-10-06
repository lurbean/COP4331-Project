package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Paddle extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 100; //Dimensions of the Paddle.
    public int yv = 8; //The paddle's velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle paddle;
    private Texture paddleTexture;
    // NOTE: Floats must be signed with "f" or they will be interpreted as a double
    public Float momentum = 0f;

    public Paddle(PongGame game, boolean side)
    {
        batch = new SpriteBatch();
        this.game = game;
        paddleTexture = new Texture("paddleOne.jpg");

        //Rectangle (float x, float y, float width, float height)
        if(side)
            paddle = new Rectangle(WIDTH, (game.getHeight()/2 - HEIGHT/2), WIDTH, HEIGHT);
        else
            paddle = new Rectangle((game.getWidth() - 2*WIDTH), (game.getHeight()/2 - HEIGHT/2), WIDTH, HEIGHT);


    }

    @Override
    public void render()
    {
        batch.begin();
        batch.draw(paddleTexture, paddle.x, paddle.y);
        batch.end();
    }

    public void movePaddleUp()
    {
        if (game.getPaused())
        {
            return;
        }

        if(yv < 0)
            yv *= -1;

        if (momentum <= 0)
            momentum = 1f;
        else
            momentum = 1f + momentum * .9f;

        //Checks to see if the paddle will move out of bounds.
        if((paddle.y + yv > 0) && paddle.y + yv < game.getHeight() - HEIGHT)
            paddle.y += yv;
    }

    public void movePaddleDown()
    {
        if (game.getPaused())
        {
            return;
        }

        if(yv > 0)
            yv *= -1;

        if (momentum >= 0)
            momentum = -1f;
        else
            momentum = -1f + momentum * .9f;

        if(paddle.y + yv > 0 && paddle.y + yv < game.getHeight() - HEIGHT)
            paddle.y += yv;
    }

    public void useAbilityOneP1()
    {
        if (game.getPaused())
        {
            return;
        }
        //Arbitrary system out ability

        if (game.getBall().getY() < (paddle.getHeight()/2 + paddle.y) && game.getBall().getY() > (paddle.y - paddle.getHeight()/2))
        {
            if (game.getBall().getX() < 100)
            {
                game.getBall().powerShot();
            }
        }


    }

    public void useAbilityOneP2()
    {
        if (game.getPaused())
        {
            return;
        }
        //Arbitrary system out ability

        if (game.getBall().getY() < (paddle.getHeight()/2 + paddle.y) && game.getBall().getY() > (paddle.y - paddle.getHeight()/2))
        {
            if (game.getBall().getX() > (game.getWidth() - 100))
            {
               game.getBall().powerShot();
            }
        }


    }

    // Ability Two Under Construction: Patrick
    public void useAbilityTwoP1()
    {
        if (game.getPaused())
        {
            return;
        }

        System.out.println("Ability2 Used!");
    }

    public void useAbilityTwoP2()
    {
        if (game.getPaused())
        {
            return;
        }

        System.out.println("Ability2 Used!");
    }


    public void zeroMomentum()
    {
        this.momentum = 0f;
    }

    public void pause()
    {
        game.pause();
    }

    @Override
    public void dispose ()
    {
        paddleTexture.dispose();
    }

    //Getters/Setters
    public Rectangle getRectangle()
    {
        return paddle;
    }


}
