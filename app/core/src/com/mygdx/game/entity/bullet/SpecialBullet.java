
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.EntityStore;

public class SpecialBullet extends Bullet {
    private EntityStore store;
    private float rotationAngle;
    private float lifeTime;
    private float bulletAccumulator;

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
        float delta = Gdx.graphics.getDeltaTime();

        if (lifeTime > 2) {
            lifeTime = 0;
        }

        lifeTime += delta;

        if (lifeTime > 2) {
            dead = true;
        }

        if (speed >= 0) {
            speed += acceleration;
        }

        double speedTimesDelta = speed * (delta / (1f / 60f));

        position.x += speedTimesDelta * Math.sin(Math.toRadians(angle));
        position.y += speedTimesDelta * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation(rotationAngle);

        bulletAccumulator += delta;

        while (bulletAccumulator > (1f / 60f)) {
            moreBullets();
            bulletAccumulator -= 1f / 60f;
        }

        rotationAngle += 5 * (delta / (1f / 60f));

        if (rotationAngle > 180) {
            rotationAngle -= 360;
        }
    }

    private void moreBullets() {
        float spawnX = position.x + sprite.getWidth() / 2;
        float spawnY = position.y + sprite.getHeight() / 2 - 16;

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