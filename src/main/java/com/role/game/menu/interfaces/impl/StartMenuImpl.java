package com.role.game.menu.interfaces.impl;

import java.util.Arrays;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.enums.MainItemMenu;
import com.role.game.menu.interfaces.StartMenu;
import com.role.game.util.EnumBase;

public class StartMenuImpl extends EnumBase<MainItemMenu> implements StartMenu {

	public StartMenuImpl(InputReader reader, OutputWriter writter) {
		super(reader, writter, Arrays.asList(MainItemMenu.values()));
	}

}
