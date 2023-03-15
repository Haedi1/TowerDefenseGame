package com.towergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Tower extends Sprite implements IGameObject {

    public Tower(Texture texture, float x, float y) {
        super(texture);
        setPosition(x, y);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public boolean handleCollision(IGameObject other) {
        return false;
    }


}
