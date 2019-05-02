
package com.mygdx.game.utility.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.Smoke;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.io.FontDisplayer;
import com.mygdx.game.utility.logic.EntityStore;

public class Drawer implements Disposable {
    private EntityStore store;
    private ScreenShake screenShake;
    private SpriteBatch batch;
    private BitmapFont font;
    private FontDisplayer fontDisplayer;
    private ShaderProgram whiteShader;
    private float deltaAccumulator;
    private float animationAccumulator;
    private int fps;

    private OrthographicCamera camera;
    private Viewport viewport;

    public Drawer(EntityStore store, ScreenShake screenShake) {
        this.store = store;
        this.screenShake = screenShake;

        this.batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        this.fontDisplayer = new FontDisplayer("fonts/vcr_mono.ttf", 42);
        fontDisplayer.setColor(Color.valueOf("f4b342"));

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

        if (screenShake.isActive()) {
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
            camera.translate(screenShake.shake());
        }

        camera.update();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        viewport.update(w, h);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        store.level.draw(batch);

        drawEnemies();

        store.player.getSprite().draw(batch);
        store.player.getPods()[0].getSprite().draw(batch);
        store.player.getPods()[1].getSprite().draw(batch);

        store.bulletSystem.draw(batch);

        for (Smoke s : store.smokes.getSmokes()) {
            s.getSprite().draw(batch);
            s.Fade();
        }

        fontDisplayer.drawFont(Integer.toString(store.scoring.getScore()), 75, 750, batch);

        updateFPS();
        font.draw(batch, "" + fps, 10, 20);
        batch.end();
    }

    private void drawEnemies() {
        for (Enemy e : store.enemies) {

            if (e.isHit()) {
                batch.end();
                whiteShader.begin();
                whiteShader.setUniformi("white", 1);
                whiteShader.end();
                batch.begin();
                batch.draw(e.getFrame(animationAccumulator), e.getPosition().x, e.getPosition().y);
                batch.end();
                whiteShader.begin();
                whiteShader.setUniformi("white", 0);
                whiteShader.end();
                batch.begin();
            } else {
                batch.draw(e.getFrame(animationAccumulator), e.getPosition().x, e.getPosition().y);
            }
        }
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