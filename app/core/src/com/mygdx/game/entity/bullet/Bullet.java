
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.Entity;

public abstract class Bullet extends Entity {
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
        this.angle = Math.toRadians(angle);
        this.sprite.setOriginCenter();
        this.sprite.setRotation(-angle);
        this.dead = false;
    }

    public static Bullet createBullet(BulletType type, float x, float y, float angle) {
        switch (type) {
            case BASIC:
                return new BasicBullet(x, y, angle, new Texture("images/bullets/enemyarrow.png"));
            case PLAYER:
                return new PlayerBullet(x, y, angle, new Texture("images/bullets/playerbullet.png"));
            case PLAYERLARGE:
                return new LargePlayerBullet(x, y, angle, new Texture("images/bullets/largeplayerbullet.png"));
            case STAR:
                return new StarBullet(x, y, angle);
            default:
                return null;
        }
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}