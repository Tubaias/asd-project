
package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Config;
import com.mygdx.game.entity.Player;

public class InputHandler {
    private Player player;
    private Inputs inputs;

    public InputHandler(Player player) {
        this.player = player;
        this.inputs = new Inputs(new Config());
    }

    public void handleSystemKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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

        if (Gdx.input.isKeyPressed(inputs.getKey("left"))) {
            movement.add(-1, 0);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("right"))) {
            movement.add(1, 0);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("up"))) {
            movement.add(0, 1);
        }

        if (Gdx.input.isKeyPressed(inputs.getKey("down"))) {
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