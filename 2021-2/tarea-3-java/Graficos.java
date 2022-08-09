public class Graficos {
	public Graficos() {
		System.out.println();
		System.out.println("Representación gráfica: ");
		System.out.println();
	};
	public void Renderizar(int c, int t) {
		String tierra1 = " ";
		String tierra2 = " ";
		String tierra3 = " ";
		String tierra4 = " ";

		if (t == 3) {
			tierra1 = "       /Ñi          ";
			tierra2 = "    _ /   i_,       ";
			tierra3 = "  /' V   ''  i__    ";
			tierra4 = " /     ''''    ''i  ";
		}	

		if (t == 2) {
			tierra1 = "  -( )      _       ";
			tierra2 = "  ( * )-   (* )-    ";
			tierra3 = "(_*__*_) (_*___)    ";
			tierra4 = "__ | |__,, | |___,,,";
		}	

		if (t == 1) {
			tierra1 = "     _     ___@o   ";
			tierra2 = "    | I_|        _ ";
			tierra3 = "   Lj |       __| |";
			tierra4 = "__- | |--__--_  | |";
		}	

		if (c == 2) {
		System.out.println();
		System.out.println("        !_!        " + tierra1);       
                System.out.println("    (¬ _[T]_       " + tierra2);      
                System.out.println("     -V' 9'L()     " + tierra3);    
	        System.out.println("       _/ 2        " + tierra4);       	
		System.out.println();
		}

		if (c == 1) {
	 	System.out.println();
 		System.out.println("        _)l_       " + tierra1);      
 		System.out.println("       _<O'_       " + tierra2);  
 		System.out.println("     ?-'(%/7       " + tierra3);    
 		System.out.println("     | _/-L        " + tierra4);      
		System.out.println();
 		}

		if (c == 3) {
		System.out.println();
	 	System.out.println("         M   |     " + tierra1);
	 	System.out.println("     *  (A) _t     " + tierra2);
	   	System.out.println("     °-'l¡/'       " + tierra3);
		System.out.println("       (_#_l       " + tierra4);	
		System.out.println();
		}




		return;
	
	}

}
