class Jefe_Final implements Enemigo {
	private String nombre;
	private int vida;
	private int dano_base;

	public Jefe_Final(String N, int v, int d) {
		nombre = N;
		vida = v;
		dano_base = d;
	}
	public String getNombre() {
		return nombre;
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
	 * */
	private int atacar(int fase) {
		return (dano_base + 2*fase);
	}
	public void combate(Jugador J, int dmg_self, int fase) {
		int dmg_curr = atacar(fase);
		vida -= dmg_self;
		if (vida <= 1) {
			System.out.println("Ay no ya se murió...");
			return;
		}
		J.alterar(-dmg_curr,0,0,0,0,0);
		return;
	}
	public boolean esta_muerto () {
		if (vida <= 1) {return true;}
		return false;
	}
}
