package com.ipartek.formacion.canciones.utilidades;

public class Utilidades {
	
	/**
	 * Valida la duracion de una cancion que debe tener el siguiente formato:
	 * [0]0:00  el primer numero es opcional
	 * Antes de los dos puntos debe contener al menos 1 digito
	 * Despues de los dos puntos debe contener solo 2 digitos y ser menor de 60
	 * 
	 * @param Duracion es un String 
	 * @return true si formato correcto, false en caso contrario
	 */
	static public boolean validarDuracion(String duracion) {
		boolean resul = false;
		
		
		if(duracion != null) {
			
			try {
				
				//buscar valores negativos
				if(duracion.indexOf("-") == -1) {
					String[] tokens = duracion.split(":");
					if(tokens.length == 2) {

						int minutos = Integer.parseInt(tokens[0]);
						int segundos = Integer.parseInt(tokens[1]);
						
						
						
						//minutos y segundos
						if(minutos >= 0 &&  (segundos >= 0 && segundos < 60)) {

							resul = true;
							
						} // fin if minutos segundos length
						
					} //if split
				} // fin if indexOf
				
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} // duracion null
		
		return resul;
	}

}
