(ns pigeons.server
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [pigeons.di.component :as di-component]
            [config.core :refer [env]]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (component/start (di-component/start-server (:prod env) (Integer/parseInt (:port env))))
  #_(s/set-fn-validation! true)
  @(promise))
