/*
obtiene el largo de cierta lista
*/
len([], 0).
len([_|L], N) :-
	len(L,N1),
	N is N1 + 1.

/*
obtiene la suma de los elementos
de una lista cualquiera
*/
sum([], 0).
sum([X|L],Y) :-
	sum(L,Y1),
	Y is X+Y1.

/*
obtiene el promedio de una lista
*/
avg(L, X) :-
	sum(L, S),
	len(L, N),
	X is S/N.
/*
busca un elemento por su índice
*/
find(1,[X|_], X).
find(K,[_|L], X) :-
	K > 1,
	K1 is K-1,
	find(K1,L,X).
/*
busca la mediana de una lista
*/
media([], 0).
media(L, M) :-
	sort(L, S),
	len(S, C),
	C2 is div(C,2) + 1,
	find(C2,S,M).

/*
evalua la lista, para ver si es
bondadosa o no.
será "true" si es que el promedio
es mayor a la mediana
será "false" si es que el promedio
es menor o igual a la mediana.
*/
evaluate(L,[true]) :-
	media(L,M),
	avg(L,A),
	A > M.

evaluate(L,[false]) :-
	media(L,M),
	avg(L,A),
	A < M.

evaluate(L,[false]) :-
	media(L,M),
	avg(L,A),
	A = M.
/*
devuelve al primer elemento
de una lista
*/
primero([X|_],X).
/*
devuelve el resto de una lista
(osea le saca la cabeza)
*/
resto([_|L],L).
resto([],[]).
/*
concatena dos listas en una sola
*/
cons(X,L,[X|L]).
conc([],L,L).
conc([X|L1],L2,[X|L3]) :-
	conc(L1,L2,L3).
/*
entra a "bondad_aux" una función
recursiva
*/
bondad([],[]).
bondad(LInput,LFinal) :-
	bondad_aux(LInput,[],LFinal).

/*
esta funcion toma la lista "LInput" y evalúa
su cabeza, el resultado luego será almacenado
en la lista "LFormada", luego llama a otra 
"bondad_aux" pero con el resto de la lista.

cuando la "LInput" esté vacía, es porque se
recorrió la lista completamente, y por ende
"LFormada" será la lista final "LFinal"
*/
bondad_aux([],LFinal,LFinal).

bondad_aux(LInput,LFormada,LFinal) :-
	primero(LInput,EFirst),
	resto(LInput,LResto),
	
	evaluate(EFirst,Value),
	
	conc(LFormada,Value,LActual),
	bondad_aux(LResto,LActual,LFinal).

