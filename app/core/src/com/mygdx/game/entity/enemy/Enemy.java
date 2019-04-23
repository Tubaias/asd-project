
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entity.Entity;

public abstract class Enemy extends Entity {
    protected boolean isHit;

    public abstract void move();
    public abstract void hit();
    public abstract TextureRegion getFrame(float accumulator);

    public boolean isHit() {
        return isHit;
    }
}