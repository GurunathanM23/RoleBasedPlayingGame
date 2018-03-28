package com.role.game.menu.interfaces.impl;

import java.util.Arrays;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.enums.GameExplorationMenu;
import com.role.game.menu.interfaces.GameExploration;
import com.role.game.util.EnumBase;

public class GameExplorationImpl extends EnumBase<GameExplorationMenu> implements GameExploration{

	public GameExplorationImpl(InputReader reader, OutputWriter writter) {
		super(reader, writter, Arrays.asList(GameExplorationMenu.values()));
	}

	
}
