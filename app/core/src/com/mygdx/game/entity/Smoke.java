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
        Random r = new Random();
        float scale = r.nextFloat() / 2 + 0.3f;
        float xOffset = r.nextFloat() * 10 - 5;
        float yOffset = r.nextFloat() * 10 - 5;


        this.sprite.setScale(scale, scale);
        this.position = position;
        this.sprite.setPosition(position.x + xOffset, position.y + yOffset);
    }
    public void Fade() {
        accumulator += Gdx.graphics.getDeltaTime();
        if (accumulator > 0.07) {
            fade -= 0.1;
            this.sprite.setColor(1, 1, 1, fade);
            if (fade <= 0) {
                this.position = new Vector2(-1000, -1000);
                this.sprite.setPosition(-1000, -1000);
            }
            accumulator -= 0.07;
        }
    }
}