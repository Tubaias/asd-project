package com.mygdx.game;

import java.io.IOException;

import com.mygdx.game.io.FileIO;

public class Highscores {

    public static String[] getScores() {
        try {
            return FileIO.fileToArray("highscores.txt");
        } catch (IOException e) {
            return new String[]{"-"};
        }
    }
}