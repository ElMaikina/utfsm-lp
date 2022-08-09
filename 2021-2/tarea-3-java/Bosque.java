public class Bosque extends Tierra {
				
	public Bosque(float prob, NPC n, Monstruo M, Jefe_Final JF ) {
			super(prob,n,M,JF);
	}

	public boolean accion(int vida_h, int mana_h, Jugador J) {
		int vida_actual = vida_h;
		int mana_actual = mana_h;
		if (mana_h == 0) {
			return false;
		} 
		System.out.println("Te acercas a lo que parece un frondoso bosque");	
		System.out.println("sientes cómo una escalofriante aura rodea tu");
		System.out.println("cuerpo... te empiezas a fatigar lentamente....");
		
		mana_actual -= 3;
		if (mana_actual < 0) {
			vida_actual += mana_actual;
			mana_actual = 0;
		}
		if (vida_actual > 0) {
			System.out.println("A pesar de todo logras entrar al bosque...");
			J.alterar(vida_actual - vida_h, 0, 0, 0, 0, mana_actual - mana_h );
			return true;
		}
		return false;
	}	
}

