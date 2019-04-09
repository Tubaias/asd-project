
package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.RootterTootter;
import com.mygdx.game.io.FontDisplayer;

public class Drawer implements Disposable {
    private EntityStore store;
    private SpriteBatch batch;
    private BitmapFont font;
    private float deltaAccumulator;
    private float animationAccumulator;
    private int fps;
    private FontDisplayer fontDisplayer;

    private ShaderProgram whiteShader;

    OrthographicCamera camera;
    Viewport viewport;

    public Drawer(EntityStore store) {
        this.store = store;
        this.batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        this.fontDisplayer = new FontDisplayer("fonts/vcr_mono.ttf", 21);

        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 800, camera);
        viewport.apply();
        animationAccumulator = 0f;

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        whiteShader = new ShaderProgram(Gdx.files.internal("shaders/whiteshader.vs"), Gdx.files.internal("shaders/whiteshader.fs"));
        batch.setShader(whiteShader);
    }

    public void drawFrame(float delta) {
        deltaAccumulator += delta;
        animationAccumulator += delta;

        camera.update();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        viewport.update(w, h);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(new Texture("ship.png"), 100, 100);

        store.backgroundMap.getSprite1().draw(batch);
        store.backgroundMap.getSprite2().draw(batch);

        store.foregroundMap.getSprite1().draw(batch);
        store.foregroundMap.getSprite2().draw(batch);

        for (Enemy e : store.enemies) {
            RootterTootter r = (RootterTootter) e;

            if (r.isHit()) {
                //System.out.println("Bang");
                batch.end();
                whiteShader.begin();
                whiteShader.setUniformi("white", 1);
                whiteShader.end();
                batch.begin();
                batch.draw(r.getFrame(animationAccumulator), r.getPosition().x, r.getPosition().y);
                batch.end();
                whiteShader.begin();
                whiteShader.setUniformi("white", 0);
                whiteShader.end();
                batch.begin();
            } else {
                batch.draw(r.getFrame(animationAccumulator), r.getPosition().x, r.getPosition().y);
            }
        }

        for (Bullet b : store.bullets) {
            b.getSprite().draw(batch);
        }

        store.bulletSystem.draw(batch);

        store.player.getSprite().draw(batch);
        store.player.getPods()[0].getSprite().draw(batch);
        store.player.getPods()[1].getSprite().draw(batch);

        fontDisplayer.drawFont(Integer.toString(store.scoring.getScore()), 100, 600, batch);

        updateFPS();
        font.draw(batch, "" + fps, 10, 20);
        batch.end();
    }

    private void updateFPS() {
        while (deltaAccumulator > 0.2) {
            fps = (int) (1 / Gdx.graphics.getDeltaTime());
            deltaAccumulator -= 0.2;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}