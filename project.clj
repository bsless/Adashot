(defproject adashot "0.0.0-alpha0"
  :description "Lenses library in Clojure with reasonable performance"
  :url "https://github.com/bsless/adashot"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [bsless/clj-fast "0.0.7"]]
  :profiles
  {:dev
   [:bench :prof]
   :bench {:dependencies [[criterium "0.4.5"]]}
   :prof {:dependencies [[com.clojure-goes-fast/clj-async-profiler "0.4.0"]]}}
  :repl-options {:init-ns adashot.core})
