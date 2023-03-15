package com.towergame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.towergame.entities.Enemy;
import com.towergame.entities.Tower;
import com.towergame.map.Map;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private SpriteBatch batch;

    private OrthographicCamera camera;

    Map gameMap;

    Enemy enemy;
    Tower tower;


    @Override
    public void show() {
        batch = new SpriteBatch();
        gameMap = new Map("maps/GridMap.tmx", batch);
        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth() * gameMap.scale, Gdx.graphics.getHeight() * gameMap.scale);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture enemyTexture = new Texture(Gdx.files.internal("images/barbarianEnemy.png"));

        Texture towerTexture = new Texture(Gdx.files.internal("images/towerLego.jpg"));
        enemy = new Enemy(enemyTexture, 0, Gdx.graphics.getHeight() / 2, 100);
        tower = new Tower(towerTexture, 100, Gdx.graphics.getHeight() / 2 + 300);
        gameMap.addEnemy(enemy);
        gameMap.addTower(tower);
        ArrayList<Vector2> path = gameMap.generatePath();
        for (Enemy enemy : gameMap.enemies) {
            enemy.setPath(path);
        }

    }

    @Override
    public void render(float delta) {
        //Clears screen
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update the enemy object
        gameMap.render(camera);
        enemy.update(Gdx.graphics.getDeltaTime());

		tower.update(Gdx.graphics.getDeltaTime());
        batch.setProjectionMatrix(camera.combined);
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
        gameMap.dispose();
        batch.dispose();
    }

}
