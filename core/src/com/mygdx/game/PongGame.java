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
	boolean gameOver = false;
	
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

		if(gameOver != true)
		{
			ball.render();
			player.render();
			computerAI.render();

			//Left Player
			if (ball.getRectangle().x < 0) {
				computerAI.getCharacter().gotHit(player.getCharacter().getDamage());
				System.out.println("Computer HP: " + computerAI.getCharacter().getHitPoints());
				ball.reset();
			}

			//Right Player
			if (ball.getRectangle().x > WIDTH - ball.getRectangle().width) {
				player.getCharacter().gotHit(computerAI.getCharacter().getDamage());
				System.out.println("Player HP: " + player.getCharacter().getHitPoints());
				ball.reset();
			}
		}


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		ball.dispose();
		player.dispose();
		computerAI.dispose();
	}

	public void endGame()
	{
		gameOver = true;
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
