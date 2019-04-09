package com.mygdx.game.utility;

import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.mygdx.game.Config;

public class Inputs {
    private HashMap<String, Integer> inputs;

    public Inputs(Config config) {
        this.inputs = new HashMap<>();
        inputs.put("up", translate(config.getOption("up", "0")));
        inputs.put("left", translate(config.getOption("left", "0")));
        inputs.put("down", translate(config.getOption("down", "0")));
        inputs.put("right", translate(config.getOption("right", "0")));
        inputs.put("shoot", translate(config.getOption("shoot", "0")));
        inputs.put("spec", translate(config.getOption("special", "0")));
        inputs.put("focus", translate(config.getOption("focus", "0")));
    }

    public int getKey(String s) {
        return inputs.get(s);
    }

    private int translate(Object s) {
        return Input.Keys.valueOf((String) s);
    }
}