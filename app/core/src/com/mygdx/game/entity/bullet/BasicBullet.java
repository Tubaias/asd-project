
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

    public BasicBullet(float x, float y, float scale, float angle) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
        this.speed = 20 * scale;
        this.acceleration = 0;

        this.texture = new Texture("whitebullet.png");
        this.sprite = new Sprite(texture);

        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        this.sprite.setOriginCenter();
        this.sprite.setColor(1, 0, 0, 1f);
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
    public float getX() {
        return x;
    }

    @Override
    public void setX(float value) {
        this.x = value;
    }

    @Override
    public void setY(float value) {
        this.y = value;
    }

    @Override
    public void setAngle(float value) {
        this.angle = value;
    }

    @Override
    public void refresh(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
    }

    @Override
    public void move() {
        speed += acceleration;

        x += speed * Math.sin(angle);
        y += speed * Math.cos(angle);

        sprite.setPosition(x, y);
    }
}