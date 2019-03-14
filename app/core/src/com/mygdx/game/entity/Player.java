
package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.StarBullet;
import com.mygdx.game.utility.EntityStore;

public class Player implements Entity {
    private Vector2 position;
    private Sprite sprite;
    private float scale;
    private EntityStore store;

    public Player(float scale) {
        this.sprite = new Sprite(new Texture("ship.png"));
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);

        float x = Gdx.graphics.getWidth() / 2 - sprite.getWidth() * scale / 2;
        float y = Gdx.graphics.getHeight() / 2 - sprite.getHeight() * scale / 2;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);
        this.scale = scale;
    }

    public void setStore(EntityStore store) {
        this.store = store;
    }

    public void move() {
        sprite.setPosition(position.x, position.y);
    }

    public void shoot(boolean focused) {
        Bullet bullet1;
        Bullet bullet2;
        Bullet bullet3;

        if (focused) {
            int bulletAngle = 15;
            bullet1 = new StarBullet(position.x + (32 - 8), position.y + 64 * scale, scale, 0);
            bullet2 = new StarBullet(position.x + (32 - 8), position.y + 64 * scale, scale, -bulletAngle);
            bullet3 = new StarBullet(position.x + (32 - 8), position.y + 64 * scale, scale, bulletAngle);
        } else {
            int bulletAngle = 30;
            bullet1 = new BasicBullet(position.x + (32 - 8), position.y + 64 * scale, scale, 0);
            bullet2 = new BasicBullet(position.x + (32 - 8), position.y + 64 * scale, scale, -bulletAngle);
            bullet3 = new BasicBullet(position.x + (32 - 8), position.y + 64 * scale, scale, bulletAngle);
        }

        store.playerBullets.add(bullet1);
        store.playerBullets.add(bullet2);
        store.playerBullets.add(bullet3);
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