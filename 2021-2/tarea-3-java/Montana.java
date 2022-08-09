public class Montana extends Tierra {
	
	public Montana(float prob, NPC n, Monstruo M, Jefe_Final JF ) {
			super(prob,n,M,JF);
	}

	public boolean accion(int vida_h, int energia_h, Jugador J) {
		int vida_actual = vida_h;
		int energia_actual = energia_h;
		if (energia_h == 0) {
			return false;
		} 
		System.out.println("Vez una empinada montaña frente a tí... haz");	
		System.out.println("caminado por bastante tiempo y dudas poder");
		System.out.println("cruzarla facilmente...");
		
		energia_actual -= 3;
		if (energia_actual < 0) {
			vida_actual += energia_actual;
			energia_actual = 0;
		}
		if (vida_actual > 0) {
			System.out.println("Ante todo, escalaste la empinada montaña...");
			J.alterar(vida_actual - vida_h, 0, 0, 0, energia_actual - energia_h, 0 );
			return true;
		}
		return false;
	}	
}

