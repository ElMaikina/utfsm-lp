/*la función op(X,Signo,Y,Z) realiza 
la operación dependiendo del signo
que haya, donde Z será el resultado*/
op(X,"+",Y,Z):- 
	Z is X+Y.

op(X,"-",Y,Z):- 
	Z is X-Y.

op(X,"*",Y,Z):- 
	Z is X*Y. 

op(X,"/",Y,Z):- 
	Z is X/Y.

op(X,"^",Y,Z):- 
	exp(X,Y,0,1,Z). 

op(X,"mod",Y,Z):- 
	Z is mod(X,Y).
/*
exp(X, Y, iter, res_actual, res_final)
donde "X" se multiplica así mísmo "Y" veces,
iter indica la iteracción actual, luego
"res_actual" el resultado actual y finalmente
"res_final" indica el resultado final
*/
exp(X,Y,N,Z1,Z) :-
	N < Y,
	op(Z1,"*",X,Z2),
	N1 is N + 1,
	exp(X,Y,N1,Z2,Z).

exp(_X,Y,N,Z1,Z):-
	N = Y,
	Z is Z1.

/*verifica si "X" es una lista*/
is_list(X) :-
        var(X), 
	!,
        fail.

is_list([]).

is_list([_|T]) :-
        is_list(T).
/*devuelve el primero elemento de la lista*/
first([X|_L], X).
first([], 0).

/*devuelve el segundo elemento de la lista*/
second([_X|L], X2):-
	first(L, X2).
second([], 0).

/*devuelve el tercer elemento de la lista*/
third([_X|L], X2):-
	second(L, X2).
third([], 0).

/*
 * Al trabajar con un arbol sintactico
habran 3 opciones: 
	1. que la hoja izquierda
	y derecha tengan subhojas
	2. que una hoja tenga mas
	subhojas y que la otra  no
	3. que ninguna de las dos
	hojas tengan subhojas
para cada caso existe una operacion distinta,
donde se llama a la funcion recursivamente y
se realizan operaciones desde los ultimos hijos
hacia los primeros (recursion de cola).*/

matematica(L, R):-
	first(L, E1),
	second(L, E2),
	third(L, E3),	
	is_list(E1),
	is_list(E3),
	matematica(E1, R1),
	matetica(E3, R3),
	op(R1, E2, R3, R).

matematica(L, R):-
	first(L, E1),
	second(L, E2),
	third(L, E3),	
	number(E1),
	is_list(E3),
	matematica(E3, R3),
	op(E1, E2, R3, R).

matematica(L, R):-
	first(L, E1),
	second(L, E2),
	third(L, E3),	
	is_list(E1),
	number(E3),
	matematica(E1, R1),
	op(R1, E2, E3, R).

matematica(L, R):-
	first(L, E1),
	second(L, E2),
	third(L, E3),	
	number(E1),
	number(E3),
	op(E1, E2, E3, R).


