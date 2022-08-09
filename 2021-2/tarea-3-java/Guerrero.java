public class Guerrero extends Jugador {
	public Guerrero(String name) {
		super(name, 20, 9, 1, 10, 2);

		System.out.println(
		"-------- GUERRERO: " + name  + " ---------");
		System.out.println(
		"En las aldeas más remotas del mundo, surgió un");
		System.out.println(
		"humano con fuerza sobrenatural, dotado con los");
		System.out.println(
		"músculos de hércules y las agallas de acero.");
		System.out.println(
		"No tuviste la oportunidad a una educación digna");
		System.out.println(
		"sin embargo tu corazón es puro, y el pueblo");
		System.out.println(
		"deposita su eterna fé en tí... ");
		System.out.println();
	}
	public static int guerr_ataque(int f, int i, int e) {
		return (f*2) + e;
	}
	public static int guerr_hechizo(int f, int i, int m) {
		return (i*f/4) + m;
	}
	/*
	 * En vez de tener algun gatillante para saber cuando subir los
	 * atributos del Mago, decidí concentrarlo en una formula distinta
	 * para cada atributo que asigna los stats para cualquier nivel,
	 * me basé fórmulas de sumatoria básica, así que debería ser
	 * bastante intuitivo a la vista 
	 * */
	public void guerr_levelear() {
		redefinir(
			20 + (subir_experiencia()-1) * 3,
			9 + (subir_experiencia()-1) * 5,
			1 + (subir_experiencia()-1),
			10 + (subir_experiencia()-1) * 2,
			2 + (subir_experiencia()-1)
			);	
	}
}
