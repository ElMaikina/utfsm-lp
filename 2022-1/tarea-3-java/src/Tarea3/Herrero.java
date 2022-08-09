public class Herrero extends Persona {
	public Herrero(String n) {
		super(n);
		setEdad(18);
		setProductividad(9);
	}
	public void mejorar() {
		addProductividad(3);
	}
	public int trabajo_realizado() {
		int p = getProductividad();
		int n = getNivel();
		return ( p + (n * 2) );
	}
}


