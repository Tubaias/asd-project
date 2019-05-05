package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Highscores;
import com.mygdx.game.io.FontDisplayer;

public class HighscoresScreen extends Menu {

    private AsdGame parent;
    private FontDisplayer fontDisplay;
    private SpriteBatch batch;

    public HighscoresScreen(AsdGame game) {
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
        batch.begin();
        fontDisplay.drawMultiline(Highscores.getScores(),300, 700, batch);
        batch.end();
    }

}