package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Pod extends Entity {
    private Vector2 destination;
    private Vector2 velocity;
    private boolean attached;

    public Pod(float x, float y, String side) {
        this.position = new Vector2(x, y);
        this.destination = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.sprite = new Sprite(new Texture("pod" + side + ".png"));
        this.sprite.setPosition(x, y);
        this.attached = false;
    }

    public void move(boolean focused) {
        if (!focused) {
            attached = false;
        }

        if (attached) {
            velocity.set(0, 0);
            position.set(destination);
            sprite.setPosition(position.x, position.y);
            return;
        }

        if (position.x != destination.x || position.y != destination.y) {
            velocity.set((destination.x - position.x) / 3, (destination.y - position.y) / 3);
        }

        if (Math.abs(position.x - destination.x) < 5 && Math.abs(position.y - destination.y) < 5) {
            velocity.set(0, 0);
            if (focused) {
                attached = true;
            }
        }

        position.add(velocity);
        sprite.setPosition(position.x, position.y);
    }

    public void setDestination(float x, float y) {
        destination.set(x, y);
    }
}