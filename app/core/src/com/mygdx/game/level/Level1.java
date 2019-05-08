
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

        ActionScript kopterScriptMiddle = new ActionScript();
        kopterScriptMiddle.addCommand(new MoveCommand(300 - 128, 500, 3));
        kopterScriptMiddle.addCommand(new WaitCommand(5));
        kopterScriptMiddle.addCommand(new MoveCommand(-300, 500, 7));
        kopterScriptMiddle.addCommand(new DisappearCommand());

        ActionScript kopterScriptLeft = new ActionScript();
        kopterScriptLeft.addCommand(new MoveCommand(150 - 128, 500, 3));
        kopterScriptLeft.addCommand(new WaitCommand(8));
        kopterScriptLeft.addCommand(new MoveCommand(-300, 500, 7));
        kopterScriptLeft.addCommand(new DisappearCommand());

        ActionScript kopterScriptRight = new ActionScript();
        kopterScriptRight.addCommand(new MoveCommand(450 - 128, 500, 3));
        kopterScriptRight.addCommand(new WaitCommand(8));
        kopterScriptRight.addCommand(new MoveCommand(900, 500, 7));
        kopterScriptRight.addCommand(new DisappearCommand());

        ActionScript bossScript = new ActionScript();
        bossScript.addCommand(new MoveCommand(300 - 256, 300, 2));
        bossScript.addCommand(new WaitCommand(300));

        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 0.5f, 300 - 64, 800, store, factory, tootterScript.cpy()));

        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.2f, 0, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.4f, 100, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.6f, 200, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 2.8f, 300, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.0f, 400, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.2f, 500, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.4f, 400, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.6f, 300, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 3.8f, 200, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.0f, 100, 800, store, factory, tootterScript.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.TOOTTER, 4.2f, 0, 800, store, factory, tootterScript.cpy()));

        spawnShootterWave(4.3f, 10, 600, "left");
        spawnShootterWave(7.3f, 10, 400, "right");

        int popcornCycle = 50;
        for (float t = 0; t < 3; t += 0.1) {
            popcornCycle += 156;

            if (popcornCycle > 550) {
                popcornCycle -= 500;
            }

            spawnBasicEnemy(9 + t, (float) popcornCycle, 8, EnemyType.TOOTTER);
        }

        events.add(new EnemySpawnEvent(EnemyType.KOPTER, 11f, 300 - 128, 900, store, factory, kopterScriptMiddle.cpy()));

        spawnBasicEnemy(16f, 75, 3.5f, EnemyType.MINE);
        spawnBasicEnemy(16f, 215, 3.5f, EnemyType.MINE);
        spawnBasicEnemy(16f, 385, 3.5f, EnemyType.MINE);
        spawnBasicEnemy(16f, 525, 3.5f, EnemyType.MINE);

        spawnShootterWave(17f, 10, 600, "left");

        boolean flipFlop = false;
        for (float t = 0; t < 4.2; t += 0.7) {
            if (flipFlop) {
                spawnBasicEnemy(19 + t, 64, 5, EnemyType.SHOOTTER);
                flipFlop = false;
            } else {
                spawnBasicEnemy(19 + t, 600 - 64, 5, EnemyType.SHOOTTER);
                flipFlop = true;
            }
        }

        popcornCycle = 50;
        for (float t = 0; t < 3; t += 0.1) {
            popcornCycle += 172;

            if (popcornCycle > 550) {
                popcornCycle -= 500;
            }

            spawnBasicEnemy(24 + t, (float) popcornCycle, 8, EnemyType.TOOTTER);
        }

        events.add(new EnemySpawnEvent(EnemyType.KOPTER, 28f, 150 - 128, 900, store, factory, kopterScriptLeft.cpy()));
        events.add(new EnemySpawnEvent(EnemyType.KOPTER, 28f, 450 - 128, 900, store, factory, kopterScriptRight.cpy()));

        spawnMineAndShootters(35f, 75);
        spawnMineAndShootters(36f, 215);
        spawnMineAndShootters(37f, 385);
        spawnMineAndShootters(38f, 525);

        spawnMineAndShootters(39f, 75);
        spawnMineAndShootters(40f, 215);
        spawnMineAndShootters(41f, 385);
        spawnMineAndShootters(42f, 525);

        spawnBasicEnemy(44f, 75, 3, EnemyType.MINE);
        spawnBasicEnemy(44f, 215, 3, EnemyType.MINE);
        spawnBasicEnemy(44f, 385, 3, EnemyType.MINE);
        spawnBasicEnemy(44f, 525, 3, EnemyType.MINE);

        spawnBasicEnemy(44.5f, 75 + 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 75 - 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 215 + 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 215 - 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 385 + 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 385 - 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 525 + 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(44.5f, 525 - 32, 3, EnemyType.SHOOTTER);

        spawnShootterWave(48f, 10, 600, "left");
        spawnShootterWave(49f, 10, 400, "right");
        spawnShootterWave(50f, 10, 200, "left");

        events.add(new EnemySpawnEvent(EnemyType.BOSSTOOTTER, 55f, 300 - 256, 800, store, factory, bossScript));
    }

    private void spawnBasicEnemy(float time, float x, float speed, EnemyType type) {
        ActionScript script = new ActionScript();
        script.addCommand(new MoveCommand(x - 64, -200, speed));
        script.addCommand(new DisappearCommand());

        events.add(new EnemySpawnEvent(type, time, x - 64, 800, store, factory, script));
    }

    private void spawnMineAndShootters(float time, float x) {
        spawnBasicEnemy(time, x, 3, EnemyType.MINE);
        spawnBasicEnemy(time + 0.5f, x + 32, 3, EnemyType.SHOOTTER);
        spawnBasicEnemy(time + 0.5f, x - 32, 3, EnemyType.SHOOTTER);
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