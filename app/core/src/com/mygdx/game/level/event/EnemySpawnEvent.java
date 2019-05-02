
package com.mygdx.game.level.event;

import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.utility.logic.EnemyFactory;
import com.mygdx.game.utility.logic.EntityStore;

public class EnemySpawnEvent implements LevelEvent {
    private EnemyFactory factory;
    private EntityStore store;
    private float time;
    private boolean finished;

    private EnemyType type;
    private float x;
    private float y;

    public EnemySpawnEvent(EnemyType type, float time, float x, float y, EntityStore store, EnemyFactory factory) {
        this.store = store;
        this.factory = factory;
        this.time = time;
        this.finished = false;

        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        if (store == null) {
            System.out.println("null store");
        }

        if (store.enemies == null) {
            System.out.println("null enemylist");
        }

        if (factory == null) {
            System.out.println("null factory");
        }

        store.enemies.add(factory.createEnemy(type, x, y));
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