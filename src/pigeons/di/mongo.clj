(ns pigeons.di.mongo
  (:require
   [com.stuartsierra.component :as component]
   [config.core :refer [env]]
   [monger.core :as monger]))

(defrecord Database [db-uri]
  component/Lifecycle

  (start [component]
    (let [{:keys [conn db]} (monger/connect-via-uri db-uri)]
      (assoc component :database db
                       ::connection conn)))

  (stop [component]
    (-> component ::connection monger/disconnect)
    component))

(defn provides []
  (map->Database {:db-uri (:db-uri env)}))
