package com.mygdx.game.utility.logic;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.Smoke;

public class SmokeMachine {
    private ArrayList<Smoke> pool = new ArrayList<>();
    private ArrayList<Smoke> smokes = new ArrayList<>();
    private Texture smokeTexture = new Texture("images/enemies/skull.png");

    public ArrayList<Smoke> getSmokes() {
        return smokes;
    }

    public void createSmoke(float x, float y) {
        if (pool.isEmpty()) {
            smokes.add(new Smoke(smokeTexture, new Vector2(x, y)));
        } else {
            Smoke s = pool.remove(pool.size() - 1);
            s.set(x, y);
            smokes.add(s);
        }
    }

    public void cull() {
        ArrayList<Smoke> alive = new ArrayList<>();
        ArrayList<Smoke> dead = new ArrayList<>();

        for (Smoke s : smokes) {
            if (s.getFade() > 0) {
                alive.add(s);
            } else {
                dead.add(s);
            }
        }

        smokes = alive;

        for (Smoke d : dead) {
            pool.add(d);
        }
    }
}

