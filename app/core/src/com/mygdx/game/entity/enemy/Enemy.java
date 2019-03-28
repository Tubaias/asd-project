
package com.mygdx.game.entity.enemy;

import com.mygdx.game.entity.Entity;

public interface Enemy extends Entity {
    void move();
    void hit();
}