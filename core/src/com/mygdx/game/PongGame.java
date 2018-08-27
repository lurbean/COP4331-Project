package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

public class PongGame extends ApplicationAdapter {
	private final static int WIDTH = 1600, HEIGHT = 960;
	private SpriteBatch batch;
	private Texture backgroundImage;
	//private Texture paddleOne;
	private Texture paddleTwo;
	private Ball ball;
	private PlayerPaddle playerPaddle;
	private PlayerPaddle opponentPaddle;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("testbackground.jpg");
		//paddleTwo = new Texture("paddleTwo.jpg");

		ball = new Ball(this);
		playerPaddle = new PlayerPaddle(this, true, false);
		opponentPaddle = new PlayerPaddle(this, false, true);


		//private Sound hitPaddle; //Use 'Sound' if < 10 seconds
		//private Music backgroundMusic;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(backgroundImage, 0, 0); //Places the background in (x,y)
		batch.end();

		//If the ball hits the paddle, it will bounce back.
		if(Intersector.overlaps(playerPaddle.getRectangle(), ball.getRectangle())
				&& playerPaddle.getRectangle().x == ball.getRectangle().x) //Checks for intersection.
		{
			ball.switchVelocity();
		}

		ball.render();
		playerPaddle.render();
		opponentPaddle.render();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		ball.dispose();
		playerPaddle.dispose();
		opponentPaddle.dispose();
	}

	//Getters and Setters
	public int getHeight()
	{
		return HEIGHT;
	}

	public int getWidth()
	{
		return WIDTH;
	}

}
