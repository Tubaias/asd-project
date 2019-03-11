
package com.mygdx.game.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map {
    private float y1;
    private float y2;
    private float speed;
    private Texture texture;
    private Sprite sprite1;
    private Sprite sprite2;

    public Map(Texture texture, float speed) {
        this.y1 = 0;
        this.y2 = 1800;
        this.texture = texture;
        this.speed = speed;
        this.sprite1 = new Sprite(texture);
        sprite1.setPosition(0, y1);
        this.sprite2 = new Sprite(texture);
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

      if (y1 <= -1800) {
        y1 = 1800;
      }

      if (y2 <= -1800) {
        y2 = 1800;
      }

      this.sprite1.setPosition(0, y1);
      this.sprite2.setPosition(0, y2);
    }

}