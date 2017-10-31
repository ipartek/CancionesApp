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

		// crear Pojo
		cancionMock = new Cancion();
		cancionMock.setNombre(NOMBRE);
		cancionMock.setArtista(ARTISTA);
		cancionMock.setCover(COVER);
		cancionMock.setDuracion(DURACION);

		// insertarlo en la BBDD
		assertTrue(dao.create(cancionMock));

	}

	@After
	public void tearDown() throws Exception {

		// eliminar de la BBDD
		assertTrue(dao.delete(cancionMock.getId()));
		cancionMock = null;

	}

	@Test
	public void testFindAll() {

		ArrayList<Cancion> canciones = (ArrayList<Cancion>) dao.findAll();
		assertNotNull(canciones);

		int contadorCanciones = canciones.size();
		assertTrue("Las canciones >=0 AND <= LIMIT ", contadorCanciones >= 0 && contadorCanciones <= Persistable.LIMIT);

		// insertamos dos canciones
		dao.create(cancionMock);
		dao.create(cancionMock);

		ArrayList<Cancion> canciones2 = (ArrayList<Cancion>) dao.findAll();
		assertEquals(contadorCanciones + 2, canciones2.size());

		assertTrue("No ordena descendentemente por Id", canciones2.get(0).getId() > canciones2.get(1).getId());

	}

	@Test
	public void testFindById() {

		assertNull("No se puede buscar lo que no existe", dao.findById(0));

		Cancion cBuscada = dao.findById(cancionMock.getId());
		assertNotNull(cBuscada);
		assertEquals(cBuscada.getId(), cancionMock.getId());
		assertEquals(cBuscada.getNombre(), cancionMock.getNombre());
		assertEquals(cBuscada.getArtista(), cancionMock.getArtista());
		assertEquals(cBuscada.getCover(), cancionMock.getCover());
		assertEquals(cBuscada.getDuracion(), cancionMock.getDuracion());

	}

	@Test
	public void testDelete() {
		assertFalse("No se puede eliminar un id que no existe", dao.delete(0));
	}

	@Test
	public void testCreate() {
		assertFalse("No se puede crear null", dao.create(null));

		cancionMock.setNombre(
				"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.");
		assertFalse("No puede insertar si el campo nombre > VARCHAR(150)", dao.create(cancionMock));
	}

	@Test
	public void testUpdate() {

		int id = cancionMock.getId();
		String nombreUpdate = "La Macarena";
		String artistaUpdate = "Los del Rio";
		String duracionUpdate = "99:34";
		String coverUpdate = "http://www.semana-santa.org/wp-content/uploads/2017/03/macarena.jpg";

		cancionMock.setNombre(nombreUpdate);
		cancionMock.setArtista(artistaUpdate);
		try {
			cancionMock.setDuracion(duracionUpdate);
		} catch (CancionException e) {
			fail("formato duracion incorrecto");
		}
		cancionMock.setCover(coverUpdate);

		assertTrue("No modifica", dao.update(cancionMock, id));

		assertEquals(id, cancionMock.getId());
		assertEquals(nombreUpdate, cancionMock.getNombre());
		assertEquals(artistaUpdate, cancionMock.getArtista());
		assertEquals(duracionUpdate, cancionMock.getDuracion());
		assertEquals(coverUpdate, cancionMock.getCover());

		// Comprobar que funcione cuando no deberia funcionar
		assertFalse("No se puede modificar lo que no existe", dao.update(cancionMock, 0));
		assertFalse("No se puede modificar null", dao.update(null, id));
	}

}
