package com.ipartek.formacion.canciones.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;


public class TestCancionDAO {

	static CancionDAO dao;
	static Cancion cancionMocks;
	static final String NOMBRE = "Soldadito marinero";
	static final String ARTISTA = "Fito y los Fitipaldis";
	static final String DURACION = "3:59";
	static final String COVER = "http://1.bp.blogspot.com/-ECbO0u4Leyc/VUPO-S7WZiI/AAAAAAAAF3k/gkGx8g3nN5U/s1600/soldadito-marinero-fito-fitipaldis.jpg";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = CancionDAO.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Before
	public void setUp() throws Exception {

		// Crear pojo
		cancionMocks = new Cancion();
		cancionMocks.setNombre(NOMBRE);
		cancionMocks.setArtista(ARTISTA);
		cancionMocks.setDuracion(DURACION);
		cancionMocks.setCover(COVER);

		// Insertarlo en la BBDD
		assertTrue(dao.create(cancionMocks));

	}

	@After
	public void tearDown() throws Exception {
		// Eliminar de la BBDD

		assertTrue(dao.delete(cancionMocks.getId()));

		cancionMocks = null;

	}

	@Test
	public void testDelete() {

		assertFalse("No se puede eliminar un id que no existe", dao.delete(0));

	}

	@Test
	public void testCreate() {

		assertFalse("No se puede crear null", dao.create(null));

		cancionMocks.setNombre(
				"adssssssssssssssssssssssssssssssssssassadaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa asddddddddddddd asdddddddddddddddddddddddddddasssssssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

		assertFalse("No se puede nombre varchar mas de 150", dao.create(cancionMocks));

	}

	@Test
	public void testUpdate() {

		int id = cancionMocks.getId();
		String nombreUpdate = "La macarena";
		String artistaUpdate = "Los del rio";
		String duracionUpdate = "99:55";
		String coverUpdate = "asds";

		cancionMocks.setNombre(nombreUpdate);
		cancionMocks.setArtista(artistaUpdate);
		try {
			cancionMocks.setDuracion(duracionUpdate);
		} catch (CancionException e) {
			fail("Formato duracion incorrecta");
		}
		cancionMocks.setCover(coverUpdate);

		assertTrue("No modifica", dao.update(cancionMocks, id));
		assertEquals(id, cancionMocks.getId());
		assertEquals(nombreUpdate, cancionMocks.getNombre());
		assertEquals(artistaUpdate, cancionMocks.getArtista());
		assertEquals(duracionUpdate, cancionMocks.getDuracion());
		assertEquals(coverUpdate, cancionMocks.getCover());

		assertFalse("No se puede modificar lo que no existe", dao.update(cancionMocks, 0));
		assertFalse("No se puede modificar null", dao.update(null, id));

	}

	@Test
	public void testFindById() {

		assertNull("No se puede buscar un id que no existe", dao.findById(0));
		Cancion cBuscada = dao.findById(cancionMocks.getId());
		assertNotNull(cBuscada);
		assertEquals(cBuscada.getId(), cancionMocks.getId());
		assertEquals(cBuscada.getNombre(), cancionMocks.getNombre());
		assertEquals(cBuscada.getArtista(), cancionMocks.getArtista());
		assertEquals(cBuscada.getDuracion(), cancionMocks.getDuracion());
		assertEquals(cBuscada.getCover(), cancionMocks.getCover());

	}

	@Test
	public void testFindAll() {

		ArrayList<Cancion> canciones = (ArrayList<Cancion>) dao.findAll();
		assertNotNull(canciones);
		int contadorCanciones = canciones.size();

		assertTrue("Las canciones >= 0 AND <= LIMIT", contadorCanciones >= 0 && contadorCanciones <= Persistable.LIMIT);

		dao.create(cancionMocks);
		dao.create(cancionMocks);

		assertTrue(contadorCanciones + 2 == dao.findAll().size());

		ArrayList<Cancion> canciones2 = (ArrayList<Cancion>) dao.findAll();
		assertTrue(contadorCanciones + 2 == canciones2.size());

		assertTrue("No ordena descendentemente", canciones2.get(0).getId() >= canciones2.get(1).getId());

	}
}
