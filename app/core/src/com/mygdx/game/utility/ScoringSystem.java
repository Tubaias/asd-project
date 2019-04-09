package com.mygdx.game.utility;

import java.util.ArrayList;
import java.util.Collections;

import com.mygdx.game.io.FileIO;

public class ScoringSystem {
    private int score;

    public void increase(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void save() {
        String[] scores;
        try {
            scores = FileIO.fileToArray("highscores.txt");
        } catch (Exception e) {
            System.out.println("Error opening highscores");
            return;
        }
        ArrayList<Score> newScores = new ArrayList<>();

        for (String s : scores) {
            String[] scr = s.split("-");
            if (scr.length > 1) newScores.add(new Score(scr[0].trim(), Integer.valueOf(scr[1].trim())));
        }

        newScores.add(new Score("player", score));

        String[] newScoreStrings = newScores.stream().sorted().map(s -> s.toString()).toArray(String[]::new);

        try {
            FileIO.createFile("highscores.txt", newScoreStrings);
            System.out.println("WRITTEN");

        } catch (Exception e) {
            System.out.println("Error writing highscores");
            return;
        }
    }

    private class Score implements Comparable {
        String name;
        int score;

        public Score(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(((Score) o).score, this.score);
        }

        @Override
        public String toString() {
            return name + " - " + score;
        }
    }


}