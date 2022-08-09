#lang scheme

;;(Nombre div-by?)
;;Chequea si n es divisble por x
;;retorna un booleano
(define (div-by? n x)
  (zero? (remainder n x)))

(define (mazo list div)
  ;;Si la lista está vacía devuelve ()
  (if (empty? list)
      '()
  ;;Si no, busca si la cabeza es divisor
  (if (div-by? (car list) div)
      
      ;;Si lo es, toma la cabeza y la une al resto
      ;;de la función que será posteriormente filtrada
      (cons (car list) (mazo (cdr list) div))
      
      ;;Si no es divisible, se filtra dentro de los
      ;;parentesis y luego se repite la función
      (remove (car list) (mazo (cdr list) div))
)))

;;Caso de prueba, se ejecuta solito
(mazo '(1 2 4 6 8 9 4 2) 2)
