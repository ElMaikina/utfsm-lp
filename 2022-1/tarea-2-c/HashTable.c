#include <stdio.h>
#include <stdlib.h>

// En este contexto decidí usar una tabla de Hashing para hacer el orden
// y frecuencia de los elementos.
typedef struct HashElement {
	int key;
	int freq;
} HashElement; 

typedef struct HashTable {
	HashElement *HT;
	int max_val;	
} HashTable;

// Busca y devuelve el mayor elemento dentro de un arreglo
// se asume que todos los elementos son positivos / mayores a 0
// esto es para que la tabla de Hashing no ocupe una cantidad de
// memoria exagerada ni se quede corta en el caso de usar
// numeros muy grandes.
int find_max (int *array, int size) {
	int curr_max = 0;
	for (int i = 0; i < size; i++) {
		if ( array[i]> curr_max) {
			curr_max = array[i];
		}
	} 
	return curr_max;
}

// Se crea una tabla de Hashing, donde su tamaño es el valor
// mas grande que se encuentre en el arreglo
HashTable* createHT(int *array, int size) {
	int max = find_max(array, size);
	
	HashTable *newHT;
	newHT = (HashTable*)malloc(sizeof(HashTable));
	newHT->HT = (HashElement*)malloc(sizeof(HashElement)*max);
	newHT->max_val = max;
	
	for (int i = 0; i < max; i++) {
		newHT->HT[i].key = -1;
		newHT->HT[i].freq = 0;
	}
	return newHT;
}

// Busca si el elemento existe e incrementa su frecuencia
void insertHT(HashTable *currHT, int pos) {
	if (currHT->HT[pos - 1].key != -1) {
		currHT->HT[pos - 1].freq += 1;
	}
	else {
		currHT->HT[pos-1].key = pos;
		currHT->HT[pos-1].freq = 1;
	}
}

// Borra un elemento por su llave
void popHT(HashTable *currHT, int pos) {
	currHT->HT[pos-1].key = -1;
	currHT->HT[pos-1].freq = 0;
}

// Borra un elemento por su llave
void printHT(HashTable *currHT) {
	for (int i = 0; i < currHT->max_val; i++) {
		printf("KEY: %d		FREQ: %d\n", currHT->HT[i].key, currHT->HT[i].freq);
	}
}

// Busca el elemento con más frecuencia y lo devuelve
// borrando el elemento orginal si es que existe.
// si no hay ningún elemento retornará -1
int findtopHT(HashTable *currHT) {
	int max_freq = 0;
	int max_key = -1;

	for (int i = 0; i < currHT->max_val; i++) {
		if ( currHT->HT[i].freq > max_freq) {
			max_freq = currHT->HT[i].freq;
			max_key = currHT->HT[i].key;
		}
	} 
	popHT(currHT, max_key);
	return max_key;
}

// Mismo que el anterior pero sin borrar el elemento
int topHT(HashTable *currHT) {
	int max_freq = 0;
	int max_key = -1;

	for (int i = 0; i < currHT->max_val; i++) {
		if ( currHT->HT[i].freq > max_freq) {
			max_freq = currHT->HT[i].freq;
			max_key = currHT->HT[i].key;
		}
	} 
	return max_key;
}

// Devuelve la cantidad de elementos que tiene la tabla de Hashing
int lenHT(HashTable *currHT) {
	int len = 0;
	for (int i = 0; i < currHT->max_val; i++) {
		if (currHT->HT[i].key != -1) { 
			len++; 
		}
	} 
	return len;
}

// Devuelve la suma de los elementos en la tabla de Hashing
int sumHT(HashTable *currHT) {
	int sum = 0;
	for (int i = 0; i < currHT->max_val; i++) {
		if (currHT->HT[i].key != -1) { 
			sum += currHT->HT[i].key; 
		}
	} 
	return sum;
}
