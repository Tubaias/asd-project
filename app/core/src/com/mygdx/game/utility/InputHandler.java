
package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Player;

public class InputHandler {
    private Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public void handlePlayerInputs() {
        playerMovement();

        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            player.shoot();
        }
    }

    private void playerMovement() {
        Vector2 movement = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movement.add(-1, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movement.add(1, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            movement.add(0, 1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            movement.add(0, -1);
        }

        movement.nor();

        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            System.out.println("asd");
            movement.scl(3.5f);
        } else {
            movement.scl(7f);
        }

        player.position.add(movement);
    }
}