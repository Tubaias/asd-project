package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.TestEnemy;
import com.mygdx.game.utility.Drawer;
import com.mygdx.game.utility.EntityStore;
import com.mygdx.game.utility.InputHandler;
import com.mygdx.game.level.Map;

public class AsdGame extends ApplicationAdapter {
	float targetW;
	float targetH;
	float windowW;
	float windowH;
	float playFieldW;
	float playFieldH;
	float resolutionScale;

	EntityStore store;
	Drawer drawer;
	InputHandler inputHandler;

    Texture playerTexture;
	Sprite playerSprite;
	Player player;

	Map foregroundMap;
	Map backgroundMap;

	float deltaAccumulator = 0;

	@Override
	public void create () {
		targetW = 600;
		targetH = 800;

		windowW = Gdx.graphics.getWidth();
        windowH = Gdx.graphics.getHeight();
		playFieldH = windowH;
		playFieldW = windowH / 4 * 3;
		resolutionScale = windowH / targetH;

		float centerX = windowW / 2;
		foregroundMap = new Map(resolutionScale, centerX, new Texture("fg.png"), 5);
		backgroundMap = new Map(resolutionScale, centerX, new Texture("bg.png"), 3);

		player = new Player(resolutionScale);
		store = new EntityStore(player, foregroundMap, backgroundMap);
		inputHandler = new InputHandler(player, resolutionScale);
		drawer = new Drawer(store);
		player.setStore(store);
	}

	@Override
	public void render () {
        float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;

		inputHandler.handleSystemKeys();
        inputHandler.handlePlayerInputs();

        backgroundMap.move();
		foregroundMap.move();
		player.move();

		for (Enemy e : store.enemies) {
			e.move();
		}

		for (Bullet b : store.bullets) {
			b.move();
		}

		for (Bullet b : store.playerBullets) {
			b.move();
        }

		cleanup();

		while(deltaAccumulator > 0.25) {
			addEnemy();
			deltaAccumulator -= 0.25;
		}

		drawer.drawFrame();
	}

	private void addEnemy() {
		Random rng = new Random();
		store.enemies.add(new TestEnemy(rng.nextInt((int) playFieldW), playFieldH - 20 * resolutionScale, resolutionScale));
	}

	@Override
	public void dispose () {
		drawer.dispose();
    }

    private void cleanup() {
        ArrayList<Bullet> pb = store.playerBullets.stream().filter(b -> b.getPosition().y < playFieldH).collect(Collectors.toCollection(ArrayList::new));

        store.playerBullets = pb;
    }
}
