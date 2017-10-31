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

public class CancionDAO implements Persistable<Cancion> {

	private static CancionDAO INSTANCE = null;

	private CancionDAO() {
		super();
	}

	public synchronized static CancionDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CancionDAO();
		}
		return INSTANCE;
	}

	@Override
	public List<Cancion> findAll() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "SELECT `id`, `nombre`, `artista`, `duracion`, `cover` FROM cancion ORDER BY `id`DESC LIMIT ?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {

				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return canciones;
	}

	@Override
	public boolean create(Cancion c) {
		boolean resul = false;
		String sql = "INSERT INTO `cancion` (`nombre`, `artista`, `duracion`, `cover`) VALUES (?, ?, ?, ?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());

			int affetedRows = pst.executeUpdate();
			if (affetedRows == 1) {

				// recuperar el identificador generado

				try (ResultSet keys = pst.getGeneratedKeys()) {
					;
					while (keys.next()) {
						c.setId(keys.getInt(1));
					}
				}
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return resul;
	}

	@Override
	public boolean update(Cancion c, int id) {
		boolean resul = false;
		String sql = "UPDATE cancion SET `nombre`=?, `artista`=?, `duracion`=?, `cover`=? WHERE id=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, c.getNombre());
			pst.setString(2, c.getArtista());
			pst.setString(3, c.getDuracion());
			pst.setString(4, c.getCover());
			pst.setInt(5, id);

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
		String sql = "DELETE FROM `cancion` WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
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
	public Cancion findById(int id) {
		Cancion c = null;
		String sql = "Select `id`, `nombre`, `artista`, `duracion`, `cover` from cancion WHERE `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);

			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					c = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return c;
	}

	/**
	 * Busca canciones que coincidadn por el nombre o el artista<br>
	 * No es casesensitive<br>
	 *
	 * @param cadena
	 *            String cadena a buscar
	 * @return listado de canciones limitado por <code>LIMIT</code> o vacio si no
	 *         hay
	 */
	public List<Cancion> busqueda(String cadena) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		String sql = "Select `id`, `nombre`, `artista`, `duracion`, `cover` from cancion WHERE `nombre` LIKE ? OR `artista` LIKE ? ORDER BY `id` DESC LIMIT ?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "%" + cadena + "%");
			pst.setString(2, "%" + cadena + "%");
			pst.setInt(3, Persistable.LIMIT);

			try (ResultSet rs = pst.executeQuery();) {

				Cancion c;
				while (rs.next()) {
					c = mapeo(rs);
					canciones.add(c);
					c = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return canciones;
	}

	/**
	 * Mapea de un ResultSet a un objeto de tipo cancion
	 *
	 * @param rs
	 *            ResultSet
	 * @return Cancion o null si falla
	 * @throws SQLException
	 * @throws CancionException
	 *             si la duracion no tiene duracion adecuada @see set.duracion
	 */
	Cancion mapeo(ResultSet rs) throws SQLException, CancionException {
		Cancion c = null;
		if (rs != null) {
			c = new Cancion();
			c.setId(rs.getInt("id"));
			c.setNombre(rs.getString("nombre"));
			c.setArtista(rs.getString("artista"));
			c.setDuracion(rs.getString("duracion"));
			c.setCover(rs.getString("cover"));
		}

		return c;

	}
}
