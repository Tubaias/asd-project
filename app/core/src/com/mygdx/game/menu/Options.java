package com.mygdx.game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Config;
import com.mygdx.game.utility.Inputs;

public class Options extends Menu {
    private String hitboxStatus;
    private Config config;

    public Options(AsdGame game, Inputs Inputs) {
        this.config = new Config();
        hitboxStatus = config.getOption("hitboxes", "off").toString();

        TextButton button1 = new TextButton("Controls", Style.getStyle());
        TextButton button2 = new TextButton("Hitboxes: " + hitboxStatus, Style.getStyle());
        TextButton button3 = new TextButton("Back", Style.getStyle());

        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("controls");
            }
        });

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setHitboxStatus();
                button2.setText("Hitboxes: " + hitboxStatus);
            }
        });

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("main");
            }
        });

        this.setupMenu(new TextButton[] { button1, button2, button3 });
    }

    private void setHitboxStatus() {
        hitboxStatus = hitboxStatus.equals("on") ? "off" : "on";
        config.updateHitboxStatus(hitboxStatus);
    }
}