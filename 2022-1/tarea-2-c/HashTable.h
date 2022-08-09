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

int find_max (int *array, int size);
HashTable* createHT(int *array, int size);
void insertHT(HashTable *currHT, int pos);
void popHT(HashTable *currHT, int pos);
void printHT(HashTable *currHT);
int findtopHT(HashTable *currHT);
int lenHT(HashTable *currHT);
int sumHT(HashTable *currHT);
