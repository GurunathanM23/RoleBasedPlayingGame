package com.role.game.menu.interfaces.impl;

import java.util.Arrays;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.enums.BattleConfirmationMenu;
import com.role.game.menu.interfaces.BattleConfirmation;
import com.role.game.util.EnumBase;

public class BattleConfirmationImpl extends EnumBase<BattleConfirmationMenu> implements BattleConfirmation{

	public BattleConfirmationImpl(InputReader reader, OutputWriter writter) {
		super(reader, writter, Arrays.asList(BattleConfirmationMenu.values()));
	}

}
