
package com.mygdx.game.utility.logic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.bullet.AngledBullet;
import com.mygdx.game.entity.bullet.BasicBullet;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.entity.bullet.BulletType;
import com.mygdx.game.entity.bullet.LargePlayerBullet;
import com.mygdx.game.entity.bullet.Missile;
import com.mygdx.game.entity.bullet.PlayerBullet;
import com.mygdx.game.entity.bullet.StarBullet;

public class BulletFactory {
    private EntityStore store;

    private Texture basicBulletTexture;
    private Texture playerBulletTexture;
    private Texture largePlayerBulletTexture;
    private Texture angledBulletTexture;
    private Texture missileTexture;

    public BulletFactory(EntityStore store) {
        this.store = store;

        basicBulletTexture = new Texture("images/bullets/enemyroundbullet.png");
        playerBulletTexture = new Texture("images/bullets/playerbullet.png");
        largePlayerBulletTexture = new Texture("images/bullets/largeplayerbullet.png");
        angledBulletTexture = new Texture("images/bullets/enemybullet.png");
        missileTexture = new Texture("images/jank-arrow.png");
    }

    public Bullet createBullet(BulletType type, float x, float y, float angle) {
        switch (type) {
            case BASIC:
                return new BasicBullet(x, y, angle, basicBulletTexture);
            case PLAYER:
                return new PlayerBullet(x, y, angle, playerBulletTexture);
            case PLAYERLARGE:
                return new LargePlayerBullet(x, y, angle, largePlayerBulletTexture);
            case STAR:
                return new StarBullet(x, y, angle);
            case ANGLED:
                return new AngledBullet(x, y, angle, angledBulletTexture);
            case MISSILE:
                return new Missile(x, y, angle, missileTexture, store);
            default:
                return null;
        }
    }
}