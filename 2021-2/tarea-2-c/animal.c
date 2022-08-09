#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "animal.h"

void AsignarFuerza(Animal *a, char tipo, int *poder) {
    a->tipo_fuerza = tipo;
    a->fuerza = (int*)malloc(sizeof(int));
    a->fuerza = poder;
}
void AsignarVelocidad(Animal *a, char tipo, int *poder) {
    a->tipo_velocidad = tipo;
    a->velocidad = (int*)malloc(sizeof(int));
    a->velocidad = poder;
}
void AsignarResistencia(Animal *a, char tipo, int *poder) {
    a->tipo_resistencia = tipo;
    a->resistencia = (int*)malloc(sizeof(int));
    a->resistencia = poder;
}
void AsignarSexo(Animal *a) {
    char option_r;
    printf("\nAhora elige el tipo de reproducción que tendrá:\n  a) Simple\n  b) Cruzada\n");
    scanf("%c", &option_r);
    getchar();
    if (option_r == 'a') {a->reproduccion = ReproduccionSimple; return;}
    if (option_r == 'b') {a->reproduccion = ReproduccionCruzada; return;}
    else {
        printf("Comando incorrecto! intente nuevamente.\n");
        AsignarSexo(a);
    }
}

void AsignarPelea(Animal *a) {
    char option_r;
    printf("\nPor último, su conducta al enfrentar otro animal:\n");  
    printf("  a) Comer al otro SIEMPRE\n  b) Huir del otro SIEMPRE\n  c) Aleatorio\n");
    scanf("%c", &option_r);
    getchar();
    if (option_r == 'a') {a->comerHuir = ComerSiempre; return;}
    if (option_r == 'b') {a->comerHuir = HuirSiempre; return;}
    if (option_r == 'c') {a->comerHuir = ComerAleatorio; return;}
    else {
        printf("Comando incorrecto! intente nuevamente. \n");
        AsignarPelea(a);
    }
}
//Acciones en el mundo
Animal* CrearAnimal() {
    printf("\nHaz creado un animal nuevo! Entregale los atributos\n");
    printf("que determinaran su supervivencia... o muerte.\n");
    Animal *a;
    a = (Animal*)malloc(sizeof(Animal));
    return a;
}
void Borrar(Animal *a) {
    a->fuerza = NULL;
    a->resistencia = NULL;
    a->velocidad = NULL;
    a->comerHuir = NULL;
    a->reproduccion = NULL;
    free(a);
    a = NULL;
    printf("Ha muerto uno de nuestros animalitos :(\n");
}

void MostrarAnimal(Animal *a) {
    printf("\nMostrando Animalito: \n");
    printf("    Tipo de Fuerza: %c\n", a->tipo_fuerza);
    printf("    Tipo de Resistencia: %c\n", a->tipo_resistencia);
    printf("    Tipo de Velocidad: %c\n\n", a->tipo_velocidad);
    printf("    Fuerza: %d\n", *((int*)a->fuerza) );
    printf("    Resistencia: %d\n", *((int*)a->resistencia) );
    printf("    Velocidad: %d\n\n", *((int*)a->velocidad) );
}

void Reproducir(Animal *padre, Animal *madre, Animal *hijo) {
    srand(time(NULL));
    int random = rand();
    random = random%2;
    printf("Se eligio la reproduccion del animal %d\n", random);
    if (random == 0) { 
        padre->reproduccion(padre,madre,hijo);
        return;
    }if (random == 1) { 
        madre->reproduccion(madre,padre,hijo);
        return;
    }
}
void ComerOHuir(Animal *a, Animal *b) {
    srand(time(NULL));
    int random = rand();
    random = random%2;
    printf("Se eligio el combate del animal %d\n", random);
    if (random == 0) { a->comerHuir(a,b);}
    if (random == 1) { b->comerHuir(b,a);}
}

int Comparar(Animal *a, Animal *b) {
    int ganador = 0;
    if (a->fuerza > b->fuerza) {
        ganador++;
    }if (a->velocidad > b->velocidad) {
        ganador++;
    }if (a->resistencia > b->resistencia) {
        ganador++;
    }if (ganador > 1) {return 1;}
    else {return 0;}
}

//Tipos de relaciones
void ReproduccionSimple(Animal *padre, Animal *madre, Animal *hijo) {
    hijo = (Animal*)malloc(sizeof(Animal));
    if (Comparar(padre, madre) == 0) {
        hijo = padre;
    }if (Comparar(padre, madre) == 1) {
        hijo = madre;
    }
}
void ReproduccionCruzada(Animal *padre, Animal *madre, Animal *hijo) {
    hijo = (Animal*)malloc(sizeof(Animal));
    if (Comparar(padre, madre) == 0) {

        hijo->fuerza = padre->fuerza;
        hijo->velocidad = padre->velocidad;
        hijo->reproduccion = padre->reproduccion;
        
        hijo->resistencia = madre->resistencia;
        hijo->comerHuir = madre->comerHuir;
        return;
    }
    if (Comparar(padre, madre) == 1) {
        
        hijo->resistencia = padre->resistencia;
        hijo->comerHuir = padre->comerHuir;
        
        hijo->fuerza = madre->fuerza;
        hijo->velocidad = madre->velocidad;
        hijo->reproduccion = madre->reproduccion;
        return;
    }
}
void ComerSiempre(Animal *a, Animal *b) {
    if (a->fuerza > b->resistencia) {
        printf("Uno de los animales devoro al otro salvajemente!\n");
        Borrar(b); return;
    }else { 
        printf("El animal pudo resistir el ataque y contraatacar!\n");
        Borrar(a); return;
    }
}
void HuirSiempre(Animal *a, Animal *b) 
{    if (a->velocidad > b->velocidad) {
        printf("Uno de los animales pudo escapar!\n");
        return;
    }else { 
        printf("El animal intento escpar... y fallo :(\n");
        Borrar(a);
        return;
    }
}
void ComerAleatorio(Animal *a, Animal *b) {
    srand(time(NULL));
    int random = rand();
    random = random%3;
    int pelea;
    if (random == 0) { pelea = a->fuerza - b->fuerza;}
    if (random == 1) { pelea = a->velocidad - b->velocidad;}
    if (random == 2) { pelea = a->resistencia - b->resistencia;}
    if (pelea > 0) {Borrar(b);}
    else {Borrar(a);}
}
