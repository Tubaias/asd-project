package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.KopterPlane;
import com.mygdx.game.entity.enemy.RootterTootter;
import com.mygdx.game.level.Level;
import com.mygdx.game.level.Level1;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.utility.logic.CollisionSystem;
import com.mygdx.game.utility.graphic.Drawer;
import com.mygdx.game.utility.graphic.ScreenShake;
import com.mygdx.game.utility.logic.EntityStore;
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

        store = new EntityStore(player, bulletSystem, level, scoring, screenShake);
        collisionSystem = new CollisionSystem(store);
        inputHandler = new InputHandler(player);
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

        // while (tootterDeltaAccumulator > 0.25) {
        //     addTootter();
        //     tootterDeltaAccumulator -= 0.25;
        // }

        // while (planeDeltaAccumulator > 3) {
        //     addPlane();
        //     planeDeltaAccumulator -= 3;
        // }

        if (!collisionSystem.collision()) {
            parent.changeScreen("over", store.scoring);
        }

        drawer.drawFrame(delta);
    }

    private void moveEntities() {
        store.level.step();
        player.move();

        for (Enemy e : store.enemies) {
            e.move();
        }

        store.bulletSystem.step();
    }

    private void addTootter() {
        Random rng = new Random();
        store.enemies.add(new RootterTootter(rng.nextInt((int) width) - 64, height - 20, new Texture("images/enemies/helikipotel.png"), store));
    }

    private void addPlane() {
        Random rng = new Random();
        store.enemies.add(new KopterPlane(rng.nextInt((int) width) - 128, height - 20, new Texture("images/enemies/bigplane.png"), store));
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