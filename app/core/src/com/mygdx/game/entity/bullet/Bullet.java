
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Bullet {
    void move();
    Sprite getSprite();
    float getY();
}