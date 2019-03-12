
package com.mygdx.game.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
    private float y1;
    private float y2;
    private float spawnX;
    private float speed;
    private float height;
    private Sprite sprite1;
    private Sprite sprite2;

    public Map(float scale, float center, Texture texture, float speed) {
        this.height = texture.getHeight() * scale;
        this.y1 = 0;
        this.y2 = this.height;
        this.speed = Math.round(speed * scale);

        spawnX = center - texture.getWidth() * scale / 2;
        this.sprite1 = new Sprite(texture);
        this.sprite2 = new Sprite(texture);

        //sprite1.setScale(scale);
        //sprite2.setScale(scale);

        sprite1.setSize(sprite1.getWidth() * scale, sprite1.getHeight() * scale);
        sprite2.setSize(sprite2.getWidth() * scale, sprite2.getHeight() * scale);

        sprite1.setPosition(spawnX, y1);
        sprite2.setPosition(spawnX, y2);

        System.out.println(height + "");
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

        this.sprite1.setPosition(spawnX, y1);
        this.sprite2.setPosition(spawnX, y2);
    }

}