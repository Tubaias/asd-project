package com.mygdx.game.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Config;
import com.mygdx.game.utility.Inputs;

public class Options extends Menu {
    private String hitboxStatus;
    private String lives;
    private Config config;

    public Options(AsdGame game, Inputs Inputs) {
        this.config = new Config();

        hitboxStatus = config.getOption("hitboxes", "off").toString();
        lives = config.getOption("lives", 3).toString();

        TextButton controls = new TextButton("Controls", Style.getStyle());
        TextButton hitboxes = new TextButton("Hitboxes: " + hitboxStatus, Style.getStyle());
        TextButton life = new TextButton("Lives: " + lives, Style.getStyle());
        TextButton back = new TextButton("Back", Style.getStyle());

        controls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("controls");
            }
        });

        hitboxes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setHitboxStatus();
                hitboxes.setText("Hitboxes: " + hitboxStatus);
            }
        });

        life.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeLives();
                life.setText("Lives: " + lives);
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen("main");
            }
        });

        this.setupMenu(new TextButton[] { controls, hitboxes, life, back });
    }

    private void setHitboxStatus() {
        hitboxStatus = hitboxStatus.equals("on") ? "off" : "on";
        config.updateHitboxStatus(hitboxStatus);
    }

    private void changeLives() {
        lives = lives.equals("3") ? "999" : "3";
        config.updateLives(Integer.valueOf(lives));
    }
}