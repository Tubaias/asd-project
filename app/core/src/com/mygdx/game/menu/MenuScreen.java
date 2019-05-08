package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.utility.Inputs;

public class MenuScreen extends Menu {
    private AsdGame parent;

    public MenuScreen(AsdGame game, Inputs inputs) {
        parent = game;

        TextButton playButton = new TextButton("Play", Style.getStyle());
        TextButton optionsButton = new TextButton("Options", Style.getStyle());
        TextButton highscoresButton = new TextButton("HighScores", Style.getStyle());
        TextButton exitButton = new TextButton("Exit", Style.getStyle());

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("game");
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("options");
            }
        });

        highscoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("highscores");
            }
        });


        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        this.setupMenu(new TextButton[] { playButton, optionsButton, highscoresButton, exitButton });
    }
}