
package com.mygdx.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.StarBullet;

public class Player {
    public Vector2 position;
    public Vector2 speed;
    public Sprite sprite;
    private float scale;
    private ArrayList<Bullet> bullets;

    public Player(float scale, ArrayList<Bullet> bulletList) {
        this.sprite = new Sprite(new Texture("ship.png"));
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);

        float x = Gdx.graphics.getWidth() / 2 - sprite.getWidth() * scale / 2;
        float y = Gdx.graphics.getHeight() / 2 - sprite.getHeight() * scale / 2;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, 0);
        this.bullets = bulletList;
        this.scale = scale;
    }

    public void move() {
        position.x += speed.x;
        position.y += speed.y;

        sprite.setPosition(position.x, position.y);
    }

    public void setBullets(ArrayList<Bullet> bullets) {
      this.bullets = bullets;
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

        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
    }
}