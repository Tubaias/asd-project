package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Level;
import com.mygdx.game.level.Level1;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.utility.logic.CollisionSystem;
import com.mygdx.game.utility.graphic.Drawer;
import com.mygdx.game.utility.graphic.ScreenShake;
import com.mygdx.game.utility.EntityStore;
import com.mygdx.game.utility.InputHandler;
import com.mygdx.game.utility.logic.ScoringSystem;

public class GameScreen implements Screen {
    float width;
    float height;

    EntityStore store;
    Drawer drawer;
    InputHandler inputHandler;
    BulletSystem bulletSystem;
    CollisionSystem collisionSystem;
    ScoringSystem scoring;

    Player player;

    float tootterDeltaAccumulator = 0;
    float planeDeltaAccumulator = 0;

    private AsdGame parent;

    public GameScreen(AsdGame game) {
        parent = game;
    }

    @Override
    public void show() {
        width = 600;
        height = 800;

        Level level = new Level1();

        player = new Player();
        bulletSystem = new BulletSystem();
        scoring = new ScoringSystem();
        ScreenShake screenShake = new ScreenShake();

        store = new EntityStore(player, bulletSystem, level, scoring, screenShake, parent);
        collisionSystem = new CollisionSystem(store);
        inputHandler = new InputHandler(player);
        inputHandler.setStore(store);
        drawer = new Drawer(store, screenShake);

        player.setStore(store);

        level.setStore(store);
        level.initEvents();

        bulletSystem.setStore(store);
        bulletSystem.createPools();
        bulletSystem.initPool();
    }

    @Override
    public void render(float delta) {
        tootterDeltaAccumulator += delta;
        planeDeltaAccumulator += delta;

        inputHandler.handleSystemKeys();
        inputHandler.handlePlayerInputs();
        moveEntities();

        if (!collisionSystem.collision()) {
            parent.changeScreen("over", store.scoring);
        }

        drawer.drawFrame(delta);
    }

    private void moveEntities() {
        store.level.step();
        player.move();

        for (Enemy e : store.enemies) {
            e.step();
        }

        store.bulletSystem.step();
        store.smokes.cull();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        drawer.dispose();
    }

}