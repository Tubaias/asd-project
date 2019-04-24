
package com.mygdx.game.utility.graphic;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class ScreenShake {
    private Random rng;
    private Vector2 position;
    private float power;
    private boolean active;

    private float duration;
    private float accumulator;

    public ScreenShake() {
        this.rng = new Random();
        this.position = new Vector2(0, 0);
    }

    public void startShake(float power, float duration) {
        active = true;

        accumulator = 0;
        position.x = 0;
        position.y = 0;

        this.power = power;
        this.duration = duration;
    }

    public Vector2 shake() {
        if (accumulator <= duration) {
            float currentPower = power * ((duration - accumulator) / duration);

            position.x = (rng.nextFloat() - 0.5f) * 2 * currentPower;
            position.y = (rng.nextFloat() - 0.5f) * 2 * currentPower;

            accumulator += Gdx.graphics.getDeltaTime();
        } else {
            active = false;
        }

        return position;
    }

    public boolean isActive() {
        return active;
    }
}