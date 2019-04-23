
package com.mygdx.game.utility.logic;

import java.util.ArrayList;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Map;

public class EntityStore {
    public ArrayList<Bullet> bullets;
    public ArrayList<Enemy> enemies;

    public BulletSystem bulletSystem;
    public Player player;
    public Map foregroundMap;
    public Map backgroundMap;
    public ScoringSystem scoring;

    public EntityStore(Player player, BulletSystem bulletSystem, Map fgMap, Map bgMap, ScoringSystem scoring) {
        this.player = player;
        this.bullets = new ArrayList<>();
        this.bulletSystem = bulletSystem;
        this.enemies = new ArrayList<>();
        foregroundMap = fgMap;
        backgroundMap = bgMap;
        this.scoring = scoring;
    }
}