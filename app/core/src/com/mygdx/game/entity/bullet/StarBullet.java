
package com.mygdx.game.entity.bullet;

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

    public StarBullet(float x, float y, float angle) {
        this.x = x-32;
        this.y = y;
        this.angle = Math.toRadians(angle);
        this.speed = 10;
        this.acceleration = 0;

        this.texture = new Texture("star.png");
        this.sprite = new Sprite(texture);
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
        sprite.setRotation(sprite.getRotation() + 5);
    }
}