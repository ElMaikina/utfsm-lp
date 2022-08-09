/*	Casos de Prueba		*/
/* Se pueden borrar, están para probar*/

ancestro(p2, p1).
ancestro(p3, p1).
ancestro(p4, p1).
ancestro(p5, p1).

ancestro(p2, p10).
ancestro(p3, p10).
ancestro(p4, p10).
ancestro(p5, p10).

ancestro(p5, p2).
ancestro(p6, p2).

ancestro(p6, p4).

ancestro(p8, p5).

ancestro(p7, p9).
ancestro(p9, p6).

ancestro(p8, p7).

ancestro(p1, p9).



/*
funcion recursiva, si X es ancestro de Y
entonces N es igual a Z (N empieza como 1)
*/
ancestral(X, Y, N, Z):-
	ancestro(X, Y),
	Z is N.
/*
si X no es ancestro de Y, entonces busca
al ancestro del ancestro y suma 1 a N
*/
ancestral(X, Y, N, Z):-
	ancestro(X, Y2),
	N2 is N + 1,
	ancestral(Y2, Y, N2, Z).
/*
caso de que X es ancestro directo de Y
*/
ancestrosidad(X, Y, Z):-
	ancestro(X, Y),
	Z is 1.
/*
llama a la funcion recursiva que anota
los pasos recorridos (osea las iteraciones)
*/
ancestrosidad(X, Y, Z):-
	ancestral(X, Y, 1, Z).

