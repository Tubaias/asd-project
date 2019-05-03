package com.mygdx.game.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {

    public static String[] fileToArray(String path) throws IOException {
        File f;
        try {
            f = new File(path);
        } catch (Exception e) {
            throw new IOException("Could not find file " + path);
        }
        Scanner s = new Scanner(f);
        String r = "";
        while (s.hasNext()) {
            r += s.nextLine() + "\n";
        }
        s.close();
        return r.split("\n");
    }

    public static void createFile(String path, String[] lines) throws IOException {
        PrintWriter pw = new PrintWriter(path);
        for (String l : lines) {
            pw.println(l);
        }
        pw.close();
    }
}