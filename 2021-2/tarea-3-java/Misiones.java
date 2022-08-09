public class Misiones {
	public char requisito;
	public int valor;
	public int cantidad;
	public int recompensa;

	public Misiones(char req, int v, int c, int rew) {	
		requisito = req;
		valor = v;
		cantidad = c;
		recompensa = rew;		
		System.out.println("¡¡¡¡Se añadio una Mision nueva!!!! ");
		System.out.println();
		
		if (requisito == 'v') {
			System.out.println("Derrotar " + valor + " feroces Monstruos!!!");
		}
		if (requisito == 'm') {
			System.out.println("Reconquistar la zona en el punto " + valor);
		}
		System.out.println("Tu recompensa serán " + recompensa + " lingotes de Oro.");
		System.out.println();
	}
	public boolean verificar_requisito(int c) {
		cantidad = c;
		if (valor == cantidad) {
			System.out.println("Haz cumplido uno de tus objetivos!!!");
			return true;
					}
		else {
			System.out.println("Aun no se cumple este objetivo.");
			return false;
		}
	}	
}	
