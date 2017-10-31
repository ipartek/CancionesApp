package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.pojo.Usuario;

public class UsuarioDAO implements Persistable<Usuario> {

	private static UsuarioDAO INSTANCE = null;

	// constructor privado
	private UsuarioDAO() {
		super();

	}

	// acceso para la clase
	public synchronized static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
		return INSTANCE;
	}

	@Override
	public List<Usuario> findAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT `id`, `nombre`, `pass`, `email`, `avatar` FROM `usuario` ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {

				Usuario u;
				while (rs.next()) {
					u = mapeo(rs);
					usuarios.add(u);
					u = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public Usuario findById(int id) {
		Usuario u = null;
		String sql = "SELECT `id`, `nombre`, `pass`, `email`, `avatar` FROM `usuario` WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			// preparar Statement
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {

					u = mapeo(rs);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean create(Usuario u) {
		boolean resul = false;
		// SQL Insert y Crear PreparedStatement
		String sqlInsert = "INSERT INTO `usuario` (`nombre`, `pass`,`email`,`avatar`) VALUES (?, ?, ?, ?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

		) {

			// preparar Statement
			pst.setString(1, u.getNombre());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getAvatar());

			// executeUpdate
			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				try (ResultSet keys = pst.getGeneratedKeys();) {

					while (keys.next()) {

						u.setId(keys.getInt(1));
					}
				}

				resul = true;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean update(Usuario u, int id) {
		boolean resul = false;
		String sqlUpdate = "UPDATE `usuario` SET `nombre`= ?, `pass`= ?, `email`= ?,`avatar`= ? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sqlUpdate);) {

			// preparar Statement
			pst.setString(1, u.getNombre());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getAvatar());
			pst.setInt(5, id);

			if (pst.executeUpdate() == 1) {

				resul = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;

		// establecer conexion

		// SQL Insert y Crear PreparedStatement
		String sqlDelete = "DELETE FROM `usuario` WHERE  `id`=?;";

		try (

				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sqlDelete);

		) {

			// preparar Statement
			pst.setInt(1, id);

			if (pst.executeUpdate() == 1) {

				resul = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resul;
	}

	public ArrayList<Usuario> findByName(String campo) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT `id`, `nombre`, `pass`, `email`, `avatar` FROM `usuario` WHERE  `nombre` LIKE ? OR `email` LIKE ?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + campo + "%");
			pst.setString(2, "%" + campo + "%");

			try (ResultSet rs = pst.executeQuery();) {
				Usuario u = null;
				while (rs.next()) {

					u = mapeo(rs);
					usuarios.add(u);
					u = null;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return usuarios;

	}

	public static Usuario validar(String nombre, String pass) throws SQLException {

		Usuario u = null;

		String sql = "SELECT id,nombre,pass,email,avatar FROM usuario WHERE nombre=? AND pass=?";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, nombre);
			pst.setString(2, pass);

			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					u = new Usuario(nombre, pass, rs.getString("email"));
					u.setAvatar(rs.getString("avatar"));
					u.setId(rs.getInt("id"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return u;

	}

	Usuario mapeo(ResultSet rs) throws SQLException {

		Usuario u = null;

		if (rs != null) {

			u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNombre(rs.getString("nombre"));
			u.setPass(rs.getString("pass"));
			u.setEmail(rs.getString("email"));
			u.setAvatar(rs.getString("avatar"));

		}
		return u;

	}

}
