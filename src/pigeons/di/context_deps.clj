(ns pigeons.di.context-deps
  (:require [com.stuartsierra.component :as component]))

(defrecord ContextDeps
  []
  component/Lifecycle
  (start [this]
    (println this)
    this)
  (stop [this] this))

(defn provides
  []
  (map->ContextDeps {}))
