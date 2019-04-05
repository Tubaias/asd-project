package com.mygdx.game.utility;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.bullet.LargePlayerBullet;
import com.mygdx.game.entity.bullet.PlayerBullet;
import com.mygdx.game.entity.bullet.StarBullet;

public class BulletSystem {
    private ArrayList<Bullet> bullets;
    private BulletPool starBulletPool;
    private BulletPool basicBulletPool;
    private BulletPool playerBulletPool;
    private BulletPool largePlayerBulletPool;

    private HashMap<BulletType, BulletPool> pools;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public BulletSystem() {
        bullets = new ArrayList<>();
        starBulletPool = new BulletPool(BulletType.STAR);
        playerBulletPool = new BulletPool(BulletType.PLAYER);
        largePlayerBulletPool = new BulletPool(BulletType.PLAYERLARGE);
        basicBulletPool = new BulletPool(BulletType.BASIC);

        pools = new HashMap<>();
        pools.put(BulletType.PLAYER, playerBulletPool);
        pools.put(BulletType.PLAYERLARGE, largePlayerBulletPool);
        pools.put(BulletType.BASIC, basicBulletPool);
        pools.put(BulletType.STAR, starBulletPool);
    }

    public void newBullet(BulletType type, float x, float y, float angle) {
        switch(type) {
            case BASIC:
                newBasic(x, y, angle);
                break;
            case STAR:
                newStar(x, y, angle);
                break;
            case PLAYER:
                newPlayer(x, y, angle);
                break;
            case PLAYERLARGE:
                newLargePlayer(x, y, angle);
                break;
            default:
                return;
        }
    }

    private void newBasic(float x, float y, float angle) {
        bullets.add(basicBulletPool.newObj(x, y, angle));
    }

    private void newStar(float x, float y, float angle) {
        bullets.add(starBulletPool.newObj(x, y, angle));
    }

    private void newPlayer(float x, float y, float angle) {
        bullets.add(playerBulletPool.newObj(x, y, angle));
    }

    private void newLargePlayer(float x, float y, float angle) {
        bullets.add(largePlayerBulletPool.newObj(x, y, angle));
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
        System.out.println("bullets list size: " + bullets.size());
        System.out.println("Pool zise: " + playerBulletPool.size());

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
            pools.get(d.getType()).put(d);
        }
    }

    private boolean inPlayField(Bullet b) {
        Vector2 position = b.getPosition();
        return position.x < WIDTH && position.x > 0 && position.y < HEIGHT && position.y > 0;
    }
}