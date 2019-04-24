
package com.mygdx.game.utility.logic;

import java.util.ArrayList;

import com.mygdx.game.entity.Player;
import com.mygdx.game.utility.graphic.ScreenShake;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Map;

public class EntityStore {
    public ArrayList<Enemy> enemies;
    public BulletSystem bulletSystem;
    public Player player;
    public Map foregroundMap;
    public Map backgroundMap;
    public ScoringSystem scoring;
    public ScreenShake screenShake;

    public EntityStore(Player player, BulletSystem bulletSystem, Map fgMap, Map bgMap, ScoringSystem scoring, ScreenShake screenShake) {
        this.player = player;
        this.bulletSystem = bulletSystem;
        this.enemies = new ArrayList<>();
        foregroundMap = fgMap;
        backgroundMap = bgMap;
        this.scoring = scoring;
        this.screenShake = screenShake;
    }
}