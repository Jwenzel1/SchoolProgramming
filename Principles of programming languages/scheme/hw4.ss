;;; CMSC 331 HW4, Fall 2014.  Joseph Wenzel, jwenzel1@umbc.edu
(define (pass)
  ;; this is a a dummy function.  You should replace calls to it with
  ;; your own scheme code
  null)

(define s '(1 8 (0 8 6) (7 5)))
(define a '(0 6 (8 7 4) (2 5 6)))

(define (countZeros aList)
  ;;  returns the number of zeros in a given simple list of
  ;;  numbers. The input will be a (possibly empty) list of
  ;;  integers. For example, (countZeros '(0 1 2)) should return the
  ;;  value 1.
  (cond ((null? aList) 0)
	((equal? (car aList) 0) (+ 1 (countZeros (cdr aList))))
	(#t (+ 0 (countZeros (cdr aList))))))

;HELPER FUNCTION FOR FINDMINMAX
(define max -1)
(define (findMax list)
  (cond
  ((> (car list) max) (set! max (car list))))
  (if (not (null? (cdr list)))
      (findMax (cdr list)) max))

;HELPER FUNCTION FOR FINDMINMAX
(define min 1000000000)
(define (findMin list)
  (cond
  ((< (car list) min) (set! min (car list))))
  (if (not (null? (cdr list)))
      (findMin (cdr list)) min))

(define (findMinMax aList)
  ;; returns a list consisting of the smallest and largest values in
  ;; the list. For example, (findMinMax '(0 1 2)) should return the
  ;; list (0, 2).
  (set! min 1000000000)
  (set! max -1)
  (cond
   ((equal? aList null) null)
   ((list (findMin aList) (findMax aList)))))

(define (chopList aList) 
  ;; takes a list and removes the last element of the list. For
  ;; example, (chopList '(0 1 2)) should return the list (0 1). Make
  ;; sure that (chopList '()) returns the empty list
  (cond
   ((equal? aList '()) '())
   ((null? (cdr aList)) null)
   ((cons (car aList) (chopList (cdr aList))))))

(define unravelList '())

(define (unravel aList)
  (set! unravelList '())
  (unravelBootstrap aList)
)

(define (unravelBootstrap aList)
  ;; takes a list with possible many nested sublists, and returns a
  ;; list with all atoms at the top level. For example, 
  ;; (unravel '(1 (2 3) (a (b c)))) should return the list (1 2 3 a b c)
  (cond
   ((and (not (list? (car aList))) (not (null? (car aList)))) (set! unravelList (append unravelList (list (car aList)))))
   ((and (list? (car aList)) (not (null? (car aList)))) (unravelBootstrap (car aList)))
  )
  (cond
   ((not (null? (cdr aList))) (unravelBootstrap (cdr aList)))
  )
  unravelList
)

;HELPER FUNCTION FOR eqStruc
(define (count list)
  (cond ((null? list) 0)
        (else (+ 1 (count (cdr list))))))

(define (eqStruc l1 l2)
  ;; tests for the structural equality of two input lists. Two lists
  ;; are structurally equal if they have the same list structure,
  ;; although their atoms may be different. For example, the lists '(1
  ;; 2 3 ) and '(4 5 6) are structurally equal. The lists '(a (b c) d)
  ;; and '(a b (c d)) are not structurally equal.
  
  (cond
   ((and (equal? l1 '()) (equal? l2 '())) #t)
   ((and (not (null? l1)) (null? l2)) #f)
   ((and (null? l1) (not (null? l2))) #f)
   ((and (list? (car l1)) (not (list? (car l2)))) #f)
   ((and (not (list? (car l1))) (list? (car l2))) #f)
   ((and (not (list? (car l1))) (not (list? (car l2)))) (eqStruc (cdr l1) (cdr l2)))
   ((and (list? (car l1)) (list? (car l2))) (if (eqStruc (car l1) (car l2)) (eqStruc (cdr l1) (cdr l2)) #f))
  )
)