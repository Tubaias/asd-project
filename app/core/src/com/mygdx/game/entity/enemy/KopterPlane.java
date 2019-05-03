
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Hitbox;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.utility.EntityStore;
import com.mygdx.game.utility.graphic.Animator;

public class KopterPlane extends Enemy {
    private Vector2 position;
    private Sprite sprite;
    private Animator animation;
    private ActionScript script;
    private int hitpoints = 1500;
    private float moveAccumulator;
    private float shootAccumulator;
    private float missileAccumulator;

    private EntityStore store;
    private boolean dead = false;

    private int burst;
    private int interval;
    private int deadFrames;

    public KopterPlane(float x, float y, Texture texture, EntityStore store, ActionScript script) {
        this.store = store;
        this.position = new Vector2(x, y);

        animation = new Animator(texture, 4);
        this.sprite = new Sprite(animation.getFrame());
        this.sprite.setPosition(x, y);

        this.hitbox = new Hitbox(x + sprite.getWidth() / 2, y + sprite.getHeight() / 2, 170, 70);
        this.script = script;
    }

    @Override
    public void step() {
        if (dead) {
            if (deadFrames < 11) {
                deadFrames++;
            } else {
                this.position = new Vector2(-1000,-1000);
            }

            return;
        }

        moveAccumulator += Gdx.graphics.getDeltaTime();
        shootAccumulator += Gdx.graphics.getDeltaTime();
        missileAccumulator += Gdx.graphics.getDeltaTime();

        while (moveAccumulator > 0.0167) {
            script.step(this);
            moveAccumulator -= 0.0167;
        }

        hitbox.setPosition(position.x + sprite.getWidth() / 2, position.y + sprite.getHeight() / 2);
        this.isHit = false;

        handleShooting();
    }

    private void handleShooting() {
        while (missileAccumulator > 2.5) {
            shootMissiles();
            missileAccumulator -= 2.5;
        }

        if (burst > 0 && interval == 2) {
            shoot();
            burst--;
        } else {
            while (shootAccumulator > 1) {
                shoot();
                shootAccumulator -= 1;
            }
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
        isHit = true;
        hitpoints -= 1;

        if (!dead && hitpoints <= 0) {
            die();
        }
    }

    private void die() {
        dead = true;
        hitbox.setPosition(-1000, -1000);
        animation = new Animator(new Texture("images/effects/explosion256.png"), 11);
        store.scoring.increase(10000);
        store.screenShake.startShake(8, 0.3f);
    }

    @Override
    public void disappear() {
        dead = true;
        deadFrames = 11;
        position = new Vector2(-1000,-1000);
        hitbox.setPosition(-1000, -1000);
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