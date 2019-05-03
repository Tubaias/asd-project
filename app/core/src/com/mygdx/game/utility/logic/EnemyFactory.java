
package com.mygdx.game.utility.logic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.enemy.BossTootter;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.entity.enemy.KopterPlane;
import com.mygdx.game.entity.enemy.Mine;
import com.mygdx.game.entity.enemy.RootterTootter;
import com.mygdx.game.entity.enemy.ShootterTootter;
import com.mygdx.game.entity.enemy.script.ActionScript;
import com.mygdx.game.utility.EntityStore;

public class EnemyFactory {
    private EntityStore store;

    private Texture tootterTexture;
    private Texture shootterTexture;
    private Texture kopterTexture;
    private Texture mineTexture;

    public EnemyFactory(EntityStore store) {
        this.store = store;

        tootterTexture = new Texture("images/enemies/nocannontootter.png");
        shootterTexture = new Texture("images/enemies/helikipotel.png");
        kopterTexture = new Texture("images/enemies/bigplane.png");
        mineTexture = new Texture("images/enemies/mine-animated.png");
    }

    public Enemy createEnemy(EnemyType type, float x, float y, ActionScript script) {
        switch (type) {
            case TOOTTER:
                return new RootterTootter(x, y, tootterTexture, store, script);
            case SHOOTTER:
                return new ShootterTootter(x, y, shootterTexture, store, script);
            case KOPTER:
                return new KopterPlane(x, y, kopterTexture, store, script);
            case MINE:
                return new Mine(x, y, mineTexture, store, script);
            case BOSSTOOTTER:
                return new BossTootter(x, y, store, script);
            default:
                return null;
        }
    }
}