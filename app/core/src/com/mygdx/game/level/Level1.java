
package com.mygdx.game.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.entity.enemy.script.DisappearCommand;
import com.mygdx.game.entity.enemy.script.MoveCommand;
import com.mygdx.game.entity.enemy.script.WaitCommand;
import com.mygdx.game.level.event.EnemySpawnEvent;
import com.mygdx.game.level.event.LevelEvent;
import com.mygdx.game.utility.logic.EnemyFactory;
import com.mygdx.game.utility.EntityStore;

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

        // if (eventIndex >= events.size()) {
        //     eventIndex = 0;
        //     deltaTimer = 0;
        // }

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

        ActionScript tootterScript = new ActionScript();
        tootterScript.addCommand(new MoveCommand(300 - 64, -300, 8));
        tootterScript.addCommand(new DisappearCommand());

        ActionScript kopterScript = new ActionScript();
        kopterScript.addCommand(new MoveCommand(200, 500, 3));
        kopterScript.addCommand(new WaitCommand(5));
        kopterScript.addCommand(new MoveCommand(-300, 500, 7));
        kopterScript.addCommand(new DisappearCommand());

        ActionScript bossScript = new ActionScript();
        bossScript.addCommand(new MoveCommand(300 - 256, 300, 2));
        bossScript.addCommand(new WaitCommand(60));
        kopterScript.addCommand(new DisappearCommand());

        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.2f, 0, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.4f, 100, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.6f, 200, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.8f, 300, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.0f, 400, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.2f, 500, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.4f, 400, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.6f, 300, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 1.8f, 200, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.0f, 100, 800, store, factory, tootterScript.cpy()));
        // events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.2f, 0, 800, store, factory, tootterScript.cpy()));

        // spawnShootterWave(3f, 10, 600, "left");
        // spawnShootterWave(7f, 10, 600, "right");

        // events.add(new EnemySpawnEvent(EnemyType.KOPTER, 9f, 200, 900, store, factory, kopterScript.cpy()));

        // spawnBasicMine(13f, 75);
        // spawnBasicMine(13f, 215);
        // spawnBasicMine(13f, 385);
        // spawnBasicMine(13f, 525);

        events.add(new EnemySpawnEvent(EnemyType.BOSSTOOTTER, 1f, 300 - 256, 800, store, factory, bossScript));

        // level goes here
    }

    private void spawnBasicMine(float time, float x) {
        ActionScript mineScript = new ActionScript();
        mineScript.addCommand(new MoveCommand(x - 64, -200, 3.5f));
        mineScript.addCommand(new DisappearCommand());

        events.add(new EnemySpawnEvent(EnemyType.MINE, time, x - 64, 800, store, factory, mineScript));
    }

    private void spawnShootterWave(float time, int amount, float y, String direction) {
        boolean upDown = true;
        float spawnX;
        float destX;

        if (direction == "left") {
            destX = -200;
            spawnX = 700;
        } else {
            destX = 700;
            spawnX = -200;
        }

        ActionScript scriptUp = new ActionScript();
        scriptUp.addCommand(new MoveCommand(destX, y + 32, 5));
        scriptUp.addCommand(new DisappearCommand());

        ActionScript scriptDown = new ActionScript();
        scriptDown.addCommand(new MoveCommand(destX, y - 32, 5));
        scriptDown.addCommand(new DisappearCommand());

        for (int i = 0; i < amount; i++) {
            float spawnTime = (float) (time + 0.2 * i);

            if (upDown) {
                events.add(new EnemySpawnEvent(EnemyType.SHOOTTER, spawnTime, spawnX, y + 32, store, factory, scriptUp.cpy()));
                upDown = false;
            } else {
                events.add(new EnemySpawnEvent(EnemyType.SHOOTTER, spawnTime, spawnX, y - 32, store, factory, scriptDown.cpy()));
                upDown = true;
            }
        }
    }
}