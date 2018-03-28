package com.role.game.util;

import java.io.File;
import java.io.IOException;

public class IOUtil {

	public static File createFileIfDoesNotExist(String absolutePath) throws IOException {
		File file = new File(absolutePath);
		if (file.exists()) {
			return file;
		}

		file.getParentFile().mkdirs();
		file.createNewFile();
		return file;
	}
}
