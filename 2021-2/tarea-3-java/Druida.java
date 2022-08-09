public class Druida extends Jugador {
	public Druida(String name) {
		super(name, 15, 5, 5, 5, 5);

		System.out.println(
		"-------- DRUIDA: " + name + " ---------");
		System.out.println(
		"La realeza debe ensuciarse las manos de vez en");
		System.out.println(
		"cuando para procurar el bien de su gente, y aquí");
		System.out.println(
		"estás tú... tienes un repertorio de habilidades");
		System.out.println(
		"equilibrado, y a pesár de tener que luchar contra");
		System.out.println(
		"quiénes fueron tus hermanos y hermanas, es hora de");
		System.out.println(
		"acabar con ésta tiranía de una vez... ");
		System.out.println();
	}

	public static int druid_ataque(int f, int i, int e, int m) {
		return ( (f+i)/3 * Math.max(e/3, m/2) );
	}
	public static int druid_hechizo(int f, int i, int e, int m) {
		return ( (f+i)/3 * Math.max(e/2, m/3) );	
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
			15 + (subir_experiencia()-1) ,
			5 + (subir_experiencia()-1),
			5 + (subir_experiencia()-1),
			5 + (subir_experiencia()-1),	
			5 + (subir_experiencia()-1)
			);	
	}
}

