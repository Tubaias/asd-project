
package com.mygdx.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private float y1;
    private float y2;
    private int speed;
    private float height;
    private Sprite sprite1;
    private Sprite sprite2;

    public Background(Texture texture, int speed) {
        this.height = texture.getHeight();
        this.y1 = 0;
        this.y2 = this.height;
        this.speed = speed;

        this.sprite1 = new Sprite(texture);
        this.sprite2 = new Sprite(texture);

        this.sprite1.setColor(0.7f, 0.7f, 0.7f, 1f);
        this.sprite2.setColor(0.7f, 0.7f, 0.7f, 1f);

        sprite1.setPosition(-100, y1);
        sprite2.setPosition(-100, y2);
    }

    public Sprite getSprite1() {
        return this.sprite1;
    }

    public Sprite getSprite2() {
        return this.sprite2;
    }

    public void move() {
        double speedTimesDelta = speed * (Gdx.graphics.getDeltaTime() / (1f / 60f));

        y1 -= speedTimesDelta;
        y2 -= speedTimesDelta;

        if (y1 <= -height) {
            y1 += 2 * height;
        }

        if (y2 <= -height) {
            y2 +=  2 * height;
        }

        this.sprite1.setPosition(-100, y1);
        this.sprite2.setPosition(-100, y2);
    }

    public void draw(SpriteBatch batch) {
        sprite1.draw(batch);
        sprite2.draw(batch);
    }
}