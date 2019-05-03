
package com.mygdx.game.level.event;

import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.utility.logic.EnemyFactory;
import com.mygdx.game.utility.EntityStore;

public class EnemySpawnEvent implements LevelEvent {
    private EnemyFactory factory;
    private EntityStore store;
    private float time;

    private EnemyType type;
    private float x;
    private float y;
    private ActionScript script;

    public EnemySpawnEvent(EnemyType type, float time, float x, float y, EntityStore store, EnemyFactory factory, ActionScript script) {
        this.store = store;
        this.factory = factory;
        this.time = time;

        this.type = type;
        this.x = x;
        this.y = y;
        this.script = script;
    }

    @Override
    public void execute() {
        store.enemies.add(factory.createEnemy(type, x, y, script.cpy()));
    }

    @Override
    public boolean isTime(float currentTime) {
        return currentTime >= time;
    }
}