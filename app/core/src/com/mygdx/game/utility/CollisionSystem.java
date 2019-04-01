package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.enemy.Enemy;

public class CollisionSystem {
    EntityStore store;

    public CollisionSystem(EntityStore store) {
        this.store = store;
    }

    public void collision() {
        Player player = store.player;

        for (Enemy e : store.enemies) {
            Vector2 enemyC = getCenter(e.getSprite());
            Vector2 playerC = getCenter(player.getSprite());
            if (enemyC.dst(playerC) < 20) {
                System.out.println("OOF");
            }

            for (Bullet b : store.bulletSystem.getBullets()) {
                Vector2 bulletC = getCenter(b.getSprite());
                if (enemyC.dst(bulletC) < 16 + e.getSprite().getWidth() / 2) {
                    e.hit();
                    b.setDead(true);
                }
            }
        }
    }

    private Vector2 getCenter(Sprite s) {
        float x = s.getX() + s.getWidth() / 2;
        float y = s.getY() + s.getHeight() / 2;
        return new Vector2(x, y);
    }

}