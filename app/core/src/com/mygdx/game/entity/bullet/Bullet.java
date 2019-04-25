
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.Entity;

public abstract class Bullet extends Entity {
    protected double initialSpeed;
    protected double speed;
    protected double acceleration;
    protected double angle;
    protected Texture texture;
    protected boolean dead = false;

    public abstract void move();

    public abstract BulletType getType();

    public void setAngle(float value) {
        this.angle = value;
    }

    public void refresh(float x, float y, float angle) {
        this.position.x = x - sprite.getWidth()/2;
        this.position.y = y;
        this.speed = initialSpeed;
        this.angle = angle;
        this.sprite.setOriginCenter();
        this.sprite.setRotation(-angle);
        this.dead = false;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}