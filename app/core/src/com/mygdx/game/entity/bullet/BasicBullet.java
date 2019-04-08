
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BasicBullet extends Bullet {

    public BasicBullet(float x, float y, float angle) {
        this.angle = Math.toRadians(angle);
        this.speed = 7;

        this.texture = new Texture("enemybullet.png");
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
        //this.sprite.setColor(1f, 0.3f, 0.3f, 1f);
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
        return BulletType.BASIC;
    }
}