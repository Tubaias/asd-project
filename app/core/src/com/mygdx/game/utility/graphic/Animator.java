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

    public Animator(Texture sheet, int rows, int cols, float framerate) {
        TextureRegion[] texture = new TextureRegion[rows * cols];

        TextureRegion[][] split = TextureRegion.split(sheet, sheet.getWidth() / rows, sheet.getHeight() / cols);

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                texture[(i*rows)+j] = split[i][j];
            }
        }

        animation = new Animation<>(1/framerate, texture);
    }

    public Animator(Texture sheet, int amount) {
        this(sheet, amount, 60);
    }

    public TextureRegion getFrame() {
        accumulator += Gdx.graphics.getDeltaTime();
        return animation.getKeyFrame(accumulator, true);
    }

}