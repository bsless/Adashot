# Adashot

A lenses library in Clojure.

## Why

Sometimes you find yourself doing plenty of `get-in`s, `update-in`s or
`assoc-in`s, and you happen to care about performance.

If you know the keys in advance everything is good and you can use
[clj-fast](https://github.com/bsless/clj-fast). But what happens if you
don't, but the keys which you'll work with rarely change?

If you knew the keys, that's usually where you'll create the `fn` object
in advance and cache it. But figuring out how many keys you need to work
with isn't so expensive, and the added benefit of faster calculations
offsets the one time cost of analyzing the keys.

This is where lenses come in. Lenses are objects which satisfy a simple interface:

| lens interface | Clojure equivalent |
| :-:            | :-:                |
| view           | get-in             |
| put            | assoc-in           |
| over           | update-in          |

Since the library is built on top of clj-fast, these perform well enough
to justify caching over walking over keys dynamically.

## Usage

### Require Adashot

```clojure
(require '[adashot.core :as a])
```
or
```clojure
(ns my-ns
  (:require [adashot.core :as a]))
```

### Create a lens

```clojure
(def ks [:a :b])
(def lens (a/keys->fast-lens ks))
```

### Use a lens

```clojure
(def m {:a {:b 1}})
(a/view lens m)
(a/put lens m 2)
(a/over lens m + 2 3)
```

## License

Copyright © 2020 Ben Sless

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
