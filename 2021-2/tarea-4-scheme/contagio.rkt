#lang scheme


;;El problema consiste en visitar los vecinos en d tiempo
;;podemos ordenar el algoritmo de la siguiente manera:

;; 1- Verificar si existe el nodo
;; 2- Visitar al nodo en cuestion
;; 3- Ver si tiene vecinos
;; 4- Avanzar una unidad de tiempo (tiempo -1)
;; 5- Si 1 se cumple y tiempo > 0, volver a 2

;;(Nombre buscar)
;;Busca un nodo en el grafo y retorna un objeto de la
;;forma (Padre (hijo_1, hijo_2, hijo_3... hijo-n). Si
;;no existe retonra '().
;;Retorna (Padre (hijo_1, hijo_2, hijo_3... hijo-n)
(define (buscar grafo n)
  (if (empty? grafo)
      '()
      (if (equal? n (caar grafo))
          (car grafo)
          (buscar (cdr grafo) n))))

;;(Nombre padre)
;;Dado un objeto nodo ya descrito, retorna el padre
;;de éste mismo.
(define (padre grafo n)
  (if (empty? grafo)
      '()
      (car (buscar grafo n))))
;;(Nombre padre)
;;Dado un objeto nodo ya descrito, retorna los hijos
;;de éste mismo en forma de lista.
(define (hijos grafo n)
  (if (empty? grafo)
      '()
      (car (cdr (buscar grafo n)))))
;;(Nombre hijo)
;;Dado un nodo llamado subgrafo, va contagiando
;;el padre del nodo, y subsiguientemente a los
;;hijos, cada vez que itere reducirá la cantidad
;;de días restantes para contagiar.
(define (hijo subgrafo grafo d)
  (if (empty? subgrafo)
      '()
      (append (contagio grafo (car subgrafo) (- d 1)) (hijo (cdr subgrafo) grafo d))))

;;Dado que la tarea no lo especifica, muestra un recorrido completo de
;;contagio, es decir, se repetirán los nodos en ciertas ocaciones.
;;Ésto permite a la función ser útil en casos donde los vecinos sean
;;uni-direccionales en vez de bi-direccionales.
(define (contagio grafo n d)
  (if (positive? d)
      (if (empty? (buscar grafo n))
          '()
          (append (list n) (hijo (hijos grafo n) grafo d))
          )
      (list (append '() n))
      )
  )
  
(contagio (quote ((2 (1 3 4)) (1 (2)) (3 (2)) (4 (2)) )) 2 0)