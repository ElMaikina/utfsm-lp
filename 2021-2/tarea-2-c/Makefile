all: Sim

Sim: simulacion.o animal.h animal.o
	gcc simulacion.o animal.h animal.o -o 1.out

simulacion.o: simulacion.c
	gcc -c simulacion.c
animal.h: animal.h
	gcc -c animal.h
animal.o: animal.c
	gcc -c animal.c

clean:
	en -rf *o Sim
