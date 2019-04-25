
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.logic.EntityStore;

public class Missile extends Bullet {
    private EntityStore store;

    public Missile(float x, float y, float angle, Texture texture, EntityStore store) {
        this.store = store;
        this.angle = angle;
        this.initialSpeed = 1;
        this.speed = 1;

        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setRotation(-angle);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        if (speed < 8) {
            speed++;
        }

        float angleToPlayer = store.player.getPosition().cpy().sub(position).angle(new Vector2(0, 1));
        angle += turnAmount(angleToPlayer);

        normalizeAngle();

        System.out.println("angle: " + angle);
        System.out.println("to player: " + angleToPlayer);

        position.x += speed * Math.sin(Math.toRadians(angle));
        position.y += speed * Math.cos(Math.toRadians(angle));

        sprite.setPosition(position.x, position.y);
        sprite.setRotation((float) -angle);
    }

    private double turnAmount(float angleToPlayer) {
        double opposite = angle - (180 * Math.signum(angle));
        System.out.println("opposite: " + opposite);

        return 4 * Math.signum(angleToPlayer - angle);

        // if (angleToPlayer > angle || angleToPlayer < opposite) {
        //     System.out.println("turning right");
        //     return 5;
        // } else {
        //     System.out.println("turning left");
        //     return -5;
        // }
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