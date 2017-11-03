package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.modelo.pojo.Rol;

public class RolesDAO implements Persistable<Rol> {

	private static RolesDAO INSTANCE = null;

	private RolesDAO() {
		super();
	}

	public synchronized static RolesDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new RolesDAO();
		}
		return INSTANCE;
	}

	@Override
	public boolean create(Rol rol) {
		boolean resul = false;
		String sql = "INSERT INTO `canciones`.`rol` (`nombre`) VALUES (?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, rol.getNombre());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						rol.setId(keys.getInt(1));
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
	public boolean update(Rol rol, int id) {
		boolean resul = false;
		String sql = "UPDATE `rol` SET `nombre`=? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, rol.getNombre());
			pst.setInt(2, id);

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
		String sql = "DELETE FROM `rol` WHERE  `id`=?;";
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
	public List<Rol> findAll() {
		ArrayList<Rol> roles = new ArrayList<Rol>();
		String sql = "SELECT `id`, `nombre` FROM `rol` ORDER BY `id` desc;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Rol rol;
				while (rs.next()) {
					rol = mapeo(rs);
					roles.add(rol);
					rol = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return roles;
	}

	@Override
	public Rol findById(int id) {
		Rol rol = null;
		String sql = "SELECT `id`, `nombre` FROM `rol` WHERE `id` = ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					rol = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rol;
	}

	Rol mapeo(ResultSet rs) throws SQLException {
		Rol rol = null;
		if (rs != null) {
			rol = new Rol();
			rol.setId(rs.getInt("id"));
			rol.setNombre(rs.getString("nombre"));
		}
		return rol;
	}

}
