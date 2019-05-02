
package com.mygdx.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Level {
    void step();
    void draw(SpriteBatch batch);
}