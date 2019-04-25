
package com.mygdx.game.entity.bullet;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class StarBullet extends Bullet {
    public StarBullet(float x, float y, float angle) {
        Random rand = new Random();
        this.position = new Vector2(x - 32, y);
        this.angle = angle;
        this.initialSpeed = 10;
        this.speed = 10;
        this.acceleration = 0;

        this.texture = new Texture("images/bullets/star.png");
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        sprite.setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1f);
    }

    @Override
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(Math.toRadians(angle));
        position.y += speed * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(sprite.getRotation() + 5);
    }

    @Override
    public BulletType getType() {
        return BulletType.STAR;
    }
}