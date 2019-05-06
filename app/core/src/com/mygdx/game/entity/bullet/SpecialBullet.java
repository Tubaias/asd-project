
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SpecialBullet extends Bullet {
    private float rotationAngle;

    public SpecialBullet(float x, float y, float angle, Texture texture) {
        this.angle = angle;
        this.initialSpeed = 1;
        this.speed = this.initialSpeed;
        this.acceleration = 0.1;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setColor(0.7f, 1f, 0.7f, 0.5f);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(Math.toRadians(angle));
        position.y += speed * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotationAngle);

        rotationAngle += 5;
    }

    @Override
    public BulletType getType() {
        return BulletType.SPECIAL;
    }
}