package com.mygdx.game;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
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

        playerTexture = new Texture("ship.png");

		float centerW = windowW / 2;

		ArrayList<Bullet> bullets = new ArrayList<>();
		ArrayList<Bullet> playerBullets = new ArrayList<>();
		player = new Player(resolutionScale);
		foregroundMap = new Map(resolutionScale, centerW, new Texture("fg.png"), 5);
		backgroundMap = new Map(resolutionScale, centerW, new Texture("bg.png"), 3);

		store = new EntityStore(player, bullets, playerBullets, foregroundMap, backgroundMap);
		player.setStore(store);

		inputHandler = new InputHandler(player, resolutionScale);
		drawer = new Drawer(store);
	}

	@Override
	public void render () {
        float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;

        inputHandler.handlePlayerInputs();

        backgroundMap.move();
		foregroundMap.move();
		player.move();

		cleanup();

		drawer.drawFrame();
	}

	@Override
	public void dispose () {
		drawer.dispose();
		playerTexture.dispose();
    }

    private void cleanup() {
        ArrayList<Bullet> pb = store.playerBullets.stream().filter(b -> b.getPosition().y < playFieldH).collect(Collectors.toCollection(ArrayList::new));

        store.playerBullets = pb;
    }
}
