(ns leiningen.new.cycle
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "cycle"))

(defn cycle
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' cycle project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
