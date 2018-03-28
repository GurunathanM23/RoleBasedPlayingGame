package com.role.game.menu.interfaces.impl;

import java.util.Arrays;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.enums.BattleMenu;
import com.role.game.menu.interfaces.Battle;
import com.role.game.util.EnumBase;

public class BattleImpl extends EnumBase<BattleMenu> implements Battle{

	public BattleImpl(InputReader reader, OutputWriter writter) {
		super(reader, writter, Arrays.asList(BattleMenu.values()));
	}

}
