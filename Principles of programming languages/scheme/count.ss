(define s (list 1 2 3 4))

(define (count list)
  (cond ((null? list) 0) 
	(else (+ 1 (count (cdr list))))
   )
)
