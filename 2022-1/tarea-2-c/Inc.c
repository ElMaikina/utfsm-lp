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

int digits (int num) {
	int count = 0; 
	if(num != 0) {
		while(num) { 
			num /= 10; 
			count ++; 
		} 
		return count ; 
	} return 1;
}

char* unario(int n) {
	char *result;
	if (n < 1) {
		result = (char*)malloc(sizeof(char));
		result[0] = '0';
		return result;
	}
	result = (char*)malloc(sizeof(char)*(n));
	for (int i = 0; i < n; i++) { 
		result[i] = '1';
	} 
	result[n] = '0';
	return result;
}

// Comprime el arreglo dado con el formato Incremental
Inc* comprimir_en_inc(int n, int *grupo) {	
	Inc *result;
	result = (Inc*)malloc(sizeof(Inc));
	int *arr_diferentes;
	int n_diferentes = 0;
	int rep_len = 0;
	int bit_len = 0;
	int max_r = 0;
	arr_diferentes = (int*)malloc(sizeof(int)*n);
	bool se_repite = false;

	// Recorre todo el arreglo original	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n_diferentes; j++) {
			// Busca los numeros diferentes
			if (grupo[i] == arr_diferentes[j]) {
				se_repite = true;
			}
		} 
		if (!se_repite) { // Si no se repite, es un nuevo numero
			max_r += 1;
			arr_diferentes[n_diferentes] = grupo[i];
			rep_len += grupo[i] + 1; 
			rep_len += max_r; 
			n_diferentes += 1;
		} 
		bit_len += strlen(unario(grupo[i])); 	
		se_repite = false;
	} 

	char *bits;
	bits = (char*)malloc(sizeof(char)*digits(bit_len));

	// Se agrega a bits la representacion unaria del elemento actual
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n_diferentes; j++) {
			if (grupo[i] == arr_diferentes[j]) {
				strcat(bits, unario(j));
			}
		}
	} 
	char *representaciones;
	representaciones = (char*)malloc(sizeof(char)*rep_len);

	// Se agrega a representaciones la representacion unaria del elemento
	// más el salto correspondiente
	for (int i = 0; i < n_diferentes; i++) {
		strcat(representaciones, unario(arr_diferentes[i]));
		strcat(representaciones, unario(i));
	} 
	representaciones[rep_len - 1] = ' ';

	// Se asigna la cantidad de numeros diferentes como caracter
	char *diferentes;
	diferentes = (char*)malloc(sizeof(char)*digits(n_diferentes));
	sprintf(diferentes, "%d", max_r);

	result->bits = bits;
	result->representaciones = representaciones;
	result->n = diferentes;

	free(arr_diferentes);
	return result;
} 

// Devuelve el arreglo a su formato original
int* descomprimir_en_inc(void *inc) {
	Inc *input;
	input = (Inc*)inc;

	int len_rep = atoi(input->n); 
	char curr;
	int ceros = 0;
	int curr_elem = 0;
	int curr_pos = 0;

	int *representaciones;
	representaciones = (int*)malloc(sizeof(int)*len_rep);

	// Se recorre el arreglo de representaciones
	// y se guarda cada elemento en la posicion
	// de su orden
	for (int i = 0; i < strlen(input->representaciones); i++) {
		curr = input->representaciones[i];
		if (curr == '0') { // Indica los saltos
			ceros++; 
		} // Indica que este es el elemento
		if (curr == '1' && ceros == 0) { 
			curr_elem++; 
		} // Indica que ya obtuvimos un elemento completo
		if (ceros == 2) {
			representaciones[curr_pos] = curr_elem;
			curr_elem = 0;
			curr_pos += 1;
			ceros = 0;
		}
	} representaciones[len_rep - 1] = curr_elem;

	int *result;
	result = (int*)malloc(sizeof(int)*strlen(input->bits));
	
	ceros = 0;
	curr_elem = 0;
	curr_pos = 0;
	for (int i = 0; i < strlen(input->bits); i++) {
		curr = input->bits[i];
		if (curr == '0') { 
			result[curr_pos] = representaciones[curr_elem];
		       	curr_elem = 0;
			curr_pos += 1;
		}
		if (curr == '1') { 
			curr_elem++; 
		}
	}
	return result;
}

// Recibe un entero e y un intero i. Se busca la i-ésima
// repetición del elemento e en el arreglo.
int donde_esta_en_inc(void *inc, int e, int i) {
	int reps = 0; // número de repeticiones de e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_inc(inc);
	
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
int cuantos_mas_grande_inc(void *inc, int e) {
	int reps = 0; // cantidad de numeros mayores a e
	int iter = 0; // iteración dentro del arreglo
	int curr;
	int *fixed_array; // arreglo descomprimido
	fixed_array = descomprimir_en_inc(inc);
	
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
int bits_inc(void *inc) {
	Inc *tmp;
	tmp = (Inc*)inc; // Castea el puntero void a un Inc
	int result = 0;
	result += strlen(tmp->bits);
	result += strlen(tmp->representaciones);
	result += strlen(tmp->n);
	return result;
}

// Muestra por pantalla al arreglo comprimido en Unario
void mostrar_inc(void *inc) {
	printf("SEGUN INCREMENTAL:\n");
	Inc *tmp;
	tmp = (Inc*)inc; // Castea el puntero void a un Inc
	printf("bits: %s\n", tmp->bits);
	printf("representaciones: %s\n", tmp->representaciones);
	printf("N: %s\n", tmp->n);
}
