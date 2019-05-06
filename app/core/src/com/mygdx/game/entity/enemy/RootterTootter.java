
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Hitbox;
import com.mygdx.game.utility.EntityStore;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.utility.graphic.Animator;

public class RootterTootter extends Enemy {
    private Vector2 position;
    private Sprite sprite;
    private Animator animation;
    private ActionScript script;
    private int hitpoints = 30;
    private float moveAccumulator;
    private EntityStore store;
    private boolean dead = false;

    private int deadFrames;

    public RootterTootter(float x, float y, Texture texture, EntityStore store, ActionScript script) {
        this.store = store;
        this.position = new Vector2(x, y);

        animation = new Animator(texture, 3);
        this.sprite = new Sprite(animation.getFrame());
        this.sprite.setPosition(x, y);

        this.hitbox = new Hitbox(x + sprite.getWidth() / 2, y + sprite.getHeight() / 2, 45, 100);
        this.script = script;
    }

    @Override
    public void step() {
        if (dead) {
            if (deadFrames < 11) {
                deadFrames++;
            } else {
                this.position = new Vector2(-1000,-1000);
            }

            return;
        }

        moveAccumulator += Gdx.graphics.getDeltaTime();

        hitbox.setPosition(position.x + sprite.getWidth() / 2, position.y + sprite.getHeight() / 2 + 25);

        while (moveAccumulator > 0.0167) {
            script.step(this);
            moveAccumulator -= 0.0167;
        }

        this.isHit = false;
    }

    @Override
    public void hit(int damage) {
        this.isHit = true;
        this.hitpoints -= damage;

        if (!dead && hitpoints <= 0) {
            die();
        }
    }

    private void die() {
        dead = true;
        hitbox.setPosition(-1000, -1000);
        animation = new Animator(new Texture("images/effects/explosion128.png"), 11);
        store.scoring.increase(1000);
    }

    @Override
    public void disappear() {
        dead = true;
        position = new Vector2(-1000,-1000);
        hitbox.setPosition(-1000, -1000);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public TextureRegion getFrame(float accumulator) {
        return animation.getFrame();
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