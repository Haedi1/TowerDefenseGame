package com.towergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;

public class Enemy extends Sprite implements IGameObject {
    private final Vector2 velocity;
    private ArrayList<Vector2> path;
    private int currentWaypointIndex = 0;
    public Enemy(Texture texture, float x, float y, float speed) {
        super(texture);
        setPosition(x, y);
        this.velocity = new Vector2(speed, 0);
        this.path = new ArrayList<>();
    }

    public void setPath(ArrayList<Vector2> path) {
        this.path = path;
        this.currentWaypointIndex = 0;
        setPosition(path.get(0).x, path.get(0).y);
    }
    public Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }


    // TODO: Find out why we get an ArrayIndexOutOfBoundsException here
    @Override
    public void update(float deltaTime) {
        // If we've reached the current waypoint, move on to the next one
        if (getPosition().dst2(path.get(currentWaypointIndex)) < 1f) {
            currentWaypointIndex++;
            if (currentWaypointIndex >= path.size()) {
                // If we've reached the end of the path, remove the enemy
//                Map.enemies.remove(this);
                // if reached the end of the path, move the opposite direction
                currentWaypointIndex = 0;
                velocity.set(velocity.x * -1, velocity.y * -1);
//                return;
            }
            // Set the velocity towards the next waypoint
            Vector2 direction = path.get(currentWaypointIndex).cpy().sub(getPosition()).nor();
            velocity.set(direction.scl(velocity.len()));
        }
        // Move the enemy in the velocity direction
        setPosition(getX() + velocity.x * deltaTime, getY() + velocity.y * deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public boolean handleCollision(IGameObject other) {
        return false;
    }

}
