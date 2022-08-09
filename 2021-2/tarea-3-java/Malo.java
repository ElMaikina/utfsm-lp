public class Malo extends NPC {
	private int cantidad_energia;
	private int cantidad_mana;

	public Malo(String N, int e, int m) {
		super(N);
		cantidad_energia = e;
		cantidad_mana = m;
	}
	/*
	* Utilizamos la funcion alterar, recordamos que
	* usa el formato siguiente:
	* 	public void alterar(int v, int x, int f, int i, int e, int m) {
	*
	* */
	
	public void interaccion(Jugador J) {
		System.out.println("Luego de un largo viaje caminando, avistas");
		System.out.println("a un viajero que cojea... tu buena voluntad");
		System.out.println("te obliga a hablarle, sin embargo solo era");
		System.out.println("un Aldeano borracho pidiendo maná para conjurar");
		System.out.println("más Whisky mágico... accedes de todas formas");
		System.out.println("(porque eres el Héroe) pero ahora tienes menos");
		System.out.println("maná y energía (ya que su insistencia te agotó).");
		
		J.alterar(0,0,0,0,-cantidad_energia,-cantidad_mana);
	}
}
