(ns pigeons.server
  (:gen-class) ; for -main method in uberjar
  (:require [com.stuartsierra.component :as component]
            [pigeons.components :as components]))

(defn -main
  [& args]
  (component/start (components/system :port 3000)))
