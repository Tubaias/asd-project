package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class AsdGame extends Game {
    private GameScreen game;
    private MenuScreen menu;
    private GameOver gameOver;



    public void changeScreen(String screen) {
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
                if (gameOver == null) {
                    gameOver = new GameOver(this);
                }
                this.setScreen(gameOver);
                break;
        }
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
