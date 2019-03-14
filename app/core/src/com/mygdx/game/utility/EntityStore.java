
package com.mygdx.game.utility;

import java.util.ArrayList;

import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.bullet.Bullet;
import com.mygdx.game.level.Map;

public class EntityStore {
    public ArrayList<Bullet> bullets;
    public ArrayList<Bullet> playerBullets;
    public Player player;
    public Map foregroundMap;
    public Map backgroundMap;

    public EntityStore(Player player, ArrayList<Bullet> bullets, ArrayList<Bullet> playerBullets, Map fgMap, Map bgMap) {
        this.player = player;
        this.bullets = bullets;
        this.playerBullets = playerBullets;
        foregroundMap = fgMap;
        backgroundMap = bgMap;
    }
}