
package com.mygdx.game.entity.enemy.script;

import com.mygdx.game.entity.enemy.Enemy;

public interface ActionCommand {
    public void step(Enemy enemy);
    public boolean isFinished();
    public ActionCommand cpy();
}