public class Feria implements Atraccion {
	private String nombre;
	
	public Feria(String n) {
		nombre = n;
	}

        public int visitar(Jugador p) {
		int granjeros = p.getGranjeros();
		int cientificos = p.getCientificos();
		int herreros = p.getHerreros();

		int result = 2 * granjeros;
		result += cientificos;
		result += 2 * herreros / 3;

		return result;
	}
	String getNombre() {
		return nombre;
	}
}

