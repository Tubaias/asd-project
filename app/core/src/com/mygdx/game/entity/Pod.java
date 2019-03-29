package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Pod extends Entity {
    public Pod(float x, float y, String side) {
        this.position = new Vector2(x, y);
        this.sprite = new Sprite(new Texture("pod" + side + ".png"));
    }

    public void move(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.sprite.setPosition(x, y);
    }
}