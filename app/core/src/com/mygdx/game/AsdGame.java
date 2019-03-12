package com.mygdx.game;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
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

	InputHandler inputHandler;

	SpriteBatch batch;
    Texture playerTexture;
    Sprite playerSprite;
    Map foregroundMap;
    Map backgroundMap;

    Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> playerBullets;

	float deltaAccumulator = 0;

	BitmapFont font;
	int fps;

	@Override
	public void create () {
		bullets = new ArrayList<>();
		playerBullets = new ArrayList<>();
		targetW = 600;
		targetH = 800;

		windowW = Gdx.graphics.getWidth();
        windowH = Gdx.graphics.getHeight();
		playFieldH = windowH;
		playFieldW = windowH / 4 * 3;

		resolutionScale = windowH / targetH;

		batch = new SpriteBatch();
        playerTexture = new Texture("ship.png");

		float centerW = windowW / 2;

        player = new Player(resolutionScale, playerBullets);

        foregroundMap = new Map(resolutionScale, centerW, new Texture("fg.png"), 5);
        backgroundMap = new Map(resolutionScale, centerW, new Texture("bg.png"), 3);

		inputHandler = new InputHandler(player, resolutionScale);

		font = new BitmapFont();
		font.setColor(Color.RED);
	}

	@Override
	public void render () {
        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
		//System.out.println(1 / Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 1);
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

		while (deltaAccumulator > 0.2) {
			fps = (int) (1 / Gdx.graphics.getDeltaTime());
			deltaAccumulator -= 0.2;
		}

		font.draw(batch, "" + fps, 10, 20);

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
        ArrayList<Bullet> pb = playerBullets.stream().filter(b -> b.getY() < playFieldH).collect(Collectors.toCollection(ArrayList::new));

        playerBullets = pb;
        player.setBullets(pb);
    }
}
