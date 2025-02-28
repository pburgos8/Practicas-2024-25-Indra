import java.time.LocalDate;

public class Evento {
	
	// Numero máximo de usuarios inscritos a un evento (coincide con el número máximo de usuarios registrados en el programa
	final public int MAX_USUARIOS_INSCRITOS = MainApp.MAX_USUARIOS_REGISTRADOS;
	// Arrat con las diferentes categorias que puede tener un evento
	final public static String[] CATEGORIAS = {"Taller","Conferencia","Mercadillo","Recaudación"};
	
	private String nombre;
	private int categoria;
	private LocalDate fecha;
	private int duracion;
	private String lugar;
	private Organizador organizador;
	
	private Usuario[] inscritos = new Usuario[MAX_USUARIOS_INSCRITOS];
	private int nInscritos = 0;
	
	// Constructor
	public Evento(String nombre , int categoria , LocalDate fecha , int duracion , String lugar , Organizador organizador) 
			throws IllegalArgumentException {
		
		if(nombre == null || nombre.isBlank() || nombre.isEmpty()) {
			throw new IllegalArgumentException("Nombre del evento en blanco");
		}
		
		if(categoria < 0 || categoria >= CATEGORIAS.length) {
			throw new IllegalArgumentException("Categoria no válida");
		}
		
		if(organizador == null) {
			throw new IllegalArgumentException("Organizador no válido");
		}
		
		this.nombre = nombre;
		this.categoria = categoria;
		this.fecha = fecha;
		this.duracion = duracion;
		this.lugar = lugar;
		this.organizador = organizador;
		
	}

	// GETTERS y SETTERS
	
	public String getNombre() { return nombre; }
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCategoria() { return categoria; }
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public LocalDate getFecha() { return fecha; }
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public int getDuracion() { return duracion; }
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String getLugar() { return lugar;}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public Organizador getOrganizador() { return organizador; }
	
	/**
	 * Metodo que devuelve la información de una instancia de Evento
	 * @return cadena de texto con la información de la instancia
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("");
			sb.append("Nombre: ");
			sb.append(nombre);
			sb.append(". Categoria: ");
			sb.append(CATEGORIAS[categoria]);
			sb.append(". Fecha: ");
			sb.append(fecha.toString());
			sb.append(". Duracion (min): ");
			sb.append(duracion);
			sb.append(". Lugar: ");
			sb.append(lugar);
			sb.append(". Organizador: ");
			sb.append(organizador.getNombre());
			sb.append(".");
		return sb.toString();
	}
	
	/**
	 * Metodo que añade inscribe un usuario al evento
	 * @param nuevo : usuario que se va a inscribir
	 */
	public void inscribir(Usuario nuevo) {
		if(nuevo != null) {
			// se comprueba que haya huecos libres en el array inscritos
			if(nInscritos < inscritos.length) {
				// se comprueba que el usuario no este ya en inscrito
				if(buscarUsuario(nuevo.getNombre()) == -1) {
					inscritos[nInscritos] = nuevo;
					nInscritos++;
				}
			}
		}
	}
	
	/**
	 * Metodo que desinscribe un usuario del evento
	 * @param nuevo : usuario que se va a desinscribir
	 */
	public void desinscribir(Usuario nuevo) {
		for(int i = 0 ; i < inscritos.length ; i++) {
			if(null != inscritos[i]) {
				if(inscritos[i].equals(nuevo)) {
					inscritos[i] = null;
					nInscritos--;
				}
			}
		}
	}
	
	/**
	 * Metodo que busca un usuario según su nombre en los inscritos al evento
	 * @param nombre : nombre del usuario que se va a buscar
	 * @return indice del array deonde se encuentra el usuario, -1 si no es encuentra
	 */
	public int buscarUsuario(String nombre) {
		for(int i = 0 ; i < inscritos.length ; i++) {
			if(inscritos[i] != null) {
				if(inscritos[i].getNombre().equals(nombre)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Metodo que muestra las difernte categorias de los eventos y su indice
	 * @return cadena con la información
	 */
	public static String categoriasToString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < CATEGORIAS.length ; i++) {
			sb.append(i);
			sb.append(") ");
			sb.append(CATEGORIAS[i]);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * Metodo devuelve la información de cada usuario del array inscritos
	 * @return cadena con la informacion
	 */
	public String participantesToString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < inscritos.length ; i++) {
			if(inscritos[i] != null) {
				sb.append("ID: ");
				sb.append(i);
				sb.append(" | ");
				sb.append(inscritos[i].toString());
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}

	/**
	 * Metodo que comprueba si un evento no admite mas inscripciones
	 * @return true si el evento esta complet, false si no	
	 */
	public boolean estaCompleto() {
		return nInscritos >= inscritos.length;
	}
}
