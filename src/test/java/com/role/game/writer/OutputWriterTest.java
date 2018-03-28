package com.role.game.writer;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.role.game.io.writer.OutputWriter;

public class OutputWriterTest {

	@Test
	public void testGetObjectOuptutStream() {
		OutputWriter writter = new OutputWriter();
		assertNotNull(writter.getObjectOutputStream("shadowfight.ser"));
	}
}
