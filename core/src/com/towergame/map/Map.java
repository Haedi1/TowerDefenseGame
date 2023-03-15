package com.towergame.map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.math.Vector2;
import com.towergame.entities.Enemy;
import com.towergame.entities.Tower;


import java.util.ArrayList;

public class Map {
    private static TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer tiledMapRenderer;
    public float scale;
    SpriteBatch batch;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Tower> towers = new ArrayList<>();
    public Map(String mapfile, SpriteBatch batch) {
        this.batch = batch;
        TmxMapLoader mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(mapfile);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        this.scale = (getWidth() > getHeight()) ? getWidth() / Gdx.graphics.getWidth() : getHeight() / Gdx.graphics.getHeight();

    }
    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        for (Enemy enemy : enemies) {
            batch.begin();
            enemy.draw(tiledMapRenderer.getBatch());
            batch.end();
        }
        for (Tower tower : towers) {
            batch.begin();
            tower.draw(tiledMapRenderer.getBatch());
            batch.end();
        }



    }
    public void dispose() {
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }

    public float getWidth() {
        return tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class);
    }

    public float getHeight() {
        return tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy); // Add the enemy to the array
    }
    public void addTower(Tower tower) {
        towers.add(tower); // Add the tower to the array
    }
    public ArrayList<Vector2> generatePath() {
        ArrayList<Vector2> path = new ArrayList<>();
        // Enemies waypoints from start to finish
        path.add(new Vector2(50, 160));
        path.add(new Vector2(190, 180));
        path.add(new Vector2(190, 500));
        path.add(new Vector2(650, 500));
        path.add(new Vector2(650, 250));
        path.add(new Vector2(510, 250));
        path.add(new Vector2(510, 0));
        path.add(new Vector2(950, 0));
        path.add(new Vector2(950, 380));
        path.add(new Vector2(1200, 380));
        return path;
    }


}


