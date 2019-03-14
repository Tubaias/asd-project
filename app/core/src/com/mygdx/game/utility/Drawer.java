
package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;

public class Drawer implements Disposable {
    private EntityStore store;
    private SpriteBatch batch;
    private BitmapFont font;
    private float deltaAccumulator;
    private int fps;

    public Drawer(EntityStore store) {
        this.store = store;
        this.batch = new SpriteBatch();
        font = new BitmapFont();
		font.setColor(Color.RED);
    }

    public void drawFrame() {
        float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        store.backgroundMap.getSprite1().draw(batch);
        store.backgroundMap.getSprite2().draw(batch);

        store.foregroundMap.getSprite1().draw(batch);
        store.foregroundMap.getSprite2().draw(batch);

        for (Enemy e : store.enemies) {
            e.getSprite().draw(batch);
        }

        for (Bullet b : store.bullets) {
			b.getSprite().draw(batch);
		}

		for (Bullet b : store.playerBullets) {
            b.getSprite().draw(batch);
        }

        store.player.getSprite().draw(batch);

        while (deltaAccumulator > 0.2) {
			fps = (int) (1 / Gdx.graphics.getDeltaTime());
			deltaAccumulator -= 0.2;
		}

		font.draw(batch, "" + fps, 10, 20);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}