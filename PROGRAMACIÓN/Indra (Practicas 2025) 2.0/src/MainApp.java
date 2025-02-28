import java.time.LocalDate;
import java.util.Scanner;

// AUTOR: Pablo Burgos DAM 1M

public class MainApp {
	
	// Número máximo de usuarios registrados que admite el programa
	final static public int MAX_USUARIOS_REGISTRADOS = 50;
	// Número máximo de organizadores registrados que admite el programa
	final static public int MAX_ORGANIZADORES_REGISTRADOS = 5;
	// Número máximo de eventos registrados que admite el programa
	final static public int MAX_EVENTOS_REGISTRADOS = 15;
	
	// Lista de eventos registrados
	static Evento[] eventos = new Evento[MAX_EVENTOS_REGISTRADOS];
	// Contador de eventos registrados
	static int numEventos = 0;
	// Lista de usuarios registrados
	static Usuario[] usuarios = new Usuario[MAX_USUARIOS_REGISTRADOS];
	// Contador de usuarios registrados
	static int numUsuarios = 0;
	// Lista de organizadores registrados
	static Organizador[] organizadores = new Organizador[MAX_ORGANIZADORES_REGISTRADOS];
	// Contador de ogranizadores registrados
	static int numOrganizadores = 0;
	
	// Lista de mensajes de los menús que se muestran por consola
	final static String[] MENSAJES_MENU = {
			"Elija una opción:\n1) Iniciar sesión como usuario\n2) Iniciar sesión como organizador\n3) Cerrar programa",
			"Elija una opción:\n1) Iniciar sesión\n2) Registrarse \n3) Volver",
			"Elija una opción:\n1) Agregar evento\n2) Modificar un evento\n3) Ver asistentes a tus eventos\n4) Cerrar sesión",
			"Elija el dato que quieres cambiar:\n1) Nombre\n2) Categoria\n3) Fecha\n4) Duracion\n5) Lugar\n6) Salir",
			"Elija una opción\n1) Ver eventos\n2) Ver los eventos a los que estoy inscrito\n3) Apuntarme a evento\n4) Desapuntarme a evento\n5) Cerrar sesión"
	};
	
	// scanner por el cual se van a introducir datos por teclado durante todo el programa
	// se cierra al elegir la opcion cerrar programa
	static Scanner teclado = new Scanner(System.in);
	
	// Metodo Main
	public static void main(String[] args) {
		
		menuInicial(); // Se hace una unica llamada al metodo
		
	}
	
	static void menuInicial() {
		int opc;
		
		do {
			System.out.println(MENSAJES_MENU[0]);
			opc = teclado.nextInt();
			
			switch(opc) {
				case 1 -> {menuLogUsuario(); break;}
				case 2 -> {menuLogOrganizador(); break;}
				case 3 -> {System.out.printf("Saliendo del programa...%n"); teclado.close(); break;}
				default -> {System.err.printf("Opción incorrecta. Vuelva a intentarlo:%n"); break;}
			}
			
		} while(opc != 3);
		
	}
	
	static void menuLogOrganizador() {
		
		int opc;
		
		do {
			System.out.println(MENSAJES_MENU[1]);
			opc = teclado.nextInt();
			
			switch(opc) {
				case 1 -> {
							Organizador org = loggearOrganizador();
							if(org == null) {
								break;
							} else {
								gestionOrganizador(org);
							}
				}
				case 2 -> { registrarOrganizador(); break;}
				case 3 -> {System.out.printf("Volviendo al menú inicial...%n");}
				default -> {System.err.printf("Opción incorrecta. Vuelva a intentarlo:%n"); break;}
			}
			
		} while(opc != 3);
	
		
	}
	
	static void registrarOrganizador() {
		
		if(numOrganizadores < organizadores.length) {
		
			System.out.println("Introduce el nombre del organizador");
			String nombre = teclado.next();
			
			while(nombre == null || nombre.isBlank() || nombre.isEmpty() || Util.contarEnArray(organizadores, new Organizador(nombre , null , null,null)) != 0) {
				System.err.println("Nombre no válido. Vuelva a intentarlo");
				nombre = teclado.next();
			}
			
			System.out.println("Introduce el correo del organizador:");
			String correo = teclado.next();
			
			System.out.println("Introduce el teléfono del organizador:");
			String tel = teclado.next();
			
			System.out.println("Introduce la contraseña del organizador:");
			String pass = teclado.next();
			
			organizadores[Util.buscarUltimo(organizadores)] = new Organizador(nombre,correo,tel,pass);
			numOrganizadores++;
			System.out.println("Organizador registrado correctamente");
		} else {
			System.err.println("Ya se ha alcanzado el número máximo de organizadores registrados");
		}
	}

	/**
	 * Metodo que a partir de  un nombre y contraseña, inicia sesión en un organizador
	 * @return organizador a partir de su nombre y contraseña, si no se encuentra o no coinciden, se devuelve null
	 */
	static Organizador loggearOrganizador() {
		
		System.out.println("Introduce el nombre del organizador:");
		String nombre = teclado.next();
		System.out.println("Introduce la contraseña:");
		String contra = teclado.next();
		
		int id = Util.buscar(organizadores, nombre);
		
		if(id == -1) {
			System.err.println("Usuario inexistente.");
			return null;
		} else {
			if(organizadores[id].checkPass(contra)) {
				System.out.println("Sesión iniciada correctamente");
				return organizadores[id];
			} else {
				System.err.println("Usuario o contraseña incorrectos.");
				return null;
			}
		}
		
		
		
	}
	
	static void gestionOrganizador(Organizador organizador) {
		int opc;
		System.out.println("Sesión iniciada correctamente.");
		do {
			System.out.println(MENSAJES_MENU[2]);
			opc = teclado.nextInt();
			
			switch(opc) {
				case 1 -> {addEvento(organizador); break;}
				case 2 -> {modificarEvento(organizador); break;}
				case 3 -> {mostrarEventos(organizador,true); break;}
				case 4 -> {System.out.println("Cerrando sesión..."); break;}
				default -> {System.err.printf("Opción incorrecta. Vuelva a intentarlo:%n"); break;}
			}
			
		} while(opc != 4);
	}
	
	static void addEvento(Organizador org) {
		
		if(numEventos < eventos.length) {
			System.out.println("Introduce el nombre del evento:");
			String nombre = teclado.next();
			
			System.out.printf("Introduce el número de categoría del evento:%n%s%n",Evento.categoriasToString());
			int categoria = teclado.nextInt();	
			
			System.out.println("Introduce año del evento:");
			int year = teclado.nextInt();
			System.out.println("Introduce mes del evento:");
			int mes = teclado.nextInt();
			System.out.println("Introduce año del evento:");
			int dia = teclado.nextInt();
			
			System.out.println("Introduce la duración en minutos del evento:");
			int duracion = teclado.nextInt();
			
			System.out.println("Introduce la ubicación del evento:");
			String lugar = teclado.next();
			
			eventos[Util.buscarUltimo(eventos)] = new Evento(nombre,categoria,LocalDate.of(year,mes, dia),duracion,lugar,org);
			numEventos++;
		
		} else {
			System.out.println("Ya se ha alcanzado el número máximo de eventos");
		}
	}
	
	// Muestra los eventos de un organizador concreto
	static void mostrarEventos(Organizador org , boolean mostrarParticipantes) {
		for(int i = 0 ; i < eventos.length ; i++) {
			if(eventos[i] != null) {
				if(eventos[i].getOrganizador().equals(org)) {
					System.out.println("ID: " + i + ". " + eventos[i].toString());
					
					if(mostrarParticipantes){
						System.out.printf("Inscritos: %s%n",eventos[i].participantesToString());
						
					}
				}
			}
		}
	}

	static void mostrarEventos() {
		for(int i = 0 ; i < eventos.length ; i++) {
			if(null != eventos[i]) {
				System.out.printf("ID: %d. %s%n",i,eventos[i].toString());
			}
		}
	}
	
	// Muestra los eventos donde el usuario u esta inscrito
	static void mostrarEventos(Usuario u) {
		System.out.printf("Eventos a los que %s esta inscrito:%n", u.getNombre());
		for(int i = 0 ; i < eventos.length ; i++) {
			if(null != eventos[i]) {
				if(eventos[i].participantesToString().contains(u.getNombre())) {
					System.out.printf("ID: %d. %s%n",eventos[i].toString());
				}
			}
		}
	}
	
	// Modifica una un evento de un organziador
	static void modificarEvento(Organizador org) {
		
		boolean tiene = false;
		
		for(Evento e : eventos) {
			if(null != e) {
				if(e.getOrganizador().equals(org)) {
					tiene = true;
					break;
				}
			}
		}
		
		if(!tiene) {
			System.err.println("Este organizador aún no tiene eventos a su nombre.");
			return;
		}
		
		mostrarEventos(org,false);
		System.out.println("Introduce el id del evento que deseas modificar:");
		int evento = teclado.nextInt();
		
		if(null == eventos[evento] || !eventos[evento].getOrganizador().equals(org)) {
			System.err.println("El evento no existe o no es de este organizador.");
			return;
		}
			
		System.out.println(MENSAJES_MENU[3]);
		int opc = teclado.nextInt();
		
		switch(opc) {
		
		case 1 ->{
			System.out.println("Introduce el nuevo nombre del evento:");
			eventos[evento].setNombre(teclado.next());
			break;
			}
		
		case 2 -> {
			System.out.println("Introduce el indice de la nueva categoria del evento:");
			System.out.println(Evento.categoriasToString());
			eventos[evento].setCategoria(teclado.nextInt());
			break;
			}
		
		case 3 -> {
			System.out.println("Introduce el nuevo año, mes y día del evento:");
			eventos[evento].setFecha(LocalDate.of(teclado.nextInt(), teclado.nextInt(), teclado.nextInt()));
			break;
			}
		
		case 4 -> {
			System.out.println("Introduce la nueva duración del evento:");
			eventos[evento].setDuracion(teclado.nextInt());
			break;
			}
		case 5 ->{
			System.out.println("Introduce el nuevo lugar del evento:");
			eventos[evento].setLugar(teclado.next());
			break;
			}
		case 6 -> {
			System.out.println("Volviendo al menu...");
			break;
		}
		
		default -> {System.err.println("Opción no válida. Volviendo al menú...");}
		}

	
	}

	static void menuLogUsuario() {
		int opc;
		
		do {
			System.out.println(MENSAJES_MENU[1]);
			opc = teclado.nextInt();
			
			switch(opc) {
			
			case 1 -> {
				Usuario u = loggearUsuario();
				if(null != u) {
					menuUsuario(u); 
				}
				break;
				}
			case 2 -> {registrarUsuario(); break;}
			case 3 -> {System.out.println("Volviendo al menú principal..."); break;}
			default -> {System.err.println("Opción no válida. Vuelva a intentarlo."); break;}
			}
			
		} while (opc != 3);
		
	}

	static void registrarUsuario() {
		if(numUsuarios < usuarios.length) {
			System.out.println("Introduce el nombre del usuario:");
			String nombre = teclado.next();
			
			System.out.println("Introduce el correo del usuario:");
			String correo = teclado.next();
			
			System.out.println("Introduce la contraseña del usuario:");
			String pass = teclado.next();
			
			if(null == nombre || null == correo || null == pass) {
				System.err.println("Alguno de los campos estan vacíos.");
				return;
			}
			
			usuarios[Util.buscarUltimo(usuarios)] = new Usuario(nombre,correo,pass);
			numUsuarios++;
			
			System.out.println("Usuario registrado correctamente.");
			
		} else {
			System.err.println("Ya se ha alcanzado el número máximo de usuarios");
		}
	}

	/**
	 * Metodo que inicia sesión de usuario a partir de su nombre y contraseña
	 * @return usuario indicado, si no se encuentra o no coincide se devuelve null
	 */
	static Usuario loggearUsuario() {
		System.out.println("Introduce el nombre de usuario:");
		String nombre = teclado.next();
		
		System.out.println("Introduce la contraseña:");
		String pass = teclado.next();
		
		int id = Util.buscar(usuarios, nombre);
		
		if(id == -1) {
			System.err.println("Usuario o contraseña incorrectos");
			return null;
		}
		
		if(!usuarios[id].checkPass(pass)) {
			System.err.println("Usuario o contraseñas incorrectos");
			return null;
		}
		return usuarios[id];
	}

	static void menuUsuario(Usuario u) {
		int opc;
		
		do {
			
			System.out.println(MENSAJES_MENU[4]);
			opc = teclado.nextInt();
			
			switch(opc) {
				
				case 1 -> {mostrarEventos(); break;}
				case 2 -> {mostrarEventos(u); break;}
				case 3 -> {inscribir(u); break;}
				case 4 -> {desinscribir(u); break;}
				case 5 -> {System.out.println("Cerrando sesión..."); break;}
				default -> {System.err.println("Opción inválida. Vuelva a introducirla:"); break;}
				
			}
			
		} while(opc != 5);
	}

	static void inscribir(Usuario u) {
		System.out.println("Introduce el id del evento:");
		int id = teclado.nextInt();
		
		if(null == eventos[id]) {
			System.err.println("Evento no encontrado.");
			return;
		}
		
		if(eventos[id].estaCompleto()) {
			System.err.println("Evento ya completo.");
			return;
		}
		
		eventos[id].inscribir(u);
		System.out.println("Inscripción completada correctamente");
	}

	static void desinscribir(Usuario u) {
		System.out.println("Introduce el id del evento.");
		int id = teclado.nextInt();
		
		if(null == eventos[id]) {
			System.err.println("Evento no encontrado");
			return;
		}
		
		if(!eventos[id].participantesToString().contains(u.getNombre())) {
			System.err.println("No estas inscrito a este evento");
			return;
		}
		
		
		eventos[id].desinscribir(u);
		System.out.println("Usuario desinscrito correctamente.");
	}
}

