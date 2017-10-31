package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.modelo.pojo.Cancion;
import com.ipartek.formacion.canciones.pojo.Usuario;

public class UsuarioDAO implements Persistable<Cancion> {

	private static UsuarioDAO INSTANCE = null;

	private UsuarioDAO() {
		super();
	}

	public synchronized static UsuarioDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
		return INSTANCE;
	}

	/**
	 *
	 * Busca canciones que coinciden el nombre o artista de la
	 * <code>Cancion</code><br>
	 * No es casesensitive
	 *
	 * @param criterio
	 *            String cadena a buscar
	 * @return Lista de Canciones limitado por <code>LIMIT</code>, vacia si no
	 *         encuentra nada
	 */
	public List<Usuario> findByNameAllArtist(String criterio) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT `id`, `nombre`, `pass`, `email`, `avatar` FROM `usuario` WHERE `nombre` LIKE ? OR `email` LIKE ? ORDER BY `id` DESC LIMIT ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + criterio + "%");
			pst.setString(2, "%" + criterio + "%");
			pst.setInt(3, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {
				Usuario u;
				while (rs.next()) {
					u = mapeo(rs);
					usuarios.add(c);
					u = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return usuarios;
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
		} finally {

		}
		return usuarios;
	}

	@Override
	public boolean create(Usuario u) {
		boolean resul = false;
		String sql = "";
		if (u.getAvatar() == null || "".equals(u.getAvatar())) {
			sql = "INSERT INTO `cancion` (`nombre`, `pass`, `email`) VALUES (?, ?, ?);";
		} else {
			sql = "INSERT INTO `cancion` (`nombre`, `pass`, `email`, `avatar`) VALUES (?, ?, ?, ?);";
		}

		try (
				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				) {

			pst.setString(1, u.getNombre());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getEmail());
			if (u.getAvatar() != null && !"".equals(u.getAvatar())) {
				pst.setString(4, u.getAvatar());
			} else {

			}

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						u.setId(keys.getInt(1));
					}
					resul = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return resul;
	}

	@Override
	public boolean update(Usuario u, int id) {
		boolean resul = false;
		String sql = "";
		if (u.getAvatar() == null || "".equals(c.getAvatar())) {
			sql = "UPDATE `usuario` SET `nombre`=?, `pass`=?, `email`=? WHERE  `id`=?;";
		} else {
			sql = "UPDATE `usuario` SET `nombre`=?, `pass`=?, `email`=?, `avatar`=? WHERE  `id`=?;";
		}

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, u.getNombre());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getEmail());
			if(u.getAvatar() == null || "".equals(u.getAvatar())) {
				pst.setInt(4, id);
			} else {
				pst.setString(4, u.getAvatar());
				pst.setInt(5, id);
			}

			if (pst.executeUpdate() == 1) {
				resul = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `usuario` WHERE  `id`=?;";
		try (

				Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql);

				) {

			pst.setInt(1, id);
			if (pst.executeUpdate() == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public Usuario findById(int id) {
		Usuario u = null;
		String sql = "SELECT `id`, `nombre`, `pass`, `email`, `avatar` FROM `usuario` WHERE  `id`=?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					u = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return u;
	}

	/**
	 * Mapea de un ResultSet a un objeto de tipo Cancion
	 *
	 * @param rs
	 *            ResultSet
	 * @return Cancion o null si falla
	 * @throws SQLException
	 * @throws CancionException
	 *             si la duracion no tiene formato adecuado @see Cancion.setDuracion
	 */
	Usuario mapeo(ResultSet rs) throws SQLException, CancionException {
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
