public class Bueno extends NPC {
	private String atributo;
	private int cantidad;	

	public Bueno(String N, String A, int c) {
		super(N);
		atributo = A;
		cantidad = c;
	}
	/*
	* Utilizamos la funcion alterar, recordamos que
	* usa el formato siguiente:
	* 	public void alterar(int v, int x, int f, int i, int e, int m) {
	*
	* */
	
	public void interaccion(Jugador J) {
		System.out.println("Los Aldeanos que iban pasando cerca se emocionan");
		System.out.println("al ver al Héroe de la leyenda. Ves como se acercan");
		System.out.println("hacía tí y que llevan una canasta con comida y agua.");
		System.out.println("Te ganan con su generosidad y recibes la ofrenda.");
		
		if (atributo.equals("vida")) {
			J.alterar(cantidad,0,0,0,0,0);
		}
		if (atributo.equals("xp")) {
			J.alterar(0,cantidad,0,0,0,0);
		}
		if (atributo.equals("energia")) {
			J.alterar(0,0,0,0, cantidad,0);
		}
		if (atributo.equals("mana")) {
			J.alterar(0,0,0,0,0, cantidad);
		}
		System.out.println();
		System.out.println("¡¡¡¡Te haz regenerado!!!!");
		J.VerEstado();	
	}
}
