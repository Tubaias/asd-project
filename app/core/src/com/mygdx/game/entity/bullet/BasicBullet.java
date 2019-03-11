
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BasicBullet implements Bullet {
    private float x;
    private float y;
    private double speed;
    private double acceleration;
    private double angle;
    private Texture texture;
    private Sprite sprite;

    public BasicBullet(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
        this.speed = 20;
        this.acceleration = 0;

        this.texture = new Texture("whitebullet.png");
        this.sprite = new Sprite(texture);
        sprite.setColor(1, 0, 0, 1f);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void move() {
        speed += acceleration;

        x += speed * Math.sin(angle);
        y += speed * Math.cos(angle);

        sprite.setPosition(x, y);
    }
}