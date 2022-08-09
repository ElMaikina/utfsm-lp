public class Mago extends Jugador {
	public Mago(String name) {
		super(name, 10, 3, 10, 6, 15);

		System.out.println(
		"-------- MAGO: " + name  + " ---------");
		System.out.println(
		"Estaba escrito en la profesía que llegaría a la");
		System.out.println(
		"tierra un humano capáz de derrocar la tiranía del");
		System.out.println(
		"malévolo emperador. Un héroe capáz de conjurar los");
		System.out.println(
		"más letales hechizos, volver vivos a los muertos y");
		System.out.println(
		"traer paz a estas desgraciadas tierras.");
		System.out.println(
		"Ve adelante héroe, la gente aclama tu llegada... ");	
		System.out.println();
	}
	public static int mago_ataque(int f, int i, int e) {
		return (f*(i)/4 + e);
	}
	public static int mago_hechizo(int f, int i, int m) {
		return (i*(f)/4 + m);
	}
	/*
	 * En vez de tener algun gatillante para saber cuando subir los
	 * atributos del Mago, decidí concentrarlo en una formula distinta
	 * para cada atributo que asigna los stats para cualquier nivel,
	 * me basé fórmulas de sumatoria básica, así que debería ser
	 * bastante intuitivo a la vista 
	 * */
	public void mago_levelear() {
		redefinir(
			10 + (subir_experiencia()-1) * 2,
			3 + (subir_experiencia()-1),
			10 + (subir_experiencia()-1),
			6 + (subir_experiencia()-1),
			15 + (subir_experiencia()-1) *3
			);	
	}
}

