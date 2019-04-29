
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Smoke;
import com.mygdx.game.utility.logic.EntityStore;

public class Missile extends Bullet {
    private EntityStore store;
    private Texture smoke;
    private float smokeAccumulator;

    public Missile(float x, float y, float angle, Texture texture, EntityStore store) {
        this.store = store;
        this.angle = angle;
        this.initialSpeed = -10;
        this.speed = initialSpeed;

        this.texture = texture;
        this.sprite = new Sprite(texture);

        this.smoke = new Texture("images/enemies/skull.png");

        this.sprite.setOriginCenter();
        this.sprite.setRotation(-angle);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        if (speed < 16) {
            speed++;
        }

        float angleToPlayer = store.player.getPosition().cpy().sub(position).angle(new Vector2(0, 1));
        angle += turnAmount(angleToPlayer);
        normalizeAngle();

        emitSmoke();

        position.x += speed * Math.sin(Math.toRadians(angle));
        position.y += speed * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation((float) -angle);
    }

    private void emitSmoke() {
        smokeAccumulator += Gdx.graphics.getDeltaTime();
        if (smokeAccumulator > 0.3) {
            Smoke smk = new Smoke(smoke, position);
            store.misc.add(smk);
        }
    }

    private double turnAmount(float angleToPlayer) {
        double turnAmount = 1;
        double a = Math.signum(angleToPlayer);
        double b = Math.signum(angle);

        if (a > 0 && b < 0) {
            return -1* turnAmount;
        } else if (a < 0 && b > 0){
            return turnAmount;
        }

        return turnAmount * Math.signum(angleToPlayer - angle);
    }

    private void normalizeAngle() {
        while (angle > 180) {
            angle -= 360;
        }

        while (angle < -180) {
            angle += 360;
        }
    }

    @Override
    public BulletType getType() {
        return BulletType.MISSILE;
    }
}