public class Granero extends Edificio {
    public Granero(String n) {
        super(n);
        setCapacidad(10);
    }
	public int[] producir() {
        int[] produccion = {0, 0, 0, 0};
        int gente = getPersonas().size();

        // Cantidad de javalares producidos
        if (getGranjeros() > gente / 2) {
            for (int i = 0; i < gente; i++) {
                Persona actual = getPersona(i);
                produccion[0] += actual.trabajo_realizado() / gente; 
            }
            // Cantidad de trigo producido
            for (int i = 0; i < gente; i++) {
                Persona actual = getPersona(i);
                produccion[2] += actual.trabajo_realizado() / gente; 
            }
            // Cantidad de teconologia producida
            produccion[3] = getCientificos() % 2;
        }
        else { produccion[0] = -2 * gente; }
        return produccion;
    }
	public void mejorar() {
        addCapacidad(3);
    }
}
