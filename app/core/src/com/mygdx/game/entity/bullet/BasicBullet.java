
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BasicBullet implements Bullet {
    private Vector2 position;
    private double speed;
    private double acceleration;
    private double angle;
    private Texture texture;
    private Sprite sprite;

    public BasicBullet(float x, float y, float angle) {
        this.position = new Vector2(x, y);
        this.angle = Math.toRadians(angle);
        this.speed = 20;
        this.acceleration = 0;

        this.texture = new Texture("whitebullet.png");
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setColor(1, 0, 0, 1f);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public void setAngle(float value) {
        this.angle = value;
    }

    @Override
    public void refresh(float x, float y, float angle) {
        this.position.x = x;
        this.position.y = y;
        this.angle = Math.toRadians(angle);
    }

    @Override
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
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