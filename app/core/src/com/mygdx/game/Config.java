package com.mygdx.game;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

public class Config {
    private TomlWriter tomlWriter;
    private File configFile;
    private ConfigObject conf;

    public Config() {
        tomlWriter = new TomlWriter.Builder().indentTablesBy(1).indentValuesBy(2).build();
        conf = new ConfigObject();

        configFile = new File("config.toml");

        createConfig();

        if (!configFile.exists()) {
            try {
                update();
            } catch(Exception e) {
                System.err.println("FAIL: " + e);
            }
        } else {
            Toml toml = new Toml().read(configFile);

            Toml options = toml.getTable("options");
            Toml player1 = toml.getTable("player1");

            for (Entry<String, Object> e : options.entrySet()) {
                conf.options.put(e.getKey(), e.getValue());
            }

            for (Entry<String, Object> e : player1.entrySet()) {
                conf.player1.put(e.getKey(), e.getValue());
            }
        }
    }

    public Object getOption(String name, Object def) {
        return conf.options.getOrDefault(name, conf.player1.getOrDefault(name, def));
    }

    public Integer getInteger(String name, int def) {
        return Math.toIntExact((long) getOption(name, def));
    }


    private void createConfig() {
        conf.options.put("width", 600L);
        conf.options.put("height", 800L);
        conf.options.put("hitboxes", "off");
        conf.options.put("lives", 3);

        conf.player1.put("up", "W");
        conf.player1.put("down", "S");
        conf.player1.put("left", "A");
        conf.player1.put("right", "D");
        conf.player1.put("shoot", "J");
        conf.player1.put("special", "K");
        conf.player1.put("focus", "L-Ctrl");
    }

    public void update(){
        try {
            tomlWriter.write(conf, configFile);
        } catch (Exception e) {
            System.out.println("Config creation failed: " + e.getMessage());
        }
    }

    public void updateKeys(String[] keys) {
        conf.player1.put("up", keys[0]);
        conf.player1.put("down", keys[1]);
        conf.player1.put("left", keys[2]);
        conf.player1.put("right", keys[3]);
        conf.player1.put("shoot", keys[4]);
        conf.player1.put("special", keys[5]);
        conf.player1.put("focus", keys[6]);

        update();
    }

    public void updateHitboxStatus(String status) {
        conf.options.put("hitboxes", status);
        update();
    }

    public void updateLives(Integer lives) {
        conf.options.put("lives", lives);
        update();
    }

    private class ConfigObject {
        Map<String, Object> options = new LinkedHashMap<>();
        Map<String, Object> player1 = new LinkedHashMap<>();
    }
}