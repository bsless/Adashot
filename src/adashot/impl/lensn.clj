(ns adashot.impl.lensn
  (:require
   [adashot.impl.protocols :as p]
   [adashot.impl.util :as u]
   [clj-fast.inline :as inline]))

(defmacro deflensn
  [n]
  (let [args (mapv (fn [n] (symbol (str "k" n))) (range n))
        overs
        (map (fn [[_this _m _f & _args :as sig]]
               `(over ~sig (inline/update-in ~_m ~args ~_f ~@_args)))
             (reductions conj '[this rec f] (take 6 u/+syms+)))]
    `(deftype ~(symbol (str 'Lens n))
         ~args
       p/ILens
       (view [~'this ~'rec] (inline/get-in ~'rec ~args))
       (put [~'this ~'rec ~'v] (inline/assoc-in ~'rec ~args ~'v))
       ~@overs)))

(do
  (deflensn 1)
  (deflensn 2)
  (deflensn 3)
  (deflensn 4)
  (deflensn 5)
  (deflensn 6)
  (deflensn 7)
  (deflensn 8)
  (deflensn 9))

(defmacro emit-lens-n-dispatch
  [ks]
  (let [m (gensym "m__")
        cases
        (mapcat (fn [n]
                  (let [args (take n u/+syms+)]
                    `(~n
                      (let [[~@args] ~ks]
                        (~(symbol (str '->Lens n)) ~@args)))))
                (range 1 10))]
    `(let [n# (count ~ks)]
       (case n#
         ~@cases))))

(defn lens-n
  [ks]
  (emit-lens-n-dispatch ks))

(comment
  (def ks [:a :b])
  (def l (lens-n [:a :b]))
  (def m {:a {:b 1}})
  (p/view l m)
  (p/put l m 2)
  (p/over l m + 2 3)

  (require '[criterium.core :as cc])

  (cc/quick-bench (p/view l m))

  (cc/quick-bench (get-in m ks))

  )
