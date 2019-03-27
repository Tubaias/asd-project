package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.AsdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 800;
		config.width = 600;
		config.resizable = true;

		int resolutionmode = 1; // for quick resolution swaps while developing
		switch (resolutionmode) {
			case 1:
				config.height = 480;
				config.width = 360;
				break;
			case 2:
				config.height = 720;
				config.width = 1280;
				break;
			case 3:
				config.height = 1080;
				config.width = 1920;
				config.fullscreen = true;
			case 4:
				config.height = 800;
				config.width = 200;
		}

		new LwjglApplication(new AsdGame(), config);
	}
}
