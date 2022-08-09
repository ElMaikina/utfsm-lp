import java.util.Scanner;

public class Javation {
	public static void main(String[] args) {
		// Crea el Scanner para recibir input del Jugador
		Scanner input = new Scanner(System.in);
		
		// Creación del Jugador
		System.out.println("Ingrese el nombre del Jugador  ");
		String nombreJugador = input.nextLine();
		
		Jugador player = new Jugador(nombreJugador);
		boolean game_over = false;
		int option = 0;

		// Le entrega un nombre a la ciudad
		System.out.println("Ingrese el nombre de la Ciudad  ");
		String nombreCiudad = input.nextLine();
		
		System.out.println("\nBIENVENIDO A " + nombreCiudad + " LA MEJOR JavaCiudad DEL MUNDO ENTERO!!!\n");

		// Mientras el juego siga, preguntará al jugador por las acciones a tomar
		while (!game_over) {

			player.verStats();

			System.out.println("¿Que desea hacer? ");
			System.out.println("\n	1) Crear		5) Libres");
			System.out.println("	2) Mejorar		6) Ver Ciudad");
			System.out.println("	3) Mover		7) Ver Otros");
			System.out.println("	4) Sacar		8) Salir\n");
			
			// Precios de creacion u mejora
			int p_jl = 0;
			int p_hi = 0;
			int p_tr = 0;
			int p_tec = 0;

			option = input.nextInt();			
			// Crea una Persona, Edificio o Atraccion
			if (option == 1) {
				System.out.println("¿Que desea crear?");
				System.out.println("	1) Persona");
				System.out.println("	2) Edificio");
				System.out.println("	3) Atraccion");
				int subOption = input.nextInt();			
			
				// La primera opcion crea una Persona	
				if (subOption == 1) {
					System.out.println("¿De que tipo?");
					System.out.println("	1) Granjero");
					System.out.println("	2) Herrero");
					System.out.println("	3) Cientifico");
					int tipoPersona = input.nextInt();
					input.nextLine();

					Persona p;
					System.out.println("Ingrese el nombre de la Persona ");
					String nombrePersona = input.nextLine();
				
					if (tipoPersona == 1) { 
						p = new Granjero(nombrePersona); 
						p_jl = 5;
					}
					if (tipoPersona == 2) { 
						p = new Herrero(nombrePersona); 
						p_jl = 6;
					} 
					if (tipoPersona == 3) { 
						p = new Cientifico(nombrePersona); 
						p_jl = 8;
					}
					else {
						p = new Granjero(nombrePersona); 
						p_jl = 5;
					}
					player.pagar(p_jl, p_hi, p_tr, p_tec);
					player.agregar_persona(p);
				}
				// La segunda oopcion crea un Edificio
				if (subOption == 2) {
					System.out.println("¿De que tipo?");
					System.out.println("	1) Granero");
					System.out.println("	2) Laboratorio");
					System.out.println("	3) Herreria");
					System.out.println("	4) Zona Común");
					int tipoEdifico = input.nextInt();
					input.nextLine();

					Edificio e;
					System.out.println("Ingrese el nombre del Edificio ");
					String nombreEdifico = input.nextLine();
					
					if (tipoEdifico == 1) { 
						e = new Granero(nombreEdifico); 
						p_jl = 10;
						p_hi = 5;
						p_tr = 5;
					}
					if (tipoEdifico == 2) { 
						e = new Laboratorio(nombreEdifico); 
						p_jl = 30;
						p_hi = 10;
					} 
					if (tipoEdifico == 3) { 
						e = new Herreria(nombreEdifico); 
						p_jl = 20;
						p_hi = 20;
					}
					if (tipoEdifico == 4) { 
						e = new ZonaComun(nombreEdifico); 
						p_jl = 15;
						p_hi = 3;
						p_tr = 3;
						p_tec = 3;
					}
					else {
						e = new ZonaComun(nombreEdifico); 
						p_jl = 15;
						p_hi = 3;
						p_tr = 3;
						p_tec = 3;
					}
					player.pagar(p_jl, p_hi, p_tr, p_tec);
					player.agregar_edificio(e);
				}
				// Si no, crea una Atraccion
				if (subOption == 3) {
					System.out.println("¿De que tipo?");
					System.out.println("	1) Feria");
					System.out.println("	2) Museo");
					System.out.println("	3) Javapato");
					int tipoAtraccion = input.nextInt();
					input.nextLine();
					
					System.out.println("Ingrese el nombre la Atraccion ");
					String nombreAtraccion = input.nextLine();

					if (tipoAtraccion == 1) {
						Javapato jp = new Javapato(nombreAtraccion);
						p_jl = 50;
						p_hi = 40;
						p_tr = 0;
						p_tec = 20;

						player.pagar(p_jl, p_hi, p_tr, p_tec);
						player.agregar_javapato(jp);
					} 
					if (tipoAtraccion == 2) {
						Feria f = new Feria(nombreAtraccion);
						p_jl = 50;
						p_hi = 25;
						p_tr = 25;
						p_tec = 10;

						player.pagar(p_jl, p_hi, p_tr, p_tec);
						player.agregar_feria(f);
					} 
					if (tipoAtraccion == 3) {
						Museo m = new Museo(nombreAtraccion);
						p_jl = 50;
						p_hi = 39;
						p_tr = 3;
						p_tec = 30;

						player.pagar(p_jl, p_hi, p_tr, p_tec);
						player.agregar_museo(m);
					} 
				}
			}
		
			// Mejora una Persona o Edificio
			if (option == 2) {
				// Pregunta por pantalla que tipo de Objeto mejorar
				System.out.println("¿Que desea mejorar?");
				System.out.println("	1) Persona");
				System.out.println("	2) Edificio");
				int subOption = input.nextInt();
				input.nextLine();

				System.out.println("Ingrese el nombre a mejorar ");
				String nombre = input.nextLine();

				//Mejora a una Persona
				if (subOption == 1) {
					player.mejorar_persona(nombre);	
				}
				//Mejora un Edificio
				if (subOption == 2) {
					player.mejorar_edificio(nombre);	
				}
			}

			// Mover una Persona a un Edificio
			if (option == 3) {
				input.nextLine();
				Persona trasladada;
				player.mostrar_libres();
				System.out.println("¿Que Persona? ");
				String nombre = input.nextLine();
				
				// Busca la Persona por su nombre
				trasladada = player.getPersona(nombre);

				// La borra de la lista de no asignados
				player.sacar_persona(nombre);

				// La agrega al Edificio que tenga el nombre
				player.mostrar_asignadas();
				System.out.println("¿A que Edificio? ");
				String nombreEdificio = input.nextLine();

				player.PersonaEdificio(trasladada, nombreEdificio);
			}

			// Sacar una Persona de un Edificio
			if (option == 4) {
				input.nextLine();
				player.mostrar_asignadas();
				System.out.println("¿De que Edificio? ");
				String nombreEdificio = input.nextLine();

				// Pregunta por el nombre de la nombre de la Persona
				System.out.println("¿Que Persona? ");
				String nombre = input.nextLine();

				// Busca la Persona por su nombre
				player.EdificioPersona(nombreEdificio, nombre);
			}

			// Mostrar Personas no asignadas
			if (option == 5) {
				player.mostrar_libres();
			}

			// Mostrar los nombres de todos los Edificios
			// construidos, incluyendo a las Personas
			if (option == 6) {
				player.mostrar_asignadas();
			}

			// Mostrar nombres de todas las Atracciones
			if (option == 7) {
				player.mostrar_atracciones();
			}

			// Terminar el juego
			if (option > 7) {
				game_over = true;
			}
			player.producir();
			player.envejecer();

			// Si el saldo es negativo el juego se termina
			if (!player.check_presupuesto()) {
				System.out.println("-- TE QUEDASTE SIN SALDO!!! --");
				game_over = true;
			}
		}
		input.close();
	}
}
