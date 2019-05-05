package com.mygdx.game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.utility.Inputs;

public class Options extends Menu {
    public Options(AsdGame game, Inputs Inputs) {
        TextButtonStyle style = Style.getStyle();

        TextButton button1 = new TextButton("Controls", style);
        TextButton button2 = new TextButton("Back", style);

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("controls");
            }
        });

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("main");
            }
        });

        this.setupMenu(new TextButton[] { button1, button2 });
    }
}