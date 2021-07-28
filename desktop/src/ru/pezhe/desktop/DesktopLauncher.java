package ru.pezhe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.pezhe.SpaceQuest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Quest";
		config.height = 640;
		config.width = 480;
		new LwjglApplication(new SpaceQuest(), config);
	}
}
