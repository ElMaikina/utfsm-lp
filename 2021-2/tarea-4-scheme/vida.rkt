#lang scheme

;;(Nombre rec)
;;Recibe el elemento objetivo "h", la lista vacía "lst"
;;y el arbol "arbol". Iŕa sumando recorriendo y agregando
;;cada elemento hasta hallar la hoja buscada
;;Devuelve el recorrido hasta la hoja h
(define (rec h lst arbol)
  (if (empty? arbol)
      ;;Si el arbol es vacío retorna vacío
      ;;también ocurre si el elemento no existe
      '()
      (if (< h (car arbol))
          ;;Recorre la rama izquierda
          (rec h (cons (car arbol) lst) (cadr arbol))
          
          ;;Recorre la rama derecha
          (if(> h (car arbol))
                (rec h (cons (car arbol) lst) (caddr arbol))
                
                ;;Si "h" no es menor ni mayor a "car arbol"
                ;;debe ser igual sí o sí, entonces hemos
                ;;llegado al final del recorrido
                lst
                )
             )
          )
  )

;;Crea una lista vacía donde se almacenarán
;;los valores ancestros del elemento, luego
;;recorre el arbol hasta encontrarlo.
(define (vida h arbol)
  (if (empty? arbol)
      '()
      (reverse (rec h '() arbol))
      )
  )

;;Caso de prueba, se ejecuta solito
(vida 4 (quote (5 (3 (2 () ()) (4 () ())) (8 (6 () ()) ()))))
