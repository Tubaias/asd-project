package com.mygdx.game.utility;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.bullet.StarBullet;

public class BulletSystem {
    private ArrayList<Bullet> bullets;
    private ArrayDeque<StarBullet> starBulletPool;
    private ArrayDeque<BasicBullet> basicBulletPool;
    private ArrayDeque<BasicBullet> playerBulletPool;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public BulletSystem() {
        bullets = new ArrayList<>();
        starBulletPool = new ArrayDeque<>();
        basicBulletPool = new ArrayDeque<>();
        playerBulletPool = new ArrayDeque<>();
    }

    public void newBullet(BulletType type, float x, float y, float angle) {
        switch(type) {
            case PLAYER:

            case STAR:
                newStar(x, y, angle);
            case BASIC:
                newBasic(x, y, angle);
            default:
                return;
        }
    }

    private void newStar(float x, float y, float angle) {
        if (starBulletPool.isEmpty()) {
            StarBullet b = new StarBullet(x, y, angle);
            bullets.add(b);
        } else {
            StarBullet b = starBulletPool.pollFirst();
            b.refresh(x, y, angle);
            bullets.add(b);
        }
    }

    private void newBasic(float x, float y, float angle) {
        if (basicBulletPool.isEmpty()) {
            BasicBullet b = new BasicBullet(x, y, angle);
            bullets.add(b);
        } else {
            BasicBullet b = basicBulletPool.pollFirst();
            b.refresh(x, y, angle);
            bullets.add(b);
        }
    }

    public void step() {
        for (Bullet b : bullets) {
            b.move();
        }

        cleanup();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
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
            if (inPlayField(b) && !b.isDead()) {
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