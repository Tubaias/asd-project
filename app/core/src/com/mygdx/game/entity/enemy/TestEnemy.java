
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class TestEnemy implements Enemy {
    private Vector2 position;
    private Vector2 speed;
    private Sprite sprite;
    private float scale;

    private float angle;

    public TestEnemy(float x, float y, float scale) {
        this.position = new Vector2(x, y);
        this.speed = new Vector2(0, -2);

        this.sprite = new Sprite(new Texture("skull.png"));
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        this.sprite.setPosition(x, y);

        this.scale = scale;
    }

    @Override
    public void move() {
        position.add(speed.scl(scale));
        updateSpeed();
        sprite.setPosition(position.x, position.y);
    }

    private void updateSpeed() {
        speed.set((float) Math.sin(angle) * 3, speed.y - 0.05f);

        angle += 0.1;

        if (angle > 360) {
            angle = 0;
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
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