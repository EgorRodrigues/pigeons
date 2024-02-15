(ns pigeons.di.mongo
  (:require
   [com.stuartsierra.component :as component]
   [monger.core :as mg]
   [config.core :refer [env]]))

(defrecord Database [db-uri]
  component/Lifecycle

  (start [component]
    (let [{:keys [conn db]} (mg/connect-via-uri db-uri)]
      (assoc component :database db
                       ::connection conn)))

  (stop [component]
    (-> component ::connection mg/disconnect)
    component))

(defn provides []
  (map->Database {:db-uri (:db-uri env)}))
