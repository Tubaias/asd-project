package com.mygdx.game;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mygdx.game.io.FileIO;

public class Config {
    private Map<String, Object> confs;

    public Config() {
        createConfig();
        confs = new HashMap<>();
        Pattern numer = Pattern.compile("([a-z]+):\\s*([0-9]+)");
        Pattern alpha = Pattern.compile("([a-z]+):\\s*([\\-a-zA-Z0-9]+)");

        try {
            String[] configs = FileIO.fileToArray("config.txt");
            for (String l : configs) {
                Matcher m1 = numer.matcher(l);
                Matcher m2 = alpha.matcher(l);
                if (m1.matches()) {
                    confs.put(m1.group(1), Integer.valueOf(m1.group(2)));
                } else if (m2.matches()) {
                    confs.put(m2.group(1), m2.group(2));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object getOption(String name, Object def) {
        return confs.getOrDefault(name, def);
    }

    private void createConfig() {
        if (!new File("config.txt").exists()) {
            try {
                FileIO.createFile("config.txt", new String[]{
                    "width: 600",
                    "height: 800",
                    "up: W",
                    "down: S",
                    "left: A",
                    "right: D",
                    "shoot: J",
                    "special: K",
                    "focus: L-Ctrl",
                });
            } catch (Exception e) {
                System.out.println("Config creation failed: " + e.getMessage());
            }
        }
    }

    public void updateKeys(String[] keys) {
        try {
            FileIO.createFile("config.txt", new String[]{
                "width: " + confs.getOrDefault("width", 600),
                "height: " + confs.getOrDefault("height", 800),
                "up: " + keys[0],
                "down: " + keys[1],
                "left: " + keys[2],
                "right: " + keys[3],
                "shoot: " + keys[4],
                "special: " + keys[5],
                "focus: " + keys[6],
            });
        } catch (Exception e) {
            System.out.println("Config creation failed: " + e.getMessage());
        }
    }
}