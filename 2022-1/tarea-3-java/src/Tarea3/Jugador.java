import java.util.ArrayList;
import java.util.List;

public class Jugador {
        private String nombre;
        private int javalares;
        private int hierro;
        private int trigo;
        private int tecnologia;

        private List<Persona> personas = new ArrayList<>();
        private List<Edificio> edificios = new ArrayList<>();
        private List<Feria> ferias = new ArrayList<>();
        private List<Museo> museos = new ArrayList<>();
        private List<Javapato> javapatos = new ArrayList<>();

	// Constructor de la clase
	public Jugador(String n) {
		nombre = n;
		javalares = 30;
		hierro = 15;
		trigo = 10;
		tecnologia = 6;
	}
	// Altera la cantidad de recursos del Jugador
	public void pagar(int jl, int h, int trg, int tec) {
		javalares -= jl;
		hierro -= h;
		trigo -= trg;
		tecnologia -= tec;
	}
	// Chequea si el saldo no es negativo
	public boolean check_presupuesto() {
		if (getJavalares() >= 0) {
			return true;
		} return false;
	}
	// Chequea si tiene la cantidad suficiente de recurso
	public boolean tiene_suficiente(int jl, int h, int trg, int tec) {
		boolean si = true;
		if (getJavalares() - jl < 0) {
			si = false;
		}
		if (getHierro() - h < 0) {
			si = false;
		}
		if (getTrigo() - trg < 0) {
			si = false;
		}
		if (getTecnologia() - trg < 0) {
			si = false;
		}
		return si;
	}

	// Funciones para agregar elementos
	public void agregar_persona(Persona p) {
		personas.add(p);
	}
	public void agregar_edificio(Edificio e) {
		edificios.add(e);
	}
	public void agregar_feria(Feria f) {
		ferias.add(f);
	}
	public void agregar_museo(Museo m) {
		museos.add(m);
	}
	public void agregar_javapato(Javapato j) {
		javapatos.add(j);
	}
	// Funciones para sacar elementos
	public void sacar_persona(String n) {
		int gente = getPersonas().size();
		int pos = 0;
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual.getNombre().equals(n)) {
				pos = i;
			}
		}
		personas.remove(pos);
	}
	public void sacar_edificio(String n) {
		int cantidad = getEdificios().size();
		int pos = 0;
		for (int i = 0; i < cantidad; i++) {
			Edificio actual = getEdificio(i);
			if (actual.getNombre().equals(n)) {
				pos = i;
			}
		}
		edificios.remove(pos);
	}
	public void sacar_atraccion(String n) {
		sacar_feria(n);
		sacar_museo(n);
		sacar_javapato(n);
	}

	public void sacar_feria(String n) {
		int cantidad = getFerias().size();
		int pos = 0;
		for (int i = 0; i < cantidad; i++) {
			Feria actual = getFeria(i);
			if (actual.getNombre().equals(n)) {
				pos = i;
			}
		}				
		ferias.remove(pos);
		
	}
	public void sacar_museo(String n) {
		int cantidad = getMuseos().size();
		int pos = 0;
		for (int i = 0; i < cantidad; i++) {
			Museo actual = getMuseo(i);
			if (actual.getNombre().equals(n)) {
				pos = i;
			}
		}
		museos.remove(pos);
	}
	public void sacar_javapato(String n) {
		int cantidad = getJavapatos().size();
		int pos = 0;
		for (int i = 0; i < cantidad; i++) {
			Javapato actual = getJavapato(i);
			if (actual.getNombre().equals(n)) {
				pos = i;
			}
		}
		javapatos.remove(pos);	
	}
	// Funciones para mejorar
	public void mejorar_persona(String n) {
		int gente = getPersonas().size();
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual.getNombre().equals(n)) {
				actual.mejorar();
				personas.set(i, actual);
				return;
			}
		}
	}
	public void mejorar_edificio(String n) {
		int cantidad = getEdificios().size();
		for (int i = 0; i < cantidad; i++) {
			Edificio actual = getEdificio(i);
			if (actual.getNombre().equals(n)) {
				actual.mejorar();
				edificios.set(i, actual);
				return;
			}
		}
	}

	// Getters y Setters de la clase, incluye algunos extra
	// para poder agilizar el código
	public String getNombre() {
		return nombre;
	}
	public int getJavalares() {
		return javalares;
	}
	public int getHierro() {
		return hierro;
	}
	public int getTrigo() {
		return trigo;
	}
	public int getTecnologia() {
		return tecnologia;
	}

	public void setJavalares(int jl) {
		javalares += jl;
	}
	public void setHierro(int hi) {
		hierro += hi;
	}
	public void setTrigo(int trg) {
		trigo += trg;
	}
	public void setTecnologia(int tec) {
		tecnologia += tec;
	}

	// Funciones que retornan los arreglos completos
	public List<Persona> getPersonas() {
		return personas;
	}
	public List<Edificio> getEdificios() {
		return edificios;
	}
	public List<Feria> getFerias() {
		return ferias;
	}
	public List<Museo> getMuseos() {
		return museos;
	}
	public List<Javapato> getJavapatos() {
		return javapatos;
	}
	// Funciones que sacan un elemento en la i-esimsa
	// posicion del arreglo correspondiente
	public Persona getPersona(int index) {
		return personas.get(index);
	}
	public Edificio getEdificio(int index) {
		return edificios.get(index);
	}
	public Feria getFeria(int index) {
		return ferias.get(index);
	}
	public Museo getMuseo(int index) {
		return museos.get(index);
	}
	public Javapato getJavapato(int index) {
		return javapatos.get(index);
	}

	// Busca una persona en base a su nombre
	public Persona getPersona(String n) {
		int gente = getPersonas().size();
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual.getNombre().equals(n)) {
				return actual;
			}
		}
		return null;
	}

	// Obtiene la cantidad por tipo de personas en el
	// arreglo de personas
	public int getGranjeros() {
		int gente = getPersonas().size();
		int cantidad = 0;
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Granjero) {
				cantidad++;
			}
		}
		return cantidad;
	}
	public int getCientificos() {
		int gente = getPersonas().size();
		int cantidad = 0;
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Cientifico) {
				cantidad++;
			}
		}
		return cantidad;
	}
	public int getHerreros() {
		int gente = getPersonas().size();
		int cantidad = 0;
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			if (actual instanceof Herrero) {
				cantidad++;
			}
		}
		return cantidad;
	}
	// Permite ver por pantalla la cantidad de recursos 
	// que tiene el jugador
	public void verStats() {
		System.out.println("Recursos del alcalde " + getNombre());
		String stats = "	Javalares: " + getJavalares() + ", Hierro: " + getHierro();
		stats +=  ", Trigo: " + getTrigo() + ", Tecnologia: " + getTecnologia();
		stats += "\n";
		System.out.println(stats);
	}
	public void mostrar_libres() {
		System.out.println("Personas aun no asignadas\n");

		int gente = getPersonas().size();
		if (gente == 0) {
			System.out.println("	No hay personas sin asignar...");
		}
		if (gente != 0) {
			System.out.println("	Nombre	Edad	Nivel	Prod.");
			for (int i = 0; i < gente; i++) {
				Persona actual = getPersona(i);
				actual.mostrar();
			}
		}
		System.out.println("\n");
	}
	public void mostrar_asignadas() {
		System.out.println("Personas ya asignadas\n");
		int cantidad = getEdificios().size();
		if (cantidad == 0) {
			System.out.println("	No hay Edificios...");
		}
		if (cantidad != 0) {
			for (int i = 0; i < cantidad; i++) {
				Edificio actual = getEdificio(i);
				System.out.println("Edificio: " + actual.getNombre());

				if (actual.getPersonas().size() == 0) {
					System.out.println("	El Edificio está vacío...");
				}
				if (actual.getPersonas().size() != 0) {
					System.out.println("	Nombre	Edad	Nivel	Prod.");
					actual.mostrar();
				}
			}
		}
		System.out.println("\n");
	}
	public void mostrar_atracciones() {
		System.out.println("Atracciones construidas\n");

		int c_ferias = getFerias().size();
		int c_museos = getMuseos().size();
		int c_javapatos = getJavapatos().size();
		int total = c_ferias + c_museos + c_javapatos;

		if (total == 0) {
			System.out.println("	No hay atracciones...");
		}
		if (total != 0) {
			for (int i = 0; i < c_ferias; i++) {
				Feria actual = getFeria(i);
				System.out.println("	" + actual.getNombre());
			}
			for (int i = 0; i < c_museos; i++) {
				Museo actual = getMuseo(i);
				System.out.println("	" + actual.getNombre());
			}
			for (int i = 0; i < c_javapatos; i++) {
				Javapato actual = getJavapato(i);
				System.out.println("	" + actual.getNombre());
			}
		}
		System.out.println("\n");
	}


	// Dada una Persona p, la agrega al Edificio de nombre ne
	public void PersonaEdificio(Persona p, String ne) {
		int cantidad = getEdificios().size();
		for (int i = 0; i < cantidad; i++) {
			Edificio actual = getEdificio(i);
			System.out.println("Nombre del Edificio: " + actual.getNombre() + "\n");
			if (actual.getNombre().equals(ne)) {
				System.out.println("Se movió la Persona!\n");
				actual.agregar_persona(p);
				edificios.set(i, actual);
			}
		}
	}
	// Dada un nombre de Persona np, la saca del Edificio de nombre ne
	public void EdificioPersona(String ne, String np) {
		int cantidad = getEdificios().size();
		Persona buscada;
		for (int i = 0; i < cantidad; i++) {
			Edificio actual = getEdificio(i);
			if (actual.getNombre().equals(ne)) {
				System.out.println("Se sacó la Persona!\n");
				buscada = actual.buscar_persona(np);
				agregar_persona(buscada);
				actual.sacar_persona(np);
				edificios.set(i, actual);
			}
		}
	}
	// Obtiene las recaudaciones de todas las Personas
	public void producir() {
		int cantidad = getEdificios().size();
		int[] produccion = {0,0,0,0};
		if (cantidad > 0) {
			for (int i = 0; i < cantidad; i++) {
				Edificio actual = getEdificio(i);
				if (actual.getPersonas().size() > 0) {
					produccion[0] += actual.producir()[0];
					produccion[1] += actual.producir()[1];
					produccion[2] += actual.producir()[2];
					produccion[3] += actual.producir()[3];
				}
			}
		}
		setJavalares(produccion[0]);
		setHierro(produccion[1]);
		setTrigo(produccion[2]);
		setTecnologia(produccion[3]);
	}
	// Envejece a las Personas asignadas a los Edificos
	public void envejecer() {
		int cantidad = getEdificios().size();
		if (cantidad > 0) {
			for (int i = 0; i < cantidad; i++) {
				Edificio actual = getEdificio(i);
				if (actual.getPersonas().size() > 0) {
					actual.envejecer();
				}
			}
		}
	}
}


