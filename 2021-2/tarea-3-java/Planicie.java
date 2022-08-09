public class Planicie extends Tierra {
		
	public Planicie(float prob, NPC n, Monstruo M, Jefe_Final JF ) {
			super(prob,n,M,JF);
	}

	public boolean accion(int vida, int energia, Jugador J) {
		System.out.println(
		"Haz llegado a una zona casi completamente");	
		System.out.println(
		"desierta, puedes avistar un par de aldeas");
		System.out.println(
		"pero es poco probable que te ataquen aquí.");
		return true;
	}	
}

/*
 * 
	public Tierra(float prob, NPC n, Monstruo M, Jefe_Final JF) {
	
		probabilidad_enemigo = prob;
		NPC = n
		Monstruo = M;	
		Jefe_Final = JF;;
	}
 *
 *
 *
 * */
