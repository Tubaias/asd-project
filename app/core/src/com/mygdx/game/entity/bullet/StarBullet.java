
package com.mygdx.game.entity.bullet;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class StarBullet implements Bullet {
    private Vector2 position;
    private double speed;
    private double acceleration;
    private double angle;
    private Texture texture;
    private Sprite sprite;

    public StarBullet(float x, float y, float scale, float angle) {
        Random rand = new Random();
        this.position = new Vector2(x - 32, y);
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
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(sprite.getRotation() + 5);
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