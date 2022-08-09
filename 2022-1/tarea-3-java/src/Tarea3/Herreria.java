public class Herreria extends Edificio {
	public Herreria(String n) {
		super(n);
		setCapacidad(8);
	}
	public int[] producir() {
		int[] produccion = {0, 0, 0, 0};
		int gente = getPersonas().size();
		// Cantidad de javalares producidos
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Herrero) {
				// Se suman los trabaajos realizados de todos los herreros
				int suma = actual.trabajo_realizado() / gente;
				produccion[0] += suma; 
			} 
		}
		// Cantidad de hierro producido
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Herrero) {
				// Se suman los trabaajos realizados de todos los herreros
				int suma = actual.trabajo_realizado() / (gente - getHerreros());
				produccion[1] += suma; 
			} 
		}
		// Cantidad de tecnologia producido
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Cientifico) {
				// Se suman los trabaajos realizados de todos los herreros
				int suma = actual.trabajo_realizado() / getCientificos();
				produccion[3] += suma; 
			} 
		}
		return produccion;
	}
	public void mejorar() {
		addCapacidad(4);
	}
}
