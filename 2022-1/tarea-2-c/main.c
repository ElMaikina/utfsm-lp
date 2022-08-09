#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "Frec.h"
#include "Inc.h"

typedef struct Representacion {
	// puntero a la representacion creada
	void *representacion;

	// punteros a funciones
	int* (*su_descomprimir)(void*);
	int (*su_donde_esta)(void*, int, int);
	int (*su_cuanto_mas_grande)(void*, int);
	int (*su_bits)(void*);
	void (*su_imprimir)(void*);
} Representacion;

typedef struct Conjunto_comprimido {
	// arreglo de representaciones de grupos de numero dentro del conjunto
	void *representaciones;
} Conjunto_comprimido;

// Obtiene el tamaño según pide el enunciado de la tarea
int get_size_unario(Unario *unario) {
	return strlen(unario->bits);
}

// Obtiene el tamaño según pide el enunciado de la tarea
int get_size_frec(Frec *frec) {
	int result = 0;
	result += strlen(frec->bits);
	result += strlen(frec->representaciones);
	return result;
}

// Obtiene el tamaño según pide el enunciado de la tarea
int get_size_inc(Inc *inc) {
	int result = 0;
	result += strlen(inc->bits);
	result += strlen(inc->representaciones);
	result += strlen(inc->n);
	return result;
}

// Destructor para el struct Unario
void kill_unario(Unario *unario) {
	free(unario->bits);
	free(unario);	
}

// Destructor para el struct Frecuencia
void kill_frec(Frec *frec) {
	free(frec->bits);
	free(frec->representaciones);
}

// Destructor para el struct Incremento
void kill_inc(Inc *inc) {
	free(inc->bits);
	free(inc->representaciones);
	free(inc->n);
}

// Crea un subarreglo a apartir de un arreglo más grande
// donde offset será el punto de partida del original,
// n el largo del subarreglo y array el arreglo original
int *get_grupo(int *array, int n, int offset) {
	int *grupo;
	grupo = (int*)malloc(sizeof(int)*n);
	for (int i = offset; i < offset + n; i++) {
		grupo[i - offset] = array[i];
	} return grupo;
}

// Crea un arreglo de tres y retorna la posición donde
// se encuentre el menor elemento
int minimum_of_three(int first, int second, int third) {
	int arr[] = {first, second, third};
	int index = 0;
	for (int i = 0; i < 3; i++) {
		if (arr[i] < arr[index]) {index = i;}
	} return index;
}

int main() {
	int n, g;
	int *arreglo;
	
	printf("Hola! Usted se halla en la interfaz de la Tarea 2 de LP!\n\n");	
	printf("1) Ingrese la cantidad de números en el conjunto: ");	
	scanf("%d", &n);

	arreglo = (int*)malloc(sizeof(int)*n);

	printf("\n2) Ingrese los elementos del arreglo: \n");
	for (int i = 0; i < n; i++) {
		printf("	arreglo[%d]: ", i);
		scanf("%d", &arreglo[i]);
	}
	printf("\n3) Ingrese la cantidad de números por grupo: ");
	scanf("%d", &g);

	int iteraciones = n / g;
	int offset = 0;
	printf("\n");

	Conjunto_comprimido *compressed;
	compressed = (Conjunto_comprimido*)malloc(sizeof(Conjunto_comprimido));
	
	Representacion *temp_rep;
	temp_rep = malloc(sizeof(Representacion)*iteraciones);

	int index = 0;

	// Itera buscando la compresión más efectiva para cada grupo
	while (index < iteraciones) {
		int *grupo;
		grupo = get_grupo(arreglo, g, offset);

		Unario *temp_unario;
		Frec *temp_frec;
		Inc *temp_inc;

		// Crea una compresión de cada tipo para el grupo
		temp_unario = comprimir_en_unario(g, grupo);
		temp_frec = comprimir_en_frec(g, grupo);
		temp_inc = comprimir_en_inc(g, grupo);
		
		// Obtiene su tamaño
		int size_u = get_size_unario(temp_unario);
		int size_f = get_size_frec(temp_frec);
		int size_i = get_size_inc(temp_inc);

		Representacion *newR;
		newR = (Representacion*)malloc(sizeof(Representacion));
		
		// Para cada mínimo, asigna la compresión al arreglo de
		// representaciones, liberando las compresiones no
		// utilizadas
		if (minimum_of_three(size_u, size_f, size_i) == 0) 
		{
		      newR->representacion = temp_unario;
		      newR->su_descomprimir = descomprimir_en_unario;
		      newR->su_donde_esta = donde_esta_en_unario;
		      newR->su_cuanto_mas_grande = cuantos_mas_grande_unario;
		      newR->su_bits = bits_unario;
		      newR->su_imprimir = mostrar_unario;

		      free(temp_frec);
		      free(temp_inc);
		}
		if (minimum_of_three(size_u, size_f, size_i) == 1) 
		{
		      newR->representacion = temp_frec;
		      newR->su_descomprimir = descomprimir_en_frec;
		      newR->su_donde_esta = donde_esta_en_frec;
		      newR->su_cuanto_mas_grande = cuantos_mas_grande_frec;
		      newR->su_bits = bits_frec;
		      newR->su_imprimir = mostrar_frec;

		      free(temp_unario);
		      free(temp_inc);
		}
		if (minimum_of_three(size_u, size_f, size_i) == 2) 
		{
		      newR->representacion = temp_inc;
		      newR->su_descomprimir = descomprimir_en_inc;
		      newR->su_donde_esta = donde_esta_en_inc;
		      newR->su_cuanto_mas_grande = cuantos_mas_grande_inc;
		      newR->su_bits = bits_inc;
		      newR->su_imprimir = mostrar_inc;

		      free(temp_unario);
		      free(temp_frec);
		}

		// Se asigna la compresión elegida por el programa
		temp_rep[index] = *newR;
	
		// Se desplaza el offset del arreglo para que el siguiente
		// grupo / subarreglo tenga los elemento adyacentes al obtenido
		offset += g;
		index++;

		// Se libera el grupo creado
		free(grupo);
	}
	compressed->representaciones = temp_rep;

	printf("El progama ya determinó la mejor compresión!\n");	
	printf("Que desea hacer ahora?\n\n");	

	bool interface = true;
	int option = 0;
	while (interface) 
	{
		printf("Tiene las siguientes opciones\n");	
		printf("	1) Comprimido	 4) Bits\n");
		printf("	2) Donde está	 5) Mostar\n");
		printf("	3) Más grande	6) Salir del programa\n\n");
		scanf("%d", &option);
		
		// Mostrar por pantalla el conjunto comprimido
		if (option == 1) {
			int i;
			printf("Elija el grupo: ");
			scanf("%d", &i);

			Representacion curr;
			curr = temp_rep[i];
			int *arr;
			arr = curr.su_descomprimir((void*)curr.representacion);

			printf("Arreglo original: ");
			for (int j = 0; j < g; j++) {
				printf("%d", arr[j]);
			}
		}

		// Hacer uso del donde está para buscar un elemento
		if (option == 2) {
			int i;
			int n;
			int r;
			printf("Elija el grupo: ");
			scanf("%d", &i);

			printf("Eliga el numero del grupo: ");
			scanf("%d", &n);

			printf("El número de su repetición: ");
			scanf("%d", &r);

			Representacion curr;
			curr = temp_rep[i];
			int esta =  curr.su_donde_esta((void*)curr.representacion, n, r);
			printf("La repetición %d del elemento %d está en la posición %d", r, n, esta);
		}

		// Hacer uso del cuantos más grandes
		if (option == 3) {
			int i;
			int n;
			printf("Elija el grupo: ");
			scanf("%d", &i);

			printf("Eliga el numero del grupo: ");
			scanf("%d", &n);

			Representacion curr;
			curr = temp_rep[i];
			int mayor = curr.su_cuanto_mas_grande((void*)curr.representacion, n);
			printf("Hay %d elementos mayores a %d", mayor, n);
		}

		// Hacer uso de la función bits
		if (option == 4) {
			int i;
			printf("Elija el grupo: ");
			scanf("%d", &i);

			Representacion curr;
			curr = temp_rep[i];
			int bits = curr.su_bits((void*)curr.representacion);
			printf("La compresión ocupa %d bits", bits);
		}

		// Hacer uso de la función mostrar
		if (option == 5) {
			int i;
			printf("Elija el grupo: ");
			scanf("%d", &i);

			Representacion curr;
			curr = temp_rep[i];
			curr.su_imprimir((void*)curr.representacion);
		}

		// Salir del programa
		if (option == 6) {
			interface = false;
		}
		printf("\n");
	}
	return 0;
}
