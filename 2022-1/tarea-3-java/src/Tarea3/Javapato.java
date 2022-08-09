public class Javapato implements Atraccion {
 	private String nombre;
	
	public Javapato(String n) {
		nombre = n;
	}
        
	public int visitar(Jugador p) {
		int granjeros = p.getGranjeros();
		int cientificos = p.getCientificos();
		int herreros = p.getHerreros();

		int result = 3 * granjeros;
		result += 3 * cientificos / 2;
		result += 3 * herreros;

		return result;
	}
	String getNombre() {
		return nombre;
	}
}

