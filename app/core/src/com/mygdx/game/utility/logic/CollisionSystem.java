package com.mygdx.game.utility.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.utility.EntityStore;

public class CollisionSystem {
    EntityStore store;

    public CollisionSystem(EntityStore store) {
        this.store = store;
    }

    public boolean collision() {
        Player player = store.player;
        Vector2 playerC = getCenter(player.getSprite());

        for (Enemy e : store.enemies) {
            if (e.collide(player)) {
                if (!player.die()) {
                    return false;
                }
            }

            for (Bullet b : store.bulletSystem.getBullets()) {
                if ((b.getType() == BulletType.PLAYER || b.getType() == BulletType.PLAYERLARGE) && e.collide(b)) {
                    e.hit(1);
                    b.setDead(true);
                }
            }
        }

        for (Bullet b : store.bulletSystem.getBullets()) {
            Vector2 bulletC = getCenter(b.getSprite());
            BulletType type = b.getType();

            if (type == BulletType.BASIC || type == BulletType.MISSILE) {
                if (playerC.dst(bulletC) < 12) {
                    if (!player.die() ) {
                        return false;
                    }
                }
            } else if (type == BulletType.ANGLED) {
                if (playerC.dst(bulletC) < 8) {
                    if (!player.die()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private Vector2 getCenter(Sprite s) {
        float x = s.getX() + s.getWidth() / 2;
        float y = s.getY() + s.getHeight() / 2;
        return new Vector2(x, y);
    }

}