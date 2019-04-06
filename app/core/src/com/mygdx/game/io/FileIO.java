package com.mygdx.game.io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;

public class FileIO {

    public String[] fileToArray(String path) {
        try {
            File f = Gdx.files.local(path).file();
            Scanner s = new Scanner(f);
            String r = "";
            while (s.hasNext()) {
                r += s.nextLine() + "\n";
            }
            
            return r.split("\n");
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}