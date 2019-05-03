package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
public class Smoke extends Entity {
    private float accumulator;
    private float fade = 0.7f;

    public Smoke(Texture texture, Vector2 position) {
        this.sprite = new Sprite(texture);
        this.set(position.x, position.y);
    }

    public void Fade() {
        accumulator += Gdx.graphics.getDeltaTime();
        if (accumulator > 0.07) {
            fade -= 0.1;
            this.sprite.setColor(1, 1, 1, fade);
            accumulator -= 0.07;
        }
    }

    public void set(float x, float y) {
        Random r = new Random();

        fade = 0.7f;
        float scale = r.nextFloat() / 2 + 0.3f;
        float xOffset = r.nextFloat() * 10 - 5;
        float yOffset = r.nextFloat() * 10 - 5;
        int rotation = r.nextInt(360) - 180;

        sprite.setScale(scale, scale);
        sprite.setPosition(x + xOffset, y + yOffset);
        sprite.setRotation(rotation);
    }

    public float getFade() {
        return fade;
    }
}