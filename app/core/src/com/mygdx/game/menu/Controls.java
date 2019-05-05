package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Config;
import com.mygdx.game.io.FontDisplayer;

public class Controls implements Screen {
    
    private FontDisplayer fontDisplay;
    private SpriteBatch batch;
    private String[] keys;
    private int counter = 0;
    private String[] assignedKeys;
    private AsdGame parent;
    private Config config;

    public Controls(AsdGame game, Config config) {
        this.fontDisplay = new FontDisplayer("fonts/vcr_mono.ttf", 42);
        this.batch = new SpriteBatch();
        keys = new String[]{"Up", "Down", "Left", "Right", "Shoot", "Special", "Focus"};
        assignedKeys = new String[7];
        parent = game;
        this.config = config;
    }

    private void handle(int code) {
        assignedKeys[counter] = Keys.toString(code);
        counter++;
        if (counter > 6) {
            config.updateKeys(assignedKeys);
            parent.refreshInputs();
            parent.changeScreen("options");
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean keyDown (int keycode) {
                handle(keycode);
                return true;
             }
         });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        fontDisplay.drawFont("Press a button for:", 300, 500, batch);
        fontDisplay.drawFont(keys[counter], 300, 400, batch);
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

    }
}