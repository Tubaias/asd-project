
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
    private int hitpoints = 100000;
    private float deltaAccumulator;
    private EntityStore store;
    private boolean dead = false;

    private int burst;
    private int interval;

    public KopterPlane(float x, float y, EntityStore store) {
        this.store = store;
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        animation = new Animator(new Texture("images/enemies/bigplane.png"), 4);
        this.sprite = new Sprite(animation.getFrame());
        this.sprite.setPosition(x, y);
    }

    @Override
    public void move() {
        deltaAccumulator += Gdx.graphics.getDeltaTime();

        position.add(speed);
        sprite.setPosition(position.x, position.y);
        this.isHit = false;

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
        if (burst == 0) {
            burst = 4;
        }

        float bulletX = position.x + 128;
        float bulletY = position.y + 96;

        //int angle = (int) store.player.getPosition().cpy().sub(position).angle(new Vector2(0, 1));
        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX, bulletY, 180);
        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX + 8, bulletY, 150);
        store.bulletSystem.newBullet(BulletType.ANGLED, bulletX - 8, bulletY, 210);
    }

    @Override
    public void hit() {
        this.isHit = true;
        this.hitpoints -= 100;

        if (hitpoints <= 0) {
            if (!dead) {
                store.scoring.increase(10000);
                this.animation = new Animator(new Texture("images/effects/explosion256.png"), 11);
            }

            this.dead = true;
            //this.position = new Vector2(-1000,-1000);
        }
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