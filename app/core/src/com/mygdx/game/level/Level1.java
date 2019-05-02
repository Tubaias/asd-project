
package com.mygdx.game.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Level1(EntityStore store) {
        this.store = store;
        this.factory = new EnemyFactory(store);

        this.background = new Background(new Texture("images/desert-bg.png"), 3);
        this.foreground = new Background(new Texture("images/desert-fg.png"), 4);

        this.events = new ArrayList<>();
        initializeEvents();
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

    private void initializeEvents() {

    }
}