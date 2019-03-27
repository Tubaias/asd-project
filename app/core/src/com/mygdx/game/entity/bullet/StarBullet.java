
package com.mygdx.game.entity.bullet;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class StarBullet implements Bullet {
    private float x;
    private float y;
    private double speed;
    private double acceleration;
    private double angle;
    private Texture texture;
    private Sprite sprite;

    public StarBullet(float x, float y, float scale, float angle) {
        Random rand = new Random();
        this.x = x-32;
        this.y = y;
        this.angle = Math.toRadians(angle);
        this.speed = 10 * scale;
        this.acceleration = 0;

        this.texture = new Texture("star.png");
        this.sprite = new Sprite(texture);
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        this.sprite.setOriginCenter();
        sprite.setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1f);
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
        this.x = x-32;
        this.y = y;
        this.angle = Math.toRadians(angle);
        //this.sprite.setOriginCenter();
    }

    @Override
    public void move() {
        speed += acceleration;

        x += speed * Math.sin(angle);
        y += speed * Math.cos(angle);

        sprite.setPosition(x, y);
        sprite.setRotation(sprite.getRotation() + 5);
    }
}