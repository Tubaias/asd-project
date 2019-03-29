
package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.utility.EntityStore;

public class Player extends Entity {
    private EntityStore store;
    private Pod podL;
    private Pod podR;

    public Player() {
        this.sprite = new Sprite(new Texture("ship.png"));

        float x = 600 / 2 - sprite.getWidth() / 2;
        float y = 100;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);
        this.podL = new Pod(x-50, y, "left");
        this.podR = new Pod(x+50, y, "right");
    }

    public void setStore(EntityStore store) {
        this.store = store;
    }

    public void move() {
        sprite.setPosition(position.x, position.y);
        podL.move(position.x - 50, position.y);
        podR.move(position.x + 50, position.y);
    }

    public Pod[] getPods() {
        return new Pod[]{podL, podR};
    }

    public void shoot(boolean focused) {
        if (focused) {
            float bulletAngle = 15;
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, 0f);
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, -bulletAngle);
            store.bulletSystem.newBullet(BulletType.STAR, position.x + (32 - 8), position.y + 64, bulletAngle);
        } else {

            store.bulletSystem.newBullet(BulletType.BASIC, position.x + 32, position.y + 64, 0f);
            store.bulletSystem.newBullet(BulletType.BASIC, podL.getPosition().x/2, position.y + 64, 0f);
            store.bulletSystem.newBullet(BulletType.BASIC, podR.getPosition().x/2, position.y + 64, 0f);
        }
    }
}