
public class Organizador {
	
	private String nombre;
	private String correo;
	private String telefono;
	private String pass;
	
	// Constructor 
	public Organizador(String nombre , String correo , String telefono , String pass) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.pass = pass;
	}
	
	/**
	 * Metodo que comprueba si una cadena de texto es exactamente igual a la contraseña del organizador
	 * @param intento de contraseña
	 * @return true si coincide, false si no
	 */
	public boolean checkPass(String intento) {
		return intento.equals(pass);
	}
	
	// Metodos GETTERS
	
	public String getNombre() {return nombre;}
	
	public String getCorreo() {return correo;}
	
	public String getTelefono() {return telefono;}
	
	/**
	 * Metodo que devuelve la información de una instancia de organizaodr
	 * @return Cadena de texto con la información
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
			sb.append("Nombre: ");
			sb.append(nombre);
			sb.append(". Correo: ");
			sb.append(correo);
			sb.append(". Teléfono: ");
			sb.append(telefono);
			sb.append(".");
		return sb.toString();
	}

}
