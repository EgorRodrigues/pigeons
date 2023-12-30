(ns pigeons.server
  (:gen-class)
  (:require
   [com.stuartsierra.component :as component]
   [config.core :refer [env]]
   [pigeons.di.component :as di-component]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (component/start (di-component/start-server (:env env) (:port env)))
  #_(s/set-fn-validation! true)
  @(promise))
