package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.utility.Inputs;
import com.mygdx.game.utility.logic.ScoringSystem;
import com.mygdx.game.menu.MenuScreen;
import com.mygdx.game.menu.Options;
import com.mygdx.game.menu.Controls;
import com.mygdx.game.menu.GameOver;
import com.mygdx.game.menu.HighscoresScreen;

public class AsdGame extends Game {
    private GameScreen game;
    private MenuScreen menu;
    private GameOver gameOver;
    private Inputs inputs;
    private Config config;

    public void changeScreen(String screen, ScoringSystem scoring) {
        switch (screen) {
            case "game":
                if (game == null) {
                    game = new GameScreen(this);
                }
                this.setScreen(game);
                break;
            case "main":
                this.setScreen(new MenuScreen(this, inputs));
                break;
            case "over":
                this.setScreen(new GameOver(this, scoring, inputs, false));
                break;
            case "victory":
                this.setScreen(new GameOver(this, scoring, inputs, true));
                break;
            case "options":
                this.setScreen(new Options(this, inputs));
                break;
            case "controls":
                this.setScreen(new Controls(this, config));
                break;
            case "highscores":
                this.setScreen(new HighscoresScreen(this));
                break;
        }
    }

    public void changeScreen(String screen) {
        changeScreen(screen, null);
    }

    public void refreshInputs() {
        inputs = new Inputs(new Config());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void create() {
        config = new Config();
        inputs = new Inputs(config);

        this.setScreen(new MenuScreen(this, inputs));
    }
}
