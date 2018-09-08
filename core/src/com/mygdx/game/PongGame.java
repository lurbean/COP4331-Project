package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PongGame extends ApplicationAdapter {
	private final static int WIDTH = 800, HEIGHT = 480;
	private SpriteBatch batch;
	private Texture backgroundImage;
	private Ball ball;
	private Player player;
	private ComputerAI computerAI;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("testbackground.jpg");

		ball = new Ball(this);
		player = new Player(this, false, ball);
		computerAI = new ComputerAI(this, true, ball);

		//To add later:
		//private Sound hitPaddle; //Use 'Sound' if < 10 seconds
		//private Music backgroundMusic;
	}

	//Render updates the game's frame with a frequency that depends on your hardware
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(backgroundImage, 0, 0); //Places the background in (x,y)
		batch.end();

		ball.render();
		player.render();
		computerAI.render();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		ball.dispose();
		player.dispose();
		computerAI.dispose();
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
