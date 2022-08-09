#lang scheme

;;(Nombre riemann)
;;La función dentro de la sumatoria
;;Usé un exact->inexact para que
;;quedara flotante en vez de fraccion
;;Entrega la funciín de Riemann
(define (riemann i s)
  ( exact->inexact (/ 1 (expt i s)))
)

;;La sumatoria como tal, seguirá sumando hasta
;;que n llegue a la i-esima iteración. Para eso
;;la pasa como parámetro
(define (zeta_aux i s n)
  (if (positive? (- i n))
      ( + (riemann n s) (zeta_aux i s (+ n 1)))
      0
      )
  )
(define (zeta_simple i s)
  (zeta_aux (+ i 1) s 1)
  )

;;La sumatoria como tal, seguirá sumando hasta
;;que llega a la i-esima iteración
(define (zeta_cola i s)
  (if (positive? i)
      ( + (riemann i s) (zeta_cola (- i 1) s))
      0
      )
  )

;;Casos de prueba, se ejecutan solitos
(zeta_cola 3 2)
(zeta_simple 3 2)  
    
