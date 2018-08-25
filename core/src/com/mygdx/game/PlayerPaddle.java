package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input; //Keyboard inputs


public class PlayerPaddle extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 100; //Dimensions of the Paddle.
    private int yv = 8; //The paddle's velocity.

    private SpriteBatch batch;
    private PongGame game;
    private Rectangle paddle;
    private Texture paddleTexture;

    public PlayerPaddle(PongGame game)
    {
        batch = new SpriteBatch();
        this.game = game;
        paddleTexture = new Texture("paddleOne.jpg");
        paddle = new Rectangle();

        paddle.x = WIDTH;
        paddle.y = game.getHeight()/2;

    }

    public void render()
    {
        batch.begin();
        batch.draw(paddleTexture, paddle.x, paddle.y);
        batch.end();

        //Paddle only moves up and down, so we only need to update the y value.
        if( Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            if(yv < 0)
                yv *= -1;

            //Checks to see if the paddle will move out of bounds.
            if((paddle.y + yv > 0) && paddle.y + yv < game.getHeight() - HEIGHT)
                paddle.y += yv;
        }
        if( Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            if(yv > 0)
                yv *= -1;

            //Checks to see if the paddle will move out of bounds.
            if(paddle.y + yv > 0 && paddle.y + yv < game.getHeight() - HEIGHT)
                paddle.y += yv;
        }
    }

    @Override
    public void dispose ()
    {
        paddleTexture.dispose();
    }

}
