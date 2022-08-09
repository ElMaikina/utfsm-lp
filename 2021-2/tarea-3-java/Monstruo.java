public class Monstruo implements Enemigo {
	private int vida;
	private int dano;

	public Monstruo(int v, int d) {
		vida = v;
		dano = d;
	}
	/*
	 * Recordamos como está descrito combate
	 *	void combate(Jugador J);	
	 * 
	 * Además recordamos la funcion alterar
	 * /
		public void alterar(int v, int x, int f, int i, int e, int m) {
			vida += v;
			xp += x;
			fuerza += f;
			inteligencia += i;
			mana += m;
		}
	 *
	 *
	 * 
	 */

	public boolean esta_muerto () {
		if (vida < 1) {return true;}
		return false;
	}
	public void combate(Jugador J, int dmg_self, int fase) {
		vida -= dmg_self;
		if (esta_muerto()) {
			System.out.println("ENEMIGO ABATIDO!!!!");
			return;
		}
		J.alterar(-dano,0,0,0,0,0);	
		return;
	}
}
