package com.role.game.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class GameLauncherTest {

	// StartGame.txt contains the sequence of input stream actions that will be
	// given by the user when he starts a new game.
	@Test
	public void lauchNewGame() throws IOException {
		String fileName = "src/test/resources/StartGame.txt";
		InputStream is = new FileInputStream(new File(fileName));
		System.setIn(is);
		GameLauncher.launchGame();
		is.close();

	}

}
