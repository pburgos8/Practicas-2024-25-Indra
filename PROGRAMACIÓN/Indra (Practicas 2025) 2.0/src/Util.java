// Clase con metodos que se van a usar en varias clases del programa
public class Util {
	
	/**
	 *  Metodo que devuelve la primera posicion null que se encuentre en un array de eventos
	 * @param eventos : array que se va a recorrer
	 * @return primera posicion del array que se encuentra null
	 */
	public static int buscarUltimo(Evento[] eventos) {
		for(int i = 0 ; i < eventos.length ; i++) {
			if(eventos[i] == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 *  Metodo que devuelve la primera posicion null que se encuentre en un array de usuarios
	 * @param usuarios : array que se va a recorrer
	 * @return primera posicion del array que se encuentra null
	 */
	
	public static int buscarUltimo(Usuario[] usuarios) {
		for(int i = 0 ; i < usuarios.length ; i++) {
			if(usuarios[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 *  Metodo que devuelve la primera posicion null que se encuentre en un array de organizadores
	 * @param organizadores : array que se va a recorrer
	 * @return primera posicion del array que se encuentra null
	 */
	public static int buscarUltimo(Organizador[] organizadores) {
		for(int i = 0 ; i < organizadores.length ; i++) {
			if(organizadores[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Metodo que cuenta la cantidad de veces que un usuario se encuentra en un array de usuarios
	 * @param usuarios : array que se va a recorrer
	 * @param usuario : instancia que se va a comparar
	 * @return cantidad de veces que aparece el usuario en el array
	 */
	public static int contarEnArray(Usuario[] usuarios , Usuario usuario) {
		
		int cont = 0;
		
		for(Usuario u : usuarios) {
			if(u != null) {
				if(u.getNombre().equals(usuario.getNombre())) {
					cont++;
				}
			}
		}
		
		return cont;
	}
	
	/**
	 * Metodo que cuenta la cantidad de veces que un organizador aparece en un array de organizadores
	 * @param organizadores : array que se va a recorrer
	 * @param organizador : instancia que se va a comparar
	 * @return
	 */
	public static int contarEnArray(Organizador[] organizadores , Organizador organizador) {
		
		int cont = 0;
		
		for(Organizador o : organizadores) {
			if(o != null) {
				if(o.getNombre().equals(organizador.getNombre())) {
					cont++;
				}
			}
		}
		
		return cont;
	}

	/**
	 * Metodo que busca la posicion de un organizador en un array a través de su nombre
	 * @param organizadores : array que se va a recorrer
	 * @param nombre : nombre del organizador que se busca
	 * @return posición del organizador que se busca en el array, -1 si no se encuentra
	 */
	public static int buscar(Organizador[] organizadores , String nombre) {
		for(int i = 0 ; i < organizadores.length ; i++) {
			if(organizadores[i] != null) {
				if(organizadores[i].getNombre().equals(nombre)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Metodo que busca la posicion de un usuario en un array a través de su nombre
	 * @param usuarios : array que se va a recorrer
	 * @param nombre : nombre del usuario que se busca
	 * @return posición del usuario que se busca en el array, -1 si no se encuentra
	 */
	public static int buscar(Usuario[] usuarios , String nombre) {
		
		for(int i = 0 ; i < usuarios.length ; i++) {
			if(null != usuarios[i]) {
				if(usuarios[i].getNombre().equals(nombre)) {
					return i;
				}
			}
		}
		
		return -1;
	}
}
