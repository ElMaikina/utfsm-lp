public class Neutro extends NPC {
	private char requisito;
	private int valor;
	private int recompensa;
	public boolean ya_dio_mision;

	public Neutro(String N, char req, int v, int rew) {
		super(N);
		requisito = req;
		valor = v;
		recompensa = rew;
		ya_dio_mision = false;
	}
/*
 * Usamos el formato de nueva_mision  para darle misiones al jugador
 *
 *	public void nueva_mision(char req, int v, int c, int rew) {
 *		Misiones nueva_mision = new Misiones(req, v, c, rew);
 *		lista_misiones.add(nueva_mision);
 *	}
 *
 * */
	public void favor(Jugador J) {	
		System.out.println("Los Aldeanos que iban pasando se ven deprimidos,");
		System.out.println("ves como el malvado Emperador los ha despojado");
		System.out.println("de sus tierras. Te acercas y preguntas si puedes");
		System.out.println("ayudarlos de alguna manera....");
		
		J.nueva_mision(requisito, valor, 0, recompensa);
	}
	public void interaccion(Jugador J) {
		if (!ya_dio_mision) {
			ya_dio_mision = true;
			favor(J);
			return;
		} 
		System.out.println("Ya hablaste con éstos personajes!!!!!!");	
		return;
	}
}
