package com.ipartek.formacion.canciones.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.canciones.excepciones.CancionException;
import com.ipartek.formacion.canciones.pojo.Cancion;

public class ModeloCancionMySQLImpl implements ModeloCancion {

	private static ModeloCancionMySQLImpl INSTANCE = null;
	
	private static final String SQL_GETALL = "SELECT id,nombre,artista,duracion,cover FROM cancion ORDER BY id DESC;";

	// constructor privado
	private ModeloCancionMySQLImpl() {
		super();	
	}

	// acceso para la clase
	public synchronized static ModeloCancionMySQLImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModeloCancionMySQLImpl();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Cancion> getAll() {

		ArrayList<Cancion> resul = new ArrayList<Cancion>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			//conseguir todas las canciones ordenadas por ID descendente
			con = ConnectionManager.getConnection();
			pst = con.prepareStatement(SQL_GETALL);
			rs = pst.executeQuery();
			
			//recorrer todos los resultados
			while( rs.next() ) {
				
				Cancion c = mapeo(rs);
				resul.add(c);
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				ConnectionManager.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
		return resul;
	}


	/**
	 * Mapea un ResultSet a un objeto Cancion
	 * @param rs	ResultSet con los datos para una cancion
	 * @return Cancion si ok, null en contrario
	 * @throws SQLException 
	 * @throws CancionException 
	 */
	private Cancion mapeo(ResultSet rs) throws SQLException, CancionException {

		Cancion c= null;
		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		String artista = rs.getString("artista");
		String duracion = rs.getString("duracion");
		String cover = rs.getString("cover");

		c = new Cancion(nombre, artista, duracion);
		c.setCover(cover);
		c.setId(id);
		
		
		return c;
	}

	@Override
	public Cancion getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Cancion c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cancion c, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

}