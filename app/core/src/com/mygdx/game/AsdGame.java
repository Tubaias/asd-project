package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.TestEnemy;
import com.mygdx.game.utility.CollisionSystem;
import com.mygdx.game.utility.Drawer;
import com.mygdx.game.utility.EntityStore;
import com.mygdx.game.utility.InputHandler;
import com.mygdx.game.level.Map;
import com.mygdx.game.utility.BulletSystem;

public class AsdGame extends ApplicationAdapter {
	float width;
	float height;

	EntityStore store;
	Drawer drawer;
	InputHandler inputHandler;
	BulletSystem bulletSystem;
	CollisionSystem collisionSystem;

	Player player;
	Map foregroundMap;
	Map backgroundMap;

	float deltaAccumulator = 0;

	@Override
	public void create () {
		width = 600;
		height = 800;

		foregroundMap = new Map(new Texture("fg.png"), 5);
		backgroundMap = new Map(new Texture("bg.png"), 3);

		player = new Player();
		bulletSystem = new BulletSystem();
		store = new EntityStore(player, bulletSystem, foregroundMap, backgroundMap);
		collisionSystem = new CollisionSystem(store);
		inputHandler = new InputHandler(player);
		drawer = new Drawer(store);
		System.out.println(Gdx.graphics.getWidth());
		player.setStore(store);
	}

	@Override
	public void render () {
    float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;

		inputHandler.handleSystemKeys();
    inputHandler.handlePlayerInputs();
		moveEntities();

		while(deltaAccumulator > 0.25) {
			addEnemy();
			deltaAccumulator -= 0.25;
		}

		collisionSystem.collision();
		//store.bulletSystem.big_oof();

		drawer.drawFrame();
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
		store.enemies.add(new TestEnemy(rng.nextInt((int) width), height - 20));
	}

/*
 	private void cleanup() {
    ArrayList<Bullet> pb = store.playerBullets.stream().filter(b -> b.getPosition().y < height).collect(Collectors.toCollection(ArrayList::new));
    store.playerBullets = pb;
	}
*/

	@Override
	public void dispose () {
		drawer.dispose();
    }
}
