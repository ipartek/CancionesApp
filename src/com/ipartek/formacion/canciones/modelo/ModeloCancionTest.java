package com.ipartek.formacion.canciones.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.pojo.Cancion;


public class ModeloCancionTest {

	@Test
	public void test() {
		
		ModeloCancionImpl model = ModeloCancionImpl.getInstance();
		
		assertEquals(3, model.getAll().size() );
		
		//insertar nueva Cancion
		Cancion c = null;
		try {
			c = new Cancion("Corazon de minbre", "Marea", "5:37", "");
			assertTrue("No ha podido insertar Cancion",model.insert(c));
			assertEquals("No ha generado bien el IDentificador",526, c.getId());
			assertEquals(4, model.getAll().size() );
		} catch (CancionException e) {
			fail("No deberia lanzar Exception");
		}		
		
		
		//buscar por ID
		assertNull ( model.getById(-1));
		assertEquals( c , model.getById(526));
		
		
		//eliminar
		assertFalse(model.delete(-1));
		assertTrue(model.delete(526));
		assertEquals(3, model.getAll().size() );
		assertNull( model.getById(526));
		
		//update
		
		Cancion cModificar = model.getById(525);
		cModificar.setNombre("EL conejo de la Loles");
		cModificar.setArtista("Popular - anonima");		
		assertTrue ( model.update(cModificar, cModificar.getId()) );
		
		assertFalse ( model.update(cModificar, -1));
		assertFalse ( model.update( null , -1));
		 
	}

}
