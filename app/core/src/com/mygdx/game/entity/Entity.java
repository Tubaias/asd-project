
package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
    Sprite getSprite();
    Vector2 getPosition();
    void setPosition(Vector2 position);
}