package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Config;
import com.mygdx.game.utility.Inputs;

public class Menu implements Screen {
    Stage stage;

    Vector2 marker;
    SpriteBatch batch;
    int pointer = 0;
    TextButton[] elements;

    OrthographicCamera camera;
    FitViewport viewport;

    TextButtonStyle style;

    Inputs inputs;

    public Menu() {
        inputs = new Inputs(new Config());
    }

    public void setupMenu(TextButton[] elements) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 800, camera);

        marker = new Vector2(100, 100);
        batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        FitViewport viewport = new FitViewport(600, 800, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        // Create Table
        Table mainTable = new Table();
        // Set table to fill stage
        mainTable.setFillParent(true);
        // Set alignment of contents in the table.
        mainTable.center();

        this.elements = elements;

        int i = 0;
        for (TextButton t : elements) {
            int temp = i;
            System.out.println(temp);
            t.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    marker.y = t.getY();
                    marker.x = t.getX() - 50;
                    setPointer(temp);
                }

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Hello");
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
        batch.begin();
        batch.draw(new Texture("images/bullets/enemybullet.png"), marker.x, marker.y);
        batch.end();
        stage.act();
        stage.draw();
        if (Gdx.input.isKeyJustPressed(inputs.getKey("down"))) {
            pointer++;
            if (pointer >= elements.length) {
                pointer = 0;
            };    
        }

        if (Gdx.input.isKeyJustPressed(inputs.getKey("up"))) {
            pointer--;
            if (pointer < 0) {
                pointer = elements.length - 1;
            };
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

        TextButton t = elements[pointer];
            marker.x = t.getX()- 50;
            marker.y = t.getY();
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