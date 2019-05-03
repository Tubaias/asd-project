
package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.utility.logic.EntityStore;

public class Player extends Entity {
    private EntityStore store;
    private Pod podL;
    private Pod podR;

    private boolean focused;
    private int bulletFlipFlop;

    private int lives = 3;
    private float invulnerabilityTimer = 0;

    public Player() {
        this.sprite = new Sprite(new Texture("images/ship.png"));

        float x = 600 / 2 - sprite.getWidth() / 2;
        float y = 100;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);
        this.podL = new Pod(x + 12, y + 42, "left");
        this.podR = new Pod(x + 84, y + 42, "right");

        this.bulletFlipFlop = 0;
        this.focused = false;
    }

    public void setStore(EntityStore store) {
        this.store = store;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean die() {
        if (invulnerabilityTimer > 0) return true;

        lives--;

        if (lives < 0) {
            return false;
        }

        invulnerabilityTimer = 1.5f;

        float x = 600 / 2 - sprite.getWidth() / 2;
        float y = 100;

        this.sprite.setPosition(x, y);
        this.position = new Vector2(x, y);

        return true;
    }

    public void move() {
        invulnerabilityTimer -= Gdx.graphics.getDeltaTime();

        if (invulnerabilityTimer > 0) {
            this.sprite.setColor(1f, 1f, 1f, 0.5f);
            this.podL.sprite.setColor(1f, 1f, 1f, 0.5f);
            this.podR.sprite.setColor(1f, 1f, 1f, 0.5f);
        } else {
            this.sprite.setColor(1f, 1f, 1f, 1f);
            this.podL.sprite.setColor(1f, 1f, 1f, 1f);
            this.podR.sprite.setColor(1f, 1f, 1f, 1f);
        }

        sprite.setPosition(position.x, position.y);

        if (focused) {
            podL.setDestination(position.x + 28, position.y + 42);
            podR.setDestination(position.x + 68, position.y + 42);
        } else {
            podL.setDestination(position.x + 12, position.y + 42);
            podR.setDestination(position.x + 84, position.y + 42);
        }

        podL.move(focused);
        podR.move(focused);
    }

    public Pod[] getPods() {
        return new Pod[]{podL, podR};
    }

    public void shoot() {
        float podLX = podL.getPosition().x + 16;
        float podRX = podR.getPosition().x + 16;
        float podY = position.y + 48 + bulletFlipFlop;

        float playerX = position.x + 64;
        float playerY = position.y + 80 + bulletFlipFlop;

        float angle1 = 3;
        float angle2 = 6;

        if (focused) {
            angle1 = 1;
            angle2 = 2;
        }

        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, playerX, playerY, -angle1);
        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, playerX, playerY, 0f);
        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, playerX, playerY, angle1);

        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, podLX, podY, 0f);
        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, podLX, podY, -angle1);
        store.bulletSystem.newBullet(BulletType.PLAYER, podLX, podY, angle1);
        store.bulletSystem.newBullet(BulletType.PLAYER, podLX, podY, -angle2);

        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, podRX, podY, 0f);
        store.bulletSystem.newBullet(BulletType.PLAYERLARGE, podRX, podY, angle1);
        store.bulletSystem.newBullet(BulletType.PLAYER, podRX, podY, -angle1);
        store.bulletSystem.newBullet(BulletType.PLAYER, podRX, podY, angle2);

        if (bulletFlipFlop == 0) {
            bulletFlipFlop = -16;
        } else {
            bulletFlipFlop = 0;
        }
    }

    public void special() {
        float playerY = position.y + 80;

        store.bulletSystem.newBullet(BulletType.STAR, position.x + (64 - 8), playerY, 0f);
        store.bulletSystem.newBullet(BulletType.STAR, position.x + (64 - 8), playerY, -15);
        store.bulletSystem.newBullet(BulletType.STAR, position.x + (64 - 8), playerY, 15);
    }

    public int getLives() {
        return lives;
    }
}