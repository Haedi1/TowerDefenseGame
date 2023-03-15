package com.towergame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.towergame.screens.GameOverScreen;
import com.towergame.screens.GameScreen;
import com.towergame.screens.MainMenuScreen;

public class TowerDefense extends Game {
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;

	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen();
		gameOverScreen = new GameOverScreen();
		setScreen(mainMenuScreen);
	}
	public void startGame() {
		setScreen(gameScreen);
		mainMenuScreen.dispose();
	}

	public void gameOver() {
		setScreen(gameOverScreen);
		gameScreen.dispose();
	}
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		mainMenuScreen.dispose();
		gameScreen.dispose();
		gameOverScreen.dispose();
	}
}
