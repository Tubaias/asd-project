
package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Vector2 speed;
    public Sprite sprite;

    public Player(float x, float y, Sprite sprite) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, 0);
        this.sprite = sprite;
        this.sprite.setPosition(x, y);
    }

    public void move() {
        position.x += speed.x;
        position.y += speed.y;

        sprite.setPosition(position.x, position.y);
    }
}