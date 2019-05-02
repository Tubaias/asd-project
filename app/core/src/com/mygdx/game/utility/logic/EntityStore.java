
package com.mygdx.game.utility.logic;

import java.util.ArrayList;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.Smoke;
import com.mygdx.game.utility.graphic.ScreenShake;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Background;
import com.mygdx.game.level.Level;

public class EntityStore {
    public ArrayList<Enemy> enemies;
    public BulletSystem bulletSystem;
    public Player player;
    public Level level;
    public ScoringSystem scoring;
    public ScreenShake screenShake;
    public SmokeMachine smokes;

    public EntityStore(Player player, BulletSystem bulletSystem, Level level, ScoringSystem scoring, ScreenShake screenShake) {
        this.player = player;
        this.bulletSystem = bulletSystem;
        this.enemies = new ArrayList<>();
        this.level = level;
        this.scoring = scoring;
        this.screenShake = screenShake;
        this.smokes = new SmokeMachine();
    }
}