
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class LargePlayerBullet extends Bullet {

    public LargePlayerBullet(float x, float y, float angle) {
        this.angle = Math.toRadians(angle);
        this.speed = 30;
        this.acceleration = 0;

        this.texture = new Texture("largeplayerbullet.png");
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        this.sprite.setColor(0.7f, 1f, 0.7f, 0.7f);
        this.position = new Vector2(x - this.sprite.getWidth() / 2, y);
    }

    @Override
    public void move() {
        speed += acceleration;

        position.x += speed * Math.sin(angle);
        position.y += speed * Math.cos(angle);

        sprite.setPosition(position.x, position.y);
    }

    @Override
    public BulletType getType() {
        return BulletType.PLAYERLARGE;
    }
}