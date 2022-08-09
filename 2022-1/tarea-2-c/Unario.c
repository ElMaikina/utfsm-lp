#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Unario {
	/* arreglo de 0 e 1 */
	char *bits;
} Unario;

/* n representa la cantidad de numeros dentro del arreglo grupo */

/* Comprime el arreglo dado con el formato unario */
Unario* comprimir_en_unario(int n, int *grupo) {
	int largo = 0;
	Unario *result = (Unario*)malloc(sizeof(Unario));

	// el largo del resultado será la suma de cada elemento
	// del arreglo grupo, más la cantidad de espacios, la
	// cuál corresponde a la cantidad de elementos en grupo
	// menos uno, ya que el último elemento no forma un espacio;
	for (int i = 0; i < n; i++) {
		largo += grupo[i];
	}
       	largo += n - 1;
	 
	result = (Unario*)malloc(sizeof(Unario));
	result->bits = (char*)malloc(sizeof(char)*largo);
	
	// Luego, todos los elementos de este string se igualarán
	// a 1, ya que el arreglo contiene mayormente ese número.
	for (int i = 0; i < largo; i++) {
		result->bits[i] = '1';
	}

	// Se recorrerá nuevamente el arreglo grupo, para cada una
	// de sus posiciones se saltará al espacio donde corresponderá
	// colocar un 0.
	int div = 0;
	for (int i = 0; i < n - 1; i++) {
		div += grupo[i];
		result->bits[div] = '0';
		div++;
	}
	return result;
} 

/* Devuelve el arreglo a su formato original */
int* descomprimir_en_unario(void *unario) {
	Unario *tmp;
	tmp = (Unario*)unario; // Castea el puntero void a un Unario
	char c = 'a'; // Se asigna 'a' para entrar al while
	int largo = 0;
	int elementos = 1;

	// Consigue el largo de bits comprimidos
	while (c) {
		c = tmp->bits[largo];
		if (c == '0') {
			elementos++;
		}
		largo++;
	}
	largo--;
	
	// Pide la memoria para el arreglo resultante
	int *result;
	result = (int*)malloc(sizeof(int)*elementos);
	int pos = 0;

	// Inicializa al arreglo con elementos cero
	for(int i = 0; i < elementos; i++) {
		result[i] = 0;
	}

	// Recorrerá el arreglo bits, sumándole una vez
	// a cada elemento si es que haya un uno. De lo
	// contrario, pasará a la siguiente casilla del
	// arreglo resultado y seguirá sumando allí.
	for(int i = 0; i < largo; i++) {
		c = tmp->bits[i];
		if (c == '1') {
			result[pos] += 1;	
		}
		if (c == '0') {
			pos++;
		}
	}
	return result;
}

// Recibe un entero e y un intero i. Se busca la i-ésima
// repetición del elemento e en el arreglo.
int donde_esta_en_unario(void *unario, int e, int i) {
	int reps = 0; // número de repeticiones de e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_unario(unario);
	
	while(curr) {
		curr = fixed_array[iter];
		// Si el elemento actual es igual a e
		if (curr == e) {
			reps++;
		}
		// Si la cantidad de repeticiones es
		// igual a la cantidad buscada, oséa i
		if (reps == i) {
			free(fixed_array);
			return iter;
		} 
		iter++;
	}
	free(fixed_array);
	return -1;
}

// Cuenta cuantos elementos son mayores a e dento del arreglo 
int cuantos_mas_grande_unario(void *unario, int e) {
	int reps = 0; // cantidad de numeros mayores a e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_unario(unario);
	
	while(curr) {
		curr = fixed_array[iter];
		// Si el elemento actual es mayor a e
		if (curr > e) {
			reps++;
		}
		iter++;
	}
	return reps;
}

// Obtiene el tamaño según pide el enunciado de la tarea
int bits_unario(void *unario) {
	Unario *tmp;
	tmp = (Unario*)unario; // Castea el puntero void a un Unario
	return strlen(tmp->bits);
}

// Muestra por pantalla al arreglo comprimido en Unario
void mostrar_unario(void *unario) {
	printf("UNARIO:\n");
	Unario *tmp;
	tmp = (Unario*)unario; // Castea el puntero void a un Unario
	char c = 'a';
	int largo = 0;

	while (c) {
		c = tmp->bits[largo];
		printf("%c", c);
		largo++;
	}
	printf("\n");
}
