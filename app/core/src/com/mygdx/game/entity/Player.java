
package com.mygdx.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.StarBullet;

public class Player {
    public Vector2 position;
    public Vector2 speed;
    public Sprite sprite;
    private ArrayList<Bullet> bullets;

    public Player(float x, float y, Sprite sprite, ArrayList<Bullet> bulletList) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, 0);
        this.sprite = sprite;
        this.sprite.setPosition(x, y);
        this.bullets = bulletList;
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
            bullet1 = new StarBullet(position.x + 32 - 8, position.y + 128, 0);
            bullet2 = new StarBullet(position.x + 32 - 8, position.y + 128, -bulletAngle);
            bullet3 = new StarBullet(position.x + 32 - 8, position.y + 128, bulletAngle);
        } else {
            int bulletAngle = 30;
            bullet1 = new BasicBullet(position.x + 32 - 8, position.y + 128, 0);
            bullet2 = new BasicBullet(position.x + 32 - 8, position.y + 128, -bulletAngle);
            bullet3 = new BasicBullet(position.x + 32 - 8, position.y + 128, bulletAngle);
        }

        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
    }
}