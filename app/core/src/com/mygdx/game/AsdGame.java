package com.mygdx.game;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.utility.InputHandler;
import com.mygdx.game.level.Map;

public class AsdGame extends ApplicationAdapter {
	float w;
	float h;

	InputHandler inputHandler;

	SpriteBatch batch;
    Texture playerTexture;
    Texture bulletTexture;
    Sprite playerSprite;
    Map foregroundMap;
    Map backgroundMap;

    Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> playerBullets;

	float deltaAccumulator = 0;

	@Override
	public void create () {
		bullets = new ArrayList<>();
		playerBullets = new ArrayList<>();

		w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
        playerTexture = new Texture("ship.png");
        bulletTexture = new Texture("whitebullet.png");

		playerSprite = new Sprite(playerTexture);

		float spawnX = w / 2 - playerSprite.getWidth() / 2;
		float spawnY = h / 2 - playerSprite.getHeight() / 2;
        player = new Player(spawnX, spawnY, playerSprite, playerBullets);

        foregroundMap = new Map(new Texture("fg.png"), 5);
        backgroundMap = new Map(new Texture("bg.png"), 3);

		inputHandler = new InputHandler(player);
	}

	@Override
	public void render () {
        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
		//System.out.println(1 / Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        inputHandler.handlePlayerInputs();

        backgroundMap.move();

        backgroundMap.getSprite1().draw(batch);
        backgroundMap.getSprite2().draw(batch);

        foregroundMap.move();

        foregroundMap.getSprite1().draw(batch);
        foregroundMap.getSprite2().draw(batch);

        for (Bullet b : bullets) {
			b.move();
			b.getSprite().draw(batch);
		}

		for (Bullet b : playerBullets) {
			b.move();
            b.getSprite().draw(batch);

        }

        cleanup();

        player.move();
		player.sprite.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
    }

    private void cleanup() {
        ArrayList<Bullet> pb = playerBullets.stream().filter(b -> b.getY() < 900).collect(Collectors.toCollection(ArrayList::new));

        playerBullets = pb;
        player.setBullets(pb);
    }
}
