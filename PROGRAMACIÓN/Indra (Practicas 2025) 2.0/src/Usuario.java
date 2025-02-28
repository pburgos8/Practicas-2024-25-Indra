/**
 *  Clase que crea objetos 'Usuario'
 */
public class Usuario {
	
	private String nombre; // Nombre de usuario
	private String correo; // Correo del usuario
	private String pass;   // Contraseña del usuario
	
	// Constructor 
	public Usuario(String nombre , String correo , String pass) {
		this.nombre = nombre;
		this.correo = correo;
		this.pass = pass;
	}
	
	// Getter del atributo nombre
	public String getNombre() {return nombre;}
	
	// Getter del atributo correo
	public String getCorreo() {return correo;}
	
	/**
	 * Metodo que comprueba si una cadena de texto es exactamente igual a 'pass'
	 * @param nuevo : cadena que se va a comprobar
	 * @return true si coinciden, false en caso contrario
	 */
	public boolean checkPass(String intento) {
		return intento.equals(pass);
	}
	
	/**
	 * Metodo que devuelve la información de una instancia Usuario
	 * @return cadena de texto con la información del objeto
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
			sb.append("Nombre: ");
			sb.append(nombre);
			sb.append(". Correo: ");
			sb.append(correo);
			sb.append(".");
		return sb.toString();
	}
}
