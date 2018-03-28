package com.role.game.util;

import java.util.List;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.interfaces.impl.BaseCatalogueImpl;

@SuppressWarnings("rawtypes")
public class EnumBase<T extends Enum> extends BaseCatalogueImpl<T> {

	public EnumBase(InputReader reader, OutputWriter writter, List<T> mainMenuItems) {
		super(reader, writter, mainMenuItems);
	}

}
