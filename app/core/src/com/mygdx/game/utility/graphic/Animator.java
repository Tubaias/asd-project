package com.mygdx.game.utility.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    private Animation<TextureRegion> animation;
    private float accumulator;

    public Animator(Texture sheet, int amount, float framerate) {
        TextureRegion[] texture = TextureRegion.split(sheet, sheet.getWidth()/amount, sheet.getHeight())[0];
        animation = new Animation<TextureRegion>(1/framerate, texture);
        accumulator = 0;
    }

    public Animator(Texture sheet, int amount) {
        this(sheet, amount, 60);
    }

    public TextureRegion getFrame() {
        accumulator += Gdx.graphics.getDeltaTime();
        return animation.getKeyFrame(accumulator, true);
    }

}