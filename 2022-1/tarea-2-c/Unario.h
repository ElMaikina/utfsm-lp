#include <stdio.h>
#include <stdlib.h>

typedef struct Unario {
	/* arreglo de 0 e 1 */
	char *bits;
} Unario;

Unario* comprimir_en_unario(int n, int *grupo);
int* descomprimir_en_unario(void *unario);
int donde_esta_en_unario(void *unario, int e, int i);
int cuantos_mas_grande_unario(void *unario, int e);
int bits_unario(void *unario);
void mostrar_unario(void *unario);
