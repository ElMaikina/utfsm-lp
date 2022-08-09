public abstract class Tierra {
	private float probabilidad_enemigo;
	private Monstruo monstruo;
	private Jefe_Final jefe_final;
	private NPC npc;
	
	public Tierra(float prob, NPC n, Monstruo M, Jefe_Final JF) {	
		probabilidad_enemigo = prob;
		npc = n;
		monstruo = M;	
		jefe_final = JF;
	}
	public String GetNombreJefe() {
		return jefe_final.getNombre();
	}
	public NPC socializar() {
		return npc;
	}
	public float GetProb() {
		return probabilidad_enemigo;
	}
	public Monstruo GetMons() {
		return monstruo;
	}
	
	public Jefe_Final getJefe() {
		return jefe_final;
	}
	public abstract boolean accion(int v, int stat, Jugador J);
}



