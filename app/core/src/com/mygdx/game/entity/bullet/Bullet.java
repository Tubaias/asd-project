
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;

public abstract class Bullet implements Entity {
    protected Vector2 position;
    protected double speed;
    protected double acceleration;
    protected double angle;
    protected Texture texture;
    protected Sprite sprite;
    protected boolean dead = false;

    public abstract void move();

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    public void setAngle(float value) {
        this.angle = value;
    }

    public void refresh(float x, float y, float angle) {
        this.position.x = x - sprite.getWidth()/2;
        this.position.y = y;
        this.angle = Math.toRadians(angle);
        this.sprite.setOriginCenter();
        this.dead = false;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}