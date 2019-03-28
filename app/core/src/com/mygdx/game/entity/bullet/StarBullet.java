
package com.mygdx.game.entity.bullet;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class StarBullet extends Bullet {
    public StarBullet(float x, float y, float angle) {
        Random rand = new Random();
        this.position = new Vector2(x - 32, y);
        this.angle = Math.toRadians(angle);
        this.speed = 10;
        this.acceleration = 0;

        this.texture = new Texture("star.png");
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        sprite.setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1f);
    }

    @Override
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(sprite.getRotation() + 5);
    }
}