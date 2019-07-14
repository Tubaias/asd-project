package com.mygdx.game.utility.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SingleUseAnimation {
    private Animator animator;
    private float time;
    private float accumulator;
    private Vector2 position;

    public SingleUseAnimation(Texture texture, int frames, float framerate, float x, float y) {
        this.animator = new Animator(texture, frames, framerate);
        this.time = (float) frames / framerate;
        this.position = new Vector2(x, y);
        this.accumulator = 0;
    }

    public TextureRegion getFrame() {
        this.accumulator += Gdx.graphics.getDeltaTime();
        if (accumulator > time) {
            return null;
        }

        return animator.getFrame();
    }

    public Vector2 getPosition() {
        return this.position;
    }


}