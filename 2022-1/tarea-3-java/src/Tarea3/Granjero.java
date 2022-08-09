public class Granjero extends Persona {
	public Granjero(String n) {
		super(n);
		setEdad(15);
		setProductividad(8);
	}
	public void mejorar() {
		addProductividad(2);
	}
	public int trabajo_realizado() {
		int p = getProductividad();
		int n = getNivel();
		return ( p * 2 ) + ( n / 2 );
	}
}


