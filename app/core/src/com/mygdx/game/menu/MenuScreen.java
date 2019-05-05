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

        TextButtonStyle style = Style.getStyle();

        TextButton button1 = new TextButton("Play", style);
        TextButton button2 = new TextButton("Options", style);
        TextButton button3 = new TextButton("Exit", style);

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

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        this.setupMenu(new TextButton[] { button1, button2, button3 });
    }

}