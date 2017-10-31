package com.acme.maven.model.dao;

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
import com.ipartek.formacion.canciones.modelo.dao.CancionDAO;
import com.ipartek.formacion.canciones.modelo.dao.Persistable;
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;

public class TestCancionDAO {

	static CancionDAO dao;
	static Cancion cancionMock;

	static final String NOMBRE = "Soldadito Marinero";
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

		// crear pojo
		cancionMock = new Cancion();
		cancionMock.setNombre(NOMBRE);
		cancionMock.setArtista(ARTISTA);
		cancionMock.setDuracion(DURACION);
		cancionMock.setCover(COVER);

		// insertarlo en la BD
		assertTrue(dao.create(cancionMock));

	}

	@After
	public void tearDown() throws Exception {

		// eliminar de la BD
		assertTrue(dao.delete(cancionMock.getId()));

		cancionMock = null;
	}

	@Test
	public void testDelete() {

		assertFalse("No se puede eliminar un id que no existe", dao.delete(0));
	}

	@Test
	public void testCreate() {

		assertFalse("No se puede crear null", dao.create(null));
	}

	@Test
	public void testUpdate() {

		int id = cancionMock.getId();
		String nombre = "La Macarena";
		String artista = "Los del Rio";
		String duracion = "3:59";
		String cover = "http://1.bp.blogspot.com/-ECbO0u4Leyc/VUPO-S7WZiI/AAAAAAAAF3k/gkGx8g3nN5U/s1600/soldadito-marinero-fito-fitipaldis.jpg";

		cancionMock.setNombre(nombre);
		cancionMock.setArtista(artista);
		try {
			cancionMock.setDuracion(duracion);
		} catch (CancionException e) {
			fail("formato duracion incorrecto");
		}
		cancionMock.setCover(cover);

		assertTrue(dao.update(cancionMock, id));

		assertEquals(id, cancionMock.getId());
		assertEquals(nombre, cancionMock.getNombre());
		assertEquals(artista, cancionMock.getArtista());
		assertEquals(duracion, cancionMock.getDuracion());
		assertEquals(cover, cancionMock.getCover());

		assertFalse("No se puede modificar lo que no existe", dao.update(cancionMock, 0));
		assertFalse("No se puede modificar null", dao.update(null, id));

	}

	@Test
	public void testFindbyId() {

		assertNull("No se puede buscar lo que no existe", dao.findById(0));
		Cancion cBuscada = dao.findById(cancionMock.getId());

		assertNotNull(cBuscada);
		assertEquals(cBuscada.getId(), cancionMock.getId());
		assertEquals(cBuscada.getNombre(), cancionMock.getNombre());
		assertEquals(cBuscada.getArtista(), cancionMock.getArtista());
		assertEquals(cBuscada.getDuracion(), cancionMock.getDuracion());
		assertEquals(cBuscada.getCover(), cancionMock.getCover());

	}

	@Test
	public void testFindAll() {

		ArrayList<Cancion> canciones = (ArrayList<Cancion>) dao.findAll();

		assertNotNull(canciones);

		int contadorCanciones = canciones.size();

		assertTrue("Las canciones >=0 AND  <=limit", contadorCanciones >= 0 && contadorCanciones <= Persistable.LIMIT);

		dao.create(cancionMock);
		dao.create(cancionMock);

		assertEquals(contadorCanciones + 2, dao.findAll().size());

		ArrayList<Cancion> canciones2 = (ArrayList<Cancion>) dao.findAll();
		assertEquals(contadorCanciones + 2, canciones2.size());

		assertTrue("No ordena descendentemente por id", canciones2.get(0).getId() > canciones2.get(1).getId());

	}

}
