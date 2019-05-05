package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.io.FontDisplayer;
import com.mygdx.game.utility.Inputs;
import com.mygdx.game.utility.logic.ScoringSystem;
import com.mygdx.game.AsdGame;

public class GameOver implements Screen {

    private AsdGame parent;
    private SpriteBatch batch;
    private FontDisplayer fontDisplay;
    private char[] name = new char[]{'A', 'A', 'A'};
    private int pointer = 0;
    private ScoringSystem score;
    private long timer;
    private Inputs inputs;

    public GameOver(AsdGame game, ScoringSystem score, Inputs inputs) {
        parent = game;
        batch = new SpriteBatch();
        fontDisplay = new FontDisplayer("fonts/vcr_mono.ttf", 63);
        this.score = score;
        timer = System.currentTimeMillis();
        this.inputs = inputs;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        fontDisplay.drawFont("Game Over", 300, 700, batch);
        fontDisplay.drawFont("Score: " + score.getScore(), 300, 500, batch);
        fontDisplay.drawFont("Press Shoot\nto continue", 300, 100, batch);

        float x = 265;
        float y = 400;
        for (int i = 0; i < 3; i++) {
            if (pointer == i) {
                fontDisplay.setColor(Color.RED);
                fontDisplay.drawFont("" + name[i], x, y, batch);
                fontDisplay.setColor(Color.WHITE);
            } else {
                fontDisplay.drawFont("" + name[i], x, y, batch);
            }

            x += 35;
        }

        if (System.currentTimeMillis() - timer > 500) {
            if (Gdx.input.isKeyJustPressed(inputs.getKey("shoot"))) {
                score.save("" + name[0] + name[1] + name[2]);
                parent.changeScreen("main");
            }

            if (Gdx.input.isKeyJustPressed(inputs.getKey("up"))) {
                name[pointer]++;
                if (name[pointer] > 'Z') {
                    name[pointer] = 'A';
                }
            }

            if (Gdx.input.isKeyJustPressed(inputs.getKey("down"))) {
                name[pointer]--;
                if (name[pointer] < 'A') {
                    name[pointer] = 'Z';
                }
            }

            if (Gdx.input.isKeyJustPressed(inputs.getKey("right"))) {
                pointer++;
                if (pointer > 2) {
                    pointer = 0;
                }
            }

            if (Gdx.input.isKeyJustPressed(inputs.getKey("left"))) {
                pointer--;
                if (pointer < 0) {
                    pointer = 2;
                }
            }
        }

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