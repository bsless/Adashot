(ns adashot.impl.lens
  (:require
   [adashot.impl.protocols :as p]
   [adashot.impl.util :as u]))

(deftype Lens [getter setter modifier]
  p/ILens
  (view [this m] (getter m))
  (put [this m v] (setter m v))
  (over [this m f a] (modifier m f a))
  (over [this m f a b] (modifier m f a b))
  (over [this m f a b c] (modifier m f a b c))
  (over [this m f a b c d] (modifier m f a b c d))
  (over [this m f a b c d e] (modifier m f a b c d e))
  (over [this m f a b c d e f] (modifier m f a b c d e f)))

(defn -lens
  [getter setter modifier]
  (->Lens getter setter modifier))

(defn lens
  [ks]
  (->Lens (u/-get-in ks) (u/-assoc-in ks) (u/-update-in ks)))

(comment
  (def ks [:a :b])
  (def l (lens [:a :b]))
  (def m {:a {:b 1}})
  (p/view l m)
  (p/put l m 2)
  (p/over l m + 2 3)

  (require '[criterium.core :as cc])
  (cc/quick-bench (p/view l m))
  (cc/quick-bench (get-in m ks)))
