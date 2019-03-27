
package com.mygdx.game.entity.bullet;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;

public interface Bullet extends Entity {
    void move();
    Sprite getSprite();
    void setAngle(float value);
    void refresh(float x, float y, float angle);
    Vector2 getPosition();
    void setPosition(Vector2 pos);
}