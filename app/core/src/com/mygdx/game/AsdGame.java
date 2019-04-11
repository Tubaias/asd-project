package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.utility.logic.ScoringSystem;

public class AsdGame extends Game {
    private GameScreen game;
    private MenuScreen menu;
    private GameOver gameOver;

    public void changeScreen(String screen, ScoringSystem scoring) {
        switch (screen) {
            case "game":
                if (game == null) {
                    game = new GameScreen(this);
                }
                this.setScreen(game);
                break;
            case "main":
                this.setScreen(menu);
                break;
            case "over":
                this.setScreen(new GameOver(this, scoring));
                break;
        }
    }

    public void changeScreen(String screen) {
        changeScreen(screen, null);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void create() {
        menu = new MenuScreen(this);
        this.setScreen(menu);
    }
}
