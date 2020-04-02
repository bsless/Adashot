(ns adashot.impl.util
  (:require
   [clj-fast.inline :as inline]))

(def +syms+ (map (comp symbol str) "abcdefghijklmnopqrstuvwxyz"))

(defmacro emit-get-in
  [ks]
  (let [m (gensym "m__")
        cases
        (mapcat (fn [n]
                  (let [args (take n +syms+)]
                    `(~n
                      (let [[~@args] ~ks]
                        (fn [~m] (inline/get-in ~m [~@args]))))))
                (range 22))]
    `(let [n# (count ~ks)]
       (case n#
         ~@cases))))

(defmacro emit-update-in
  "over"
  [ks]
  (let [m (gensym "m__")
        f (gensym "f__")
        f* (gensym "f*__")
        args* (gensym "args__")
        cases
        (mapcat (fn [n]
                  (let [args (take n +syms+)]
                    `(~n
                      (let [[~@args] ~ks]
                        (fn [~m ~f & ~args*]
                          (let [ ~f* (partial apply ~f)]
                            (inline/update-in ~m [~@args] ~f* ~args*)))))))
                (range 1 22))]
    `(let [n# (count ~ks)]
       (case n#
         ~@cases))))

(defmacro emit-assoc-in
  [ks]
  (let [m (gensym "m__")
        v (gensym "v__")
        cases
        (mapcat (fn [n]
                  (let [args (take n +syms+)]
                    `(~n
                      (let [[~@args] ~ks]
                        (fn [~m ~v] (inline/assoc-in ~m [~@args] ~v))))))
                (range 1 22))]
    `(let [n# (count ~ks)]
       (case n#
         ~@cases))))

(defn -get-in
  [ks]
  (emit-get-in ks))

(defn -update-in
  [ks]
  (emit-update-in ks))

(defn -assoc-in
  [ks]
  (emit-assoc-in ks))
