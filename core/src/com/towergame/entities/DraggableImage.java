package com.towergame.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class DraggableImage extends Image {
    private float lastX;
    private float lastY;

    public DraggableImage(Drawable drawable) {
        super(drawable);

        // Add a drag listener to the image
        addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                // Calculate the amount that the mouse has moved
                float deltaX = x - lastX;
                float deltaY = y - lastY;

                // Move the image by the same amount as the mouse movement
                moveBy(deltaX, deltaY);

                // Save the current mouse position for the next drag event
                lastX = x;
                lastY = y;
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                // Save the initial mouse position
                lastX = x;
                lastY = y;
            }
        });
    }
}

