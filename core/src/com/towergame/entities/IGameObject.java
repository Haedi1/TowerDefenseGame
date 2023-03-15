package com.towergame.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGameObject {
    void update(float deltaTime);
    void render(SpriteBatch batch);
    boolean handleCollision(IGameObject other);
}