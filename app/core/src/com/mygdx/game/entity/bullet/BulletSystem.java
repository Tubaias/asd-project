package com.mygdx.game.entity.bullet;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.stream.Collectors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utility.EntityStore;


public class BulletSystem {
    private ArrayList<Bullet> bullets;
    private ArrayDeque<StarBullet> starBulletPool;
    private ArrayDeque<BasicBullet> basicBulletPool;
    private EntityStore store;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public BulletSystem(EntityStore store) {
        bullets = new ArrayList<>();
        starBulletPool = new ArrayDeque<>();
        basicBulletPool = new ArrayDeque<>();
        this.store = store;
    }

    public void newBullet(BulletType type, float x, float y, float angle) {
        if (type == BulletType.STAR) {
            if (starBulletPool.isEmpty()) {
                StarBullet b = new StarBullet(x, y, angle);
                store.bullets.add(b);
                bullets.add(b);
            } else {
                StarBullet b = starBulletPool.pollFirst();
                b.refresh(x, y, angle);
                store.bullets.add(b);
                bullets.add(b);
            }
        } else {
            if (basicBulletPool.isEmpty()) {
                BasicBullet b = new BasicBullet(x, y, angle);
                store.bullets.add(b);
                bullets.add(b);
            } else {
                BasicBullet b = basicBulletPool.pollFirst();
                b.refresh(x, y, angle);
                store.bullets.add(b);
                bullets.add(b);
            }
        }
    }

    public void step() {
        for (Bullet b : bullets) {
            b.move();
        }

        cleanup();
    }

    public void big_oof() {
        System.out.println("B: " + basicBulletPool.size() + " S: " + starBulletPool.size());
    }


    public void draw(SpriteBatch batch) {
        for (Bullet b : bullets) {
            b.getSprite().draw(batch);
        }
    }

    private void cleanup() {
        ArrayList<Bullet> alive = new ArrayList<>();
        ArrayList<Bullet> dead = new ArrayList<>();
        for (Bullet b : bullets) {
            if (inPlayField(b)) {
                alive.add(b);
            } else {
                dead.add(b);
            }
        }

        bullets = alive;

        for (Bullet d : dead) {
            if (d instanceof StarBullet) {
                starBulletPool.addFirst((StarBullet) d);
            } else {
                basicBulletPool.addFirst((BasicBullet) d);
            }
        }
    }


    private boolean inPlayField(Bullet b) {
        Vector2 position = b.getPosition();
        return position.x < WIDTH && position.x > 0 && position.y < HEIGHT && position.y > 0;
    }
}