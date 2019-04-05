package com.mygdx.game.utility;

import java.util.ArrayList;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;

public class BulletPool {
    private ArrayList<Bullet> pool;
    private BulletType type;

    public BulletPool(BulletType type) {
        this.pool = new ArrayList<>();
        this.type = type;
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
        return Bullet.createBullet(type, x, y, angle);
    }

    public Class<?> getType() {
        return this.getClass();
    }

    public int size() {
        return pool.size();
    }
}