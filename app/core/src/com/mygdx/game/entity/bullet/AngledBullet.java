
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class AngledBullet extends Bullet {

    public AngledBullet(float x, float y, float angle, Texture texture) {
        this.angle = Math.toRadians(angle);
        this.speed = 7;

        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setRotation(-angle);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
    }

    @Override
    public BulletType getType() {
        return BulletType.ANGLED;
    }
}