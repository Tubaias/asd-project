package com.mygdx.game.desktop;

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
        Pattern alpha = Pattern.compile("([a-z]+):\\s*([a-z0-9]+)");
        
        try {
            String[] configs = FileIO.fileToArray("config.txt");
            for (String l : configs) {
                Matcher m1 = numer.matcher(l);
                Matcher m2 = alpha.matcher(l);
                if (m1.matches()) {
                    confs.put(m1.group(1), Integer.valueOf(m1.group(2)));
                } else if (m2.matches()) {
                    System.out.println("Alpha");
                    confs.put(m1.group(0), m1.group(1));
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
                FileIO.createFile("config.txt", new String[]{"width: 600", "height: 800"});
            } catch (Exception e) {
                System.out.println("Config creation failed: " + e.getMessage());
            }
        }
    }
}