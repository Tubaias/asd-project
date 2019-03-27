
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Bullet {
    void move();
    Sprite getSprite();
    float getY();
    float getX();
    void setX(float value);
    void setY(float value);
    void setAngle(float value);
    void refresh(float x, float y, float angle);
}