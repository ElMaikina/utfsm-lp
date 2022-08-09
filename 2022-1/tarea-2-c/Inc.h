#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

typedef struct Inc {
	/* arreglo de 0 e 1 */
	char *bits;
	/* arreglo para recordar la representacion de cada número */
	char *representaciones;
	/* cantidad de numeros distintos en el conjunto */
	char *n;
} Inc;

int digits (int num);
char* unario(int n);
Inc* comprimir_en_inc(int n, int *grupo);	
int* descomprimir_en_inc(void *inc);
int donde_esta_en_inc(void *inc, int e, int i);
int cuantos_mas_grande_inc(void *inc, int e);
int bits_inc(void *inc);
void mostrar_inc(void *inc);
