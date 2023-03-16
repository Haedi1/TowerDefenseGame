package com.towergame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.towergame.entities.DraggableImage;
import com.towergame.entities.Enemy;
import com.towergame.entities.Tower;
import com.towergame.map.Map;

import java.util.ArrayList;

public class GameScreen implements Screen, GestureDetector.GestureListener {
    private SpriteBatch batch;

    private OrthographicCamera camera;

    Map gameMap;

    Enemy enemy;
    Tower tower;
    Stage stage;


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
        // Test
        stage = new Stage();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new GestureDetector( this));
        Gdx.input.setInputProcessor(inputMultiplexer);
        // Load the image file into a texture
        Texture texture = new Texture(Gdx.files.internal("images/barbarianEnemy.png"));
        // Create a drawable from the texture
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        // Create a draggable image
        DraggableImage draggableImage = new DraggableImage(drawable);
        // Add the image to the stage
        stage.addActor(draggableImage);
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
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        System.out.println("Touch down at " + x + ", " + y);
        System.out.println(pointer);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
