
package com.mygdx.game.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Map {
    private float y1;
    private float y2;
    private int speed;
    private float height;
    private Sprite sprite1;
    private Sprite sprite2;

    public Map(Texture texture, int speed) {
        this.height = texture.getHeight();
        this.y1 = 0;
        this.y2 = this.height;
        this.speed = speed;

        this.sprite1 = new Sprite(texture);
        this.sprite2 = new Sprite(texture);

        sprite1.setPosition(0, y1);
        sprite2.setPosition(0, y2);
    }

    public Sprite getSprite1() {
        return this.sprite1;
    }

    public Sprite getSprite2() {
        return this.sprite2;
    }

    public void move() {
        y1 -= speed;
        y2 -= speed;

        if (y1 <= -height) {
            y1 = height;
        }

        if (y2 <= -height) {
            y2 = height;
        }

        this.sprite1.setPosition(0, y1);
        this.sprite2.setPosition(0, y2);
    }

}