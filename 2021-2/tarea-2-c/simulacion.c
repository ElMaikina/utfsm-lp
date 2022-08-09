#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "animal.h"

/*
STRUCT MATRIX: LA MATRIZ GUARDARA LAS POSICIONES DE LOS ANIMALES
            DENTRO DE UN ARREGLO DE ENTEROS QUE SIMULARA UNA 
            MATRIZ REAL.
*/
typedef struct Matrix{
    int width, height, limit, used;
    int *array;
} Matrix;

/*
STRUCT OBJETO: ESTE SERA EL OBJETO ALMACENADO EN NUESTRA
        COLA, TENDRA UN PUNTERO A UN ANIMAL, COORDENADAS,
        UN PUNTERO AL OBJETO SIGUIENTE Y LOS PUNTEROS
        PARA LA FUERZA, VELOCIDAD Y RESISTENCIA DEL ANIMAL.
*/
typedef struct Object{
    struct Object *Next;
    int p_fuerza, 
    p_velocidad, 
    p_resistencia;
    Animal *A;
    int x, y;
} Object;

/*
STRUCT MATRIX: LA COLA QUE NOS PERMITIRA ALMACENAR LOS OBJETOS.
*/
typedef struct Queue{
    struct Object *First;
    struct Object *Last;
} Queue;


/*
CREAR MATRIX: INICIALIZA NUESTRA MATRIZ DE MUNDO VACIA, ADEMAS
            PIDE LA MEMORIA SUFICIENTE, LOS ESPACIOS NO USADOS
            SERAN INDICADOS COMO CEROS.
*/
Matrix* CrearMatriz (int W, int H) {
    Matrix *FinalMatrix;
    FinalMatrix = (Matrix*)malloc(sizeof(Matrix));
    FinalMatrix->width = W;
    FinalMatrix->height = H;
    FinalMatrix->limit = W*H;
    FinalMatrix->used = 0;
    FinalMatrix->array = (int*)malloc(sizeof(int)*W*H);
    for(int i = 0; i<W*H; i++) {
        FinalMatrix->array[i] = 0;
    }return FinalMatrix;
}

/*
MOSTRAR MATRIX: MUESTRA LA MATRIZ MUNDO CON LAS CORRESPONDIENTES
            CASILLAS UTILIZADAS DENMINADAS POR UN UNO, AQUELLAS
            VACIAS ESTARAN INDICADAS POR UN CERO.
*/
void MostrarMatriz(Matrix* M) {
    printf("\n");
    for(int i = 0; i<M->limit; i++) {
        if (i%M->width == 0) {printf("\n");}
        printf("%d ", M->array[i]);
    }printf("\n\n");
}

/*
SUMAR PUNTO EN LA MATRIZ: REVISA LA CASILLA EN DONDE SE QUIERE
            INSERTAR UN NUEVO PUNTO, ESTA DELIMITIDO POR LAS
            CONDICIONES QUE LA TAREA MISMA INDICA.
*/
void SumarPuntoMatriz(Matrix* M, int x, int y) {
    if (M->array[x+y*M->width] != 0) {
        printf("No se puede insertar el animal aqui\n");
        return;
    }if (x < 0 || x > M->width) {
        printf("No se puede insertar el animal aqui\n");
        return;
    }if (y < 0 || y > M->height) {
        printf("No se puede insertar el animal aqui\n");
        return;
    }M->array[x+y*M->width] += 1;
}

/*
BUSCAR EN LA COLA: BUSCA EL OBJETO QUE ESTA HACIA NUESTRAS
                COORDENADAS OBJETIVO A TRAVES DE UN FOR.
*/
Object* searchInQueue(Queue *Q, int x, int y) {
    Object *O;
    O = (Object*)malloc(sizeof(Object));
    for(O->Next = Q->First; O->Next != NULL; O = O->Next) {
        if (O->x == x && O->y == y) {
            return O;
        }
    }printf("No se encontro el animal :(\n");
    return NULL;
}

/*
AÑADIR ELEMENTO EN LA COLA Y MATRIZ: AÑADE A UN ANIMAL DENTRO DE NUESTRA
        COLA Y NUESTRO MUNDO MATRIZ
*/
void QueueAdd(Queue* Q, Matrix* M, Object *NewObject){
    int x, y;
    x = NewObject->x;
    y = NewObject->y;
    SumarPuntoMatriz(M, x, y);

    if (Q->First == NULL) {
        Q->First = NewObject;
        Q->Last = NewObject;
        return;
    }if (Q->First != NULL) {
        Q->Last->Next = NewObject;
        Q->Last = NewObject;
        return;
    }
}
/*
MATAR A UN BEBE: SI EL BEBE NO PUEDE NACER, ENTONCES SE
        LIBERARA LA MEMORIA QUE ESTE OCUPA. SE BUSCA DENTRO
        DE LA COLA POR EL ANIMAL ES CUESTION Y LUEGO SE
        CONECTAN LOS PUNTOS.
*/
void MatarUnBebe (Animal *Abortado, Queue *Q) {
    Object *O;
    O = (Object*)malloc(sizeof(Object));
    for(O->Next = Q->First; O->Next != NULL; O = O->Next) {
        if (O->Next->A == Abortado) {
            Object *Aux;
            Aux = O->Next;
            O->Next = O->Next->Next;
            Aux->Next = NULL;
            Aux->A = NULL;
            free(Aux); 
            return;
            printf("Fue devorado for otras criaturas mas feroces que si mismo.\n");
        }
    }printf("El animal magicamente se aborto solo\n");
    return;
}
/*
PARIR A UN BEBE: RECIBE EL OBJETO QUE SE VA A REMPLAZAR POR
        EL ANIMAL QUE NACERA Y BUSCA LAS CASILLAS ADYACENTES
        PARA VER SI Y DONDE PUEDE NACER SIN QUE LO MATEN.
        EN EL CASO DE NO HAYAR DONDE PARIR, SE INVOCARA LA
        FUNCION MATAR A UN BEBE (DESCRITA MAS ABAJO).
*/
void ParirUnBebe (Object *Baby, Queue *Q, Matrix *M)  {
    int tries, newX, newY;
    tries = 0;
    while (tries < 4) {
        if (tries == 0) {
            newX = Baby->x - 1;
            newY = Baby->y;
        } if (tries == 1) {
            newX = Baby->x + 1;
            newY = Baby->y;
        } if (tries == 2) {
            newX = Baby->x;
            newY = Baby->y - 1;
        } if (tries == 3) {
            newX = Baby->x;
            newY = Baby->y + 1;
        }
        if (newX >= 0 && newX <= M->width) {
            if (newY >= 0 && newY <= M->height) {
                if (M->array[newX + newY*M->width] == 0) {
                    Baby->x = newX; 
                    Baby->y = newY;
                    printf("Un animalito ha nacido en: [%d,%d]\n", newX, newY);
                    return;
                }
            }
        }
        tries++;
    }printf("El animalito murio apenas entro a este cruel mundo...\n");
    MatarUnBebe(Baby->A,Q);
    return;
}

/*
MOVER PUNTO EN LA MATRIZ: TOMA UNA MATRIZ M, Y BUSCA LAS
            COORDENADAS X E Y PARA INSERTAR UN NUEVO ANIMAL
            SI EL ESPACIO ESTA OCUPADO, SE INDICARA QUE HAY
            UN CONFLICTO, INVOCARA A LA FUNCION DE REPRODUCCION
            Y A LA DE COMER O HUIR QUE CORRESPONDA, LUEGO
            INSERTARA EL HIJO DONDE HAYA UN ESPACIO LIBRE.
*/
void MoverPuntoMatriz(Matrix* M, Queue *Q, Object *Move, int x, int y) {
    if (M->array[x+y*M->width] != 0) {
        printf("Aqui habra un conflicto!!!\n");
        
        Animal *hijo;
        Object *Enemy;
        hijo = (Animal*)malloc(sizeof(Animal));
        Enemy = searchInQueue(Q, x, y);
        printf("\n----DOS ANIMALES ESTAN FORNICANDOO!----\n\n");
        Reproducir(Move->A, Enemy->A, hijo);
        printf("\n----Y AHORA SE ESTAN MATANDOOOO NOOOO!----\n\n");
        ComerOHuir(Move->A, Enemy->A);
        
        if (Move->A == NULL && Enemy->A != NULL) {
            printf("Caso A\n");
            Move->A = hijo;
            Move->x = x;
            Move->y = y;
            ParirUnBebe(Move, Q, M);
            M->array[x+y*M->width] = 1;
            return;
            
        }if (Move->A != NULL && Enemy->A == NULL) {
            printf("Caso B\n");
            Enemy->A = hijo;
            Enemy->x = x;
            Enemy->y = y;
            ParirUnBebe(Enemy, Q, M);M->array[x+y*M->width] = 1;
            return;
            
        }if (Move->A != NULL && Enemy->A != NULL) {
            printf("Caso C\n");
            Object *HijoObject;
            HijoObject = (Object*)malloc(sizeof(Object));
            HijoObject->A = hijo;
            HijoObject->x = x;
            HijoObject->y = y;
            QueueAdd(Q, M, HijoObject);
            ParirUnBebe(HijoObject, Q, M);M->array[x+y*M->width] = 1;
            return;
            
        }if (Move->A == NULL && Enemy->A == NULL) {
            printf("Caso D\n");
            MatarUnBebe(Enemy->A, Q);
            Move->A = hijo;
            Move->x = x;
            Move->y = y;
            ParirUnBebe(Move, Q, M);M->array[x+y*M->width] = 1;
            return;
            
        }
    }M->array[x+y*M->width] += 1;
}

/*
LIBERAR LA MATRIZ: LIBERA LA MEMORIA DE NUESTRO MUNDO
*/
void LiberarMatriz (Matrix *M) {
    M->array = NULL;
    free(M);
    M = NULL;
    printf("Se ha destruido el mundo completo...\n");
}

/*
EMPEZAR LA COLA: INICIALIZA LA COLA CON LA MEMORIA 
                        CORRESPONDIENTE.
*/
Queue* StartQueue() {
    Queue *Q;
    Q = (Queue*)malloc(sizeof(Queue));
    Q->First = NULL;
    Q->Last = NULL;
    return Q;
}

/*
MOVER OBJETO (AUXILIAR): MUEVE UN ANIMAL CADA VEZ QUE EL TIEMPO
            PASE DENTRO DEL JUEGO SIEMPRE CUANDO ESA POSCION NO
            ESTE FUERA DEL MAPA. ADEMAS LLAMA AL SIGUIENTE ANIMAL
            A HACER JUSTAMENTE LO MISMO.
*/
void MoveObjectsAux (Object *Curr, Queue *Q, Matrix *M)  {
    if (Curr->Next != NULL) {
        MoveObjectsAux(Curr->Next, Q, M);
    }
    srand(time(NULL));
    int random = rand();
    random = random%3;
    int newX, newY;

    if (random == 0) {
        newX = Curr->x - 1;
        newY = Curr->y;
    } if (random == 1) {
        newX = Curr->x + 1;
        newY = Curr->y;
    } if (random == 2) {
        newX = Curr->x;
        newY = Curr->y - 1;
    } if (random == 3) {
        newX = Curr->x;
        newY = Curr->y + 1;
    }
    if (newX >= 0 && newX <= M->width) {
        if (newY >= 0 && newY <= M->height) {
            M->array[Curr->x + Curr->y*M->width] -= 1;
            Curr->x = newX; Curr->y = newY;
            printf("Un animalito se ha movido hacia: [%d,%d]\n", newX, newY);
            MoverPuntoMatriz(M, Q, Curr, newX, newY);
            return;
        }
    }
    printf("\nEl animalito en [%d,%d] intento escaparse del mapa \nasi que tu lo devolviste a la tierra.\n", Curr->x, Curr->y);
}

/*
MOVER OBJETO (PRINCIPAL): MUEVE UN ANIMAL CADA VEZ QUE EL TIEMPO
            PASE DENTRO DEL JUEGO SIEMPRE CUANDO ESA POSCION NO
            ESTE FUERA DEL MAPA.
*/
void MoveObjects (Queue *Q, Matrix *M)  {
    if (Q->First != NULL) {
        MoveObjectsAux(Q->First, Q, M);
        return;
    }
}

/*
COLA MOSTRAR ANIMALES (AUXILIAR): NOS MUESTRA LAS CARACTERIRSITCAS 
        DEL ANIMAL INDICADO EN NUESTRO MUNDO. ADEMAS LLAMA AL SIGUIENTE
        OBJETO DE LA COLA A HACER LO MISMO
*/
void QueueShowAux(Object *O) {
    if (O->Next != NULL) {
        QueueShowAux(O->Next);
    }printf("Animalito encontrado en [%d, %d]", O->x, O->y);
    MostrarAnimal(O->A);
}

/*
COLA MOSTRAR ANIMALES (PRINCIPAL): LLAMA A TODOS LOS ELEMENTOS DE LA COLA
        A MOSTRAR LOS ATRIBUTOS DE SU ANIMAL EN CUESTION.
*/
void QueueShow(Queue *Q) {
    if (Q->First != NULL) {
        QueueShowAux(Q->First);
        return;
    } printf("No hay animales creados!\n");
}

/*
COLA BORRAR ANIMALES (AUXILIAR): BORRA Y LIBERA AL ANIMAL INDICADO EN 
        NUESTRO MUNDO. ADEMAS LLAMA AL SIGUIENTE OBJETO DE LA COLA A 
        HACER LO MISMO.
*/
void QueueDelAux(Object *O) {
    if (O->Next != NULL) {
        QueueDelAux(O->Next);
    }printf("Animalito borrado en [%d, %d]. ", O->x, O->y);
    Borrar(O->A);
    O->Next = NULL;
    O->A = NULL;
    free(O);
    O = NULL;
}

/*
COLA BORRAR ANIMALES (PRINCIPAL): BORRA A TODOS LOS ELEMENTOS DE LA COLA.
*/
void QueueDel(Queue *Q) {
    if (Q->First != NULL) {
        QueueDelAux(Q->First);
        Q->First = NULL;
        Q->Last = NULL;
        Q = NULL;
        return;
    } printf("No hay animales creados!\n");
}

/*
PEDIR A JUGADOR POR ANIMAL POR PANTALLA: DEVUELVE UN OBJETO QUE CONTIENE
        AL ANIMAL QUE SE PIDE POR PANTALLA AL JUGADOR
*/
Object* AskPlayerThroughConsole(Matrix *M, int width, int height) {
    Object *NewObject;
    NewObject = (Object*)malloc(sizeof(Object));
    NewObject->A = CrearAnimal();
    
    int x, y;
    printf("\nAhora indica donde Spawneara el Animalito\n");
    printf("Coordenada X: ");
    scanf("%d", &x); getchar();
    printf("Coordenada Y: ");
    scanf("%d", &y); getchar();

    if (x < 0 || x > width) {
        printf("Este punto esta fuera del mapa!\n");
        free(NewObject);
        return NULL;
    }if (y < 0 || y > height) {
        printf("Este punto esta fuera del mapa!\n");
        free(NewObject);
        return NULL;
    }if (M->array[x+y*height] != 0) {
        printf("Este punto esta ocupado!\n");
        free(NewObject);
        return NULL;
    }
    NewObject->x = x; NewObject->y = y;

    char tipo;
    printf("\nDescribe el tipo de fuerza que tendrá: ");
    scanf("%c", &tipo); getchar();
    printf("Escribe la magnitud de fuerza: ");
    scanf("%d", &NewObject->p_fuerza); getchar();   
    AsignarFuerza(NewObject->A, tipo, &NewObject->p_fuerza);

    printf("\nDescribe el tipo de velocidad que tendrá: ");
    scanf("%c", &tipo); getchar();
    printf("Escribe la magnitud de velocidad: ");
    scanf("%d", &NewObject->p_velocidad); getchar();
    AsignarVelocidad(NewObject->A, tipo, &NewObject->p_velocidad);

    printf("\nDescribe el tipo de resistencia que tendrá: ");
    scanf("%c", &tipo); getchar();
    printf("Escribe la magnitud de resistencia: ");
    scanf("%d", &NewObject->p_resistencia); getchar();
    AsignarResistencia(NewObject->A, tipo, &NewObject->p_resistencia);

    AsignarPelea(NewObject->A);
    AsignarSexo(NewObject->A);

    return NewObject;
}

int main() {
    Matrix *matrix;
    Queue *bestiario;
    int W, H;
    W = 20; H = 20;

    matrix = CrearMatriz(W, H);
    bestiario = StartQueue();

    int GameRunning = 0;
        
    printf("Se te ha otorgado el poder de dios. Manipular la vida es\n");
    printf("es mera diversión tí. Se te ha dado la tarea de jugar con\n");
    printf("la vida de los seres vivos...\n");
 
    while (GameRunning < 6) {
        printf("\nELIGE TU PROXIMO MOVIMIENTO!\n\n");
        printf("    1) CREAR NUEVO ANIMAL\n");
        printf("    2) VER ANIMALES CREADOS\n");
        printf("    3) AVANZAR EL TIEMPO\n");
        printf("    4) VER MAPA DEL MUNDO\n");
        printf("    5) DESTRUIR EL MUNDO\n");
        printf("    6) CERRAR JUEGO\n");

        scanf("%d", &GameRunning); getchar();
        
        if (GameRunning == 1) {
            Object *newAnimal;
            newAnimal = AskPlayerThroughConsole(matrix,W,H);
            if (newAnimal != NULL) {
                QueueAdd(bestiario, matrix,newAnimal);
            }
        }if (GameRunning == 2) {
            QueueShow(bestiario);
        }if (GameRunning == 3) {
            MoveObjects(bestiario, matrix);
        }if (GameRunning == 4) {
            MostrarMatriz(matrix);
        }
    }
    QueueDel(bestiario);
    LiberarMatriz(matrix);
    matrix = NULL;
    bestiario = NULL;
    return 0;
}
