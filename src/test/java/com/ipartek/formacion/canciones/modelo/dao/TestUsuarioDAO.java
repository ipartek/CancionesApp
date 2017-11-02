package com.ipartek.formacion.canciones.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.canciones.modelo.pojo.Usuario;

public class TestUsuarioDAO {

	static UsuarioDAO dao;
	static Usuario usuarioMock;
	static final String NOMBRE = "qwer";
	static final String PASS = "1234";
	static final String EMAIL = "abc@gmail.com";
	static final String AVATAR = "https://cdn.pixabay.com/photo/2016/08/31/11/54/user-1633249_960_720.png";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = UsuarioDAO.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		// crear pojo usuario
		usuarioMock = new Usuario();
		usuarioMock.setNombre(NOMBRE);
		usuarioMock.setPass(PASS);
		usuarioMock.setEmail(EMAIL);
		usuarioMock.setAvatar(AVATAR);

		// Insertarlo en la BBDD
		assertTrue(dao.create(usuarioMock));

	}

	@After
	public void tearDown() throws Exception {

		// eliminar de la BBDD
		assertTrue(dao.delete(usuarioMock.getId()));
		usuarioMock = null;
	}

	@Test
	public void testFindAll() {
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) dao.findAll();
		assertNotNull(usuarios);

		int contadorUsuarios = usuarios.size();
		assertTrue("Los usuarios >=0 and <= LIMIT", contadorUsuarios >= 0 && contadorUsuarios <= Persistable.LIMIT);

		// insertamos dos canciones
		dao.create(usuarioMock);
		dao.create(usuarioMock);

		ArrayList<Usuario> usuarios2 = (ArrayList<Usuario>) dao.findAll();
		assertEquals(contadorUsuarios + 2, usuarios2.size());

		assertTrue("No ordena descendentemente por id", usuarios2.get(0).getId() > usuarios2.get(1).getId());

	}

	@Test
	public void testFindById() {

		assertNull("No se puede buscar lo que no existe", dao.findById(0));

		Usuario uBuscado = dao.findById(usuarioMock.getId());
		assertNotNull(uBuscado);
		assertEquals(uBuscado.getId(), usuarioMock.getId());
		assertEquals(uBuscado.getNombre(), usuarioMock.getNombre());
		assertEquals(uBuscado.getPass(), usuarioMock.getPass());
		assertEquals(uBuscado.getEmail(), usuarioMock.getEmail());
		assertEquals(uBuscado.getAvatar(), usuarioMock.getAvatar());

	}

	@Test
	public void testDelete() {
		assertFalse("No se puede eliminar un id que no existe", dao.delete(0));
	}

	@Test
	public void testCreate() {

		assertFalse("No se puede crear null", dao.create(null));

		usuarioMock.setNombre(
				"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.");
		assertFalse("No puede insertar si el campo nombre > VARCHAR (150)", dao.create(usuarioMock));
	}

	@Test
	public void testUpdate() {

		int id = usuarioMock.getId();
		String nombreUpdate = "Macarena";
		String passUpdate = "789";
		String emailUpdate = "mac@gmail.com";
		String avatarUpdate = "https://cdn.pixabay.com/photo/2016/08/31/11/54/user-1633249_960_720.png";

		usuarioMock.setNombre(nombreUpdate);
		usuarioMock.setPass(passUpdate);
		usuarioMock.setEmail(emailUpdate);
		usuarioMock.setAvatar(avatarUpdate);

		assertTrue("No modifica", dao.update(usuarioMock, id));

		assertEquals(id, usuarioMock.getId());
		assertEquals(nombreUpdate, usuarioMock.getNombre());
		assertEquals(passUpdate, usuarioMock.getPass());
		assertEquals(emailUpdate, usuarioMock.getEmail());
		assertEquals(avatarUpdate, usuarioMock.getAvatar());

		// comprobar que funcione cuando no deberia funcionar
		assertFalse("No se puede modificar lo que no existe", dao.update(usuarioMock, 0));
		assertFalse("No se puede modificar null", dao.update(null, id));
	}
}
