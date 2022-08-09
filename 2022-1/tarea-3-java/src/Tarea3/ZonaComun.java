public class ZonaComun extends Edificio {
	public ZonaComun(String n) {
		super(n);
		setCapacidad(6);
	}
	public int[] producir() {
		int[] produccion = {0, 0, 0, 0};
		int gente = getPersonas().size();
		produccion[0] += gente; 
		return produccion;
	}
	public void mejorar() {
		addCapacidad(4);
	}
}
