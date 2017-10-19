package com.ipartek.formacion.canciones.utilidades;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilidadesTest {
	

	@Test
	public void testValidarDuracion() {
		
		assertFalse("No puede ser nulo",Utilidades.validarDuracion(null));
		assertFalse(Utilidades.validarDuracion(""));
		assertFalse(Utilidades.validarDuracion("  "));
		assertFalse(Utilidades.validarDuracion("62jfkdoek"));
		assertFalse(Utilidades.validarDuracion(":23"));
		assertFalse(Utilidades.validarDuracion("12:60"));
		assertFalse(Utilidades.validarDuracion("12:61"));
		assertFalse("No aceptamos duracion negativa",Utilidades.validarDuracion("-0:01"));
		assertFalse(Utilidades.validarDuracion("a:23"));
		
		
		
		
		assertTrue(Utilidades.validarDuracion("00:00"));
		assertTrue(Utilidades.validarDuracion("0:01"));
		assertTrue(Utilidades.validarDuracion("230:40"));
				
	}

}
