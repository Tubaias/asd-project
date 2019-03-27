package com.mygdx.game.entity.bullet;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.stream.Collectors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BulletSystem {
    private ArrayList<Bullet> bullets;
    private ArrayDeque<StarBullet> starBulletPool;
    private ArrayDeque<BasicBullet> basicBulletPool;
    private float scale;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public BulletSystem(float scale) {
        bullets = new ArrayList<>();
        starBulletPool = new ArrayDeque<>();
        basicBulletPool = new ArrayDeque<>();
        this.scale = scale;
    }

    public void newBullet(BulletType type, float x, float y, float angle) {
        if (type == BulletType.STAR) {
            if (starBulletPool.isEmpty()) {
                StarBullet b = new StarBullet(x, y, scale, angle);
                bullets.add(b);
            } else {
                StarBullet b = starBulletPool.pollFirst();
                b.refresh(x, y, angle);
                bullets.add(b);
            }
        } else {
            if (basicBulletPool.isEmpty()) {
                BasicBullet b = new BasicBullet(x, y, scale, angle);
                bullets.add(b);
            } else {
                BasicBullet b = basicBulletPool.pollFirst();
                b.refresh(x, y, angle);
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
        float scaledH = scale * HEIGHT;
        float scaledW = scale * WIDTH;
        return b.getX() < scaledW && b.getX() > 0 && b.getY() < scaledH && b.getY() > 0;
    }
}