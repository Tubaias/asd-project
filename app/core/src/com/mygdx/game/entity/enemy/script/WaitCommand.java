
package com.mygdx.game.entity.enemy.script;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.enemy.Enemy;

public class WaitCommand implements ActionCommand {
    private boolean finished;
    private float targetTime;
    private float timer;

    public WaitCommand(float targetTime) {
        this.finished = false;
        this.targetTime = targetTime;
    }

    @Override
    public void step(Enemy enemy) {
        timer += Gdx.graphics.getDeltaTime();

        if (timer >= targetTime) {
            finished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public ActionCommand cpy() {
        return new WaitCommand(targetTime);
    }
}