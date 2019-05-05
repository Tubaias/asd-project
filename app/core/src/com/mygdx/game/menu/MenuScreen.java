package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.utility.Inputs;

public class MenuScreen extends Menu {

    private AsdGame parent;

    public MenuScreen(AsdGame game, Inputs inputs) {
        parent = game;

        

        TextButton button1 = new TextButton("Play", Style.getStyle());
        TextButton button2 = new TextButton("Options", Style.getStyle());
        TextButton highscores = new TextButton("HighScores", Style.getStyle());
        TextButton button3 = new TextButton("Exit", Style.getStyle());

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("game");
            }
        });

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("options");
            }
        });

        highscores.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen("highscores");
            }
        });


        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        this.setupMenu(new TextButton[] { button1, button2, highscores, button3 });
    }

}