#lang scheme

;; Nombre: suma_especial

;; Descipcion: Suma todos los elementos de la
;; lista 'l' y luego devuelve la division entera 
;; entre dicha suma 'sum' y 'a'

;; Entrega: Un entero

(define (suma_especial l a)
  (let ((sum (apply + l)))
    (if (zero? sum)
      0
      (quotient sum a)
)))

;; Caso de prueba para Suma Especial
(suma_especial '(1 2 3 9) 3)
(suma_especial '(1 2 3 4) 4)



;; Nombre: merge_simple

;; Descripcion: La funcion evalua el primer 
;; elemento de cada lista y luego invoca la
;; funcion en lo que resta de las listas

;; Entrega: Una lista de enteros

(define (merge_simple l1 l2 ops)
  (if (empty? l1)
       '()
       (let ( 
	      (x (first l1)) 
	      (y (first l2)) 
	      (op (first ops))
	      )
	 (cons (evaluate x y op) 
	       (merge_simple 
		 (rest l1) 
		 (rest l2) 
		 (rest ops)) 
))))

;; Nombre: evaluate

;; Descripcion: Dado dos operandos y un operador
;; realiza la operacion dentro de los operandos

;; Entrega: Un entero

(define (evaluate x y op)
  (if (equal? op #\S) (+ x y)
  (if (equal? op #\R) (- x y)
  (if (equal? op #\M) (* x y)
    '()
))))


;; Caso de prueba
(merge_simple '(1 2 3) '(4 5 6) '(#\S #\M #\R))



;; Nombre: merge_cola

;; Descripcion Esta funcion solo esta para llamar a
;; la funcion auxiliar (que sale mas abajo)

;; Entrega: Una lista de enteros

(define (merge_cola l1 l2 ops)
  (if (empty? l1)
       '()
       (merge_cola_aux l1 l2 ops '())
))

;; Nombre: merge_cola_aux

;; Descripcion: la funcion tiene los mismos parametros
;; que la funcion original, mas un parametro que se
;; llama res, al cual se le agregan los resultados
;; despues de cada recursion que se realiza, permitiendo
;; a la funcion ser del tipo recursion de cola

;; Entrega: Una lista de enteros

(define (merge_cola_aux l1 l2 ops res)
  (if (empty? l1)
    res
    (let* (
	   (x (first l1)) 
	   (y (first l2)) 
	   (op (first ops)) 
	   (z (evaluate x y op))
	   )
      (merge_cola_aux 
	(rest l1) 
	(rest l2) 
	(rest ops) 
	(append res (list z)))
)))

;; Caso de prueba
(merge_cola '(1 2 3) '(4 5 6) '(#\S #\M #\R))




;; Nombre: demerge_simple

;; Descripcion: Mezcla ambas funciones anteriores para crear una lista
;; que contenga ambas sublistas, la que realiza la operacion 'A' y luego 
;; la que realiza la operacion 'B'

;; Entrega: Dos listas de Enteros

(define (demerge_simple l f)
  (list (demerge_simple_a l f) (demerge_simple_b l f))
)

;; Nombre: demerge_simple_a

;; Descripcion: Crea la lista que realiza la operacion 'A' dentro de la lista

;; Entrega: Una lista de enteros

(define (demerge_simple_a l f)
  (if (empty? l)
       '()
       (let ( (a (f (first l))) )
	 (cons a (demerge_simple_a (rest l) f))
)))

;; Nombre: demerge_simple_a

;; Descripcion: Crea la lista que realiza la operacion 'B' dentro de la lista

;; Entrega: Una lista de enteros

(define (demerge_simple_b l f)
  (if (empty? l)
       '()
       (let ( (b (- (first l) (f (first l)))) )
	 (cons b (demerge_simple_b (rest l) f))
)))

;; Caso de prueba
(demerge_simple '(1 2 3 4 5 6) (lambda (x) (quotient x 2)))






;; Nombre: demerge_cola

;; Descripcion: Mezcla ambas funciones anteriores para crear una lista
;; que contenga ambas sublistas, la que realiza la operacion 'A' y luego 
;; la que realiza la operacion 'B'

;; Entrega: Dos listas de Enteros

(define (demerge_cola l f)
  (list 
    (demerge_cola_a l f '()) 
    (demerge_cola_a l f '()) 
))

;; Nombre: demerge_cola_a

;; Descripcion: Asigna en 'a' la operacion realizada en el primer
;; elemento de la lista, en 'new_res' el resultado actual tras haber
;; realizado la operacion en 'a'. Luego realiza la funcion nuevamente
;; pasando como parametros el resto de las listas y a 'new_res' como
;; resultado actual

;; Entrega: Una listas de Enteros

(define (demerge_cola_a l f res)
  (if (empty? l)
    res
    (let* ( 
	    (a (list (f (first l)))) 
	    (new_res (append res a))
	    )
      (demerge_cola_a (rest l) f new_res)
)))

;; Nombre: demerge_cola_b

;; Descripcion: Asigna en 'b' la operacion realizada en el primer
;; elemento de la lista, en 'new_res' el resultado actual tras haber
;; realizado la operacion en 'b'. Luego realiza la funcion nuevamente
;; pasando como parametros el resto de las listas y a 'new_res' como
;; resultado actual

;; Entrega: Una listas de Enteros

(define (demerge_cola_b l f res)
  (if (empty? l)
    res
    (let* ( 
	    (b (list (- (first l) (f (first l)))))
	    (new_res (append res b))
	    )
      (demerge_cola_a (rest l) f new_res)
)))

;; Caso de prueba
(demerge_cola '(1 2 3 4 5 6) (lambda (x) (quotient x 2)))




;; Nombre: superior

;; Descripcion: La funcion define cuatro variables distintas: 

;; primero; "merge_res" que contiene la funcion "merge" aplicada a
;; 	"l", "l" y "ops". 
;; segundo; tenemos "demerge_res" que aplica "demerge" a "merge_res"
;; 	y "f". 
;; tercero; "r1" que aplica "suma_especial" a "l", "num" y la multiplica 
;; 	por 2.
;; cuarto; "r2" que aplica suma especial al primer y segundo elemento de 
;;	"demerge_res" y luego los suma.

;; Finalmente, la funcion compara el resultado de "r1" y "r2", si "r1" es
;; mayor a "r2", la funcion retorna 1, de no ser asi, la funcion retorna 0

;; Entrega: Un entero, que representa un valor booleano

(define (superior l ops f num)
  (let* ( 
	 (merge_res (merge_cola l l ops))
	 (demerge_res (demerge_cola merge_res f)) 
	 (r1 (* (suma_especial l num) 2))
	 (r2 (+ (suma_especial (car demerge_res) num)
		(suma_especial (cadr demerge_res) num))))
    (if (> r1 r2)
      1
      0
)))

;; Casos de prueba
(superior '(1 2 3) '(#\S #\M #\R) (lambda (x) (- x 2)) 2)
(superior '(1 2 3) '(#\S #\S #\M) (lambda (x) (modulo x 2)) 2)
(superior '(1 2 3 4 5 6) '(#\S #\M #\R #\S #\M #\R) (lambda (x) (- x 2)) 2)




;; Nombre: all_superior

;; Descripcion: La funcion esencialmente aplica una sub-funcion recursiva que se 
;; aplica para cada fila que halla en la matriz, luego esta misma invoca a otra 
;; funcion recursiva, dado que estamos operando en matrices de dos dimensiones.

;; Entrega: Una lista de listas de enteros

(define (all_superior matriz_ls matriz_ops matriz_f matriz_nums c f)
  (if (empty? matriz_ls)
    '()
    (let* (
	(fila_ls (car matriz_ls)) 
	(fila_ops (car matriz_ops)) 
	(fila_f (car matriz_f)) 
	(fila_nums (car matriz_nums))
	(matriz_sup (superior_fila fila_ls fila_ops fila_f fila_nums))
	)
      (cons matriz_sup (all_superior (cdr matriz_ls) (cdr matriz_ops) (cdr matriz_f) (cdr matriz_nums) c f))
)))

;; Nombre: superior_fila

;; Descripcion: Esta funcion itera dentro de cada fila de la matriz, usando la funcion
;; "superior" para cada lista que halle dentro de la fila que se le entrega

;; Entrega: Una lista de Enteros

(define (superior_fila fila_ls fila_ops fila_f fila_nums)
  (if (empty? fila_ls)
    '()
    (cons (superior (car fila_ls) (car fila_ops) (car fila_f) (car fila_nums))
	  (superior_fila (cdr fila_ls) (cdr fila_ops) (cdr fila_f) (cdr fila_nums)))))


;; Caso de prueba
(all_superior
'(((1 2 3) (1 1 1)) ((2 2 2) (3 4 5)))
'(((#\S #\M #\R) (#\S #\S #\S)) ((#\R #\R #\R) (#\S #\S #\M)))
(list (list (lambda (x) (- x 2)) (lambda (x)
(modulo x 2))) (list (lambda (x) (quotient x 2)) (lambda (x) (modulo x 2))))
'((2 3) (2 2))
2 2
)





