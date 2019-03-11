
package com.mygdx.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;

public class Player {
    public Vector2 position;
    public Vector2 speed;
    public Sprite sprite;
    private ArrayList<Bullet> bullets;
    private Texture bulletTexture;

    public Player(float x, float y, Sprite sprite, ArrayList<Bullet> bulletList) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, 0);
        this.sprite = sprite;
        this.sprite.setPosition(x, y);
        this.bullets = bulletList;

        bulletTexture = new Texture("whitebullet.png");
    }

    public void move() {
        position.x += speed.x;
        position.y += speed.y;

        sprite.setPosition(position.x, position.y);
    }

    public void shoot() {
        Sprite bulletSprite = new Sprite(bulletTexture);
        Bullet bullet1 = new BasicBullet(position.x + 32 - 8, position.y + 128, 0, bulletSprite);
        Bullet bullet2 = new BasicBullet(position.x + 32 - 8, position.y + 128, -15, bulletSprite);
        Bullet bullet3 = new BasicBullet(position.x + 32 - 8, position.y + 128, 15, bulletSprite);

        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
    }
}