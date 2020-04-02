(ns adashot.core
  (:require
   [adashot.impl.util :as u]
   [adashot.impl.protocols :as p]
   [adashot.impl.lens :as l]
   [adashot.impl.lensn :as l+]))

(defn lens
  "Returns a Lens object satisfying ILens.
  Getter setter and modifier must be functions."
  [getter setter modifier]
  (l/-lens getter setter modifier))

(defn keys->lens
  "Returns a Lens object satisfying ILens, with inlined get/assoc/update-in functions."
  [ks]
  (l/lens ks))

(defn keys->fast-lens
  "Returns a Lens object satisfying ILens, with inlined get/assoc/update-in functions.
  Supports up to 9 keys' depth but slightly faster."
  [ks]
  (l+/lens-n ks))

(def view p/view)
(reset-meta! #'view (meta #'p/view))

(def put p/put)
(reset-meta! #'put (meta #'p/put))

(def over p/over)
(reset-meta! #'over (meta #'p/over))
