package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Paddle extends ApplicationAdapter {

    private final static int WIDTH = 25, HEIGHT = 100; //Dimensions of the Paddle.
    private int yv = 8; //The paddle's velocity.

    private boolean player;
    private SpriteBatch batch;
    private PongGame game;
    private Rectangle paddle;
    private Texture paddleTexture;

    public Paddle(PongGame game, boolean player, boolean side)
    {
        batch = new SpriteBatch();
        this.game = game;
        this.player = player;
        paddleTexture = new Texture("paddleOne.jpg");

        //Rectangle (float x, float y, float width, float height)
        if(side == true)
            paddle = new Rectangle(WIDTH, game.getHeight()/2, WIDTH, HEIGHT);
        else
            paddle = new Rectangle((game.getWidth() - 2*WIDTH), game.getHeight()/2, WIDTH, HEIGHT);


    }

    @Override
    public void render()
    {
        batch.begin();
        batch.draw(paddleTexture, paddle.x, paddle.y);
        batch.end();

        if(player == false)
        {
            computerAI();
        }

    }

    public void movePaddleUp()
    {
        if(yv < 0)
            yv *= -1;

        //Checks to see if the paddle will move out of bounds.
        if((paddle.y + yv > 0) && paddle.y + yv < game.getHeight() - HEIGHT)
            paddle.y += yv;
    }

    public void movePaddleDown()
    {
        if(yv > 0)
            yv *= -1;

        //Checks to see if the paddle will move out of bounds.
        if(paddle.y + yv > 0 && paddle.y + yv < game.getHeight() - HEIGHT)
            paddle.y += yv;
    }

    //THING TO DO: I purposely wrote bad AI to see if you guys can figure out a better one. -Lavine
    public void computerAI()
    {
        paddle.y += yv;

        if((paddle.y + yv <= 0) || (paddle.y + yv >= game.getHeight() - HEIGHT))
            yv = -yv;
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
