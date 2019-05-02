
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.utility.logic.EntityStore;
import com.mygdx.game.utility.graphic.Animator;

public class KopterPlane extends Enemy {
    private Vector2 position;
    private Vector2 speed;
    private Sprite sprite;
    private Animator animation;
    private int hitpoints = 1000;
    private float deltaAccumulator;
    private float missileAccumulator;

    private EntityStore store;
    private boolean dead = false;

    private int burst;
    private int interval;
    private int deadFrames;


    public KopterPlane(float x, float y, Texture texture, EntityStore store) {
        this.store = store;
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        animation = new Animator(texture, 4);
        this.sprite = new Sprite(animation.getFrame());
        this.sprite.setPosition(x, y);
    }

    @Override
    public void move() {
        if (dead) {
            if (deadFrames < 11) {
                deadFrames++;
            } else {
                this.position = new Vector2(-1000,-1000);
            }
        }

        deltaAccumulator += Gdx.graphics.getDeltaTime();
        missileAccumulator += Gdx.graphics.getDeltaTime();

        position.add(speed);
        sprite.setPosition(position.x, position.y);
        this.isHit = false;

        if (missileAccumulator > 1.5) {
            shootMissiles();
            missileAccumulator -= 1.5;
        }

        if (burst > 0 && interval == 2) {
            shoot();
            burst--;
        } else if (deltaAccumulator > 1) {
            shoot();
            deltaAccumulator -= 1;
        }

        if (interval > 2) {
            interval = 0;
        } else {
            interval++;
        }
    }

    private void shoot() {
        if (dead) {
            return;
        }

        if (burst == 0) {
            burst = 4;
        }

        float bulletX = position.x + 128;
        float bulletY = position.y + 96;

        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX, bulletY, 180);
        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX + 8, bulletY, 150);
        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX - 8, bulletY, 210);
    }

    private void shootMissiles() {
        float missileX = position.x + 128;
        float missileY = position.y + 110;

        store.bulletSystem.newBullet(BulletType.MISSILE, missileX - 16, missileY, 170);
        store.bulletSystem.newBullet(BulletType.MISSILE, missileX + 16, missileY, -170);
        store.bulletSystem.newBullet(BulletType.MISSILE, missileX + 50, missileY, -160);
        store.bulletSystem.newBullet(BulletType.MISSILE, missileX - 50, missileY, 160);
    }

    @Override
    public void hit() {
        this.isHit = true;
        this.hitpoints -= 1;

        if (!dead && hitpoints <= 0) {
            die();
        }
    }

    private void die() {
        dead = true;
        animation = new Animator(new Texture("images/effects/explosion256.png"), 11);
        store.scoring.increase(10000);
        store.screenShake.startShake(8, 0.3f);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    public TextureRegion getFrame(float accumulator) {
        return animation.getFrame();
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