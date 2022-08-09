#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Animal{
    //Tipos de los atributos
    char tipo_fuerza;
    char tipo_velocidad;
    char tipo_resistencia;
    //Magnitud de los atributos
    void* fuerza;
    void* velocidad;
    void* resistencia;
    //Relaciones Inter-Especie
    
    void (*reproduccion)(struct Animal*, struct Animal*, struct Animal*);
    void (*comerHuir)(struct Animal*, struct Animal*);
}Animal;

//Acciones en el mundo
void AsignarFuerza(Animal *a, char tipo, int *poder);
void AsignarVelocidad(Animal *a, char tipo, int *poder);
void AsignarResistencia(Animal *a, char tipo, int *poder);
void AsignarPelea(Animal *a);
void AsignarSexo(Animal *a);
Animal* CrearAnimal();
void Borrar(Animal *a);
void MostrarAnimal(Animal *a);
void Reproducir(Animal *padre, Animal *madre, Animal *hijo);
void ComerOHuir(Animal *a, Animal *b);
int Comparar(Animal *a, Animal *b);

//Tipos de relaciones
void ReproduccionSimple(Animal *padre, Animal *madre, Animal *hijo);
void ReproduccionCruzada(Animal *padre, Animal *madre, Animal *hijo);
void ComerSiempre(Animal *a, Animal *b);
void HuirSiempre(Animal *a, Animal *b);
void ComerAleatorio(Animal *a, Animal *b);
