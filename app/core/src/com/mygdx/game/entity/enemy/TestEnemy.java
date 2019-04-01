
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class TestEnemy extends Enemy {
    private Vector2 position;
    private Vector2 speed;
    private Sprite sprite;
    private int hitpoints = 10000;

    private float sinewaveAngle;

    public TestEnemy(float x, float y) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        this.sprite = new Sprite(new Texture("skull.png"));
        this.sprite.setPosition(x, y);
    }

    @Override
    public void move() {
        position.add(speed);
        updateSpeed();
        sprite.setPosition(position.x, position.y);
        sprite.setColor(Color.WHITE);
    }

    @Override
    public void hit() {
        sprite.setColor(1f, 0.3f, 0.3f, 1f);

        this.hitpoints -= 100;
        if (hitpoints <= 0) {
            this.position = new Vector2(-1000,-1000);
        }
    }

    private void updateSpeed() {
        speed.set((float) Math.sin(sinewaveAngle) * 3, speed.y - 0.05f);

        sinewaveAngle += 0.1;

        if (sinewaveAngle > 360) {
            sinewaveAngle = 0;
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}