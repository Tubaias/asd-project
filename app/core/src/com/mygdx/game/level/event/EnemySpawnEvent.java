
package com.mygdx.game.level.event;

import com.mygdx.game.entity.enemy.EnemyType;

public class EnemySpawnEvent implements LevelEvent {
    private float time;
    private boolean finished;

    public EnemySpawnEvent(EnemyType type, float time) {
        this.time = time;
        this.finished = false;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isTime(float currentTime) {
        return currentTime >= time;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}