
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RootterTootter extends Enemy {
    private Vector2 position;
    private Vector2 speed;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Texture rootorSheet;
    private int hitpoints = 10000;

    private float sinewaveAngle;

    public RootterTootter(float x, float y) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        this.sprite = new Sprite(new Texture("helikipotel.png"));
        this.sprite.setPosition(x, y);

        rootorSheet = new Texture("helikipotel.png");
        TextureRegion[] texture = TextureRegion.split(rootorSheet, rootorSheet.getWidth()/3, rootorSheet.getHeight())[0];
        animation = new Animation<TextureRegion>(0.0167f, texture);

        this.sprite = new Sprite(texture[0]);
        this.sprite.setPosition(x, y);
    }

    @Override
    public void move() {
        position.add(speed);
        updateSpeed();
        sprite.setPosition(position.x, position.y);
        sprite.setColor(Color.WHITE);
    }

    @Override
    public void hit() {
        sprite.setColor(1f, 0.3f, 0.3f, 1f);

        this.hitpoints -= 100;
        if (hitpoints <= 0) {
            this.position = new Vector2(-1000,-1000);
        }
    }

    private void updateSpeed() {
        speed.set((float) Math.sin(sinewaveAngle) * 3, speed.y - 0.05f);

        sinewaveAngle += 0.1;

        if (sinewaveAngle > 360) {
            sinewaveAngle = 0;
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    public TextureRegion getFrame(float accumulator) {
        return animation.getKeyFrame(accumulator, true);
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}