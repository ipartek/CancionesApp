package com.ipartek.formacion.canciones.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.canciones.modelo.ConnectionManager;
import com.ipartek.formacion.canciones.modelo.pojo.Genero;

public class GenerosDAO implements Persistable<Genero> {

	private static GenerosDAO INSTANCE = null;

	private GenerosDAO() {
		super();
	}

	public synchronized static GenerosDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new GenerosDAO();
		}
		return INSTANCE;
	}

	@Override
	public boolean create(Genero genero) {
		boolean resul = false;
		String sql = "INSERT INTO `genero` (`nombre`) VALUES (?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, genero.getNombre());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// recuperar el Identificador generado
				try (ResultSet keys = pst.getGeneratedKeys();) {
					while (keys.next()) {
						genero.setId(keys.getInt(1));
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
	public boolean update(Genero genero, int id) {
		boolean resul = false;
		String sql = "UPDATE `genero` SET `nombre`=? WHERE  `id`=?;";

		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, genero.getNombre());
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
		String sql = "DELETE FROM `genero` WHERE  `id`=?;";
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
	public List<Genero> findAll() {
		ArrayList<Genero> generos = new ArrayList<Genero>();
		String sql = "SELECT `id`, `nombre` FROM `genero` ORDER BY `id` desc;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Genero genero;
				while (rs.next()) {
					genero = mapeo(rs);
					generos.add(genero);
					genero = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return generos;
	}

	@Override
	public Genero findById(int id) {
		Genero genero = null;
		String sql = "SELECT `id`, `nombre` FROM `genero` WHERE `id` = ?;";
		try (Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					genero = mapeo(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return genero;
	}

	Genero mapeo(ResultSet rs) throws SQLException {
		Genero genero = null;
		if (rs != null) {
			genero = new Genero();
			genero.setId(rs.getInt("id"));
			genero.setNombre(rs.getString("nombre"));
		}
		return genero;
	}

}
