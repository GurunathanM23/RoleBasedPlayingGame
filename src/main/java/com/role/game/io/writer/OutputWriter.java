package com.role.game.io.writer;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.util.IOUtil;

public class OutputWriter {

	private static final String RESOURCES = "src/main/resources/";

	private final Logger log = LogManager.getLogger(OutputWriter.class);

	public void showMessage(String msg) {
		out.println(msg);
	}

	public ObjectOutputStream getObjectOutputStream(String fileName) {

		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOuputStream = null;
		try {
			File file = IOUtil.createFileIfDoesNotExist(absolutePath(RESOURCES, fileName));
			fileOutputStream = new FileOutputStream(file);
			objectOuputStream = new ObjectOutputStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			log.error("Error occured while creating file");
		} catch (IOException e) {
			log.error("Error occured while creating file");
		}
		return objectOuputStream;
	}

	protected static String absolutePath(String basePath, String pathToFile) {
		return Paths.get(basePath, pathToFile).toAbsolutePath().toString();
	}

}
