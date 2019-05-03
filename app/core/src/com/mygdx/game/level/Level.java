
package com.mygdx.game.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utility.EntityStore;

public interface Level {
    void step();
    void draw(SpriteBatch batch);
    void setStore(EntityStore store);
    void initEvents();
}