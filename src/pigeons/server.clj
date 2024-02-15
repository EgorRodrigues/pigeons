(ns pigeons.server
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [pigeons.di.component :as di-component]
            [config.core :refer [env]]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "Creating server...")
  (component/start (di-component/start-server :dev (Integer/parseInt (:port env))))
  #_(s/set-fn-validation! true)
  (println  "Server started, have fun! ;)")
  @(promise))
