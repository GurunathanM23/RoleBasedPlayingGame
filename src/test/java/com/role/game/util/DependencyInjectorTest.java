package com.role.game.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.role.game.exception.DependencyInjectionException;
import com.role.game.io.reader.InputReader;

public class DependencyInjectorTest {
	

	@Test
	public void getObject() {
		assertNotNull(DependencyInjector.getObject(InputReader.class));
	}
	
	@Test(expected = DependencyInjectionException.class)
	public void getInvalidObject() {
		DependencyInjector.getObject(Mockito.class);
	}
}
