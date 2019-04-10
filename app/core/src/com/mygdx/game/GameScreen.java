package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.RootterTootter;
import com.mygdx.game.level.Map;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.utility.logic.CollisionSystem;
import com.mygdx.game.utility.graphic.Drawer;
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
    Map foregroundMap;
    Map backgroundMap;

    float deltaAccumulator = 0;

    private AsdGame parent;

    public GameScreen(AsdGame game) {
        parent = game;
    }

    @Override
    public void show() {
        width = 600;
        height = 800;

        foregroundMap = new Map(new Texture("images/fg.png"), 5);
        backgroundMap = new Map(new Texture("images/bg.png"), 3);

        player = new Player();
        bulletSystem = new BulletSystem();
        scoring = new ScoringSystem();

        store = new EntityStore(player, bulletSystem, foregroundMap, backgroundMap, scoring);
        collisionSystem = new CollisionSystem(store);
        inputHandler = new InputHandler(player);
        drawer = new Drawer(store);
        player.setStore(store);

        store.enemies.add(new RootterTootter(width / 2, height / 2, store));
    }

    @Override
    public void render(float delta) {
        deltaAccumulator += delta;

        inputHandler.handleSystemKeys();
        inputHandler.handlePlayerInputs();
        moveEntities();

        while (deltaAccumulator > 0.25) {
            addEnemy();
            deltaAccumulator -= 0.25;
        }

        if (!collisionSystem.collision()) {
            parent.changeScreen("over");
        }

        drawer.drawFrame(delta);
    }

    private void moveEntities() {
        backgroundMap.move();
        foregroundMap.move();
        player.move();

        for (Enemy e : store.enemies) {
            e.move();
        }

        for (Bullet b : store.bullets) {
            b.move();
        }

        store.bulletSystem.step();
    }

    private void addEnemy() {
        Random rng = new Random();
        store.enemies.add(new RootterTootter(rng.nextInt((int) width), height - 20, store));
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