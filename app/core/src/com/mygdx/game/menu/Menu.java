package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Config;
import com.mygdx.game.utility.Inputs;

public class Menu implements Screen {
    Stage stage;
    SpriteBatch batch;
    TextButton[] elements;
    OrthographicCamera camera;
    FitViewport viewport;
    Inputs inputs;

    int pointer = 0;
    Boolean bottom = false;

    public Menu() {
        inputs = new Inputs(new Config());
    }

    public void setupMenu(TextButton[] elements) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 800, camera);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 800, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        if (bottom) {
            mainTable.bottom();
            mainTable.pad(50);
        } else {
            mainTable.center();
        }

        this.elements = elements;

        int i = 0;
        for (TextButton t : elements) {
            int temp = i;
            t.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    setPointer(temp);
                }
            });
            mainTable.add(t).fillX().uniformX();
            mainTable.row().pad(10, 0, 10, 0);
            i++;
        }

        stage.addActor(mainTable);
    }

    private void setPointer(int pointer) {
        this.pointer = pointer;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if (Gdx.input.isKeyJustPressed(inputs.getKey("down"))) {
            TextButton t = elements[pointer];
            InputEvent event1 = new InputEvent();
            event1.setType(InputEvent.Type.exit);
            t.fire(event1);
            pointer++;
            if (pointer >= elements.length) {
                pointer = 0;
            };

            t = elements[pointer];

            event1 = new InputEvent();
            event1.setType(InputEvent.Type.enter);
            t.fire(event1);
        }

        if (Gdx.input.isKeyJustPressed(inputs.getKey("up"))) {
            TextButton t = elements[pointer];
            InputEvent event1 = new InputEvent();
            event1.setType(InputEvent.Type.exit);
            t.fire(event1);
            pointer--;
            if (pointer < 0) {
                pointer = elements.length - 1;
            };

            t = elements[pointer];

            event1 = new InputEvent();
            event1.setType(InputEvent.Type.enter);
            t.fire(event1);
        }

        if (Gdx.input.isKeyJustPressed(inputs.getKey("shoot"))) {
            TextButton t = elements[pointer];

            InputEvent event1 = new InputEvent();
            event1.setType(InputEvent.Type.touchDown);
            t.fire(event1);

            InputEvent event2 = new InputEvent();
            event2.setType(InputEvent.Type.touchUp);
            t.fire(event2);
        }

        for (int i = 0; i < elements.length; i++) {
            if (pointer == i) {
                elements[i].getStyle().fontColor = Color.ORANGE;
            } else {
                elements[i].getStyle().fontColor = Color.WHITE;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
        viewport.update(width, height);
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