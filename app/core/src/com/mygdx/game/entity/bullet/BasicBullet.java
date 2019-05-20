
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BasicBullet extends Bullet {

    public BasicBullet(float x, float y, float angle, Texture texture) {
        this.angle = angle;
        this.initialSpeed = 7;
        this.speed = 7;

        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        double speedTimesDelta = speed * (Gdx.graphics.getDeltaTime() / (1f / 60f));

        position.x += speedTimesDelta * Math.sin(Math.toRadians(angle));
        position.y += speedTimesDelta * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
    }

    @Override
    public BulletType getType() {
        return BulletType.BASIC;
    }
}