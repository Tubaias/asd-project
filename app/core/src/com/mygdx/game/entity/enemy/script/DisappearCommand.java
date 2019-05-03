
package com.mygdx.game.entity.enemy.script;

import com.mygdx.game.entity.enemy.Enemy;

public class DisappearCommand implements ActionCommand {
    private boolean finished;

    public DisappearCommand() {
        this.finished = false;
    }

    @Override
    public void step(Enemy enemy) {
        enemy.disappear();
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public ActionCommand cpy() {
        return new DisappearCommand();
    }

}