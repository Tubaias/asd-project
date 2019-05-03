package com.mygdx.game.utility.logic;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.bullet.LargePlayerBullet;
import com.mygdx.game.entity.bullet.PlayerBullet;
import com.mygdx.game.utility.EntityStore;

public class BulletSystem {
    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    private ArrayList<Bullet> bullets;
    private HashMap<BulletType, BulletPool> pools;
    private BulletFactory factory;
    private EntityStore store;

    private BulletPool starBulletPool;
    private BulletPool basicBulletPool;
    private BulletPool angledBulletPool;
    private BulletPool missilePool;
    private BulletPool playerBulletPool;
    private BulletPool largePlayerBulletPool;

    private Texture playerBulletTexture;
    private Texture largePlayerBulletTexture;

    public BulletSystem() {
        bullets = new ArrayList<>();
        playerBulletTexture = new Texture("images/bullets/playerbullet.png");
        largePlayerBulletTexture = new Texture("images/bullets/largeplayerbullet.png");
    }

    public void setStore(EntityStore store) {
        this.store = store;
    }

    public void createPools() {
        factory = new BulletFactory(store);

        starBulletPool = new BulletPool(BulletType.STAR, factory);
        playerBulletPool = new BulletPool(BulletType.PLAYER, factory);
        largePlayerBulletPool = new BulletPool(BulletType.PLAYERLARGE, factory);
        basicBulletPool = new BulletPool(BulletType.BASIC, factory);
        angledBulletPool = new BulletPool(BulletType.ANGLED, factory);
        missilePool = new BulletPool(BulletType.MISSILE, factory);

        pools = new HashMap<>();
        pools.put(BulletType.PLAYER, playerBulletPool);
        pools.put(BulletType.PLAYERLARGE, largePlayerBulletPool);
        pools.put(BulletType.BASIC, basicBulletPool);
        pools.put(BulletType.ANGLED, angledBulletPool);
        pools.put(BulletType.MISSILE, missilePool);
        pools.put(BulletType.STAR, starBulletPool);
    }

    public void initPool() {
        for (int i = 0; i < 100; i++) {
            playerBulletPool.put(new PlayerBullet(0, 0, 0, playerBulletTexture));
            largePlayerBulletPool.put(new LargePlayerBullet(0, 0, 0, largePlayerBulletTexture));
        }
    }

    public void newBullet(BulletType type, float x, float y, float angle) {
        switch(type) {
            case PLAYER:
                bullets.add(playerBulletPool.newObj(x, y, angle));
                break;
            case PLAYERLARGE:
                bullets.add(largePlayerBulletPool.newObj(x, y, angle));
                break;
            case BASIC:
                bullets.add(basicBulletPool.newObj(x, y, angle));
                break;
            case STAR:
                bullets.add(starBulletPool.newObj(x, y, angle));
                break;
            case ANGLED:
                bullets.add(angledBulletPool.newObj(x, y, angle));
                break;
            case MISSILE:
                bullets.add(missilePool.newObj(x, y, angle));
                break;
            default:
                return;
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
        System.out.println("bullets list size: " + bullets.size());
        System.out.println("plaýer pool zise: " + playerBulletPool.size());
        System.out.println("L pool zise: " + largePlayerBulletPool.size());


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
        return position.x < WIDTH+64 && position.x > -64 && position.y < HEIGHT+64 && position.y > -64;
    }
}