public class Cientifico extends Persona {
	public Cientifico(String n) {
		super(n);
		setEdad(24);
		setProductividad(10);
	}
	public void mejorar() {
		addProductividad(1);
	}
	public int trabajo_realizado() {
		int p = getProductividad();
		int n = getNivel();
		return ( (p * 3) + n );
	}
}


