import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		
		/*
		 * AQUI VA LA CREACIÓN DEL JUGADOR
		 * DEFINÍ AL GUERRERO COMO CLASE POR
		 * DEFECTO PARA CONVENIENCIA
		 * EL RESTO ES COMPLETAMENTE ESTÁNDAR
		 * */
		
		boolean heroe_creado = false;
		boolean jefe_creado = false;
		Jugador Heroe;
		
		
		System.out.println("Escribe el nombre de tu Héroe!: ");		
		String Nombre = in.nextLine();
		System.out.println("Ahora elige su clase, 1)Mago, 2)Guerrero, 3)Druida:");		
		int Clase = in.nextInt();
		
	
			if (Clase == 1) {
				Heroe = new Mago(Nombre);
				heroe_creado = true;
			}
			else if (Clase == 3) {	
				Heroe = new Druida(Nombre);	
				heroe_creado = true;
			}
			else {
				Heroe = new Guerrero(Nombre);	
				heroe_creado = true;
			}
		
		System.out.println("Ahora hay que crear un Mundo para salvar! Ingresa su tamaño[int]"); 
		int mundo_size = in.nextInt();
		Tierra Mundo[] = new Tierra[mundo_size];

		for (int i = 0; i<mundo_size; i++) {
			Monstruo NuevoMonstruo;
			Jefe_Final NuevoJefe; 	
			System.out.println();		
			System.out.println();	
			System.out.println(
			"---- CREACIÓN DE LA CASILLA: " + (i+1) + " ----");
		/*
		 * AQUI VA LA CREACIÓN DEL MONSTRUO
		 * NO HAY MUCHO MAS QUE AGREGAR
		 * */
			
			System.out.println();	
			System.out.println("Cada casilla tendrá un Monstruo con atributos diferentes...");
			System.out.println("Ingresa su Vida[int], Ataque[int] y Probablildad de Atacar[float]");		
			int vida_mnstr = in.nextInt();
			int dano_mnstr = in.nextInt();
			float prob_mnstr = in.nextFloat();
			in.nextLine();
			NuevoMonstruo = new Monstruo(vida_mnstr, dano_mnstr);

		/*
		 * AQUI VA LA CREACIÓN DEL JEFE COMO
		 * LA TAREA NO LO EXPLICITABA, DEJE
		 * QUE EL DM PUDIESE CREAR MAS DE UNO
		 * */

			int vida_jefe = 0;
			int dano_jefe = 0;	
	
			String nombre_jefe = "NULL";

			if (jefe_creado == false) {
				System.out.println("Además, habrá espacio para un Jefe, si deseas no insertarlo nómbralo NULL");
				nombre_jefe = in.nextLine();
				jefe_creado = true;

				System.out.println("Entonces ingresa su Vida[int[ y Daño Base[int]");{
					vida_jefe = in.nextInt();
					dano_jefe = in.nextInt();	
				}
			}
			NuevoJefe = new Jefe_Final(nombre_jefe, vida_jefe, dano_jefe);	
	

			System.out.println(
			"Además de Enemigos, habrán Aldeanos Amigables. Escribe su Nombre[String]:");
			String nombre_npc = in.nextLine();	

			int tipo_NPC;
			NPC NuevoNPC;

			System.out.println("Luego elige su tipo: Bueno(1), Neutro(2) o Malo(3)");
			int tipo_npc = in.nextInt();
			in.nextLine();
			if (tipo_npc == 2) {	
				System.out.println(
				"Los NPCs Neutros, te asignarán una Misión[1 ó 2], su Requsito[int] y Recompensa[int]");
				int mision_i = in.nextInt();	
				int requisito = in.nextInt();
				int reward = in.nextInt();
				char mision_c = 'v';
				if (mision_c == 2) {
					mision_c = 'm';
				}	
				NuevoNPC = new Neutro(nombre_npc, mision_c, requisito, reward);
			}
			
			else if (tipo_npc == 3) {	
				System.out.println(
				"Los NPCs Malos restarán cierto Maná[int] y Resistencia[int]");
				int mana_resta = in.nextInt();
				int energia_resta = in.nextInt();
				NuevoNPC = new Malo(nombre_npc, mana_resta, energia_resta);
			}
	
			else {
				System.out.println(
				"Los NPCs Buenos sumarán a tu Atributo[vida/xp/mana/energia] una Cantidad[int], ingresalos en ese orden");
				String atributo = in.nextLine();
				int cantidad = in.nextInt();
				NuevoNPC = new Bueno(nombre_npc, atributo, cantidad);
			}



		/*
		 * AQUI VA LA CREACIÓN DE LA TIERRA
		 * LA PLANICIE ES EL TIPO POR DEFECTO
		 * */

			System.out.println("Finalmente, toca decidir el tipo de Tierra que será ésta casilla!");
			System.out.println("Pudes elegir entre Planicie(1), Montaña(2), Bosque(3)");		
			int tipo_tierra = in.nextInt();
			boolean tierra_creada = false;	
			
			while (!tierra_creada) {



				if (tipo_tierra == 2) {
					Mundo[i] = new Montana(prob_mnstr, NuevoNPC, NuevoMonstruo, NuevoJefe);
					tierra_creada = true;		
				}
				else if (tipo_tierra == 3) {
					Mundo[i] = new Bosque(prob_mnstr, NuevoNPC, NuevoMonstruo, NuevoJefe);		
					tierra_creada = true;		
				}
				else {	
					Mundo[i] = new Planicie(prob_mnstr, NuevoNPC, NuevoMonstruo, NuevoJefe);	
					tierra_creada = true;
				}
			
			}
			System.out.println();	
	
		}
		System.out.println();	
		System.out.println("Dicho todo esto, inserta tu Personaje en alguna casilla[int]");
		int posicion = in.nextInt();
		int option;
		boolean gameplay = true;
		boolean inCombateMinion = false;	
		boolean inCombateJefe = false;
		int intTierra = 1;
		Random R = new Random();
		Graficos DosDe = new Graficos();


		if (!jefe_creado) {
			System.out.println("Parece que al DM se le olvidó crear un Jefe Final!");	
			System.out.println("y por ende hasta aquí llega tu aventura...");
			return;
		}
		
		
		
		while (gameplay) {
			posicion = posicion % mundo_size;
			if (posicion < 0) {posicion += mundo_size;}
			System.out.println(
			"Actualmente tu Héroe está en la casilla " + posicion + "!"		
			);

			if (Mundo[posicion] instanceof Planicie) {
				intTierra = 1;
				if (!Mundo[posicion].accion(Heroe.GetV(), Heroe.GetE(), Heroe)) {
					System.out.println("Tu Héroe no fue capáz de cruzar la zona, ya"); 
					System.out.println("que los secuacez del Emperador te hicieron");
					System.out.println("una emboscada!!");
					gameplay = false;
					break;
				}
			}
		
			if (Mundo[posicion] instanceof Bosque) {
				intTierra = 2;	
				if (!Mundo[posicion].accion(Heroe.GetV(), Heroe.GetM(), Heroe)) {	
					System.out.println("Tu Héroe no fue capáz de cruzar la zona, ya"); 
					System.out.println("que los secuacez del Emperador te hicieron");
					System.out.println("una emboscada!!");
					gameplay = false;
					break;
				}
			}
			if (Mundo[posicion] instanceof Montana) {
				intTierra = 3;
				if (!Mundo[posicion].accion(Heroe.GetV(), Heroe.GetE(), Heroe)) {
					System.out.println("Tu Héroe no fue capáz de cruzar la zona, ya"); 
					System.out.println("que los secuacez del Emperador te hicieron");
					System.out.println("una emboscada!!");
					gameplay = false;
					break;
				}
			}
			if (Mundo[posicion].socializar() instanceof Bueno) {
				Mundo[posicion].socializar().interaccion(Heroe);
			}	
			if (Mundo[posicion].socializar() instanceof Neutro) {
				Mundo[posicion].socializar().interaccion(Heroe);
			}
			if (Mundo[posicion].socializar() instanceof Malo) {
				Mundo[posicion].socializar().interaccion(Heroe);
			}
			
			Monstruo Minion = Mundo[posicion].GetMons();
			if ( R.nextFloat()%1.0 < Mundo[posicion].GetProb() && !Minion.esta_muerto()) {
				System.out.println();
				System.out.println("                     /   /   . .   ");
				System.out.println("       '   '        /   /   / / / /");
				System.out.println("--- OH NO, SE ACERCA UN ENEMIGO ---");		
				System.out.println("/ / / /   /   /     '    '         ");	
				System.out.println("   ' '   /   /                     ");	
				System.out.println();
				inCombateMinion = true;
			}

			if ( !Mundo[posicion].GetNombreJefe().equals("NULL")) {
				
				System.out.println("-----------------------------------");
				System.out.println("|      '   '        /   /   / / / |");
				System.out.println("|--  SE ACERCA EL JEFE FINAL    --|");		
				System.out.println("| / / /   /   /     '    '        |");	
				System.out.println("-----------------------------------");	
				
				System.out.println();
				System.out.println();
				System.out.println("DESEAS ENFRENTARLO???? [Escribe: Si, deseo pelear!]");	
				System.out.println();
				System.out.println();
				in.nextLine();
				String jefesiono = in.nextLine();
				if (jefesiono.equals("Si, deseo pelear!")) {
					inCombateMinion = true;
				}
			}
			Jefe_Final FinalBoss = Mundo[posicion].getJefe();
			while(inCombateMinion) {
				System.out.println(">>>>> JEFE FINAL " + FinalBoss.getNombre() + " <<<<<<");
				System.out.println();
				System.out.println(
				"1) Atacar  2) Lanzar Hechizo  3) Huir");
				System.out.println();
				option = in.nextInt();
				if (option == 2) {	
					int dano_j = Heroe.hechizo(Clase);
					System.out.println();	
					System.out.println("Sientes como el maná fluye por tus venas...");	
					System.out.println("la tierra se empieza a sacudir mientras el");	
					System.out.println("rugido de mil Dragones cae sobre el enemigo!");
					System.out.println();
					System.out.println("Le haces " + dano_j + " al Enemigo!");
					System.out.println();
					FinalBoss.combate(Heroe, dano_j, 0);
				}	
				else if (option == 3) {
					inCombateMinion = false;
					System.out.println();
					System.out.println("....Saliste corriendo");	
					System.out.println();
				}
				else {	
					int dano_j = Heroe.ataque(Clase);	
					System.out.println();	
					System.out.println("Te abalanzaste hacia el enemigo y lo moliste");	
					System.out.println("a golpes con tus propias manos!!!! >:( ");
					System.out.println();
					System.out.println("Le haces " + dano_j + " al Enemigo!");
					System.out.println();
					FinalBoss.combate(Heroe, dano_j, 0);
				}

				if (FinalBoss.esta_muerto()) {
					System.out.println("El enemigo yace inmóvil en el piso...");
					inCombateMinion = false;
					return;
				}
	
				if (Heroe.GetV() < 1) {
					System.out.println("Nuestro Héroe ha fallecido en combate...");
					gameplay = false;
					inCombateMinion = false;
					option = 9;
					return;
				}
				System.out.println();	
				System.out.println("El Montruo te araña con sus enormes garras");	
				System.out.println("dejándote con " + Heroe.GetV() + " puntos de vida!");	
				System.out.println();	
			}

			Heroe.subir_experiencia();

			DosDe.Renderizar(Clase, intTierra);
	
			System.out.println(
			"Opciones:"		
			);
			System.out.println(
			"1) Ir a la Izquierda       2) Ir a la Derecha"		
			);
			System.out.println(
			"3) Rezar [+5 Mana]         4) Descansar [+5 Energia]"		
			);
			System.out.println(
			"5) Ver Estado              6) Salir del Juego"		
			);	
			System.out.println();
			option = in.nextInt();
			if (option == 1) {
				posicion -= 1;
			}	
			else if (option == 2) {
				posicion += 1;
			}
			else if (option == 5) {
				Heroe.VerEstado();
			}
			else if (option == 3) {
				Heroe.Pray();
			}
			else if (option == 4) {
				Heroe.Rest();
			}
			else if (option > 5 ) {
				gameplay = false;
			}	
		}

		System.out.println();
		System.out.println("La aventura ha llegado a su fin... pero");	
		System.out.println("no temas! éste ramo aún no se acaba... y");
		System.out.println("aunque la contienda sea desigual, todavía");
		System.out.println("hay chance de pasar el ramo, incluso si");
		System.out.println("eso implique aprender Prolog!");
	}
}
