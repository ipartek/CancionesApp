package com.ipartek.formacion.canciones.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ipartek.formacion.canciones.pojo.Usuario;

public class ModeloUsuario {

	private static ModeloUsuario INSTANCE = null;

	// constructor privado
	private ModeloUsuario() {
		super();
	}

	// acceso para la clase
	public synchronized static ModeloUsuario getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModeloUsuario();
		}
		return INSTANCE;
	}
	
	/**
	 * Comprueba que exista el usuario en la bbdd
	 * @param nombre del usuario
	 * @param pass contraseña del usuario
	 * @return null si no existe usuario, usuario con datos si existe
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public Usuario validar( String nombre, String pass ) throws SQLException, ClassNotFoundException {
		
		Usuario u = null;
		Connection con = ConnectionManager.open();
		
		String sql = "SELECT id,nombre,pass,email,avatar FROM usuario WHERE nombre=? AND pass=?";
		PreparedStatement pst = con.prepareStatement(sql);		
		pst.setString(1, nombre );
		pst.setString(2, pass );
		
		ResultSet rs = pst.executeQuery();
		if ( rs.next() ) {
			u = new Usuario( nombre, pass, rs.getString("email") );
			u.setAvatar(rs.getString("avatar"));
			u.setId( rs.getInt("id") );
		}
		
		return u;
		
	}
	
	

	public Usuario insertar(Usuario u) throws SQLException, ClassNotFoundException {
		
		
		String sql = "";
		if ( "".equals(u.getAvatar()) ) {
			sql = "INSERT INTO `usuario` (`nombre`, `pass`, `email`) VALUES ('"+u.getNombre()+"', '"+u.getPass()+"', '"+u.getEmail()+"');";
		}else {
			sql = "INSERT INTO `usuario` (`nombre`, `pass`, `email`, `avatar`) VALUES ('"+u.getNombre()+"', '"+u.getPass()+"', '"+u.getEmail()+"','"+u.getAvatar()+"');";
		}		
		
		Connection con = ConnectionManager.open();
		PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		int affectedRows = pst.executeUpdate();

        if (affectedRows != 1) {
            throw new SQLException("No se ha podido insertar");
        }

        try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
		
		
		return u;
	}

}
