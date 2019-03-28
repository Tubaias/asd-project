
package com.mygdx.game.utility;

import java.util.ArrayList;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.utility.BulletSystem;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Map;

public class EntityStore {
    public ArrayList<Bullet> bullets;
    public BulletSystem bulletSystem;
    public ArrayList<Enemy> enemies;
    public Player player;
    public Map foregroundMap;
    public Map backgroundMap;

    public EntityStore(Player player, BulletSystem bulletSystem, Map fgMap, Map bgMap) {
        this.player = player;
        this.bullets = new ArrayList<>();
        this.bulletSystem = bulletSystem;
        this.enemies = new ArrayList<>();
        foregroundMap = fgMap;
        backgroundMap = bgMap;
    }
}