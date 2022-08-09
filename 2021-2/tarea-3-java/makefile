JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
Bosque.java \
Bueno.java \
Druida.java \
Enemigo.java \
Graficos.java \
Guerrero.java \
Jefe_Final.java \
Jugador.java \
Mago.java \
Main.java \
Malo.java \
Misiones.java \
Monstruo.java \
Montana.java \
Neutro.java \
NPC.java \
Planicie.java \
Tierra.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
