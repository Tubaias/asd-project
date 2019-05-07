
package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config;
import com.mygdx.game.entity.Player;

public class InputHandler {
    private Player player;
    private Inputs inputs;
    private EntityStore store;

    public InputHandler(Player player, EntityStore store) {
        this.player = player;
        this.store = store;
        this.inputs = new Inputs(new Config());
    }

    public void handleSystemKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            store.game.changeScreen("main");
        }
    }

    public void handlePlayerInputs() {
        if (Gdx.input.isKeyPressed(inputs.getKey("focus"))) {
            player.setFocused(true);
        } else {
            player.setFocused(false);
        }

        playerMovement();

        if (Gdx.input.isKeyPressed(inputs.getKey("spec"))) {
            player.special();
        } else if (Gdx.input.isKeyPressed(inputs.getKey("shoot"))) {
            player.shoot();
        }
    }

    private void playerMovement() {
        Vector2 movement = new Vector2(0, 0);
        Vector2 playerPos = player.getPosition();

        if (Gdx.input.isKeyPressed(inputs.getKey("left")) && playerPos.x > -60) {
            movement.add(-1, 0);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("right")) && playerPos.x < 600-68) {
            movement.add(1, 0);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("up")) && playerPos.y < 800-68) {
            movement.add(0, 1);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("down")) && playerPos.y > -60) {
            movement.add(0, -1);
        }

        movement.nor();

        if (Gdx.input.isKeyPressed(inputs.getKey("focus"))) {
            movement.scl(3.5f);
        } else {
            movement.scl(7f);
        }

        player.getPosition().add(movement);
    }
}