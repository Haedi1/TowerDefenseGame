package com.towergame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.towergame.TowerDefense;

public class MainMenuScreen implements Screen {
    TowerDefense game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private Rectangle startButton;
    private Rectangle optionsButton;
    private Rectangle exitButton;

    public MainMenuScreen(TowerDefense game) {
        this.game = game;
    }


    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2f);

        float buttonWidth = 200f;
        float buttonHeight = 50f;
        float buttonSpacing = 20f;
        float totalHeight = buttonHeight * 3 + buttonSpacing * 2;
        float startY = (Gdx.graphics.getHeight() - totalHeight) / 2;

        startButton = new Rectangle(
                (Gdx.graphics.getWidth() - buttonWidth) / 2,
                startY + buttonHeight * 2 + buttonSpacing * 2,
                buttonWidth,
                buttonHeight);

        optionsButton = new Rectangle(
                (Gdx.graphics.getWidth() - buttonWidth) / 2,
                startY + buttonHeight + buttonSpacing,
                buttonWidth,
                buttonHeight);

        exitButton = new Rectangle(
                (Gdx.graphics.getWidth() - buttonWidth) / 2,
                startY,
                buttonWidth,
                buttonHeight);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw start button
        if (startButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            shapeRenderer.setColor(Color.BLUE);
        } else {
            shapeRenderer.setColor(Color.GREEN);
        }
        shapeRenderer.rect(startButton.x, startButton.y, startButton.width, startButton.height);

        // Draw options button
        if (optionsButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            shapeRenderer.setColor(Color.BLUE);
        } else {
            shapeRenderer.setColor(Color.YELLOW);
        }
        shapeRenderer.rect(optionsButton.x, optionsButton.y, optionsButton.width, optionsButton.height);

        // Draw exit button
        if (exitButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            shapeRenderer.setColor(Color.BLUE);
        } else {
            shapeRenderer.setColor(Color.RED);
        }
        shapeRenderer.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);

        shapeRenderer.end();

        // Draw button labels
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, "Start", startButton.x + 50, startButton.y + 30);
        font.draw(spriteBatch, "Options", optionsButton.x + 30, optionsButton.y + 30);
        font.draw(spriteBatch, "Exit", exitButton.x + 50, exitButton.y + 30);
        spriteBatch.end();

        // Handle button clicks
        if (Gdx.input.isTouched()) {
            if (startButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
//                game.setScreen(new GameScreen(game));
//                game.setScreen(new GameScreen());
                game.startGame();
//                dispose();

                System.out.println("Start button clicked");

            } else if (optionsButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
//                game.setScreen(new OptionsScreen(game));
//                dispose();
                System.out.println("Options button clicked");
            } else if (exitButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                System.out.println("Exit button clicked");
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();

    }
}

