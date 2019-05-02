
package com.mygdx.game.entity.enemy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Hitbox;

public abstract class Enemy extends Entity {
    protected boolean isHit;
    public Hitbox hitbox;

    public abstract void step();
    public abstract void hit();
    public abstract TextureRegion getFrame(float accumulator);

    public boolean isHit() {
        return isHit;
    }

    public boolean collide(Entity other) {
        Vector2 otherPos = other.getPosition();
        Vector2 adjustedPosition = new Vector2(otherPos.x + other.getSprite().getWidth() / 2, otherPos.y + other.getSprite().getHeight());
        return this.hitbox.collide(adjustedPosition);
    }
}