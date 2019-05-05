
package com.mygdx.game.utility;

import java.util.ArrayList;

import com.mygdx.game.AsdGame;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.level.Level;
import com.mygdx.game.utility.graphic.ScreenShake;
import com.mygdx.game.utility.logic.BulletSystem;
import com.mygdx.game.utility.logic.ScoringSystem;
import com.mygdx.game.utility.logic.SmokeMachine;

public class EntityStore {
    public ArrayList<Enemy> enemies;
    public BulletSystem bulletSystem;
    public Player player;
    public Level level;
    public ScoringSystem scoring;
    public ScreenShake screenShake;
    public SmokeMachine smokes;
    public AsdGame game;

    public EntityStore(Player player, BulletSystem bulletSystem, Level level, ScoringSystem scoring, ScreenShake screenShake, AsdGame game) {
        this.player = player;
        this.bulletSystem = bulletSystem;
        this.enemies = new ArrayList<>();
        this.level = level;
        this.scoring = scoring;
        this.screenShake = screenShake;
        this.smokes = new SmokeMachine();
        this.game = game;
    }
}