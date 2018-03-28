package com.role.game.io.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.util.IOUtil;

public class InputReader {

	private static final Logger log = LogManager.getLogger(InputReader.class);
	
	 private static final String RESOURCES = "src/main/resources/";


	private Scanner scanner;

	public InputReader() {

	}

	public InputReader(InputStream stream) {
		scanner = new Scanner(stream);
	}

	public String readStringValueFromConsole() {
		return scanner.nextLine();
	}

	public Integer readIntegerValueFromConsole() {
		return Integer.parseInt(scanner.nextLine());

	}

	public ObjectInputStream getObjectInputStream(String fileName) {
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(absolutePath(RESOURCES,fileName));
			objectInputStream = new ObjectInputStream(fileInputStream);
		} catch (FileNotFoundException e) {
			log.error("error occured whilereading" + e);
		} catch (IOException e) {
			log.error("error occured whilereading" + e);
		}
		return objectInputStream;
	}

	protected static String absolutePath(String basePath, String pathToFile) {
        return Paths.get(basePath, pathToFile).toAbsolutePath().toString();
    }

}
