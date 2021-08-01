package ru.pezhe;

import com.badlogic.gdx.Game;
import ru.pezhe.screen.MenuScreen;

public class SpaceQuest extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

}
