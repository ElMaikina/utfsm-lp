public abstract class Persona {
        private String nombre;
        private int edad;
        private int nivel;
        private int productividad;

        public Persona(String n) {
		nombre = n;
	}
	// Sube la edad de la persona
	public void envejecer() {
		edad += 1;
	}
	public abstract void mejorar();
	public abstract int trabajo_realizado();

	// Funciones Get que retornan el valor
	// de cada paramétro
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public int getNivel() {
		return nivel;
	}
	public int getProductividad() {
		return productividad;
	}

	// Funciones Set que definen el valor
	// de cada paramétro
	public void setNombre(String n) {
		nombre = n;
	}
	public void setEdad(int e) {
		edad = e;
	}
	public void setNivel(int l) {
		nivel = l;
	}
	public void setProductividad(int p) {
		productividad = p;
	}

	// Funciones Add que suman al valor
	// de cada paramétro
	public void addEdad(int e) {
		edad += e;
	}
	public void addNivel(int l) {
		nivel += l;
	}
	public void addProductividad(int p) {
		productividad += p;
	}
	// Muestra por pantalla los atributos de la Persona
	public void mostrar() {
		System.out.println("	" + getNombre() + "	" + getEdad() + "	" + getNivel() + "	" + getProductividad());
	}
}


