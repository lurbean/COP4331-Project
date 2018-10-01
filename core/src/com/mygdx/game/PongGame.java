package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

public class PongGame extends ApplicationAdapter {
	private final static int WIDTH = 800, HEIGHT = 580;
	private SpriteBatch batch;
	private Texture backgroundImage;
	private Ball ball;
	private Player player, player1;
	private ComputerAI computerAI;
	boolean gameOver = false;
	private Music backgroundMusic;

	public PongGame(SETTINGS settings) {
		// do stuff here ADAM
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("background.jpg");

		ball = new Ball(this);
		player = new Player(this, false, ball);
		player1 = new Player(this, true, ball);
		computerAI = new ComputerAI(this, true, ball);

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("fightMusic.wav"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
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
			player1.render();
			//computerAI.render();

			//Left Player
			if (ball.getRectangle().x < 0) {
				//computerAI.getCharacter().gotHit(player.getCharacter().getDamage());
				player1.getCharacter().gotHit(player1.getCharacter().getDamage());
				ball.reset();
			}

			//Right Player
			if (ball.getRectangle().x > WIDTH - ball.getRectangle().width) {
				player.getCharacter().gotHit(player1.getCharacter().getDamage());
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
		player1.dispose();
		//computerAI.dispose();
		backgroundMusic.dispose();
	}

	public void endGame()
	{
		gameOver = true;
	}

	//Getters and Setters
	public int getHeight()
	{
		return HEIGHT - 100;
	}

	public int getWidth()
	{
		return WIDTH;
	}

}
