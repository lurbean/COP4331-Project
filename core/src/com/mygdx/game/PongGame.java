package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

public class PongGame extends ApplicationAdapter {
	PongGame() {}
	private final static int WIDTH = 800, HEIGHT = 580;
	private SpriteBatch batch;
	private Texture backgroundImage;
	private Texture texturePause;
	private Texture textureShade;
	private Texture textureControls;
	private Texture textureGameOver;
	private Texture[] textureDigitArray;
    private Texture[] textureRedDigitArray;
    private Ball ball;
	private Player player2, player1;
	private ComputerAI computerAI;
	boolean gameOver = false;
	private Music backgroundMusic;
	public SETTINGS settings;
	private boolean paused;
	long pauseToggleTime;
	int gameTimer;
	int secondCountdown;
	int timerLocationLeftX;
	int timerLocationRightX;
	int timerLocationY;
	int coolDownTimerP1A1 = 0;
	int coolDownTimerP2A1 = 0;

	public PongGame(SETTINGS settings) {
		this.settings = settings;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("background.png");
		texturePause = new Texture("paused.png");
		textureShade = new Texture("shade.png");
		textureControls = new Texture("controls.png");
		textureGameOver = new Texture("gameOver.png");
		textureDigitArray = new Texture[10];
        textureRedDigitArray = new Texture[10];
		for (int i=0; i<10; i++)
        {
            textureDigitArray[i] = new Texture(String.valueOf(i) + ".png");
            textureRedDigitArray[i] = new Texture("red" + String.valueOf(i) + ".png");
        }
		gameTimer = settings.gameLength;
        secondCountdown = 60;
        timerLocationLeftX = WIDTH/2 - textureDigitArray[0].getWidth() - 10;
        timerLocationRightX = WIDTH/2 + 10;
        timerLocationY = HEIGHT - textureDigitArray[0].getHeight() - 25;

		ball = new Ball(this, settings);
		player2 = new Player(this, false, ball, settings);
		player1 = new Player(this, true, ball, settings);
		computerAI = new ComputerAI(this, true, ball);

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("fightMusic.wav"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		paused = false;
		pauseToggleTime = System.currentTimeMillis();
	}

	//Render updates the game's frame with a frequency that depends on your hardware
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if(gameOver == true)
            batch.draw(textureGameOver, 0, 0);
        else {
            batch.draw(backgroundImage, 0, 0);

            secondCountdown--;
            if (secondCountdown == 0) {
                gameTimer--;
                secondCountdown = 60;
				if (gameTimer == -100)
					gameTimer = 0;
                // When the timers goes red and every 10 seconds after that
                if (gameTimer < 0 && ( (-gameTimer) % 10 == 1))
				{
					ball.additiveSpeed += 1;
					player1.character.damage += 1;
					player2.character.damage += 1;
				}

            }
            if (gameTimer >= 0) {
                batch.draw(textureDigitArray[gameTimer / 10], timerLocationLeftX, timerLocationY);
                batch.draw(textureDigitArray[gameTimer % 10], timerLocationRightX, timerLocationY);
            }
            else {
                batch.draw(textureRedDigitArray[(-gameTimer) / 10], timerLocationLeftX, timerLocationY);
                batch.draw(textureRedDigitArray[(-gameTimer) % 10], timerLocationRightX, timerLocationY);
            }
        }
        batch.end();

		if(gameOver != true)
		{
			ball.render();
			player1.render();
			player2.render();
			//computerAI.render();

			//Left Player
			if (ball.getRectangle().x < 0) {
				//computerAI.getCharacter().gotHit(player.getCharacter().getDamage());
				player1.getCharacter().gotHit(player2.getCharacter().getDamage());
				player1.setAbilityOneUsed(false);
				coolDownTimerP1A1 = 0;
				coolDownTimerP2A1 = 0;
				ball.reset();
				if (player1.getCharacter().getHitPoints()==0) { //hit points=0 attempt to end game
					//dispose(); see below
					WinWindow.lose_window(0);
				}
			}

			if (player1.getAbilityOneUsed() && coolDownTimerP1A1 == 0)
			{
				if (ball.getRectangle().x < 50){
					if (ball.getRectangle().y <= (player1.getPaddle().getRectangle().getHeight()/2 + player1.getPaddle().getRectangle().y))
					{
						if (ball.getRectangle().y >= (player1.getPaddle().getRectangle().y - player1.getPaddle().getRectangle().getHeight()/2)){
							ball.powerShot();
							player1.setAbilityOneUsed(false);
							System.out.println("P1: Ability one successfully used!");
							// Cooldown timer initiate here.
							coolDownTimerP1A1 = 600;
						}
					}

				}

			}

			//Right Player
			if (ball.getRectangle().x > WIDTH - ball.getRectangle().width) {
				player2.getCharacter().gotHit(player1.getCharacter().getDamage());
				player2.setAbilityOneUsed(false);
				coolDownTimerP1A1 = 0;
				coolDownTimerP2A1 = 0;
				ball.reset();
				if (player2.getCharacter().getHitPoints()==0) { //hit points=0 attempt to end game
					//dispose(); TODO - this might be necessary for resetting, but it's currently a breaking change
					WinWindow.lose_window(1);
				}
			}

			if (player2.getAbilityOneUsed() && coolDownTimerP2A1 == 0)
			{
				if (ball.getRectangle().x > (WIDTH - 80)){
					if (ball.getRectangle().y <= (player2.getPaddle().getRectangle().getHeight()/2 + player2.getPaddle().getRectangle().y))
					{
						if (ball.getRectangle().y >= (player2.getPaddle().getRectangle().y - player2.getPaddle().getRectangle().getHeight()/2)){
							ball.powerShot();
							player2.setAbilityOneUsed(false);
							System.out.println("P2: Ability one successfully used!");
							// Cooldown timer initiate here.
							coolDownTimerP2A1 = 600;
						}
					}

				}
			}
		}

		if (coolDownTimerP1A1 > 0)
		{
			coolDownTimerP1A1--;
			player1.setAbilityOneUsed(false);
			player2.setAbilityOneUsed(false);
		}
		if (coolDownTimerP2A1 > 0)
		{
			coolDownTimerP2A1--;
			player1.setAbilityOneUsed(false);
			player2.setAbilityOneUsed(false);
		}

		if (paused)
		{
			batch.begin();
			batch.draw(textureShade, 0, 0);
			batch.draw(texturePause, (WIDTH / 2) - (texturePause.getWidth() / 2), (HEIGHT / 2) - (texturePause.getHeight()/2) + 50);
			batch.draw(textureControls, 0, 0);
			batch.end();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		texturePause.dispose();
		textureShade.dispose();
		textureControls.dispose();
		textureGameOver.dispose();
		ball.dispose();
		player2.dispose();
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

	public void pause()
	{
		if (System.currentTimeMillis() > (pauseToggleTime + 100))
		{
			paused = !paused;
		}

		pauseToggleTime = System.currentTimeMillis();
	}

	public boolean getPaused()
	{
		return paused;
	}
	public Ball getBall() { return ball; }
	public Player getPlayer2() {return player2;}
	public Player getPlayer1() {return player1;}
}
