package com.mygdx.game.menu;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Highscores;
import com.mygdx.game.io.FontDisplayer;

public class HighscoresScreen extends Menu {

    private AsdGame parent;
    private FontDisplayer fontDisplay;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;

    public HighscoresScreen(AsdGame game) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 800, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        parent = game;

        fontDisplay = new FontDisplayer("fonts/vcr_mono.ttf", 42);
        batch = new SpriteBatch();

        TextButton button3 = new TextButton("Back", Style.getStyle());

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("main");
            }
        });

        this.bottom = true;

        this.setupMenu(new TextButton[] { button3 });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        camera.update();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        viewport.update(w, h);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        String[] highscores = Highscores.getScores();

        int max = highscores.length > 15 ? 15 : highscores.length;
        fontDisplay.drawMultiline(Arrays.copyOfRange(highscores, 0, max),300, 700, batch);
        batch.end();
    }

}