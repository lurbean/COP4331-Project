package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Paddle extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 100; //Dimensions of the Paddle.
    public int yv; //The paddle's velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle paddlePosition;
    public Texture paddleTexture;
    // NOTE: Floats must be signed with "f" or they will be interpreted as a double
    public Float momentum = 0f;

    public Paddle(PongGame game, boolean side, int paddleSpeed)
    {
        yv = paddleSpeed;
        batch = new SpriteBatch();
        this.game = game;
        paddleTexture = new Texture("paddleOne.jpg");

        //Rectangle (float x, float y, float width, float height)
        if(side)
            paddlePosition = new Rectangle(WIDTH, (game.getHeight()/2 - HEIGHT/2), WIDTH, HEIGHT);
        else
            paddlePosition = new Rectangle((game.getWidth() - 2*WIDTH), (game.getHeight()/2 - HEIGHT/2), WIDTH, HEIGHT);


    }

    @Override
    public void render()
    {
        batch.begin();
        batch.draw(paddleTexture, paddlePosition.x, paddlePosition.y);
        batch.end();
    }

    public void movePaddleUp()
    {
        if (game.getPaused())
        {
            return;
        }

        if (momentum <= 0)
            momentum = 1f;
        else
            momentum = 1f + momentum * .9f;

        //Checks to see if the paddle will move out of bounds.
        if((paddlePosition.y + yv > 0) && paddlePosition.y + yv < game.getHeight() - HEIGHT)
            paddlePosition.y += yv;
    }

    public void movePaddleDown()
    {
        if (game.getPaused())
        {
            return;
        }

        int negYV = -yv;

        if (momentum >= 0)
            momentum = -1f;
        else
            momentum = -1f + momentum * .9f;

        if(paddlePosition.y + negYV > 0 && paddlePosition.y + negYV < game.getHeight() - HEIGHT)
            paddlePosition.y += negYV;
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

    public Rectangle getRectangle()
    {
        return paddlePosition;
    }


}
