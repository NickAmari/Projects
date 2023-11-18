#|
Author: Nicholas Amari
Date: Feb 23, 2021
DEscription: Creating the Sieve of Eratosthenes
|#
(define make-2-n-list (lambda (inList)
                        (if (= inList 2) ; base case
                            (list 2) ; base case return value
                            (append (make-2-n-list(- inList 1)) (list inList)) ; appending next value to current list so it shows up in decending order
                        )
                      )
)


(define divisible-by? (lambda (num1 num2)
                        (cond ((eq? (/ num1 num2) 1) #f) ; testing condition if its the same number
                              ((eq? (modulo num1 num2) 0) #t) ; if its divisible
                              (else #f)
                        )
                      )
)


(define not-divisible-by (lambda (num1)
                           (lambda (num2)
                             (cond ((eq? (divisible-by? num2 num1) #t) #f); flipping the output so that its testing if the numbers are not divisible by
                                   (else #t)  
                              )
                           )
                         )
)

(define filter-list (lambda (func inList)
                      (cond ((null? inList) '())
                            ((func (car inList));if current number in list returns #t from function, make a pair with it and the next entry
                             (cons (car inList) (filter-list func (cdr inList)))
                             )
                            (else (filter-list func (cdr inList))); if current number in list returns #f just move on to the next entry
                      )
                    )
)


(define prime-sieve (lambda (max) ; always false if statement
                      (if (< max (sqrt max))
                          '()
                          (sieve-helper (make-2-n-list max) 2 max) ; calling the helper with recursion and creating the list going from 2 to the max number
                      )
                    )
)

(define sieve-helper (lambda (inList num max)
                       (if (> num (sqrt max)) ; while the number is less than the sqrt of the max it will continue to filter the list and increase num by 1 every time
                           inList
                           (sieve-helper (filter-list (not-divisible-by num) inList) (+ num 1) max) ; list should eventually become filtered completely with only prime numbers and return
                       )
                     )
)