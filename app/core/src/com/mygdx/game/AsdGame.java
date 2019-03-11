package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsdGame extends ApplicationAdapter {
	float w;
	float h;

	SpriteBatch batch;
    Texture playerTexture;
    Texture bulletTexture;
	Sprite playerSprite;
	ArrayList<Sprite> sprites;

	@Override
	public void create () {
		w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
        playerTexture = new Texture("ship.png");
        bulletTexture = new Texture("bullet.png");

		playerSprite = new Sprite(playerTexture);
		playerSprite.setPosition(w / 2 - playerSprite.getWidth() / 2, h / 2 - playerSprite.getHeight() / 2);

		sprites = new ArrayList<>();
	}

	@Override
	public void render () {
		System.out.println(1 / Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		handleInputs();

		for (Sprite s : sprites) {
            s.draw(batch);
        }

		playerSprite.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
	}

	private void handleInputs() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                playerSprite.translateX(-3f);
            } else {
                playerSprite.translateX(-7.0f);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                playerSprite.translateX(3f);
            } else {
                playerSprite.translateX(7.0f);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                playerSprite.translateY(3f);
            } else {
                playerSprite.translateY(7.0f);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                playerSprite.translateY(-3f);
            } else {
                playerSprite.translateY(-7.0f);
            }
        }
    }
}
