(memory 1 10) ;; min max memory (number of 64K pages)
(type $clos_t (func (result i32)))     ;; function type for closures
(global $HEAP (mut i32) (i32.const 0)) ;; heap pointer initialized to 0
(global $ENV  (mut i32) (i32.const 0)) ;; env pointer initialized to NIL
(global $ACC  (mut i32) (i32.const 999)) ;; accumulator initialized to 999
(global $LIST i32 (i32.const 1))       ;; LIST tag (for non empty lists)
(global $NIL  i32 (i32.const 0))       ;; NIL tag (for empty lists)

;; pair(first, second)
(func $pair (param $first i32) (param $second i32) (result i32)
  (local $result i32)
  (local.set $result (global.get $HEAP))
  (i32.store (global.get $HEAP) (local.get $first))
  (global.set $HEAP (i32.add (global.get $HEAP) (i32.const 4)))
  (i32.store (global.get $HEAP) (local.get $second))
  (global.set $HEAP (i32.add (global.get $HEAP) (i32.const 4)))
  (local.get $result)
  return)

;; cons(head, tail)
(func $cons (param $head i32) (param $tail i32) (result i32)
  (local $result i32)
  (local.set $result (global.get $HEAP))
  (i32.store (global.get $HEAP) (global.get $LIST))
  (global.set $HEAP (i32.add (global.get $HEAP) (i32.const 4)))
  (i32.store (global.get $HEAP) (local.get $head))
  (global.set $HEAP (i32.add (global.get $HEAP) (i32.const 4)))
  (i32.store (global.get $HEAP) (local.get $tail))
  (global.set $HEAP (i32.add (global.get $HEAP) (i32.const 4)))
  (local.get $result)
  return)

;; head(list)
(func $head (param $list i32) (result i32)
  (i32.load (i32.add (local.get $list) (i32.const 4)))
  return)

;; tail(list)
(func $tail (param $list i32) (result i32)
  (i32.load (i32.add (local.get $list) (i32.const 8)))
  return)

;; search(n, list)
(func $search (param $n i32) (param $list i32) (result i32)
  (local.get $n)
  (if (result i32)
    (then
      (i32.sub (local.get $n) (i32.const 1))
      (local.get $list)
      (call $tail)
      (call $search))
    (else
      (local.get $list)
      (call $head)))
  return)

;; closure
(func $apply (param $W i32)(param $C i32)(result i32)
  (local $e i32) ;; the environment e stored in the closure
  (local.get $W) ;; element 0 of the environment
  (local.get $C) ;; element 1 of the environment
;; retrieve the environment in the closure (2nd element of a pair)
  (local.set $e (i32.load (i32.add (local.get $C)(i32.const 4))))
;; extend the environment e to <W, <C, e>>
  (local.get $e)
  (call $cons)
  (call $cons)
  (global.set $ENV)
;; retrieve index of closure body and execute the body
(call_indirect (type $clos_t) (i32.load (local.get $C)))
)
