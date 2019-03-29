
package com.mygdx.game.entity.enemy;

import com.mygdx.game.entity.Entity;

public abstract class Enemy extends Entity {
    public abstract void move();
    public abstract void hit();
}