
package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.utility.EntityStore;

public class Player implements Entity {
    private Vector2 position;
    private Sprite sprite;
    private EntityStore store;

    public Player() {
        this.sprite = new Sprite(new Texture("ship.png"));

        float x = 600 / 2 - sprite.getWidth() / 2;
        float y = 100;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);
    }

    public void setStore(EntityStore store) {
        this.store = store;
    }

    public void move() {
        sprite.setPosition(position.x, position.y);
    }

    public void shoot(boolean focused) {
        if (focused) {
            float bulletAngle = 15;
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, 0f);
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, -bulletAngle);
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, bulletAngle);
        } else {
            float bulletAngle = 30;
            store.bulletSystem.newBullet(BulletType.BASIC, position.x + (32 - 8), position.y + 64, 0f);
            store.bulletSystem.newBullet(BulletType.BASIC, position.x + (32 - 8), position.y + 64, -bulletAngle);
            store.bulletSystem.newBullet(BulletType.BASIC, position.x + (32 - 8), position.y + 64, bulletAngle);
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
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