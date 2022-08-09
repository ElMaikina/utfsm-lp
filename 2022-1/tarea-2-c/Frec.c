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

// Comprime el arreglo dado con el formato Frecuencia
Frec* comprimir_en_frec(int n, int *grupo) 
{	
	Frec *result;
	result = (Frec*)malloc(sizeof(Frec));

	// Se utiliza una tabla de Hashing para almacenar
	// cada elemento con su respectiva frecuencia|
	HashTable *HT;
	HT = createHT(grupo, n);

	// Se insertan los numeros en la tabla, si el elemento existe
	// se incrementa la frecuencia, en vez de haber una colisión
	for (int i = 0; i < n; i++) { 
		insertHT(HT, grupo[i]);
	}
	// Representacion de bits en un arreglo de enteros
	int *bit_int_arr;
	bit_int_arr = (int*)malloc(sizeof(int)*n);

	int curr = findtopHT(HT);
	int priority = 1;

	while (curr != -1) {
		// Para cada máximo en la tabla, buscará en que posiciones
		// del areglo grupo esta calza
		for (int i = 0; i < n; i++) { 
			// Una vez que calce, se guardará en base a su
			// representación dentro del arreglo bit_int_arr
			if (grupo[i] == curr) {
				bit_int_arr[i] = priority;
			}				
		} 
		priority++;
		curr = findtopHT(HT);
	}

	// Se convierte al arreglo de bits a formato Unario
	Unario *bits_result;
	bits_result = comprimir_en_unario(n, bit_int_arr);
	free(bit_int_arr);

	// Se reinicia la tabla
	for (int i = 0; i < n; i++) { 
		insertHT(HT, grupo[i]);
	}

	// Representacion de representaciones en un arreglo de enteros
	int *rep_int_arr;
	rep_int_arr = (int*)malloc(sizeof(int)*n);

	curr = findtopHT(HT);
	priority = 1;
	int pos = 0;

	// Nuevamente buscará a través de los máximos
	while (curr != -1) {
		rep_int_arr[pos] = curr;
		rep_int_arr[++pos] = priority;
		priority++;
		pos++;
		curr = findtopHT(HT);
	}

	// Se convierte al arreglo de bits a formato Unario
	Unario *rep_result;
	rep_result = comprimir_en_unario(pos, rep_int_arr);
	free(rep_int_arr);

	result->bits = bits_result->bits;
	result->representaciones = rep_result->bits;
	free(HT);

	return result;
} 

// Devuelve el arreglo a su formato original
int* descomprimir_en_frec(void *frec) {
	Frec *input;
	input = (Frec*)frec;
	int rep; // Largo del arrelgo bits
	int bit; // Largo del arreglo representacion
	
	rep = strlen(input->representaciones);
	bit = strlen(input->bits);

	char curr;
	int ceros = 0;
	int curr_elem = 0;
	int curr_freq = 0;

	int *new_rep;
	new_rep = (int*)malloc(sizeof(int)*rep);
	int new_rep_index = 0;

	// Se recorre el arreglo de representaciones
	// y se guarda cada elemento en la posicion
	// de su orden
	for (int i = 0; i < rep; i++) {
		curr = input->representaciones[i];
		if (curr == '0') { // Indica los saltos
			ceros++; 
		} // Indica que este es el elemento
		if (curr == '1' && ceros == 0) { 
			curr_elem++; 
		} // Indica que este es su representacion
		if (curr == '1' && ceros == 1) { 
			curr_freq++; 
		} // Indica que ya obtuvimos un elemento completo
		if (ceros == 2) {
			new_rep[curr_freq - 1] = curr_elem;
			curr_elem = 0;
			curr_freq = 0;
			ceros = 0;
		}
	}
	new_rep[curr_freq - 1] = curr_elem;
	curr_elem = 0;
	int *new_bit;
	int pos = 0;
	new_bit = (int*)malloc(sizeof(int)*bit);

	// Se recorre el arreglo de bits y se guarda cada 
	// elemento en la posicion de su orden
	for (int i = 0; i < bit; i++) {
		curr = input->bits[i];
		// Cuenta la cantidad de unos
		if (curr == '1') {
			curr_elem++; 
		} // Indica los saltos
		if (curr == '0') {
			new_bit[pos] = new_rep[curr_elem - 1];
			curr_elem = 0;
			pos++;
		}
	} new_bit[pos] = new_rep[curr_elem - 1];
	pos++;
	int *result;
	result = (int*)malloc(sizeof(int)*pos);

	for (int i = 0; i < pos; i++) {
		result[i] = new_bit[i];
	}

	free(new_bit);
	free(new_rep);

	return result;
}

// Recibe un entero e y un intero i. Se busca la i-ésima
// repetición del elemento e en el arreglo.
int donde_esta_en_frec(void *frec, int e, int i) {
	int reps = 0; // número de repeticiones de e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_frec(frec);
	
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
int cuantos_mas_grande_frec(void *frec, int e) {
	int reps = 0; // cantidad de numeros mayores a e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_frec(frec);
	
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
int bits_frec(void *frec) {
	Frec *tmp;
	tmp = (Frec*)tmp; // Castea el puntero void a un Frec
	int result = 0;
	result += strlen(tmp->bits);
	result += strlen(tmp->representaciones);
	return result;
}


// Muestra por pantalla al arreglo comprimido en Unario
void mostrar_frec(void *frec) {
	printf("SEGUN FRECUENCIA:\n");
	Frec *tmp;
	tmp = (Frec*)frec; // Castea el puntero void a un Unario
	char c = 'a';
	int largo = 0;

	printf("bits: ");
	while (c) {
		c = tmp->bits[largo];
		printf("%c", c);
		largo++;
	}
	printf("\n");

	c = 'a';
	largo = 0;

	printf("representaciones: ");
	while (c) {
		c = tmp->representaciones[largo];
		printf("%c", c);
		largo++;
	}
	printf("\n");
}
