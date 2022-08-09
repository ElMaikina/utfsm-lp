import java.util.ArrayList;
import java.util.List;

public abstract class Edificio {
	private String nombre;
	private int nivel;
	private int capacidad;
	private List<Persona> personas = new ArrayList<>();
	
	// Constructor de la clase Edificio
	public Edificio(String n) {
		nombre = n;
		nivel = 1;
	}

	// Saca a una Persona del Edificio por su nombre
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
	// Busca a una Persona en el Edificio por su nombre
	public Persona buscar_persona(String n) {
		Persona actual;
		for (int i = 0; i < capacidad; i++) {
			actual = personas.get(i);
			if (actual.getNombre().equals(n)) {
				return actual;
			}
		}
		return null;
	}

	// Agrega una Persona que recibe como parametro
	public void agregar_persona(Persona p) {
		personas.add(p);
	}
	// Metodos abstractos que pide la Tarea
	public abstract int[] producir();
	public abstract void mejorar();

	// Getters y Setters de la clase Edificio
	public String getNombre() {
		return nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public Persona getPersona(int index) {
		return personas.get(index);
	}

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

	public void setNombre(String n) {
		nombre = n;
	}
	public void setNivel(int l) {
		nivel = l;
	}
	public void setCapacidad(int c) {
		capacidad = c;
	}
	public void addCapacidad(int c) {
		capacidad += c;
	}
	// Muestra a todas las Personas presentes en el Edificio
	public void mostrar() {
		int gente = getPersonas().size();
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			actual.mostrar();
		}
	}
	// Envejece a todas las Personas presentes en el Edificio
	public void envejecer() {
		int gente = getPersonas().size();
		for (int i = 0; i < gente; i++) {
			Persona actual = getPersona(i);
			actual.envejecer();
			if (actual.getEdad() > 30) {
				System.out.println("-- Se murió una JavaPersona!!! --");
				sacar_persona(actual.getNombre());				
			}
			else {
				personas.set(i, actual);
			}
		}
	}
}


