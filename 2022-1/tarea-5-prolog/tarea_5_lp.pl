% Si LISTA es vacio, L sera vacio
first([], []).
first([X|_], X).
last([], []).
last(L, X):- append(_, [X], L).

% Pregunta 1.

% Sandwich obtiene los elementos de una lista
% dada una cota inferior I, una cota superior
% J, la posicion actual N y una lista LIN
% el resultado actual queda en LCUR, mientras
% que el final queda en LOUT.
sandwich(I, J, [], LCUR, []).

sandwich(I, J, LIN, LCUR, LOUT):-
	I < J,
	nth0(I, LIN, E),
	append(LCUR, [E], LCUR2),
	I2 is I + 1,
	sandwich(I2, J, LIN, LCUR2, LOUT).

sandwich(I, J, LIN, LCUR, LOUT):-
	I = J,
	nth0(J, LIN, E),
	append(LCUR, [E], LOUT).

separacion([], I, J, []).

separacion(LISTA, I, J, L):-
	sandwich(I, J, LISTA, [], L).

% Pregunta 2.

% Verifica si un numero es par o impar
even(X):-
	0 is mod(X, 2).
odd(X):-
	1 is mod(X, 2).

% Obtiene el largo de una lista en base
% a una recursion simple
list_length([],0).
list_length([_|TAIL],N):-
	list_length(TAIL,N1), 
	N is N1 + 1.

% Obtiene el largo de la lista, luego busca en donde
% termina la mitad izquierda (P1), donde empieza la
% mitad derecha (P2), donde termina la mitad derecha
% (LEN2), luego obtiene cada sublista a traves de la
% funcion separacion  y finalmente define a LL como 
% la agregacion de L1 y L2
division([], []).
division(LISTA, LL):-
	list_length(LISTA, LEN),
	even(LEN),
	P1 is (LEN / 2) - 1,
	P2 is LEN / 2,
	LEN2 is LEN - 1,
	separacion(LISTA, 0, P1, L1),
	separacion(LISTA, P2, LEN2, L2),
	append([L1], [L2], LL).

division(LISTA, LL):-
	list_length(LISTA, LEN),
	odd(LEN),
	P1 is (LEN // 2),
	P2 is (LEN // 2) + 1,
	LEN2 is LEN - 1,
	separacion(LISTA, 0, P1, L1),
	separacion(LISTA, P2, LEN2, L2),
	append([L1], [L2], LL).

% Problema 3

% obtiene la suma de los elementos de
% una lista cualquiera
sum([], 0).
sum([X|L],Y) :-
	sum(L,Y1),
	Y is X+Y1.

% Usa las dos funciones anteriores para
% separar la lista en L1 y L2, luego saca
% la suma de los elementos de cada uno y
% los almacena en R1 y R2 respectivamente.
% Finalmente compara el resultado entre ambas
% y retorna el resultado correspondiente
% (1 si el izquierdo es mayor y 0 si el derecho
% es mayor)
pasadofuturo(LISTA, RES):-
	division(LISTA, LL),
	append([L1], [L2], LL),
	sum(L1, R1),
	sum(L2, R2),
	R1 >= R2,
	RES is 1.

pasadofuturo(LISTA, RES):-
	division(LISTA, LL),
	append([L1], [L2], LL),
	sum(L1, R1),
	sum(L2, R2),
	R1 < R2,
	RES is 0.

% Problema 4
arbolbonito(ARBOL, ESBONITO).
