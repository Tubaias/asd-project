
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.utility.EntityStore;

public class RootterTootter extends Enemy {
    private Vector2 position;
    private Vector2 speed;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Texture rootorSheet;
    private int hitpoints = 10000;
    private boolean isHit;
    private float sinewaveAngle;
    private float deltaAccumulator;
    private EntityStore store;

    public RootterTootter(float x, float y, EntityStore store) {
        this.store = store;
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        this.sprite = new Sprite(new Texture("helikipotel.png"));
        this.sprite.setPosition(x, y);

        rootorSheet = new Texture("helikipotel.png");
        TextureRegion[] texture = TextureRegion.split(rootorSheet, rootorSheet.getWidth()/3, rootorSheet.getHeight())[0];
        animation = new Animation<TextureRegion>(0.0167f, texture);

        this.sprite = new Sprite(texture[0]);
        this.sprite.setPosition(x, y);
        this.isHit = false;
    }

    @Override
    public void move() {
        deltaAccumulator += Gdx.graphics.getDeltaTime();

        position.add(speed);
        updateSpeed();
        sprite.setPosition(position.x, position.y);
        this.isHit = false;

        if (deltaAccumulator > 0.7) {
            shoot();
            deltaAccumulator -= 0.7;
        }
    }

    private void shoot() {
        int angle = (int) store.player.getPosition().cpy().sub(position).angle(new Vector2(0, 1));
        store.bulletSystem.newBullet(BulletType.BASIC, position.x + 64, position.y + 64 - 16, angle);
    }

    @Override
    public void hit() {
        this.isHit = true;
        this.hitpoints -= 100;

        if (hitpoints <= 0) {
            this.position = new Vector2(-1000,-1000);
        }
    }

    private void updateSpeed() {
        speed.set((float) Math.sin(sinewaveAngle) * 3, speed.y - 0.05f);

        sinewaveAngle += 0.1;

        if (sinewaveAngle > 360) {
            sinewaveAngle = 0;
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    public TextureRegion getFrame(float accumulator) {
        return animation.getKeyFrame(accumulator, true);
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isHit() {
        return isHit;
    }
}