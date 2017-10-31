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

	private UsuarioDAO() {
		super();
	}

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
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				u = mapeo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public boolean create(Usuario u) {
		boolean resul = false;
		String sqlQuery = "INSERT INTO `spoty`.`usuario` (`nombre`, `pass`, `email`, `avatar`) VALUES ( ?, ?, ?, ?);";

		try (Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, u.getNombre());
			pst.setString(2, u.getPass());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getAvatar());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				// Recuperar el identificador generado
				try (ResultSet keys = pst.getGeneratedKeys()) {
					while (keys.next()) {
						u.setId(keys.getInt(1));
					}
					resul = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean update(Usuario c, int id) {
		boolean resul = false;
		
		String sqlQuery = "UPDATE `usuario` SET `nombre` = ?, `pass` = ?, `email` = ?, `avatar` = ? WHERE `id` = ?;";
		
		try(Connection con = ConnectionManager.open();
				PreparedStatement pst = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);) {
			
			pst.setString(1, c.getNombre());
			pst.setString(2, c.getPass());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getAvatar());
			pst.setLong(5, id);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean delete(int d) {
		boolean resul = false;
		
		String sqlQuery = "DELETE FROM `spoty`.`usuario` WHERE `id` = ?;";
		
		try(Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);) {

			pst.setLong(1, d);

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	/**
	 * Mapea de un ResultSet a un Objeto de tipo Usuario
	 *
	 * @param rs
	 *            ResultSet return Usuario o null si falla
	 * @throws SQLException
	 */

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
	
	public static Usuario validar( String nombre, String pass ) throws SQLException, ClassNotFoundException {
		
		Usuario u = null;
		String sql = "SELECT id,nombre,pass,email,avatar FROM usuario WHERE nombre=? AND pass=?";
		
		try(Connection con = ConnectionManager.open(); PreparedStatement pst = con.prepareStatement(sql);) {
			
		pst.setString(1, nombre );
		pst.setString(2, pass );
		
		try (ResultSet rs = pst.executeQuery()){
			if ( rs.next() ) {
				u = new Usuario( nombre, pass, rs.getString("email") );
				u.setAvatar(rs.getString("avatar"));
				u.setId( rs.getInt("id") );
			}
		}
		
		}
		
		return u;
		
	}
}
