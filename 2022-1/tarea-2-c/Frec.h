#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "HashTable.h"
#include "Unario.h"

typedef struct Frec {
	/* arreglo de 0 e 1 */
	char *bits;
	/* arreglo para recordar la representacion de cada número */
	char *representaciones;
} Frec;

Frec* comprimir_en_frec(int n, int *grupo);
int* descomprimir_en_frec(void *frec);
int donde_esta_en_frec(void *frec, int e, int i);
int cuantos_mas_grande_frec(void *frec, int e);
int bits_frec(void *frec);
void mostrar_frec(void *frec);
