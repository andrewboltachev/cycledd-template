(ns {{name}}.core
    (:require [clojure.core.async :refer [<! >! timeout chan alt! alts! go go-loop put!
                                          sliding-buffer]]
               ({{name}} [utils :refer [watch-file-for-changes-debounced]]
                      [stuff :as stuff]
                      )
              [puget.printer :refer [cprint]]
              )
  )


(def ch1 (chan))

(defn -main
  [& _]
  (go
  (loop []
    (let [data (<! ch1)]
      ;(println "File change")
      (try
        (require '[{{name}}.stuff :as stuff] :reload)
        (put! stuff/stuff-ch data)
        (catch Exception e
          (println "Error:")
          (cprint e)))
      )
    (recur)
    )
    )
  (watch-file-for-changes-debounced "src/{{name}}/stuff.clj" ch1)

  (println "Hello, World!"))
