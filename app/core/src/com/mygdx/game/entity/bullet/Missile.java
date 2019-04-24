
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.logic.EntityStore;

public class Missile extends Bullet {
    private EntityStore store;

    public Missile(float x, float y, float angle, Texture texture, EntityStore store) {
        this.store = store;
        this.angle = Math.toRadians(angle);
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

        angle = (int) store.player.getPosition().cpy().sub(position).angle(new Vector2(0, 1));

        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
        sprite.setRotation((float) -angle);
    }

    @Override
    public BulletType getType() {
        return BulletType.MISSILE;
    }
}