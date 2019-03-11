
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BasicBullet implements Bullet {
    private float x;
    private float y;
    private double speed;
    private double acceleration;
    private double angle;
    private Sprite sprite;

    public BasicBullet(float x, float y, float angle, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
        this.speed = 10;
        this.acceleration = 0;
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public void move() {
        speed += acceleration;

        x += speed * Math.sin(angle);
        y += speed * Math.cos(angle);

        sprite.setPosition(x, y);
    }
}