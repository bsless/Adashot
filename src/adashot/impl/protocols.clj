(ns adashot.impl.protocols)

(defprotocol ILens
  (view [this rec] "Get")
  (put [this rec v])
  (over
    [this m f]
    [this m f a]
    [this m f a b]
    [this m f a b c]
    [this m f a b c d]
    [this m f a b c d e]
    [this m f a b c d e f]
    "Modify"))
