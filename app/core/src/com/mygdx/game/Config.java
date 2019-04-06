package com.mygdx.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.io.FileIO;

public class Config {
    private Map<String, Object> confs;

    public Config() {
        confs = new HashMap<>();
        
        try {
            String[] configs = new FileIO().fileToArray("config.txt");
            for (String l : configs) {
                String[] split = l.split(":");
                System.out.println("Toinen " + split[1]);
                try {
                    confs.put(split[0].trim(), Integer.valueOf(split[1].trim()));
                } catch (Exception e) {
                    confs.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object getOption(String name, Object def) {
        return confs.getOrDefault(name, def);
    }
}