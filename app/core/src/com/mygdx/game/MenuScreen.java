package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.io.FontDisplayer;

public class MenuScreen implements Screen {

    private AsdGame parent;
    private SpriteBatch batch;
    private FontDisplayer bigFont;
    private FontDisplayer smallFont;

    public MenuScreen(AsdGame game) {
        parent = game;
        this.batch = new SpriteBatch();
        bigFont = new FontDisplayer("fonts/vcr_mono.ttf", 63);
        smallFont = new FontDisplayer("fonts/vcr_mono.ttf", 42);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            parent.changeScreen("game");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bigFont.drawFont("Press J\nto Start", 300, 400, batch);
        String highscore = Highscores.getScores()[0];
        smallFont.drawMultiline(new String[]{"Highscores:", highscore}, 300, 100, batch);
        batch.end();
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
        batch.dispose();
    }

}