public class Laboratorio extends Edificio {
    public Laboratorio(String n) {
        super(n);
        setCapacidad(5);
    }
	public int[] producir() {
        int[] produccion = {0, 0, 0, 0};
        int gente = getPersonas().size();
        // Cantidad de javalares producidos
        // Si se la cantidad de cientificos es mayor a 4/3 de gente
        if (getCientificos() > 3 * gente / 4) {
            for (int i = 0; i < gente; i++)
            {
                Persona actual = getPersona(i);
                if (actual instanceof Cientifico) {

                    // Se suman los trabaajos realizados de todos los cientificos
                    int suma = actual.trabajo_realizado() / gente;
                    suma *= (gente - getCientificos());
                    produccion[0] += suma; 
                } 
            }
            // Cantidad de tecnologia producido
            for (int i = 0; i < gente; i++) {
                Persona actual = getPersona(i);
                // Si es cientifico, su trabajo suma a la produccion
                if (actual instanceof Cientifico) {
                    produccion[3] += actual.trabajo_realizado(); 
                }
            }
        }
        else { produccion[0] = -4 * gente; }
        return produccion;
    }
	public void mejorar() {
        addCapacidad(3);
    }
}
