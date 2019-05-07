
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.EntityStore;

public class SpecialBullet extends Bullet {
    private EntityStore store;
    private float rotationAngle;
    private int lifeTime;

    public SpecialBullet(float x, float y, float angle, Texture texture, EntityStore store) {
        this.store = store;
        this.angle = angle;
        this.initialSpeed = 15;
        this.speed = this.initialSpeed;
        this.acceleration = -0.3;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setColor(1f, 1f, 1f, 0.7f);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        if (lifeTime > 120) {
            lifeTime = 0;
        }

        lifeTime++;

        if (lifeTime > 120) {
            dead = true;
        }

        if (speed >= 0) {
            speed += acceleration;
        }

        position.x += speed * Math.sin(Math.toRadians(angle));
        position.y += speed * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotationAngle);

        moreBullets();

        rotationAngle += 5;

        if (rotationAngle > 180) {
            rotationAngle -= 360;
        }
    }

    private void moreBullets() {
        float spawnX = position.x + sprite.getWidth() / 2;
        float spawnY = position.y + sprite.getHeight() / 2 - 16;

        // store.bulletSystem.newBullet(BulletType.PLAYERLARGE, spawnX, spawnY, rotationAngle);
        // store.bulletSystem.newBullet(BulletType.PLAYERLARGE, spawnX, spawnY, rotationAngle - 180);

        for (int i = 0; i < 8; i++) {
            store.bulletSystem.newBullet(BulletType.PLAYERLARGE, spawnX, spawnY, rotationAngle + i * 45);
            store.bulletSystem.newBullet(BulletType.PLAYERLARGE, spawnX, spawnY, rotationAngle - 180 + i * 45);
        }
    }

    @Override
    public BulletType getType() {
        return BulletType.SPECIAL;
    }
}