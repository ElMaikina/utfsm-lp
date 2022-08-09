public class Museo implements Atraccion {
	private String nombre;

	public Museo(String n) {
		nombre = n;
	}

        public int visitar(Jugador p) {
		int granjeros = p.getGranjeros();
		int cientificos = p.getCientificos();
		int herreros = p.getHerreros();

		int result = granjeros;
		result += 4 * cientificos;
		result += 2 * herreros;

		return result;
	}
	String getNombre() {
		return nombre;
	}
}


