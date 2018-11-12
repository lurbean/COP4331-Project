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
	private Texture texture1wins;
	private Texture texture2wins;
	private Texture textureRematch;
	public Texture[] textureBlackDigitArray;
    public Texture[] textureRedDigitArray;
    public Texture[] textureDeepRedDigitArray;
    public Texture[] textureWhiteDigitArray;
    private Ball ball;
    private Control control;
	public Player player2, player1;
	boolean gameOver = false;
	private Music backgroundMusic;
	public SETTINGS settings;
	private boolean paused = false;
	long pauseStateTime = 0;
	int gameTimer;
	int secondCountdown;
	int timerLocationLeftX;
	int timerLocationRightX;
	int timerLocationY;

	public PongGame(SETTINGS settings) {
		this.settings = settings;
	}

	@Override
	public void create () {
		if (backgroundImage == null)
			createTextures();
		initializeTimer();
		createGameObjects();
		if (backgroundMusic == null)
			setupMusic();
	}

	private void createGameObjects() {
		ball = new Ball(this, settings);
		player1 = new Player(this, true, ball, settings);
		player2 = new Player(this, false, ball, settings);
		this.control = new Control(settings.State, player1, player2);
	}

	private void setupMusic() {
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("fightMusic.wav"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	private void initializeTimer() {
		gameTimer = settings.gameLength;
		secondCountdown = 60;
		timerLocationLeftX = WIDTH/2 - textureBlackDigitArray[0].getWidth() - 5;
		timerLocationRightX = WIDTH/2 + 5;
		timerLocationY = HEIGHT - textureBlackDigitArray[0].getHeight() - 25;
	}

	private void createTextures() {
		batch = new SpriteBatch();
		backgroundImage = new Texture("background.png");
		texturePause = new Texture("paused.png");
		textureShade = new Texture("shade.png");
		textureControls = new Texture("controls.png");
		textureGameOver = new Texture("gameOver.png");
		texture1wins = new Texture("player1wins.png");
		texture2wins = new Texture("player2wins.png");
		textureRematch = new Texture("rematch.png");
		textureBlackDigitArray = new Texture[10];
        textureWhiteDigitArray = new Texture[10];
		textureRedDigitArray = new Texture[10];
        textureDeepRedDigitArray = new Texture[10];
		for (int i=0; i<10; i++)
        {
            textureBlackDigitArray[i] = new Texture(String.valueOf(i) + ".png");
            textureRedDigitArray[i] = new Texture("red" + String.valueOf(i) + ".png");;
            textureDeepRedDigitArray[i] = new Texture("deepRed" + String.valueOf(i) + ".png");
            textureWhiteDigitArray[i] = new Texture("white" + String.valueOf(i) + ".png");
        }
	}

	//Render updates the game's frame with a frequency that depends on your hardware
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (gameOver) {
			batch.draw(textureGameOver, 0, 0);
			batch.draw(textureRematch, (WIDTH/2) - (textureRematch.getWidth()/2), 128);

			if (player1.character.getHitPoints() <= 0)
			{
				batch.draw(texture2wins, (WIDTH/2) - (texture2wins.getWidth()/2), 416);
			}
			else
			{
				batch.draw(texture1wins,(WIDTH/2) - (texture1wins.getWidth()/2),416);
			}
			batch.end();

			Keyboard masterKey = new Keyboard();
			if (masterKey.isEnterPressed())
			{
				gameOver = false;
				create();
			}
		}
			/*
			String str;
			if (player1.character.getHitPoints() <= 0)
				str = "Two";
			else
				str = "One";
			switch (PopUpQuestion.TwoOptions(null, "Play again!", "Exit", "Player " + str + " Wins!", " "))
			{
				case 0:
					dispose();
					break;
				case 1:
					System.exit(0);
			}
			dispose();*/
		else if (!paused) {
			drawRefresh();
			ball.render();
			player1.render();
			player2.render();
			checkForHits();
		}
		else// if (paused)
		{
			batch.draw(backgroundImage, 0, 0);
			batch.draw(textureShade, 0, 0);
			batch.draw(texturePause, (WIDTH / 2) - (texturePause.getWidth() / 2), (HEIGHT / 2) - (texturePause.getHeight() / 2) + 50);
			batch.draw(textureControls, 0, 0);
			batch.end();
			if (player1.doUnpause() || player2.doUnpause())
				pause(false);
		}
	}

		public void checkForHits ()
		{
			//Left Player then Right Player
			if (ball.getRectangle().x < 0) {
				//computerAI.getCharacter().gotHit(player.getCharacter().getDamage());
				player1.character.gotHit(player2.character.getDamage());
				ball.reset();
				if (player1.character.getHitPoints() <= 0) { //hit points=0 attempt to end game
					//WinWindow.lose_window(0);
					endGame();
				}
			} else if (ball.getRectangle().x > WIDTH - ball.getRectangle().width) {
				player2.character.gotHit(player1.character.getDamage());
				ball.reset();
				if (player2.character.getHitPoints() <= 0) { //hit points=0 attempt to end game
					//dispose(); TODO - this might be necessary for resetting, but it's currently a breaking change
					//WinWindow.lose_window(1);
					endGame();
				}
			}
		}

		private void drawRefresh ()
		{

			batch.draw(backgroundImage, 0, 0);
			secondCountdown--;
			if (secondCountdown == 0) {
				gameTimer--;
				secondCountdown = 60;
				if (gameTimer == -100)
					gameTimer = 0;
				// When the timers goes red and every 10 seconds after that
				if (gameTimer < 0 && ((-gameTimer) % 10 == 1)) {
					ball.additiveSpeed += 1;
					player1.character.damage += 1;
					player2.character.damage += 1;
				}

			}
			if (gameTimer >= 0) {
				batch.draw(textureBlackDigitArray[gameTimer / 10], timerLocationLeftX, timerLocationY);
				batch.draw(textureBlackDigitArray[gameTimer % 10], timerLocationRightX, timerLocationY);
			} else {
				batch.draw(textureRedDigitArray[(-gameTimer) / 10], timerLocationLeftX, timerLocationY);
				batch.draw(textureRedDigitArray[(-gameTimer) % 10], timerLocationRightX, timerLocationY);
			}
			batch.end();
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
		backgroundMusic.dispose();
	}

	public void endGame()
	{
		gameOver = true;
	}

	public int getHeight()
	{
		return HEIGHT - 100;
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public boolean getPaused()
	{
		return this.paused;
	}
	public void pause(boolean doPause)
	{
		if (System.currentTimeMillis() > (pauseStateTime + 500))
		{
			paused = doPause;
			pauseStateTime = System.currentTimeMillis();
		}
	}

	public Ball getBall() { return ball; }
}