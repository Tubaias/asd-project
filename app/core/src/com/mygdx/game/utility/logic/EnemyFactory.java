
package com.mygdx.game.utility.logic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.enemy.Enemy;
import com.mygdx.game.entity.enemy.EnemyType;
import com.mygdx.game.entity.enemy.KopterPlane;
import com.mygdx.game.entity.enemy.RootterTootter;

public class EnemyFactory {
    private EntityStore store;

    private Texture tootterTexture;
    private Texture kopterTexture;

    public EnemyFactory(EntityStore store) {
        this.store = store;

        tootterTexture = new Texture("images/enemies/helikipotel.png");
        kopterTexture = new Texture("images/enemies/bigplane.png");
    }

    public Enemy createEnemy(EnemyType type, float x, float y) {
        switch (type) {
            case TOOTTER:
                return new RootterTootter(x, y, tootterTexture, store);
            case KOPTER:
                return new KopterPlane(x, y, kopterTexture, store);
            default:
                return null;
        }
    }
}