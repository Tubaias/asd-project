
package com.mygdx.game.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.entity.enemy.script.MoveCommand;
import com.mygdx.game.level.event.EnemySpawnEvent;
import com.mygdx.game.level.event.LevelEvent;
import com.mygdx.game.utility.logic.EnemyFactory;
import com.mygdx.game.utility.logic.EntityStore;

public class Level1 implements Level {
    private EntityStore store;
    private EnemyFactory factory;
    private Background background;
    private Background foreground;

    private float deltaTimer;
    private ArrayList<LevelEvent> events;
    private int eventIndex;

    public Level1() {
        this.background = new Background(new Texture("images/desert-bg.png"), 3);
        this.foreground = new Background(new Texture("images/desert-fg.png"), 4);
    }

    public void setStore(EntityStore store) {
        this.store = store;
        this.factory = new EnemyFactory(store);
    }

    @Override
    public void step() {
        deltaTimer += Gdx.graphics.getDeltaTime();

        background.move();
        foreground.move();

        if (eventIndex >= events.size()) {
            eventIndex = 0;
            deltaTimer = 0;
        }

        while (eventIndex < events.size() && events.get(eventIndex).isTime(deltaTimer)) {
            events.get(eventIndex).execute();
            eventIndex++;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        background.draw(batch);
        foreground.draw(batch);
    }

    public void initEvents() {
        this.events = new ArrayList<>();

        ActionScript script = new ActionScript();
        script.addCommand(new MoveCommand(new Vector2(300, 400), 8));

        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.2f, 0, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.4f, 100, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.6f, 200, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.8f, 300, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.0f, 400, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.2f, 500, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.4f, 400, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.6f, 300, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.8f, 200, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.0f, 100, 800, store, factory, script.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.2f, 0, 800, store, factory, script.cpy()));

        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.2f, 0, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.4f, 100, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.6f, 200, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.8f, 300, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.0f, 400, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.2f, 500, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.4f, 400, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.6f, 300, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.8f, 200, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 5.0f, 100, 800, store, factory, script.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 5.2f, 0, 800, store, factory, script.cpy()));
    }
}