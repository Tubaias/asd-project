package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.AsdGame;
import com.mygdx.game.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Config conf = new Config();

		config.setWindowedMode(conf.getInteger("width", 600), conf.getInteger("height", 800));
		config.setResizable(true);
		config.useVsync(false);

		new Lwjgl3Application(new AsdGame(), config);
	}
}
