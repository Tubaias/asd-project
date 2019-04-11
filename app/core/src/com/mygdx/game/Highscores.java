package com.mygdx.game;

import java.io.File;
import java.io.IOException;

import com.mygdx.game.io.FileIO;

public class Highscores {

    public static String[] getScores() {
        createHighScores();
        try {
            return FileIO.fileToArray("highscores.txt");
        } catch (IOException e) {
            return new String[]{"-"};
        }
    }

    private static void createHighScores() {
        if (!new File("highscores.txt").exists()) {
            try {
                FileIO.createFile("highscores.txt", new String[]{});
            } catch (Exception e) {
                System.out.println("Config creation failed: " + e.getMessage());
            }
        }
    }
}