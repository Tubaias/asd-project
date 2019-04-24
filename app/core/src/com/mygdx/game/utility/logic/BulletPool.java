package com.mygdx.game.utility.logic;

import java.util.ArrayList;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;

public class BulletPool {
    private ArrayList<Bullet> pool;
    private BulletType type;
    private BulletFactory factory;

    public BulletPool(BulletType type, BulletFactory factory) {
        this.pool = new ArrayList<>();
        this.type = type;
        this.factory = factory;
    }

    public void put(Bullet obj) {
        pool.add(obj);
    }

    public Bullet newObj(float x, float y, float angle) {
        if (!pool.isEmpty()) {
            Bullet b =  pool.remove(pool.size() - 1);
            b.refresh(x, y, angle);
            return b;
        }

        return factory.createBullet(type, x, y, angle);
    }

    public BulletType getType() {
        return this.type;
    }

    public int size() {
        return pool.size();
    }
}