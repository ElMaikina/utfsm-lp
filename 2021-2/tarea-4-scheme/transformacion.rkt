#lang scheme

;;(Nombre comp)
;;Funcion que compara entre r1 y r2
;;retorna el mayor entre ambos
;;Retorna el mayor entre r1 y r2
(define (comp f1 f2 n)
  (let ((r1 (f2 (f1 n))) (r2 (f1 (f2 n))))
  (if (> r1 r2)
      r1
      r2
      )
  )
)

;;Compara el primer elemento y luego
;;llama la función al resto de la lista
(define (transformacion f1 f2 n)
    (if (empty? n)
        '()
        (cons (comp f1 f2 (car n)) (transformacion f1 f2 (cdr n)))
        )
    )

;;Caso de prueba, se ejecuta solito
(transformacion (lambda (x) (+ 2 x)) (lambda (x) (/ x 2)) '(2 3 4))
  
    
