import java.util.LinkedList;
import java.util.List;

public class Jugador {
	private String nombre;
	private int vida;
	private int xp;	
	private int fuerza;
	private int inteligencia;
	private int energia;
	private int mana;
	private List<Misiones> lista_misiones = new LinkedList<>();

	/*
	 * Constructor de la Clase
	 */
	public Jugador(String name, int v, int f, int i, int e, int m) {
		nombre = name;
		vida = v;
		xp = 0;
		fuerza = f;
		inteligencia = i;
		energia = e;
		mana = m;
	}
	public void VerEstado() {
		System.out.println();
		System.out.println("| Nombre de tu Héroe: " + nombre);	
		System.out.println("---------------------------------");
		System.out.println("| Vida: " + vida);	
		System.out.println("| Nivel: " + subir_experiencia());	
		System.out.println("| Energia: " + energia);
		System.out.println("| XP: " + xp);
		System.out.println("| Fuerza: " + fuerza);	
		System.out.println("| Inteligencia: " + inteligencia);	
		System.out.println();
	}
	public int GetV() {
		return vida;
	}
	public int GetM() {
		return mana;
	}
	public int GetE() {
		return energia;
	}
	public void Rest() {
		energia += 5;
	}
	public void Pray() {
		mana += 5;
	}
	/*
	 * Funcion Extra que cumple el propósito de aumentar o reducir
	 * cualquiera de los atributos del Jugador. Ya sea para subir
	 * de nivel o interactuar con NPCs.
	 * */
	public void alterar(int v, int x, int f, int i, int e, int m) {
		vida += v;
		xp += x;
		fuerza += f;
		inteligencia += i;
		energia += e;
		mana += m;
	}
	public void redefinir(int v, int x, int f, int i, int m) {
		vida = v;
		xp = x;
		fuerza = f;
		inteligencia = i;
		mana = m;
	}
	/*Recordamos que el constructor de la clase misiones es:
	 *
	 * public Misiones(char req, int v, int c, int rew)
	 *
	 * */
	public void nueva_mision(char req, int v, int c, int rew) {
		Misiones nueva_mision = new Misiones(req, v, c, rew);
		lista_misiones.add(nueva_mision);
	}

	/*
	 * Accede a las funciones de su hijo para calcular como se
	 * llevará a cabo el Ataque
	 * */
	public int ataque(int clase) {
		if (energia <= 0) {
			return 0;
		}
		else {
			if (clase == 1) {
				energia -= 3;
				return Mago.mago_ataque(fuerza, inteligencia, energia);
			}
			if (clase == 2) {
				energia -= 5;
				return Guerrero.guerr_ataque(fuerza, inteligencia, energia);
			}
			if (clase == 3) {
				energia -= 3;
				return Druida.druid_ataque(fuerza, inteligencia, energia, mana);
			}
		}
		return 0;
	}
	/*
	 * Al igual que el Ataque, accede a las funciones de su hijo
	 * para calcular el efecto del Hechizo	
	 * */
	public int hechizo(int clase) { 
		if (mana <= 0) {
			return 0;
		}
		else {
			if (clase == 1) {
				mana -= 5;
				return Mago.mago_hechizo(fuerza, inteligencia, mana);
			}
			if (clase == 2) {
				mana -= 3;
				return Guerrero.guerr_hechizo(fuerza, inteligencia, mana);
			}
			if (clase == 3) {
				energia -= 3;
				return Druida.druid_hechizo(fuerza, inteligencia, energia, mana);
			}
		}
		return 0;
	}
	/*
	 * Determina el nivel actual del jugador e incrementa sus
	 * caracteristicas dependiendo de su clase y nivel
	 * */
	public int subir_experiencia() {
		/*TODO: Subir atributos por cada clase distintia*/
		if (xp < 10) { 
			return 1;	
		}
		if (xp < 25) { 
			return 2;	
		}	
		if (xp < 50) { 
			return 3;	
		}
		if (xp < 100) { 
			return 4;	
		}
		if (xp < 200) { 
			return 5;	
		}
		if (xp < 350) { 
			return 6;	
		}
		if (xp < 600) { 
			return 7;	
		} 
		return 8;		
	}
}








